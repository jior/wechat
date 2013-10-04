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
<title>内容发布</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/easyui/themes/${theme}/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/themes/${theme}/styles.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/icons/styles.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/core.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.form.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/glaf-base.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/glaf-core.js"></script>
<script type="text/javascript">
    var contextPath="<%=request.getContextPath()%>";

	KE.show({  id : 'content'
	           ,allowFileManager : true
	           ,imageUploadJson : '<%=request.getContextPath()%>/mx/wx/uploadJson'
			   ,fileManagerJson : '<%=request.getContextPath()%>/mx/wx/fileManagerJson' });

	function saveData(){
		document.getElementById("content").value=KE.html('content');
		var params = jQuery("#iForm").formSerialize();
		jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/mx/wx/wxContent/saveWxContent',
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
		document.getElementById("content").value=KE.html('content');
		var params = jQuery("#iForm").formSerialize();
		jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/mx/wx/wxContent/saveWxContent',
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

	function editRelations(){
		var link = '<%=request.getContextPath()%>/mx/wx/wxContent/choose?elementId=relationIds&elementName=relations&type=category&selecteds=${wxContent.relationIds}';
		var x=100;
		var y=100;
		if(is_ie) {
			x=document.body.scrollLeft+event.clientX-event.offsetX-200;
			y=document.body.scrollTop+event.clientY-event.offsetY-200;
		}
		openWindow(link,self,x, y, 745, 480);
	}

	function editRecommendations(){
		var link = '<%=request.getContextPath()%>/mx/wx/wxContent/choose?elementId=recommendationIds&elementName=recommendations&type=category&selecteds=${wxContent.recommendationIds}';
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
	<span class="x_content_title">编辑内容</span>
	<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-save'" onclick="javascript:saveData();" >保存</a> 
	
    </div> 
  </div>

  <div data-options="region:'center',border:false,cache:true">
  <form id="iForm" name="iForm" method="post">
  <input type="hidden" id="id" name="id" value="${wxContent.id}"/>
  <input type="hidden" id="categoryId" name="categoryId" value="${categoryId}"/>
  <input type="hidden" id="type" name="type" value="${type}"/>
  <table class="easyui-form" style="width:96%;" align="center">
    <tbody>
	<tr>
		<td width="15%" align="left">标题</td>
		<td align="left">
            <input id="title" name="title" type="text" 
			       class="easyui-validatebox x-text"  size="80"
			       data-options="required:true"
				   value="${wxContent.title}"/>
		</td>
	</tr>
	<tr>
		<td width="15%" align="left">作者（选填）</td>
		<td align="left">
            <input id="author" name="author" type="text" 
			       class="easyui-validatebox x-text"  size="80"
			       data-options="required:false"
				   value="${wxContent.author}"/>
		</td>
	</tr>
    <tr>
		<td width="15%" align="left" valign="middle">图文封面</td>
		<td align="left" valign="middle">
		    <c:if test="${not empty wxContent.icon }">
			<img src="<%=request.getContextPath()%>/${wxContent.icon}" width="60" height="60" border="0"/>&nbsp; 
			</c:if>
			<br>建议图片大小720*400像素（即宽度720，高度400）
			<br>
			<input id="icon" name="icon" type="text" 
			       class="easyui-validatebox x-text"  
				   size="80"
			       data-options="required:false"
				   value="${wxContent.icon}" onclick="javascript:chooseImage();"/>
			&nbsp; <img src="<%=request.getContextPath()%>/images/icon.gif" border="0" onclick="javascript:chooseImage();"/>
			
		</td>
	</tr>

	<tr>
		<td width="15%" align="left">顺序号</td>
		<td align="left">
			<input id="sort" name="sort" type="text" 
			       class="easyui-numberspinner"  size="5"
				   increment="1"  
				   value="${wxContent.sort}"/>&nbsp;(同级栏目顺序越大越靠前)
		</td>
	</tr>
	<tr>
		<td width="15%" align="left">关键字</td>
		<td align="left">
            <input id="keywords" name="keywords" type="text" 
			       class="easyui-validatebox x-text"  size="82"
				   value="${wxContent.keywords}"/>
		<br/>(多个关键字之间用空格隔开)
		</td>
	</tr>

	<tr>
		<td width="15%" align="left">关键字匹配类型</td>
		<td align="left">
             <select id="keywordsMatchType" name="keywordsMatchType">
				<option value="1">完全匹配</option>
				<option value="0">包含匹配</option>
             </select>
			 <script type="text/javascript">
			     jQuery("#keywordsMatchType").val("${wxContent.keywordsMatchType}");
			 </script>
		</td>
	</tr>
	 
	<tr>
		<td width="15%" align="left">简介</td>
		<td align="left">
			<textarea  id="summary" name="summary" class="x-textarea"  rows="5" cols="38" style="width:545px;height:60px;">${wxContent.summary}</textarea> 
		</td>
	</tr>

	<tr>
		<td width="20%" align="left">是否发布</td>
		<td align="left">
			 <select id="status" name="status">
				<option value="0" >未发布
				<option value="1" selected>发布
		    </select>
			 <script type="text/javascript">
			    jQuery("#status").val("${wxContent.status}");
			 </script>
		</td>
	</tr>

	<tr>
		<td width="20%" align="left">详细页显示图文封面</td>
		<td align="left">
			 <select id="detailShowCover" name="detailShowCover">
				<option value="0" >否
				<option value="1" selected>是
		    </select>
			 <script type="text/javascript">
			    jQuery("#detailShowCover").val("${wxContent.detailShowCover}");
			 </script>
		</td>
	</tr>
	<tr>
		<td width="15%" align="left" valign="middle">图文详细页内容</td>
		<td align="left" valign="middle">
			<textarea  id="content" name="content" class="x-textarea"  rows="5" cols="38" style="width:545px;height:380px;">${wxContent.content}</textarea> 
		</td>
	</tr>

	<tr>
		<td width="15%" align="left" valign="middle">多图文</td>
		<td align="left" valign="middle">
			 <input type="hidden" id="relationIds" name="relationIds" value="${wxContent.relationIds}"> 
			 <input type="hidden" id="relations" name="relations"> 
			 <input type="button" value=" 添加 " onclick="javascript:editRelations();" class="btnGreen">
		</td>
	</tr>

	<tr>
		<td width="15%" align="left" valign="middle">推荐阅读</td>
		<td align="left" valign="middle">
			<input type="hidden" id="recommendationIds" name="recommendationIds" value="${wxContent.recommendationIds}"> 
			<input type="hidden" id="recommendations" name="recommendations">
			<input type="button" value=" 添加 " onclick="javascript:editRecommendations();" class="btnGreen">
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
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</div>
</div>
 

</body>
</html>