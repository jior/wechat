<%--
Licensed to the Apache Software Foundation (ASF) under one or more
contributor license agreements.  See the NOTICE file distributed with
this work for additional information regarding copyright ownership.
The ASF licenses this file to You under the Apache License, Version 2.0
(the "License"); you may not use this file except in compliance with
the License.  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*"%>
<%@ page import="com.alibaba.fastjson.*"%>
<%@ page import="com.glaf.wechat.domain.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    String theme = com.glaf.core.util.RequestUtils.getTheme(request);
    request.setAttribute("theme", theme);
    JSONObject treeJson = (JSONObject)request.getAttribute("treeJson");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
<base href="." target="mainFrame">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/index.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/bootstrap2/css/bootstrap.min.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/bootstrap2/css/bootstrap-responsive.min.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/style.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/themes.css" media="all">
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/application.js"></script>
<style type="text/css">.dropdown-submenu:hover>.dropdown-menu{display:none}</style>
<title>GLAF微信开发平台</title>
<script type="text/javascript">
  function relogin(){
        if(confirm("您确定要重新登录吗？")){
			var link = '${contextPath}/mx/login/logout';
			self.location.href = link;
		}
	}
</script>
</head>
<body>
   <div id="navigation">
        <div class="container-fluid">
            <div>
                <a href="<%=request.getContextPath()%>/mx/wechat/index" target="_self" id="brand"></a>
                <a href="<%=request.getContextPath()%>/mx/wechat/index" target="_self" class="toggle-nav" rel="tooltip" data-placement="bottom" title="收缩左栏">
				<i class="icon-sp"><img src="<%=request.getContextPath()%>/images/FIX_legend_box.png"></i></a>
            </div>

            <ul class="main-nav">
                <li class="active">
                    <a href="<%=request.getContextPath()%>/mx/wechat/main" target="_self">
                        <span>管理平台</span>
                    </a>
                </li>
				<li><a href="<%=request.getContextPath()%>/mx/wechat/main" target="_self">公众帐号管理</a></li>
            </ul>

            <div class="user">
                <ul class="icon-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" title="消息" style="display:none;"><i class="icon-envelope"></i><span class="label label-lightred">0</span></a>
                    </li>
                    <li class="dropdown sett" style="display:none;">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" title="系统设置"><i class="icon-cog"></i></a>
                    </li>
                    <li class="dropdown colo">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" title="选择颜色"><i class="icon-tint"></i></a>
                        <ul class="dropdown-menu pull-right theme-colors">
                            <li class="subtitle">选择样式</li>
                            <li>
                                <span class="red"></span>
                                <span class="orange"></span>
                                <span class="green"></span>
                                <span class="brown"></span>
                                <span class="blue"></span>
                                <span class="lime"></span>
                                <span class="teal"></span>
                                <span class="purple"></span>
                                <span class="pink"></span>
                                <span class="magenta"></span>
                                <span class="grey"></span>
                                <span class="darkblue"></span>
                                <span class="lightred"></span>
                                <span class="lightgrey"></span>
                                <span class="satblue"></span>
                                <span class="satgreen"></span>
                            </li>
                        </ul>
                    </li>
                </ul>
                <div class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="width:87px;height:27px;">
					<nobr><span class="caret">${wxUser.name}</span></nobr></a>
                    <ul class="dropdown-menu pull-right">
                        <li>
                            <a href="<%=request.getContextPath()%>/mx/wechat/main" target="_self">管理帐号</a>
                        </li>
                        <li>
                            <a href="javascript:relogin();" target="_self">退出</a>
                        </li>
                    </ul>
                </div>

            </div>
        </div>
    </div>

    <div class="container-fluid" id="content">
        <div id="left" style="overflow: hidden; outline: none;" tabindex="5000">
			<div class="subnav">
                <div class="subnav-title">
                    <a href="javascript:void(0);" class="toggle-subnav"><i class="icon-angle-right"></i><span>我的微站</span></a>
                </div>
                <ul class="subnav-menu" style="display: block">
                    <li class="active">
                        <a href="<%=request.getContextPath()%>/mx/wx/wxUser/accountInfo?accountId=${accountId}">账户信息</a>
                    </li>
                    <li>
                         
                    </li>
                </ul>
            </div>
			 <%
			      JSONArray children = treeJson.getJSONArray("children");
			      if (children != null && !children.isEmpty()) {
					  Iterator<Object> iterator = children.iterator();
				      while (iterator.hasNext()) {
					    Object obj = iterator.next();
					    if (obj instanceof JSONObject) {
							JSONObject jsonObject =(JSONObject)obj;
			   %>

            <div class="subnav">
                <div class="subnav-title">
                    <a href="javascript:void(0);" class="toggle-subnav">
					<i class="icon-angle-right"></i>
					<span><%=jsonObject.getString("text")%></span>
					</a>
                </div>
                <ul class="subnav-menu">
				   <%
				     if(jsonObject.containsKey("children")){
                        JSONArray children2 = jsonObject.getJSONArray("children");
			            if (children2 != null && !children2.isEmpty()) {
						    Iterator<Object> iterator2 = children2.iterator();
						    while (iterator2.hasNext()) {
							  Object obj2 = iterator2.next();
							  if (obj2 instanceof JSONObject) {
								  JSONObject json =(JSONObject)obj2;
								  if(!json.containsKey("url")){
									  continue;
								  }
				   %>
                    <li>
                        <a href="<%=request.getContextPath()%><%=json.getString("url")%>"><%=json.getString("text")%></a>
                    </li>
                     <%     }
						  }
						}
				      }
					%>
                </ul>
            </div>
            <%            }
		           }
			}
			%>
        </div>

        <div class="right">
          <div class="main">

            <iframe frameborder="0" id="mainFrame" name="mainFrame" 
			        src="<%=request.getContextPath()%>/mx/wx/wxUser/accountInfo?accountId=${accountId}" 
			        scrolling="auto" ></iframe>

          </div>
        </div>
    </div>
<script type="text/javascript">  P.skn();  </script>
<div id="ascrail2000" style="width: 7px; z-index: 9002; position: absolute; top: 40px; left: 193px; height: 664px; display: none;"><div style="position: relative; top: 0px; float: right; width: 5px; height: 0px; background-color: rgb(66, 66, 66); border: 1px solid rgb(255, 255, 255); background-clip: padding-box; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px;"></div></div><div id="ascrail2000-hr" style="height: 8px; z-index: 9002; top: 696px; left: 0px; position: absolute; display: none;"><div style="position: relative; top: 0px; height: 5px; width: 0px; background-color: rgb(66, 66, 66); border: 1px solid rgb(255, 255, 255); background-clip: padding-box; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px;"></div>
</div>
</body>
</html>