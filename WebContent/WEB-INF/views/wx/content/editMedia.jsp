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
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript">
    var contextPath="<%=request.getContextPath()%>";

	function saveData(){
		var title = document.getElementById("title").value.trim();
	 
		if(title == ""){
			alert("名称不能为空！");
			document.getElementById("title").focus();
			return ;
		}
		if(document.getElementById("summary").value.trim().length>250){
			alert("描述长度超过250字节！");
			document.getElementById("summary").focus();
			return ;
		}

		<c:if test="${type eq 'K'}">
		if(document.getElementById("keywords").value.trim().length==0){
			alert("请填写关键字！");
			document.getElementById("keywords").focus();
			return;
		}
        if(document.getElementById("keywords").value.trim().length>250){
			alert("关键字长度超过250字节！");
			document.getElementById("keywords").focus();
			return;
		}
	    </c:if>
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
					   /**
					   if (window.opener) {
						window.opener.location.reload();
					   } else if (window.parent) {
						window.parent.location.reload();
					   }**/
					  //history.back();
					  location.href='<%=com.glaf.core.util.RequestUtils.decodeURL(request.getParameter("fromUrl"))%>';
				   }
			 });
	}

	function saveAsData(){
		document.getElementById("id").value="";
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
		var link = '<%=request.getContextPath()%>/mx/wx/wxFile/chooseFile?elementId=icon&elementName=icon&accountId=${accountId}';
		var x=50;
		var y=50;
		if(is_ie) {
			x=document.body.scrollLeft+event.clientX-event.offsetX-200;
			y=document.body.scrollTop+event.clientY-event.offsetY-200;
		}
		openWindow(link,self,x, y, 845, 580);
	}

	function editRelations(){
		var link = '<%=request.getContextPath()%>/mx/wx/wxContent/choose?elementId=relationIds&elementName=relations&type=category&selecteds=${wxContent.relationIds}&accountId=${accountId}';
		var x=100;
		var y=100;
		if(is_ie) {
			x=document.body.scrollLeft+event.clientX-event.offsetX-200;
			y=document.body.scrollTop+event.clientY-event.offsetY-200;
		}
		openWindow(link,self,x, y, 845, 480);
	}

	function editRecommendations(){
		var link = '<%=request.getContextPath()%>/mx/wx/wxContent/choose?elementId=recommendationIds&elementName=recommendations&type=category&selecteds=${wxContent.recommendationIds}&accountId=${accountId}';
		var x=100;
		var y=100;
		if(is_ie) {
			x=document.body.scrollLeft+event.clientX-event.offsetX-200;
			y=document.body.scrollTop+event.clientY-event.offsetY-200;
		}
		openWindow(link,self,x, y, 845, 480);
	}

	function chooseLink(){
		var link = '<%=request.getContextPath()%>/mx/wx/wxChoose/chooseOne?elementId=url&elementName=url&accountId=${accountId}';
		var x=100;
		var y=100;
		if(is_ie) {
			x=document.body.scrollLeft+event.clientX-event.offsetX-200;
			y=document.body.scrollTop+event.clientY-event.offsetY-200;
		}
		openWindow(link,self,x, y, 845, 480);
	}

	function chooseMedia(){
		var link = '<%=request.getContextPath()%>/mx/wx/wxFile/chooseFile?elementId=url&elementName=url&accountId=${accountId}';
		var x=50;
		var y=50;
		if(is_ie) {
			x=document.body.scrollLeft+event.clientX-event.offsetX-200;
			y=document.body.scrollTop+event.clientY-event.offsetY-200;
		}
		openWindow(link,self,x, y, 845, 580);
	}

</script>
</head>

<body>
<div style="margin:0;"></div>  

