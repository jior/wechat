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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    String theme = com.glaf.core.util.RequestUtils.getTheme(request);
    request.setAttribute("theme", theme);
 
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/index.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/bootstrap2/css/bootstrap.min.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/bootstrap2/css/bootstrap-responsive.min.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/style.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/bootstrap2/css/todc_bootstrap.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/themes.css" media="all">
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/application.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap2/js/bootstrap.min.js"></script>
<style type="text/css">.dropdown-submenu:hover>.dropdown-menu{display:none}</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/glaf-side.js"></script>
<title>公众号列表</title>
</head>
<body>
	 <div id="main">
        <div class="container-fluid">

            <div class="row-fluid">
                <div class="span12">

                    <div class="box">
                        <div class="box-title">
                            <div class="span6">
                                <h3><i class="icon-table"></i>管理微信公众帐号</h3>
                            </div>

                        </div>

                        <div class="box-content nozypadding">
                            <div class="row-fluid">
                                <div class="span8 control-group">

                                    <a class="btn " href="javascript:location.href='<%=request.getContextPath()%>/mx/wx/wxUser/editAccount';"><i class="icon-plus"></i>添加公众帐号</a>
                                   
                                </div>


                            </div>

                            <div class="row-fluid dataTables_wrapper">
                                <!-- <div class="alert">
                                    <strong>温馨提示</strong>  
									<span class="line hide"></span>
                                </div> -->
                                <form method="post" action="" id="listForm">
                                    <table id="listTable" class="table table-hover table-nomargin table-bordered usertable dataTable">
                                        <thead>
                                            <tr>
                                                <th>公众号名称</th>
                                                <th>创建时间/到期时间</th>
                                                <th>已定义/上限</th>
                                                <th>请求数</th>
                                                <th>剩余请求数</th>
                                                <th>操作</th>
                                            </tr>

                                        </thead>
                                        <tbody>
                                           <c:forEach items="${users}" var="user">
										     <tr>
                                                <td style="text-align:center;">
                                                    <p>
                                                        <a href="javascript:void(0)" onclick="parent.location.href='<%=request.getContextPath()%>/wechat/index'" title="点击进入功能管理">
                                                        </a>
                                                    </p>
                                                    <p>${user.name}</p>
                                                </td>
                                                <td>
                                                    <p>创建时间:<fmt:formatDate value="${user.createDate}" pattern="yyyy-MM-dd" /></p>
                                                    <p>到期时间:<fmt:formatDate value="${user.endDate}" pattern="yyyy-MM-dd" /></p>
                                                </td>
                                                <td>
                                                    <p>文本：1000</p>
                                                    <p>图文：100</p>
                                                    <p>语音：10</p>
                                                </td>
                                                <td>
                                                    <p>总请求数:0</p>
                                                    <p>本月请求数:0</p>
                                                </td>
                                                <td>
                                                    <p>请求数剩余：2000</p>
                                                </td>                                             
                                                <td>
                                                    <a href="<%=request.getContextPath()%>/mx/wx/wxUser/editAccount?id=${user.id}" class="btn" rel="tooltip" title="编辑"><i class="icon-edit"></i></a>
                                                    <a href="#" onclick="parent.location.href='<%=request.getContextPath()%>/mx/wechat/index?accountId=${user.id}'" class="btn" rel="tooltip" title="管理"><i class="icon-cog"></i></a>
                                                </td>
                                            </tr>
                                           </c:forEach>
										  </tbody>

                                    </table>
									<div class="dataTables_paginate paging_full_numbers">
									<span>       
									</span>
									</div>                            
								</form>
							</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

<div id="fallr-overlay"></div>
</body>
</html>