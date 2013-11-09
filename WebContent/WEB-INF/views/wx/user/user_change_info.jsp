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
    String theme = com.glaf.core.util.RequestUtils.getTheme(request);
    request.setAttribute("theme", theme);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改用户信息</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/easyui/themes/${theme}/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/themes/${theme}/styles.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/icons/styles.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/core.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.form.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/easyui/locale/easyui-lang-zh_CN.js"></script>
<script language="javascript" src='<%=context%>/scripts/main.js'></script>
<script language="javascript" src='<%=context%>/scripts/verify.js'></script></head>
<script language="javascript">
function checkForm(form){
  if(verifyAll(form)){
	 return true;
  }
   return false;
}
function setValue(obj){
  obj.value=obj[obj.selectedIndex].value;
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
<html:form action="${contextPath}/mx/wx/wxUser/saveModifyInfo" method="post" target="hiddenFrame" onsubmit="return checkForm(this);"> 
  <table width="600" border="0" align="center" cellpadding="0" cellspacing="0" class="box">
      <tr>
        <td height="40">姓　　名*</td>
        <td>
		<input name="name" type="text" size="50" class="easyui-validatebox x-text" data-options="required:true" datatype="string" 
		  value="<%=bean.getName()%>" 
		  nullable="no" maxsize="50" chname="姓名">   
		</td>
      </tr>
      <tr>
        <td height="40">手　　机*</td>
        <td>
          <input name="mobile" type="text" size="50" class="easyui-validatebox x-text" data-options="required:true" datatype="string" 
		  value="<%=bean.getMobile() != null ? bean.getMobile() : ""%>" 
		  nullable="no" maxsize="50" chname="手机">        
		</td>
      </tr>
      <tr>
        <td height="40">邮　　件*</td>
        <td>
          <input name="email" type="text" size="50" class="easyui-validatebox x-text" data-options="required:true" datatype="email" 
		  value="<%=bean.getEmail() != null ? bean.getEmail():""%>" 
		  nullable="no" maxsize="50" chname="邮件">        
		</td>
      </tr>
      <tr>
        <td height="40">办公电话*</td>
        <td>
          <input name="telephone" type="text" size="50" class="easyui-validatebox x-text" data-options="required:true" datatype="string" 
		  value="<%=bean.getTelephone() != null ? bean.getTelephone() :""%>" nullable="no" maxsize="50" chname="办公电话">       
		</td>
      </tr>
      <tr>
        <td class="input-box2"  height="30">&nbsp;</td>
        <td  align="left" height="40">&nbsp;
		   <br>
           <input name="btn_save2" type="submit" value="保存" class="btnGreen">
	    </td>
      </tr>

</table>
</html:form>

</body>
</html>
