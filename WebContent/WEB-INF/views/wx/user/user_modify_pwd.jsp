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
    pageContext.setAttribute("contextPath", context); 
    String theme = com.glaf.core.util.RequestUtils.getTheme(request);
    request.setAttribute("theme", theme);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
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
<script language="JavaScript">
function checkForm(form){
  if(verifyAll(form)){
     if(form.newPwd.value!=form.password2.value){
	   alert("新密码与确认密码不匹配！");
	 }else{
	   return true;
	 }
  }
   return false;
}
function setValue(obj){
  obj.value=obj[obj.selectedIndex].value;
}
</script>
</head>
<body>
<div class="content-block" style="width: 90%;"><br>
<div class="x_content_title"><img
	src="<%=request.getContextPath()%>/images/window.png"
	alt="修改用户密码">&nbsp;修改用户密码
 
</div>
<br>
<html:form action="${contextPath}/mx/wx/wxUser/savePwd" method="post"  onsubmit="return checkForm(this);"> 
 
<table width="600" border="0" align="center" cellpadding="0" cellspacing="0" class="box">
 	  <tr>
        <td class="input-box2" valign="top" height="30">原密码*</td>
        <td align="left">
		<input name="oldPwd" type="password" size="40" datatype="string" nullable="no" minsize="6" maxsize="20" chname="密码" class="easyui-validatebox x-text"    data-options="required:true">
		</td>
      </tr>
      <tr>
        <td class="input-box2" valign="top" height="30">新密码*</td>
        <td align="left">
		<input name="newPwd" type="password" size="40" datatype="string" nullable="no" minsize="6" maxsize="20" chname="密码" class="easyui-validatebox x-text"    data-options="required:true">
		</td>
      </tr>
      <tr>
        <td class="input-box2" valign="top" height="30">确认密码*</td>
        <td align="left">
		<input name="password2" type="password" size="40"  datatype="string" nullable="no" minsize="6" maxsize="20" chname="确认密码" class="easyui-validatebox x-text"    data-options="required:true">
		</td>
      </tr>
      <tr>
	    <td class="input-box2" valign="top" height="30">&nbsp;</td>
        <td  align="left" valign="bottom" height="30">&nbsp;
		   <br>
           <input name="btn_save2" type="submit" value="修改密码" class="btnGreen">
		</td>
      </tr>
  </tr>
</table>
</html:form>
</div>
</body>
</html>