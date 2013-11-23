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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
 String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>封面信息</title>
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
<form name="iForm" method="post" ENCTYPE="multipart/form-data"
	class="x-form"
	action="<%=request.getContextPath()%>/mx/wx/wxCover/save"
	onsubmit="return checkForm();">
	<input type="hidden" id="accountId" name="accountId" value="${accountId}"/>
<c:if test="${not empty categoryId}">
	<input type="hidden" name="nodeId" value="${categoryId}">
	<input type="hidden" name="categoryId" value="${categoryId}">
</c:if>

<c:choose>
	<c:when test="${not empty wxCover.id }">
	<input type="hidden" id="id" name="id" value="${wxCover.id}">
	</c:when>
	<c:otherwise>
				 
	</c:otherwise>
</c:choose>

<div class="content-block" style="width: 96%;"><br>
<div class="x_content_title"><img
	src="<%=request.getContextPath()%>/images/window.png"
	alt="设置默认封面信息">&nbsp;设置默认封面信息
 
</div>
<br>
 
<table border=0 cellspacing=0 cellpadding=2>
	<tbody>
		<tr class="x-content-hight">
			<td align="left" width="70%" align="left" valign="bottom">
			<span>宽720像素*高400像素图片</span>
			<br>
			<c:if test="${not empty wxCover.bigIcon }">
			   <img src="<%=request.getContextPath()%>/${wxCover.bigIcon}" border="0" />
			   <br>
			   <br>
			</c:if>
			<input type="file" id="bigIcon" name="bigIcon" size="50" class="input-file x-text">
            </td>
            <td align="left" width="10%" align="left" valign="bottom">&nbsp;</td>
			<td align="left" width="20%" align="left" valign="bottom">
			<span>60*60图片</span>
            <br>
			<c:if test="${not empty wxCover.smallIcon }">
			   <img src="<%=request.getContextPath()%>/${wxCover.smallIcon}" border="0" />
			   <br>
			   <br>
			</c:if>
			<input type="file" id="smallIcon" name="smallIcon" size="50" class="input-file x-text">
            </td>
		</tr>
	</tbody>
</table>
 
<div align="center">
<br />
<input type="button" class="btnGreen" value=" 保存设置 "
	onclick="javascript:submitRequest(this.form);" /> 
<br />
<br />
</div>

</div>
</form>
</center>
<br />
<br />

<%@ include file="/WEB-INF/views/tm/footer.jsp"%>