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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    String theme = com.glaf.core.util.RequestUtils.getTheme(request);
    request.setAttribute("theme", theme);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>接口设置</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/easyui/themes/${theme}/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/themes/${theme}/styles.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/core.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/icons/styles.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.form.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript">
    var contextPath="<%=request.getContextPath()%>";

    KE.show({  id : 'defaultReply' });

	function saveData(){
		document.getElementById("defaultReply").value=KE.html('defaultReply');
		var params = jQuery("#iForm").formSerialize();
		jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/mx/wx/wxConfig/saveWxConfig',
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
				   }
			 });
	}

	function saveAsData(){
		document.getElementById("id").value="";
		document.getElementById("defaultReply").value=KE.html('defaultReply');
		var params = jQuery("#iForm").formSerialize();
		jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/mx/wx/wxConfig/saveWxConfig',
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
				   }
			 });
	}

</script>
</head>

<body>
<div style="margin:0;"></div>  

<div class="easyui-layout" data-options="fit:true">  
  <div data-options="region:'north',split:true,border:true" style="height:40px"> 
    <div class="toolbar-backgroud"> 
	<span class="x_content_title">接口设置</span>
	<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-save'" onclick="javascript:saveData();" >保存</a> 
    </div> 
  </div>

  <div data-options="region:'center',border:false,cache:true">
  <form id="iForm" name="iForm" method="post">
  <input type="hidden" id="id" name="id" value="${wxConfig.id}"/>
  <table class="easyui-form" style="width:80%;" align="center">
    <tbody>
	<tr>
		<td width="20%" align="left">微信接口URL</td>
		<td align="left">
            <input id="callBackUrl" name="callBackUrl" type="text" 
			       class="easyui-validatebox x-text"  size="50"
				   value="${wxConfig.callBackUrl}"
				   data-options="required:true"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">微信接口Token</td>
		<td align="left">
            <input id="token" name="token" type="text" 
			       class="easyui-validatebox x-text"   size="50"
				   value="${wxConfig.token}"
				   data-options="required:true"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">应用编号AppId</td>
		<td align="left">
            <input id="appId" name="appId" type="text" 
			       class="easyui-validatebox x-text"  size="50" 
				   value="${wxConfig.appId}"
				   data-options="required:true"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">应用密码AppSecret</td>
		<td align="left">
            <input id="appSecret" name="appSecret" type="text" 
			       class="easyui-validatebox x-text"  size="50" 
				   value="${wxConfig.appSecret}"
				   data-options="required:true"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">LBS信息距离</td>
		<td align="left">
            <input id="lbsPosition" name="lbsPosition" type="text" 
			       class="easyui-validatebox x-text"  size="5" 
				   value="${wxConfig.lbsPosition}"
				   data-options="required:true"/>（千米）
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">默认回复</td>
		<td align="left">
			<textarea  id="defaultReply" name="defaultReply" class="x-textarea"  rows="5" cols="38" style="width:515px;height:380px;">${wxConfig.defaultReply}</textarea> 
		</td>
	</tr>
	<tr>
	    <td width="20%" align="left"></td>
		<td align="left" ><br>
            <input type="button" value="保存配置" onclick="javascript:saveData();" class="btnGreen">
		</td>
	</tr>
	 
    </tbody>
  </table>
  </form>
 <p></p>
 <p></p>
</div>
</div>
</body>
</html>