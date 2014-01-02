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
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="html"%>
<%@ page import="java.util.*"%>
<%@ page import="com.glaf.base.modules.sys.*"%>
<%@ page import="com.glaf.base.modules.sys.model.*"%>
<%@ page import="com.glaf.base.utils.*"%>
<%
    String context = request.getContextPath();
    pageContext.setAttribute("contextPath", context); 
 
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<%@ include file="/WEB-INF/views/wx/inc/wx_styles.jsp"%>
<%@ include file="/WEB-INF/views/wx/inc/wx_scripts.jsp"%>
<script language="JavaScript">
 
	function saveData(){
		if(document.getElementById("oldPwd").value.trim().length == 0){
			alert("请输入原密码！");
			document.getElementById("oldPwd").focus();
			return ;
		}

		if(document.getElementById("newPwd").value.trim().length == 0){
			alert("请输入新密码！");
			document.getElementById("newPwd").focus();
			return ;
		}

		if(document.getElementById("newPwd").value.trim() != document.getElementById("password2").value.trim()){
			alert("两次密码输入不一致，请再次输入新密码！");
			document.getElementById("password2").focus();
			return ;
		}
		var params = jQuery("#iForm").formSerialize();
		jQuery.ajax({
				   type: "POST",
				   url: '${contextPath}/mx/wx/wxUser/savePwd',
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
<div class="content-block" style="width: 80%;"><br>
<div class="x_content_title"><img
	src="<%=request.getContextPath()%>/images/window.png"
	alt="修改用户密码">&nbsp;修改用户密码
 
</div>
<br>
<form id="iForm" name="iForm" method="post" > 
 
<table width="600" border="0" align="center" cellpadding="0" cellspacing="0" class="box">
 	  <tr>
        <td class="input-box2"  height="40">原密码*</td>
        <td align="left">
		<input id="oldPwd" name="oldPwd" type="password" size="40" datatype="string" nullable="no" minsize="6" maxsize="20" chname="密码" class="easyui-validatebox x-text" data-options="required:true" maxlength="20">
		</td>
      </tr>
      <tr>
        <td class="input-box2"  height="40">新密码*</td>
        <td align="left">
		<input id="newPwd" name="newPwd" type="password" size="40" datatype="string" nullable="no" minsize="6" maxsize="20" chname="密码" class="easyui-validatebox x-text" data-options="required:true" maxlength="20">
		</td>
      </tr>
      <tr>
        <td class="input-box2"  height="40">确认密码*</td>
        <td align="left">
		<input id="password2" name="password2" type="password" size="40"  datatype="string" nullable="no" minsize="6" maxsize="20" chname="确认密码" class="easyui-validatebox x-text" data-options="required:true" maxlength="20"> 
		</td>
      </tr>
      <tr>
	    <td class="input-box2"  height="40">&nbsp;</td>
        <td  align="left" valign="bottom" height="40">&nbsp;
		   <br>
           <input name="btn_save2" type="button" value="修改密码" class="btnGreen" onclick="javascript:saveData();">
		</td>
      </tr>
  </tr>
</table>
</form>
</div>
</body>
</html>