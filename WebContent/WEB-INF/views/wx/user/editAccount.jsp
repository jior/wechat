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
    String theme = com.glaf.core.util.RequestUtils.getTheme(request);
    request.setAttribute("theme", theme);
	List  list = (List)request.getAttribute("depts");
	String serviceUrl =  com.glaf.wechat.util.WechatUtils.getServiceUrl(request);
 
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/bootstrap2/css/bootstrap.min.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/bootstrap2/css/bootstrap-responsive.min.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/style.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/bootstrap2/css/todc_bootstrap.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/webside.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/themes.css" media="all">
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.form.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/chosen.jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.validate.methods.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/glaf-site.js"></script>
<script type="text/javascript">
    function saveData(){
		var params = jQuery("#iForm").formSerialize();
		jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/mx/wx/wxUser/saveWxUser?id=${wxUser.id}',
				   data: params,
				   dataType:  'json',
				   error: function(data){
					   alert('服务器处理错误！');
				   },
				   success: function(data){
					   if(data != null && data.message != null){
						 alert(data.message);
					   } else {
						 alert('操作成功完成！');
					   }
					   location.href='<%=request.getContextPath()%>/mx/wx/wxUser/account';
				   }
			 });
	}
</script>
<title>添加公众帐号</title>
</head>
<body>
	<div id="main">
        <div class="container-fluid">

            <div class="row-fluid">
                <div class="span12">
                    <div class="box">
                        <div class="box-title">
                            <div class="span10">
                                <h3><i class="icon-edit"></i>添加公众帐号</h3>
                            </div>
                            <div class="span2"><a class="btn" onclick="javascript:window.history.go(-1);">返回</a></div>
                        </div>

                        <div class="box-content">
                             
                            <form id="iForm" name="iForm" 
							      action="<%=request.getContextPath()%>/mx/wx/wxUser/save?id=${wxUser.id}" 
							      method="POST" class="form-horizontal form-validate" novalidate="novalidate">
                                <div class="control-group">
                                    <label for="plc_name" class="control-label">名称：</label>
                                    <div class="controls">
                                        <input type="text" name="name" id="name" class="input-medium valid" data-rule-required="true" value="${wxUser.name}"><span class="maroon">*</span>
                                    <span for="plc_name" class="help-block error valid"></span></div>
                                </div>
                                <!-- <div class="control-group">
                                    <label for="wxid" class="control-label">微信公众号原始id：</label>
                                    <div class="controls">
                                        <input type="text" name="wxSourceId" id="wxSourceId" class="input-medium" data-rule-required="true" value="${wxUser.wxSourceId}" ><span class="maroon">*</span><span class="help-inline">  
                                    </span></div>
                                </div>
                                <div class="control-group">
                                    <label for="wechat_id" class="control-label">微信号：</label>
                                    <div class="controls">
                                        <input type="text" name="wxid" id="wxid" class="input-medium" data-rule-required="true" value="${wxUser.wxid}"><span class="maroon">*</span>
                                    </div>
                                </div> -->
								<div class="control-group">
                                    <label for="wechat_id" class="control-label">微信应用编号AppId：</label>
                                    <div class="controls">
                                        <input type="text" name="wxAppId" id="wxAppId" class="input-medium" data-rule-required="false" value="${wxUser.wxAppId}">
                                    </div>
                                </div>
								<div class="control-group">
                                    <label for="wechat_id" class="control-label">微信应用密码AppSecret：</label>
                                    <div class="controls">
                                        <input type="text" name="wxAppSecret" id="wxAppSecret" class="input-medium" data-rule-required="false" value="${wxUser.wxAppSecret}">
                                    </div>
                                </div>

								<!-- <div class="control-group">
                                    <label for="wxid" class="control-label">易信公众号原始id：</label>
                                    <div class="controls">
                                        <input type="text" name="yxSourceId" id="yxSourceId" class="input-medium" data-rule-required="false" value="${wxUser.yxSourceId}" ><span class="help-inline">  
                                    </span></div>
                                </div>
                                <div class="control-group">
                                    <label for="wechat_id" class="control-label">易信号：</label>
                                    <div class="controls">
                                        <input type="text" name="yxid" id="yxid" class="input-medium" data-rule-required="false" value="${wxUser.yxid}">
                                    </div>
                                </div> -->

								<div class="control-group">
                                    <label for="wechat_id" class="control-label">易信应用编号AppId：</label>
                                    <div class="controls">
                                        <input type="text" name="yxAppId" id="yxAppId" class="input-medium" data-rule-required="false" value="${wxUser.yxAppId}">
                                    </div>
                                </div>
								<div class="control-group">
                                    <label for="wechat_id" class="control-label">易信应用密码AppSecret：</label>
                                    <div class="controls">
                                        <input type="text" name="yxAppSecret" id="yxAppSecret" class="input-medium" data-rule-required="false" value="${wxUser.yxAppSecret}">
                                    </div>
                                </div>

                                <c:if test="${!empty wxUser}">
								<div class="control-group">
                                    <label for="wechat_url" class="control-label">接口地址：</label>
                                    <div class="controls">
                                        <%=serviceUrl+"/weixin/"%>${wxUser.id}
                                    </div>
                                </div>
								<div class="control-group">
                                    <label for="wechat_id" class="control-label">TOKEN：</label>
                                    <div class="controls">
                                        ${wxUser.token}
                                    </div>
                                </div>
                                </c:if>

								<div class="control-group">
                                    <label for="wechat_id" class="control-label">LBS信息距离：</label>
                                    <div class="controls">
                                        <input type="text" name="lbsPosition" id="lbsPosition" class="input-medium" data-rule-required="false" value="${wxUser.lbsPosition}">（米）
                                    </div>
                                </div>
                                  
                                                  
                                <div class="control-group">
                                    <label for="type" class="control-label">类型：</label>
                                    <div class="controls">
                                        <select id="type" name="type" class="input-medium" data-nosearch="true" 
										        onChange="javascript:setValue(this);">
                                            <%
											if(list!=null){
											  Iterator iter=list.iterator();   
											  while(iter.hasNext()){
												SysDepartment bean2=(SysDepartment)iter.next();
											%>
												<option value="<%=bean2.getId()%>">
											<%
												out.print(bean2.getName());
											%>
											   </option>
											<%    
											  }
											}
											%>
										</select>
                                    </div>
                                </div>
                                
                                <div class="form-actions">
									<input type="hidden" name="aid" id="aid" value="19345">
                                    <button type="button" class="btn btn-primary" onclick="javascript:saveData();">保存</button>
                                    <a class="btn" href="javascript:window.history.go(-1)">取消</a>

                                </div>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

	<script type="text/javascript">
	$(function(){
		$('#plc_name').focus();
	});
</script>

<div id="fallr-overlay"></div></body></html>