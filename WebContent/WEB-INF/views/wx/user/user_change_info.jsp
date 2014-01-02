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
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="html"%>
<%@ page import="java.util.*"%>
<%@ page import="com.glaf.base.modules.sys.*"%>
<%@ page import="com.glaf.base.modules.sys.model.*"%>
<%@ page import="com.glaf.base.utils.*"%>
<%
    String context = request.getContextPath();
    SysUser bean=(SysUser)request.getAttribute("bean");
    pageContext.setAttribute("contextPath", context); 
 
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改用户信息</title>
<%@ include file="/WEB-INF/views/wx/inc/wx_styles.jsp"%>
<%@ include file="/WEB-INF/views/wx/inc/wx_scripts.jsp"%>
<script language="javascript">
 
    function saveData(){
	    if(document.getElementById("name").value.trim().length == 0){
			alert("请输入姓名！");
			document.getElementById("name").focus();
			return ;
		}

		if(document.getElementById("mobile").value.trim().length == 0){
			alert("请输入手机号码！");
			document.getElementById("mobile").focus();
			return ;
		}

		if(document.getElementById("email").value.trim().length == 0){
			alert("请输入电子邮件！");
			document.getElementById("email").focus();
			return ;
		}

		var params = jQuery("#iForm").formSerialize();
		jQuery.ajax({
				   type: "POST",
				   url: '${contextPath}/mx/wx/wxUser/saveModifyInfo',
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
	alt="修改个人资料">&nbsp;修改个人资料
</div>
<br>
<form id="iForm" name="iForm" method="post"  > 
  <table width="600" border="0" align="center" cellpadding="0" cellspacing="0" class="box">
      <tr>
        <td height="40">姓　　名*</td>
        <td>
		<input id="name" name="name" type="text" maxlength="50" size="50" class="easyui-validatebox x-text" data-options="required:true" datatype="string" 
		  value="<%=bean.getName()%>" 
		  nullable="no" maxsize="50" chname="姓名">   
		</td>
      </tr>
      <tr>
        <td height="40">手　　机*</td>
        <td>
          <input id="mobile" name="mobile" type="text" size="50" class="easyui-validatebox x-text" data-options="required:true" datatype="string" maxlength="11"
		  value="<%=bean.getMobile() != null ? bean.getMobile() : ""%>" 
		  nullable="no" maxsize="12" chname="手机">        
		</td>
      </tr>
      <tr>
        <td height="40">邮　　件*</td>
        <td>
          <input id="email" name="email" type="text" size="50" maxlength="80" class="easyui-validatebox x-text" data-options="required:true" datatype="email" 
		  value="<%=bean.getEmail() != null ? bean.getEmail():""%>" 
		  nullable="no" maxsize="50" chname="邮件">        
		</td>
      </tr>
      <tr>
        <td height="40">办公电话</td>
        <td>
          <input name="telephone" type="text" size="50" maxlength="50" class="easyui-validatebox x-text" data-options="required:false" datatype="string" 
		  value="<%=bean.getTelephone() != null ? bean.getTelephone() :""%>" nullable="yes" maxsize="50" chname="办公电话">       
		</td>
      </tr>
      <tr>
        <td class="input-box2"  height="30">&nbsp;</td>
        <td  align="left" height="40">&nbsp;
		   <br>
           <input name="btn_save2" type="button" value="保存" class="btnGreen" onclick="javascript:saveData();">
	    </td>
      </tr>

</table>
</form>

</body>
</html>
