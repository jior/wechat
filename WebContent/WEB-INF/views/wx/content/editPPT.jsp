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
<title>内容发布</title>
<%@ include file="/WEB-INF/views/wx/inc/wx_styles.jsp"%>
<%@ include file="/WEB-INF/views/wx/inc/wx_scripts.jsp"%>
<script type="text/javascript">
    var contextPath="<%=request.getContextPath()%>";

	function saveData(){
		var title = document.getElementById("title").value.trim();
	 
		if(title == ""){
			alert("名称不能为空！");
			document.getElementById("title").focus();
			return ;
		}
		var params = jQuery("#iForm").formSerialize();
		jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/mx/wx/wxContent/saveWxContent?categoryId=${categoryId}',
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
					   } else {
					   <%if(request.getParameter("fromUrl")!=null){%>
					     location.href='<%=com.glaf.core.util.RequestUtils.decodeURL(request.getParameter("fromUrl"))%>';
					   <%}%>
					   }
				   }
			 });
	}

	function saveAsData(){
		document.getElementById("id").value="";
		var params = jQuery("#iForm").formSerialize();
		jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/mx/wx/wxContent/saveWxContent?categoryId=${categoryId}',
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
		var link = '<%=request.getContextPath()%>/mx/wx/wxFile/chooseFile?elementId=icon&elementName=icon&accountId=${accountId}';
		var x=50;
		var y=50;
		if(is_ie) {
			x=document.body.scrollLeft+event.clientX-event.offsetX-200;
			y=document.body.scrollTop+event.clientY-event.offsetY-200;
		}
		openWindow(link,self,x, y, 845, 580);
	}

	function chooseLink(){
		var link = '<%=request.getContextPath()%>/mx/wx/wxChoose/chooseOne?elementId=url&elementName=url&accountId=${accountId}';
		var x=50;
		var y=50;
		if(is_ie) {
			x=document.body.scrollLeft+event.clientX-event.offsetX-200;
			y=document.body.scrollTop+event.clientY-event.offsetY-200;
		}
		openWindow(link,self,x, y, 845, 480);
	}

</script>
</head>

<body>
<div style="margin:0;"></div>  

<div class="easyui-layout" data-options="fit:true">  
  <div data-options="region:'north',split:true,border:true" style="height:40px"> 
    <div class="toolbar-backgroud"> 
	<span class="x_content_title">编辑幻灯片</span>
	<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-save'" 
	   onclick="javascript:saveData();" >保存</a> 
	<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-back'"
	   onclick="javascript:window.history.go(-1);">返回</a>    
    </div> 
  </div>

  <div data-options="region:'center',border:false,cache:true">
  <form id="iForm" name="iForm" method="post">
  <input type="hidden" id="id" name="id" value="${wxContent.id}"/>
  <input type="hidden" id="accountId" name="accountId" value="${accountId}"/>
  <input type="hidden" id="categoryId" name="categoryId" value="${categoryId}"/>
  <input type="hidden" id="type" name="type" value="PPT"/>
  <table class="easyui-form" style="width:96%;" align="left">
    <tbody>
	<tr>
		<td width="15%" align="left">标题</td>
		<td align="left">
            <input id="title" name="title" type="text" 
			       class="easyui-validatebox x-text"  size="60" maxlength="200"
			       data-options="required:true"
				   value="${wxContent.title}"/>
		</td>
	</tr>
	<tr>
		<td width="15%" align="left">图片</td>
		<td align="left">
		     <c:if test="${not empty wxContent.icon }">
			    <a href="<%=request.getContextPath()%>/${wxContent.icon}" target="_blank"><img src="<%=request.getContextPath()%>/${wxContent.icon}" border="0" width="160" height="105"/></a>
				<br>
			 </c:if>
			 <input id="icon" name="icon" type="text" 
			       class="easyui-validatebox x-text"  size="60"
			       data-options="required:true"
				   value="${wxContent.icon}" onclick="javascript:chooseImage();"/>
				   &nbsp; <img src="<%=request.getContextPath()%>/images/icon.gif" border="0"  onclick="javascript:chooseImage();"/>
			<br>建议图片大小：宽640像素，高420像素
		</td>
	</tr>
	 
	<tr>
		<td width="15%" align="left">排序</td>
		<td align="left">
			<input id="sort" name="sort" type="text" 
			       class="easyui-numberspinner"  size="5"
				   increment="1"  
				   value="${wxContent.sort}"/>(同级栏目顺序越大越靠前)
		</td>
	</tr>
	 
	<tr>
		<td width="15%" align="left">跳转地址</td>
		<td align="left">
			<input type="text" id="url" name="url"class="x-text" size="60" value="${wxContent.url}" maxlength="250">
			&nbsp;<img src="<%=request.getContextPath()%>/images/link.png" 
			           title="链接"
			           onclick="javascript:chooseLink();" border="0"/>&nbsp;
			<br>可以选择内部链接
			<br>也可以直接输入外部链接（以http://或https://开始）
		</td>
	</tr>

	<tr>
		<td width="20%" align="left">是否发布</td>
		<td align="left">
			 <select  id="status" name="status">
			    <option value="1" selected>发布
				<option value="0" >未发布
		    </select>
			 <script type="text/javascript">
			    jQuery("#status").val("${wxContent.status}");
			 </script>
		</td>
	</tr>
 
   	<tr>
	    <td width="20%" align="left"></td>
		<td align="left" ><br>
            <!-- <input type="button" value=" 保存 " onclick="javascript:saveData();" class="btnGreen"> -->
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