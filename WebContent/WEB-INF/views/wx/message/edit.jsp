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
<title>WxMessage</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/easyui/themes/${theme}/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/themes/${theme}/styles.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/icons/styles.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.form.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
        var contextPath="<%=request.getContextPath()%>";

	function saveData(){
		var params = jQuery("#iForm").formSerialize();
		jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/wx/wxMessage/saveWxMessage',
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
					   if (window.opener) {
						window.opener.location.reload();
					   } else if (window.parent) {
						window.parent.location.reload();
					   }
				   }
			 });
	}

	function saveAsData(){
		document.getElementById("id").value="";
		document.getElementById("id").value="";
		var params = jQuery("#iForm").formSerialize();
		jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/wx/wxMessage/saveWxMessage',
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
	<span class="x_content_title">编辑WxMessage</span>
	<!-- <input type="button" name="save" value=" 保存 " class="button btn btn-primary" onclick="javascript:saveData();">
	<input type="button" name="saveAs" value=" 另存 " class="button btn" onclick="javascript:saveAsData();">
	<input type="button" name="back" value=" 返回 " class="button btn" onclick="javascript:history.back();"> -->
	<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-save'" onclick="javascript:saveData();" >保存</a> 
	<!-- 
	<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-saveas'" onclick="javascript:saveAsData();" >另存</a> 
        -->
	<!--
	<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-back'" onclick="javascript:history.back();">返回</a>
	-->
    </div> 
  </div>

  <div data-options="region:'center',border:false,cache:true">
  <form id="iForm" name="iForm" method="post">
  <input type="hidden" id="id" name="id" value="${wxMessage.id}"/>
  <table class="easyui-form" style="width:600px;" align="center">
    <tbody>
	<tr>
		<td width="20%" align="left">Name</td>
		<td align="left">
            <input id="name" name="name" type="text" 
			       class="easyui-validatebox"  
			
				   value="${wxMessage.name}"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">Mobile</td>
		<td align="left">
            <input id="mobile" name="mobile" type="text" 
			       class="easyui-validatebox"  
			
				   value="${wxMessage.mobile}"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">Title</td>
		<td align="left">
            <input id="title" name="title" type="text" 
			       class="easyui-validatebox"  
			
				   value="${wxMessage.title}"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">Content</td>
		<td align="left">
            <input id="content" name="content" type="text" 
			       class="easyui-validatebox"  
			
				   value="${wxMessage.content}"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">Uuid</td>
		<td align="left">
            <input id="uuid" name="uuid" type="text" 
			       class="easyui-validatebox"  
			
				   value="${wxMessage.uuid}"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">CreateBy</td>
		<td align="left">
            <input id="createBy" name="createBy" type="text" 
			       class="easyui-validatebox"  
			
				   value="${wxMessage.createBy}"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">CreateDate</td>
		<td align="left">
			<input id="createDate" name="createDate" type="text" 
			       class="easyui-datebox"
			
				  value="<fmt:formatDate value="${wxMessage.createDate}" pattern="yyyy-MM-dd"/>"/>
		</td>
	</tr>
 
    </tbody>
  </table>
  </form>
</div>
</div>
</body>
</html>