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
<title>上传模板信息</title>
<%@ include file="/WEB-INF/views/wx/inc/wx_styles.jsp"%>
<%@ include file="/WEB-INF/views/wx/inc/wx_scripts.jsp"%>
 <script language="javascript">
  String.prototype.trim = function() {
     return this.replace(/(^\s*)|(\s*$)/g, "");
  }

 //(用于onKeypress事件)浮点数字框不能输入其他字符
 function numberFilter() {
		var berr=true;
		if (!(event.keyCode==46 || (event.keyCode>=48 && event.keyCode<=57)))
		{
			alert("该字段只能输入数字！");
			berr=false;
		}
		return berr;
	}

  //(用于onKeypress事件)浮点数字框不能输入其他字符
  function integerInputFilter() {
		var berr=true;
		if (!((event.keyCode>=48 && event.keyCode<=57)))
		{
			alert("该字段只能输入正整数！");
			berr=false;
		}
		return berr;
	}

 //在确定提交前的编码里
 function isInteger(obj,name){
	var value1 = obj.value;
	for(i=0;i<obj.value.length;i++){
		var ch=obj.value.charAt(i);
		if((ch<'0' || ch>'9')){
			alert("<"+name+">只能是数字！");
			obj.focus();
		   return false;
		}
		if(value1<=0){
			alert("<"+name+">应该是一个大于0的数字！");
			obj.focus();
		   return false;
		}
	}
	return true;	    
}

 function submitRequest(form){
    var file = form.file.value.trim();
	 
    if(file == ""){
		alert("名称不能为空！");
		form.file.focus();
		return ;
	 }
    
	form.submit();
	window.parent.reload();
	window.close();
 }

</script>
</head>
<body>
<center>
<form name="iForm" method="post" ENCTYPE="multipart/form-data"
	class="x-form"
	action="<%=request.getContextPath()%>/mx/wx/wxTemplate/upload/${accountId}"
	onsubmit="return checkForm();">
	<input type="hidden" id="accountId" name="accountId" value="${accountId}"/>
<c:if test="${not empty categoryId}">
	<input type="hidden" name="nodeId" value="${categoryId}">
	<input type="hidden" name="categoryId" value="${categoryId}">
</c:if>

<div class="content-block" style="width: 80%;"><br>
<div class="x_content_title"><img
	src="<%=request.getContextPath()%>/images/window.png"
	alt="上传模板信息">&nbsp;上传模板信息
 
</div>
<br>
 
<table border=0 cellspacing=0 cellpadding=2>
	<tbody>
 
 		<tr class="x-content-hight">
			<td align="left" width="18%" align="left"><span>文件</span></td>
			<td align="left" width="82%">
			<input type="file" name="file" size="40" class="input-file x-text">
			<br> 注：模板文件必须是zip格式并且并且扩展名也必须是小写的.zip
			<br> index.html是首页模板，文件格式必须是UTF-8
			<br> list.html是列表页模板，文件格式必须是UTF-8
			<br> detail.html是详细页模板，文件格式必须是UTF-8
            </td>
		</tr>

	</tbody>
</table>
 

<div align="center">
<br />
<input type="button" class="btnGreen" value="确定"
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