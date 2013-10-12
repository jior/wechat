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
<title>编辑栏目</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/easyui/themes/${theme}/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/themes/${theme}/styles.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/icons/styles.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/core.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.form.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/glaf-base.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/glaf-core.js"></script>
<script type="text/javascript">
    var contextPath="<%=request.getContextPath()%>";

	function saveData(){
		var params = jQuery("#iForm").formSerialize();
		jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/mx/wx/wxCategory/saveWxCategory',
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
		var params = jQuery("#iForm").formSerialize();
		jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/mx/wx/wxCategory/saveWxCategory',
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

	function chooseImage(){
		var link = '<%=request.getContextPath()%>/mx/wx/wxFile/chooseFile?elementId=icon&elementName=icon';
		var x=100;
		var y=100;
		if(is_ie) {
			x=document.body.scrollLeft+event.clientX-event.offsetX-200;
			y=document.body.scrollTop+event.clientY-event.offsetY-200;
		}
		openWindow(link,self,x, y, 745, 480);
	}

	function chooseImage2(){
		var link = '<%=request.getContextPath()%>/mx/wx/wxFile/chooseFile?elementId=coverIcon&elementName=coverIcon';
		var x=100;
		var y=100;
		if(is_ie) {
			x=document.body.scrollLeft+event.clientX-event.offsetX-200;
			y=document.body.scrollTop+event.clientY-event.offsetY-200;
		}
		openWindow(link,self,x, y, 745, 480);
	}


</script>
</head>

<body>
<div style="margin:0;"></div>  

<div class="easyui-layout" data-options="fit:true">  
  <div data-options="region:'north',split:true,border:true" style="height:40px"> 
    <div class="toolbar-backgroud"> 
	<span class="x_content_title">编辑栏目</span>
	<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-save'" onclick="javascript:saveData();" >保存</a> 
    </div> 
  </div>

  <div data-options="region:'center',border:false,cache:true">
  <form id="iForm" name="iForm" method="post">
  <input type="hidden" id="id" name="id" value="${wxCategory.id}"/>
  <input type="hidden" id="parentId" name="parentId" value="${parentId}"/>
  <input type="hidden" id="type" name="type" value="${type}"/>
  <table class="easyui-form" style="width:600px;" align="center">
    <tbody>
	
	<tr>
		<td width="20%" align="left">名称</td>
		<td align="left">
            <input id="name" name="name" type="text"  size="50"
			       class="easyui-validatebox x-text"  
			       data-options="required:true"
				   value="${wxCategory.name}"/>
		</td>
	</tr>
	<!-- <tr>
		<td width="20%" align="left">代码</td>
		<td align="left">
            <input id="code" name="code" type="text"  size="50"
			       class="easyui-validatebox x-text"  
			       data-options="required:false"
				   value="${wxCategory.code}"/>
		</td>
	</tr> -->
	<tr>
		<td width="20%" align="left">描述</td>
		<td align="left">
            <input id="desc" name="desc" type="text"  size="50"
			       class="easyui-validatebox x-text"  
				   value="${wxCategory.desc}"/>
		</td>
	</tr>
	<tr>
		<td width="15%" align="left" valign="middle">分类封面</td>
		<td align="left" valign="top">
		     <c:if test="${not empty wxCategory.coverIcon }">
			     <img src="<%=request.getContextPath()%>/${wxCategory.coverIcon}" width="60" height="60" border="0"/>&nbsp;
			</c:if>
			 <input id="coverIcon" name="coverIcon" type="text" 
			       class="easyui-validatebox x-text" size="38"
			       data-options="required:false"
				   value="${wxCategory.coverIcon}" onclick="javascript:chooseImage2();"/>
			&nbsp; <img src="<%=request.getContextPath()%>/images/icon.gif" border="0"  onclick="javascript:chooseImage2();"/>
		</td>
	</tr>
	<tr>
		<td width="15%" align="left" valign="middle">图标</td>
		<td align="left" valign="top">
		     <c:if test="${not empty wxCategory.icon }">
			     <img src="<%=request.getContextPath()%>/${wxCategory.icon}" width="60" height="60" border="0"/>&nbsp;
			</c:if>
			<input id="icon" name="icon" type="text" 
			       class="easyui-validatebox x-text" size="38"
			       data-options="required:false"
				   value="${wxCategory.icon}" onclick="javascript:chooseImage();"/>
			&nbsp; <img src="<%=request.getContextPath()%>/images/icon.gif" border="0"  onclick="javascript:chooseImage();"/>
           <br>用于二级页面菜单及其他栏目信息时的小图标
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">显示顺序</td>
		<td align="left">
			<input id="sort" name="sort" type="text"  size="5"
			       class="easyui-numberspinner"  
				   increment="1"  
				   value="${wxCategory.sort}"/>&nbsp;(同级栏目顺序越大越靠前)
		</td>
	</tr>
	 
	<tr>
		<td width="20%" align="left">是否官网显示</td>
		<td align="left">
		    <select  id="indexShow" name="indexShow">
				<option value="1" selected>显示
				<option value="0">不显示
		    </select>
			 <script type="text/javascript">
			    jQuery("#indexShow").val("${wxCategory.indexShow}");
			 </script>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">是否启用</td>
		<td align="left">
			 <select  id="locked" name="locked">
				<option value="0" selected>启用
				<option value="1">禁用
		    </select>
			 <script type="text/javascript">
			    jQuery("#locked").val("${wxCategory.locked}");
			 </script>
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
<p>&nbsp;</p>
</div>
</div>
</body>
</html>