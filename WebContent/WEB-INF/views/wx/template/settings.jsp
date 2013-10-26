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
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.glaf.wechat.domain.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	 String contextPath = request.getContextPath();
	 String type = request.getParameter("type");
	 String title="首页模板风格设定";///mx/wx/wxTemplate/settings?type=0
	 if("1".equals(type)){
		 title="列表页模板风格设定";
	 } else if("2".equals(type)){
		 title="详细页模板风格设定";
	 }
	 List templates = (List)request.getAttribute("templates");
	 WxUserTemplate wxUserTemplate = (WxUserTemplate)request.getAttribute("wxUserTemplate");
%>
<!DOCTYPE html>
<html>
<head>
<title>模版设置</title>
<%@ include file="/WEB-INF/views/tm/mx_header.jsp"%>
 <script language="javascript">
  String.prototype.trim = function() {
     return this.replace(/(^\s*)|(\s*$)/g, "");
  }
 
 function submitRequest(form){
	form.submit();
 }

</script>
</head>
<body>
<center>
<form name="iForm" method="post" class="x-form"
	action="<%=request.getContextPath()%>/mx/wx/wxTemplate/saveUserTemplate"
	onsubmit="return checkForm();">
    <input type="hidden" id="templateId" name="templateId" >
<c:if test="${not empty categoryId}">
	<input type="hidden" name="nodeId" value="${categoryId}">
	<input type="hidden" name="categoryId" value="${categoryId}">
</c:if>
<c:if test="${not empty type}">
	<input type="hidden" name="type" value="${type}">
</c:if>
<div class="content-block" style="width: 80%;"><br>
<div class="x_content_title"><img
	src="<%=request.getContextPath()%>/images/window.png"
	alt="<%=title%>">&nbsp;<%=title%>
</div>
<br>
 
<table border=0 cellspacing=0 cellpadding=2 align="center">
	<tbody>
     <%if(templates != null && !templates.isEmpty()){
	   int begin = 0;
       out.println("<tr>");
	   for(int i=0; i<templates.size(); i++){
		   WxTemplate template = (WxTemplate)templates.get(i);
           String checked = "";
           if(wxUserTemplate != null && (wxUserTemplate.getTemplateId() == template.getId())){
			   checked = "checked";
		   }
		   begin++;
           if(begin > 0 && begin % 4 == 0){
		      out.println("<tr>");
	       }
	 %>
          <td width="20%">
		      <img src="<%=request.getContextPath()%><%=template.getSkinImage()%>" border="0">
			  <br><br>
			  <input type="radio" name="tmp_<%=template.getId()%>" <%=checked%>
			         onclick="javascript:document.getElementById('templateId').value='<%=template.getId()%>'">
			  &nbsp;<%=template.getName()%>
		  </td>
	 <%
	     if(begin > 0 && begin % 4 == 0){
		     out.println("\n</tr>");
	     }
	   }
	   if(begin > 0 && begin % 4 != 0){
		   out.println("\n</tr>");
	   }
	 }%>

	</tbody>
</table>
 

<div align="center">
<br />
<input type="button" class="btnGreen" value="确定"
	onclick="javascript:submitRequest(this.form);" /> 
<c:if test="${not empty categoryId && categoryId > 0 }">
&nbsp;
<input type="button" class="btnGreen" value="返回"
	onclick="javascript:history.back();" />
</c:if>
<br />
<br />
</div>

</div>
</form>
</center>
<br />
<br />

<%@ include file="/WEB-INF/views/tm/footer.jsp"%>