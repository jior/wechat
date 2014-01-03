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
<title>编辑菜单</title>
<%@ include file="/WEB-INF/views/wx/inc/wx_styles.jsp"%>
<%@ include file="/WEB-INF/views/wx/inc/wx_scripts.jsp"%>
<script type="text/javascript">
    var contextPath="<%=request.getContextPath()%>";

	function saveData(){
		if(document.getElementById("type").value=='view'){
            if(document.getElementById("url").value==''){
               document.getElementById("url").focus();
			   alert("链接地址不能为空！");
			   return;
			}
		}
		if(document.getElementById("desc").value.trim().length>500){
			alert("描述长度超过500字节！");
			document.getElementById("desc").focus();
			return ;
		}
		var params = jQuery("#iForm").formSerialize();
		jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/mx/wx/wxMenu/saveWxMenu',
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
					   /*
					   if (window.opener) {
						  //window.opener.location.reload();
						  window.opener.reloadGrid();
					   } else if (window.parent) {
						  //window.parent.location.reload();
						  window.parent.reloadGrid();
					   }
					   window.close();
					   */
					  //history.back();
					  location.href='<%=com.glaf.core.util.RequestUtils.decodeURL(request.getParameter("fromUrl"))%>';
				   }
			 });
	}

	function saveAsData(){
		document.getElementById("id").value="";
		if(document.getElementById("type").value=='view'){
            if(document.getElementById("url").value==''){
               document.getElementById("url").focus();
			   alert("链接地址不能为空！");
			   return;
			}
		}
		var params = jQuery("#iForm").formSerialize();
		jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/mx/wx/wxMenu/saveWxMenu',
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
		var x=100;
		var y=100;
		if(is_ie) {
			x=document.body.scrollLeft+event.clientX-event.offsetX-200;
			y=document.body.scrollTop+event.clientY-event.offsetY-200;
		}
		openWindow(link,self,x, y, 745, 580);
	}

	function chooseImage2(){
		var link = '<%=request.getContextPath()%>/mx/wx/wxFile/chooseFile?elementId=coverIcon&elementName=coverIcon&accountId=${accountId}';
		var x=100;
		var y=100;
		if(is_ie) {
			x=document.body.scrollLeft+event.clientX-event.offsetX-200;
			y=document.body.scrollTop+event.clientY-event.offsetY-200;
		}
		openWindow(link,self,x, y, 745, 580);
	}

	function chooseLink(){
		var link = '<%=request.getContextPath()%>/mx/wx/wxChoose/chooseOne?elementId=url&elementName=url&type=category&accountId=${accountId}';
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
	<span class="x_content_title">编辑菜单</span>
	<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-save'" 
	   onclick="javascript:saveData();" >保存</a> 
	<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-back'"
	   onclick="javascript:window.history.go(-1);">返回</a>    
    </div> 
  </div>

  <div data-options="region:'center',border:false,cache:true">
  <form id="iForm" name="iForm" method="post">
  <input type="hidden" id="id" name="id" value="${wxMenu.id}"/>
  <input type="hidden" id="group" name="group" value="${group}"/>
  <input type="hidden" id="accountId" name="accountId" value="${accountId}"/>
  <table class="easyui-form" style="width:600px;" align="left">
    <tbody>
	<c:choose>
	<c:when test="${empty wxMenu }">
	<tr>
		<td width="20%" align="left">上级菜单</td>
		<td align="left">
             <select id="parentId" name="parentId">
			    <option value="0">/</option>
			   <c:forEach items="${topMenus}" var="menu">
				<option value="${menu.id}">${menu.name}</option>
			   </c:forEach>
             </select>
             <script type="text/javascript">
                 document.getElementById("parentId").value="${parentId}";
             </script>
		</td>
	</tr>
	</c:when>
	<c:when test="${!empty wxMenu && wxMenu.parentId != 0  }">
	<tr>
		<td width="20%" align="left">上级菜单</td>
		<td align="left">
             <select id="parentId" name="parentId">
			   <c:forEach items="${topMenus}" var="menu">
				<option value="${menu.id}">${menu.name}</option>
			   </c:forEach>
             </select>
             <script type="text/javascript">
               <c:choose>
				<c:when test="${!empty wxMenu }">
				    document.all.parentId.value="${wxMenu.parentId}";	
				</c:when>
				<c:otherwise>
				   document.all.parentId.value="${parentId}";	
				</c:otherwise>
			  </c:choose>
             </script>
		</td>
	</tr>
	</c:when>
	<c:otherwise>
	  <input type="hidden" id="parentId" name="parentId" value="${parentId}">
	</c:otherwise>
	</c:choose>
	<tr>
		<td width="20%" align="left">名称</td>
		<td align="left">
            <input id="name" name="name" type="text"  size="50" maxlength="14"
			       class="easyui-validatebox x-text"  
			       data-options="required:true"
				   value="${wxMenu.name}"/>
			<br>（提示：一级菜单最多4个汉字，二级菜单最多7个汉字）
		</td>
	</tr>
	<tr>
		<td width="15%" align="left">类型</td>
		<td align="left">
             <select id="type" name="type">
				<option value="view">链接</option>
				<option value="click">按钮</option>
             </select>
			 <script type="text/javascript">
			     jQuery("#type").val("${wxMenu.type}");
			 </script>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">描述</td>
		<td align="left">
		    <textarea id="desc" name="desc" rows="8" cols="48" 
			 style="width:320px;height:120px;"
			 class="easyui-validatebox x-textarea" >${wxMenu.desc}</textarea>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">链接</td>
		<td align="left">
            <input id="url" name="url" type="text"  size="50"
			       class="easyui-validatebox x-text"  
				   value="${wxMenu.url}"/> 
		    &nbsp;<img src="<%=request.getContextPath()%>/images/link.png" 
			           title="链接"
			           onclick="javascript:chooseLink();" border="0"/>&nbsp;
			<br>可以选择内部链接
			<br>也可以直接输入外部链接（以http://或https://开始）
		</td>
	</tr>
	<tr>
		<td width="15%" align="left" valign="middle">图标</td>
		<td align="left" valign="top">
		    <c:if test="${not empty wxMenu.icon }">
			     <img src="<%=request.getContextPath()%>/${wxMenu.icon}" width="60" height="60" border="0"/>&nbsp;
				 <br>
			</c:if>
			<input id="icon" name="icon" type="text" 
			       class="easyui-validatebox x-text" size="50"
			       data-options="required:false"
				   value="${wxMenu.icon}" onclick="javascript:chooseImage();"/>
			&nbsp; <img src="<%=request.getContextPath()%>/images/icon.gif" border="0" 
			            title="图标"
			            onclick="javascript:chooseImage();"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">显示顺序</td>
		<td align="left">
			<input id="sort" name="sort" type="text"  size="5"
			       class="easyui-numberspinner"  
				   increment="1"  
				   value="${wxMenu.sort}"/>&nbsp;(同级菜单顺序越大越靠前)
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
			    jQuery("#locked").val("${wxMenu.locked}");
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
</div>
</div>
</body>
</html>