<div class="easyui-layout" data-options="fit:true">  
  <div data-options="region:'north',split:true,border:true" style="height:40px"> 
    <div class="toolbar-backgroud"> 
	<span class="x_content_title">编辑内容</span>
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
  <input type="hidden" id="type" name="type" value="${type}"/>
  <table class="easyui-form" style="width:96%;" align="left">
    <tbody>
	<tr>
		<td width="15%" align="left">标题</td>
		<td align="left">
            <input id="title" name="title" type="text" 
			       class="easyui-validatebox x-text"  size="80" maxlength="200"
			       data-options="required:true"
				   value="${wxContent.title}"/>
		</td>
	</tr>
	<!-- <tr>
		<td width="15%" align="left">作者（选填）</td>
		<td align="left">
            <input id="author" name="author" type="text" 
			       class="easyui-validatebox x-text"  size="80" maxlength="50"
			       data-options="required:false"
				   value="${wxContent.author}"/>
		</td>
	</tr> -->
    <tr>
		<td width="15%" align="left" valign="middle">图文封面</td>
		<td align="left" valign="middle">
		    <c:if test="${not empty wxContent.icon }">
			<a href="<%=request.getContextPath()%>/${wxContent.icon}" target="_blank"><img src="<%=request.getContextPath()%>/${wxContent.icon}" width="180" height="100" border="0"/></a>&nbsp; 
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
	<c:if test="${type eq 'K'}">
	<tr>
		<td width="15%" align="left">关键词</td>
		<td align="left">
            <input id="keywords" name="keywords" type="text" 
			       class="easyui-validatebox x-text"  size="80"
				   value="${wxContent.keywords}"/>
		<br/>(多个关键词之间用空格隔开)
		<br/>最多只能加10个关键词且每关键词不能超过50个字符(超过50个字符，系统会自动过滤掉)
        <br/>“subscribe”为粉丝关注反馈信息的关键词 
		</td>
	</tr>
    
	<tr>
		<td width="15%" align="left">关键字匹配类型</td>
		<td align="left">
             <select id="keywordsMatchType" name="keywordsMatchType">
				<option value="1">完全匹配</option>
				<!-- <option value="0">包含匹配</option> -->
             </select>
			 <script type="text/javascript">
			     jQuery("#keywordsMatchType").val("${wxContent.keywordsMatchType}");
			 </script>
		</td>
	</tr>
    </c:if>
	<tr>
		<td width="15%" align="left">简介</td>
		<td align="left">
			<textarea  id="summary" name="summary" class="x-textarea"  rows="5" cols="38" style="width:485px;height:60px;">${wxContent.summary}</textarea> 
		</td>
	</tr>

	<tr>
		<td width="20%" align="left">是否发布</td>
		<td align="left">
			 <select id="status" name="status">
				<option value="1" >发布
				<option value="0" >未发布
		    </select>
			 <script type="text/javascript">
			    jQuery("#status").val("${wxContent.status}");
			 </script>
		</td>
	</tr>

    <tr>
		<td width="20%" align="left">媒体类型</td>
		<td align="left">
			 <select id="msgType" name="msgType">
				<option value="image">图片</option>
				<option value="voice">语音</option>
				<option value="video">视频</option>
				<option value="music">音乐</option>
		    </select>
			 <script type="text/javascript">
			    jQuery("#msgType").val("${wxContent.msgType}");
			 </script>
		</td>
	</tr>

	<tr>
		<td width="15%" align="left" valign="middle">媒体链接</td>
		<td align="left" valign="middle">
			   <input type="text" id="url" name="url"class="x-text" size="80" value="${wxContent.url}">
			   &nbsp;<img src="<%=request.getContextPath()%>/images/link.png" 
			           title="链接" onclick="javascript:chooseMedia();" border="0"/>
			  <br>可以选择内部链接
			  <br>也可以直接输入外部链接（以http://或https://开始）
		</td>
	</tr>
	 
  	<tr>
	    <td width="20%" align="left"></td>
		<td align="left" ><br>
            <!-- <input type="button" value=" 保存 " onclick="javascript:saveData();" class="btnGreen"> -->
			<br/><br/>
		</td>
	</tr>
    </tbody>
  </table>
  </form>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</div>
</div>
 
</body>
</html>