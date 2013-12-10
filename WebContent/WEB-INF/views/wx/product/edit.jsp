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
<title>服务产品</title>
<%@ include file="/WEB-INF/views/wx/inc/wx_styles.jsp"%>
<%@ include file="/WEB-INF/views/wx/inc/wx_scripts.jsp"%>
<script type="text/javascript">
    var contextPath="<%=request.getContextPath()%>";

	function saveData(){
		var params = jQuery("#iForm").formSerialize();
		jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/mx/wx/wxProduct/saveWxProduct',
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
					   if (window.opener) {
						window.opener.location.reload();
					   } else if (window.parent) {
						window.parent.location.reload();
					   }
				   }
			 });
	}

	function saveAsData(){
		document.getElementById("id").value="";
		var params = jQuery("#iForm").formSerialize();
		jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/mx/wx/wxProduct/saveWxProduct',
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
	<span class="x_content_title">编辑服务产品</span>
	<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-save'" onclick="javascript:saveData();" >保存</a> 
    </div> 
  </div>

  <div data-options="region:'center',border:false,cache:true">
  <form id="iForm" name="iForm" method="post">
  <input type="hidden" id="id" name="id" value="${wxProduct.id}"/>
  <table class="easyui-form" style="width:100%" align="left">
    <tbody>
	<tr>
		<td width="20%" align="left">订购产品</td>
		<td align="left">
            <input id="name" name="name" type="text" size="50"
			       class="easyui-validatebox x-text" data-options="required:true" 
				   value="${wxProduct.name}"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">价格</td>
		<td align="left">
			<input id="price" name="price" type="text" data-options="required:true" size="50"
			       class="easyui-numberbox easyui-validatebox x-text"  precision="2" 
				   value="${wxProduct.price}"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">回复数(条)</td>
		<td align="left">
			<input id="newsNum" name="newsNum" type="text" data-options="required:true"
			       class="easyui-numberbox easyui-validatebox x-text"  size="50"
				   increment="10" precision="0"   
				   value="${wxProduct.newsNum}"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">分类数</td>
		<td align="left">
			<input id="categoryNum" name="categoryNum" type="text" 
			       class="easyui-numberbox easyui-validatebox x-text"  size="50"
				   increment="10"  data-options="required:true" precision="0" 
				   value="${wxProduct.categoryNum}"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">时长(天)</td>
		<td align="left">
			<input id="dayNum" name="dayNum" type="text" 
			       class="easyui-numberbox easyui-validatebox x-text"  size="50"
				   increment="10"  data-options="required:true" precision="0" 
				   value="${wxProduct.dayNum}"/>
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