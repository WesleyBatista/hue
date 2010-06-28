#!/usr/bin/env python
# Licensed to Cloudera, Inc. under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  Cloudera, Inc. licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

import logging
import threading

import django.contrib.auth.views

from django.core import urlresolvers
from django.contrib.auth import authenticate, login, get_backends
from django.http import HttpResponseRedirect
from desktop.auth.backend import AllowFirstUserDjangoBackend
from desktop.lib.django_util import render_json, render
from desktop.lib.django_util import login_notrequired
from desktop.log.access import access_warn, AccessInfo

LOG = logging.getLogger(__name__)

# The users who are currently logged in.
# This is a User -> AccessInfo map
_current_users = { }
_current_users_lk = threading.Lock()


@login_notrequired
def login_ajax(request):
  username = request.POST.get('username')
  user = authenticate(username=username,
                      password=request.POST.get('password'))
  if user:
    access_warn(request, '"%s" login ok' % (user.username,))
    login(request, user)
    _add_to_current_users(user, AccessInfo(request))

    return render_json(dict(success=True))
  else:
    access_warn(request, 'Failed login for user "%s"' % (username,))
    return render_json(dict(success=False))


@login_notrequired
def login_form(request):
  """Used by the ajax login"""
  backends = get_backends()
  first_login_ever = False
  for be in backends:
    if isinstance(be, AllowFirstUserDjangoBackend) and be.is_first_login_ever():
      first_login_ever = True

  return render("login.html", request,
                { 'first_login_ever': first_login_ever },
                force_template=True)


def get_current_users():
  """Return a copy of the User -> AccessInfo map"""
  return _current_users.copy()

def _add_to_current_users(user, access_info):
  _current_users_lk.acquire()
  try:
    _current_users[user] = access_info
  finally:
    _current_users_lk.release()


@login_notrequired
def dt_login(request):
  """Used by the non-jframe login"""
  redirect_to = request.REQUEST.get('next', '/')
  if request.method == 'POST':
    form = django.contrib.auth.forms.AuthenticationForm(data=request.POST)
    if form.is_valid():
      login(request, form.get_user())
      if request.session.test_cookie_worked():
        request.session.delete_test_cookie()
      access_warn(request, '"%s" login ok' % (request.user.username,))
      _add_to_current_users(request.user, AccessInfo(request))
      return HttpResponseRedirect(redirect_to)
    else:
      access_warn(request, 'Failed login for user "%s"' % (request.POST.get('username'),))
  else:
    form = django.contrib.auth.forms.AuthenticationForm()
  request.session.set_test_cookie()
  return render('login.mako', request, {
    'action': urlresolvers.reverse('desktop.auth.views.dt_login'),
    'form': form,
    'next': redirect_to,
  })


def dt_logout(request, next_page=None):
  """Wrapper that performs logout house-keeping"""
  _current_users_lk.acquire()
  try:
    try:
      del _current_users[request.user]
    except KeyError:
      LOG.warn('Logging out user "%s", who has no record of logging in' % (request.user.username,))
  finally:
    _current_users_lk.release()
  return django.contrib.auth.views.logout(request, next_page)


def profile(request):
  """
  Dumps JSON for user-profile information.
  """
  return render(None, request, _profile_dict(request.user))

def _profile_dict(user):
  return dict(
    username=user.username,
    first_name=user.first_name,
    last_name=user.last_name,
    last_login=str(user.last_login), # datetime object needs to be converted
    email=user.email)
