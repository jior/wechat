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
<title>微会员</title>
<%@ include file="/WEB-INF/views/wx/inc/wx_styles.jsp"%>
<%@ include file="/WEB-INF/views/wx/inc/wx_scripts.jsp"%>
<script type="text/javascript">
    var contextPath="<%=request.getContextPath()%>";

	function saveData(){
		var params = jQuery("#iForm").formSerialize();
		jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/mx/wx/wxMember/saveWxMember',
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
					   }
					   */
					   location.href='<%=com.glaf.core.util.RequestUtils.decodeURL(request.getParameter("fromUrl"))%>';
				   }
			 });
	}

	function saveAsData(){
		document.getElementById("id").value="";
		document.getElementById("id").value="";
		var params = jQuery("#iForm").formSerialize();
		jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/mx/wx/wxMember/saveWxMember',
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
	<span class="x_content_title">编辑微会员</span>
	<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-save'" 
	   onclick="javascript:saveData();" >保存</a> 
	<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-back'"
	   onclick="javascript:window.history.go(-1);">返回</a> 
    </div> 
  </div>

  <div data-options="region:'center',border:false,cache:true">
  <form id="iForm" name="iForm" method="post">
  <input type="hidden" id="id" name="id" value="${wxMember.id}"/>
  <input type="hidden" id="accountId" name="accountId" value="${accountId}"/>
  <table class="easyui-form" style="width:600px;" align="center">
    <tbody>
	<tr>
		<td width="20%" align="left">卡号</td>
		<td align="left">
            <input id="cardNo" name="cardNo" type="text" size="50"
			       class="easyui-validatebox x-text"  
				   value="${wxMember.cardNo}"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">姓名</td>
		<td align="left">
            <input id="name" name="name" type="text"  size="50"
			       class="easyui-validatebox x-text"  
				   value="${wxMember.name}"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">手机</td>
		<td align="left">
            <input id="mobile" name="mobile" type="text" size="50" 
			       class="easyui-validatebox x-text"  
				   value="${wxMember.mobile}"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">邮件</td>
		<td align="left">
            <input id="mail" name="mail" type="text" size="50" 
			       class="easyui-validatebox x-text"  
				   value="${wxMember.mail}"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">QQ</td>
		<td align="left">
            <input id="qq" name="qq" type="text"  size="50"
			       class="easyui-validatebox x-text"  
				   value="${wxMember.qq}"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">地址</td>
		<td align="left">
            <input id="address" name="address" type="text"  size="50"
			       class="easyui-validatebox x-text"  
				   value="${wxMember.address}"/>
		</td>
	</tr>

	<tr>
	    <td width="20%" align="left"></td>
		<td align="left" >
            <br><input type="button" value=" 保存 " onclick="javascript:saveData();" class="btnGreen">
		</td>
	</tr>

    </tbody>
  </table>
  </form>
</div>
</div>
</body>
</html>