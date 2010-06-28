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

"""
A tool to manage Hue applications. This does not stop/restart a
running Desktop instance.

Usage:
    %(PROG_NAME)s [flags] --install <path_to_app> [<path_to_app> ...]
        To register and install new application(s).

    %(PROG_NAME)s [flags] --remove <application_name>
        To unregister and remove an installed application.

    %(PROG_NAME)s [flags] --list
        To list all registered applications.

    %(PROG_NAME)s [flags] --sync
        Synchronize all registered applications with the Desktop environment.
        Useful after a `make clean'.

Optional flags:
    --debug             Turns on debugging output
"""


import getopt
import logging
import os
import sys
import subprocess

import build
import common
import pth
import registry

PROG_NAME = sys.argv[0]

LOG = logging.getLogger()
LOG_LEVEL = logging.INFO
LOG_FORMAT = "%(message)s"

DO_INSTALL = 'do_install'
DO_REMOVE = 'do_remove'
DO_LIST = 'do_list'
DO_SYNC = 'do_sync'


def usage(msg=None):
  """Print the usage with an optional message. And exit."""
  global __doc__
  if msg is not None:
    print >>sys.stderr, msg
  print >>sys.stderr, __doc__ % dict(PROG_NAME=PROG_NAME)
  sys.exit(1)


def get_app_info(app_loc):
  """
  get_app_info(app_loc) -> (app_name, version, description)

  Runs the app's setup.py to get the info. May raise ValueError and OSError.
  """
  if not os.path.isdir(app_loc):
    msg = "Not a directory: %s" % (app_loc,)
    LOG.error(msg)
    raise ValueError(msg)

  save_cwd = os.getcwd()
  os.chdir(app_loc)
  try:
    cmdv = [ common.ENV_PYTHON, 'setup.py', '--name', '--version', '--description' ]
    LOG.debug("Running '%s'" % (' '.join(cmdv),))
    popen = subprocess.Popen(cmdv, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    res = popen.wait()
    stdout, stderr = popen.communicate()
    # Cmd failure?
    if res != 0:
      LOG.error("Error getting application info from %s:\n%s" % (app_loc, stderr))
      raise OSError(stderr)
    LOG.debug("Command output:\n<<<\n%s\n>>>" % (stdout,))
    return stdout.split('\n')[:3]
  finally:
    os.chdir(save_cwd)


def _do_install_one(reg, app_loc):
  """Install one app, without saving. Returns True/False."""
  LOG.info("=== Installing app at %s" % (app_loc,))
  try:
    app_loc = os.path.realpath(app_loc)
    app_name, version, desc = get_app_info(app_loc)
  except (ValueError, OSError), ex:
    LOG.error(ex)
    return False

  app = registry.DesktopApp(app_name, version, app_loc, desc)
  if reg.contains(app):
    LOG.warn("=== %s is already installed" % (app,))
    return True
  return reg.register(app) and build.make_app(app)


def do_install(app_loc_list):
  """Install the apps. Returns True/False."""
  reg = registry.AppRegistry()
  for app_loc in app_loc_list:
    if not _do_install_one(reg, app_loc):
      return False
  reg.save()

  return do_sync(reg)


def do_list():
  """List all apps. Returns True/False."""
  reg = registry.AppRegistry()
  apps = reg.get_all_apps()
  LOG.info("%-20s %-7s %s" % ('Name', 'Version', 'Path'))
  LOG.info("%s %s %s" % ('-' * 20, '-' * 7, '-' * 50))
  for app in sorted(apps):
    LOG.info("%-20s %-7s %s" % (app.name, app.version, app.path))
  return True


def do_remove(app_name):
  """Uninstall the given app. Returns True/False."""
  # TODO(bc)  Does not detect dependency. The app to be uninstalled could be a
  #           pre-req for other apps, as defined in various setup.py files.
  LOG.info("=== Uninstalling %s" % (app_name,))
  reg = registry.AppRegistry()
  try:
    app = reg.unregister(app_name)
  except KeyError:
    LOG.error("%s is not installed" % (app_name,))
    return False

  reg.save()

  # Update the pth file
  try:
    pthfile = pth.PthFile()
    pthfile.remove(app)
    pthfile.save()
    return True
  except (OSError, SystemError), ex:
    LOG.error("Failed to update the .pth file. Please fix any problem and run "
              "`%s --sync'\n%s" % (PROG_NAME, ex))
    return False


def do_sync(reg=None):
  """Sync apps with virtualenv. Returns True/False."""
  if not reg:
    reg = registry.AppRegistry()

  apps = reg.get_all_apps()
  try:
    pthfile = pth.PthFile()
    pthfile.sync(apps)
    pthfile.save()

    build.make_syncdb()
    return True
  except (OSError, SystemError), ex:
    LOG.error("Failed to update the .pth file. Please fix any problem and run "
              "`%s --sync'\n%s" % (PROG_NAME, ex))
    return False


def main():
  action = None
  app = None

  # Option parsing
  try:
    opts, tail = getopt.getopt(sys.argv[1:],
                               'ir:lds',
                               ('install', 'remove=', 'list', 'debug', 'sync'))
  except getopt.GetoptError, ex:
    usage(str(ex))

  def verify_action(current, new_val):
    if current is not None:
      usage()
    return new_val

  for opt, arg in opts:
    if opt in ('-i', '--install'):
      action = verify_action(action, DO_INSTALL)
    elif opt in ('-r', '--remove'):
      action = verify_action(action, DO_REMOVE)
      app = arg
    elif opt in ('-l', '--list'):
      action = verify_action(action, DO_LIST)
    elif opt in ('-s', '--sync'):
      action = verify_action(action, DO_SYNC)
    elif opt in ('-d', '--debug'):
      global LOG_LEVEL
      LOG_LEVEL = logging.DEBUG

  if action == DO_INSTALL:
    app_loc_list = tail
  elif len(tail) != 0:
    usage("Unknown trailing arguments: %s" % ' '.join(tail))

  if action is None:
    usage()

  # Setup logging
  logging.basicConfig(level=LOG_LEVEL, format=LOG_FORMAT)

  # Dispatch
  if action == DO_INSTALL:
    ok = do_install(app_loc_list)
  elif action == DO_REMOVE:
    ok = do_remove(app)
  elif action == DO_LIST:
    ok = do_list()
  elif action == DO_SYNC:
    ok = do_sync()

  if ok:
    return 0
  return 2


if __name__ == '__main__':
  sys.exit(main())
