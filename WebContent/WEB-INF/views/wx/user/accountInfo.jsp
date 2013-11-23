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
<%@ page import="com.glaf.wechat.domain.*"%>
<%@ page import="com.glaf.base.modules.sys.*"%>
<%@ page import="com.glaf.base.modules.sys.model.*"%>
<%@ page import="com.glaf.base.utils.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    String serviceUrl =  com.glaf.wechat.util.WechatUtils.getServiceUrl(request);
%>
<!DOCTYPE html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/index.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/bootstrap2/css/bootstrap.min.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/bootstrap2/css/bootstrap-responsive.min.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/style.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/bootstrap2/css/todc_bootstrap.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/themes.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/website.css" media="all">
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.form.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.validate.methods.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/glaf-side.js"></script>
<style type="text/css">.dropdown-submenu:hover>.dropdown-menu{display:none}</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/glaf-side.js"></script>
</head>
<body>
	<div id="main">
        <div class="row-fluid">
            <div class="span12">
                <div class="box ">
                    <div class="box-title">
                        <h3><i class="icon-user"></i>账户信息</h3>
                    </div>
                    <div class="box-content">

                        <dl class="dl-horizontal">
                            <dt>
                            </dt>
                            <dd>
                                <p><strong>${wxUser.name}</strong></p>

                                <table class="table noborder">
								     <tbody><tr>
                                        <td>文本自定义：13/100</td>
                                        <td>图文自定义：0/10</td>
                                        <td>语音自定义：0/0</td>
                                    </tr>
                                    <tr>
                                        <td>总请求数：0</td>
                                        <td>本月请求数：0</td>
                                        <td>每月可请求总数：2000</td>
                                    </tr>
                                </tbody></table>
								<p><strong>接口地址：<%=serviceUrl+"/weixin/"%>${wxUser.id}</strong></p>
								<p><strong>TOKEN：${wxUser.token}</strong></p>
                            </dd>
                        </dl>
                    </div>
                </div>
            </div>
        </div>
        <div class="row-fluid">
            <div class="box">

                <div class="box-title">
                    <h3>
                        <i class="icon-rocket"></i>
                        快捷操作
                    </h3>
                </div>
                <div class="box-content">
                  <div class="block block-tiles block-tiles-animated clearfix">
                        

                    </div>
                       <script type="text/javascript">
                          $(function () {
                             var $p = window.top.document;
                             var $left_a = $("#left a", $p);
                             var keyArray = new Array;
                             $left_a.each(function () {
                                 keyArray.push($(this).attr("href"))
                             })
                             $(" div.block-tiles a:not(.not)").click(function (e) {
                                 e.preventDefault();
                                 var $this = $(this);
                                 var $h = $(this).attr("href");
                                 var $eq = $.inArray($h, keyArray);
                                 if ($eq) {
                                     window.parent.lfet_select_menu($eq);
                                     if ($this.attr("rel")) {
                                         window.top.location = $h;
                                     } else {
                                         if ($h != "javascript:void(0)") {
                                             $("#mainFrame", $p).attr("src", $h);
                                         }

                                     }
                                 } else {
                                       
                                 }

                             });

                         });

                    </script>
                </div>
            </div>
        </div>
        </div>


<div id="fallr-overlay"></div>
</body>
</html>