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
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑模板</title>
<%@ include file="/WEB-INF/views/wx/inc/wx_styles.jsp"%>
<%@ include file="/WEB-INF/views/wx/inc/wx_scripts.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript">
    var contextPath="<%=request.getContextPath()%>";

	KE.show({  id : 'content'
	           ,allowFileManager : true
	           ,imageUploadJson : '<%=request.getContextPath()%>/mx/wx/uploadJson'
			   ,fileManagerJson : '<%=request.getContextPath()%>/mx/wx/fileManagerJson' });

	function saveData(){
		var params = jQuery("#iForm").formSerialize();
		jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/mx/wx/wxTemplate/saveWxTemplate',
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
				   url: '<%=request.getContextPath()%>/mx/wx/wxTemplate/saveWxTemplate',
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
	<span class="x_content_title">编辑模板</span>
	<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-save'" onclick="javascript:saveData();" >保存</a> 
    </div> 
  </div>

  <div data-options="region:'center',border:false,cache:true">
  <form id="iForm" name="iForm" method="post">
  <input type="hidden" id="id" name="id" value="${wxTemplate.id}"/>
  <input type="hidden" id="accountId" name="accountId" value="${accountId}"/>
  <c:if test="${not empty categoryId}">
  <input type="hidden" name="nodeId" value="${categoryId}">
  <input type="hidden" name="categoryId" value="${categoryId}">
  </c:if>
  <table class="easyui-form" style="width:98%;" align="center">
    <tbody>
	
	<tr>
		<td width="15%" align="left">名称</td>
		<td align="left" colspan="3">
            <input id="name" name="name" type="text"  size="80"
			       class="easyui-validatebox x-text"  
			       data-options="required:true"
				   value="${wxTemplate.name}"/>
		</td>
	</tr>
	 
	<tr>
		<td width="15%" align="left">描述</td>
		<td align="left" colspan="3">
            <input id="desc" name="desc" type="text"  size="80"
			       class="easyui-validatebox x-text"  
				   value="${wxTemplate.desc}"/>
		</td>
	</tr>

	<tr>
		<td width="15%" align="left">类型</td>
		<td width="35%" align="left" colspan="3">
			 <select  id="type" name="type">
				<option value="0">首页
				<option value="1">列表页
				<option value="2">详细页
		    </select>
			 <script type="text/javascript">
			    jQuery("#type").val("${wxTemplate.type}");
			 </script>
		     &nbsp;&nbsp;是否启用 &nbsp;&nbsp; 
			 <select  id="locked" name="locked">
				<option value="0" selected>启用
				<option value="1">禁用
		    </select>
			 <script type="text/javascript">
			    jQuery("#locked").val("${wxTemplate.locked}");
			 </script>
		</td>
	 </tr>

	 <tr>
		<td width="15%" align="left">内容</td>
		<td align="left" colspan="3">
			<textarea  id="content" name="content" class="x-textarea"  rows="5" cols="38" style="width:535px;height:380px;"><c:out value="${wxTemplate.content}" escapeXml="false"/></textarea> 
		</td>
	</tr>
 
	<tr>
	    <td width="20%" align="left"></td>
		<td align="left" ><br>
            <input type="button" value=" 保存 " onclick="javascript:saveData();" class="btnGreen">
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