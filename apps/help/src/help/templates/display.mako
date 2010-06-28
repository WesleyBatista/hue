## Licensed to Cloudera, Inc. under one
## or more contributor license agreements.  See the NOTICE file
## distributed with this work for additional information
## regarding copyright ownership.  Cloudera, Inc. licenses this file
## to you under the Apache License, Version 2.0 (the
## "License"); you may not use this file except in compliance
## with the License.  You may obtain a copy of the License at
##
##     http://www.apache.org/licenses/LICENSE-2.0
##
## Unless required by applicable law or agreed to in writing, software
## distributed under the License is distributed on an "AS IS" BASIS,
## WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
## See the License for the specific language governing permissions and
## limitations under the License.
<html>
<head>
  <title>${title}</title>
</head>
<body>
  <div class="splitview resizable">
    <div class="left_col jframe_padded">
      <h2>Index</h2>
      <ul>
        % for app in apps:
          <li><a href="${url("help.views.view", app=app.name, path="/")}">${app.nice_name}</a></li>
        % endfor
      </ul>
    </div>
    <div class="right_col jframe_padded">
      <div id="contents">${content|n}</div>
    </div>
  </div>
</body>
</html>
