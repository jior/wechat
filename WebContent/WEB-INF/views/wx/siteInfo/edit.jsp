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
<%
	String actorId = com.glaf.core.util.RequestUtils.getActorId(request);
	com.glaf.core.identity.User user = com.glaf.core.security.IdentityFactory.getUser(actorId);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网站基本信息</title>
<%@ include file="/WEB-INF/views/wx/inc/wx_styles.jsp"%>
<%@ include file="/WEB-INF/views/wx/inc/wx_scripts.jsp"%>
<script type="text/javascript">
    var contextPath="<%=request.getContextPath()%>";

	function saveData(){
		var params = jQuery("#iForm").formSerialize();
		jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/mx/wx/wxSiteInfo/saveWxSiteInfo',
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

	function saveAsData(){
		document.getElementById("id").value="";
		document.getElementById("id").value="";
		var params = jQuery("#iForm").formSerialize();
		jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/mx/wx/wxSiteInfo/saveWxSiteInfo',
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

	function goMicroSite(){
		var link=jQuery('#microUrl').val();
		var x=200;
		var y=150;
		var fx = "height=520,width=400,status=0,toolbar=no,menubar=no,location=no,scrollbars=yes,top="+y+",left="+x+",resizable=no,modal=yes,dependent=yes,dialog=yes,minimizable=no";
		if(jQuery.browser.msie){
			window.open(link,  "预览效果", fx);
		} else {
            window.open(link, self, fx, true);
		}
	}

</script>
</head>

<body>
<div style="margin:0;"></div>  

<div class="easyui-layout" data-options="fit:true">  
  <div data-options="region:'north',split:true,border:true" style="height:40px"> 
    <div class="toolbar-backgroud"> 
	<span class="x_content_title">网站基本信息</span>
	<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-save'" onclick="javascript:saveData();" >保存</a> 
    </div> 
  </div>

  <div data-options="region:'center',border:false,cache:true">
  <form id="iForm" name="iForm" method="post">
  <input type="hidden" id="id" name="id" value="${wxSiteInfo.id}"/>
  <input type="hidden" id="accountId" name="accountId" value="${accountId}"/>
  <table class="easyui-form" style="width:680px;" align="left">
    <tbody>
	<tr>
		<td width="20%" align="left">联系人</td>
		<td align="left">
            <input id="linkman" name="linkman" type="text" 
			       class="easyui-validatebox x-text"  size="80"
			       data-options="required:true"
				   value="${wxSiteInfo.linkman}"/>
		</td>
		<td width="10%" align="left">&nbsp;</td>
	</tr>
	<tr>
		<td width="20%" align="left">电话</td>
		<td align="left">
            <input id="telephone" name="telephone" type="text" 
			       class="easyui-validatebox x-text"  size="80"
				   value="${wxSiteInfo.telephone}"/>
		</td>
		<td width="10%" align="left">&nbsp;</td>
	</tr>
	<tr>
		<td width="20%" align="left">手机</td>
		<td align="left">
            <input id="mobile" name="mobile" type="text" 
			       class="easyui-validatebox x-text" size="80" 
			       data-options="required:true"
				   value="${wxSiteInfo.mobile}"/>
		</td>
		<td width="10%" align="left">&nbsp;</td>
	</tr>
	<tr>
		<td width="20%" align="left">电子邮件</td>
		<td align="left">
            <input id="mail" name="mail" type="text" 
			       class="easyui-validatebox x-text" size="80" 
			       data-options="required:true,validType:'email'"
				   value="${wxSiteInfo.mail}"/>
		</td>
		<td width="10%" align="left">&nbsp;</td>
	</tr>
	<tr>
		<td width="20%" align="left">QQ</td>
		<td align="left">
            <input id="qq" name="qq" type="text" 
			       class="easyui-validatebox x-text"  
			       size="80"
				   value="${wxSiteInfo.qq}"/>
		</td>
		<td width="10%" align="left">&nbsp;</td>
	</tr>
	<tr>
		<td width="20%" align="left">联系地址</td>
		<td align="left">
            <input id="address" name="address" type="text" 
			       class="easyui-validatebox x-text"  
			       size="80"
				   value="${wxSiteInfo.address}"/>
		</td>
		<td width="10%" align="left">&nbsp;</td>
	</tr>
	<tr>
		<td width="20%" align="left">网站地址</td>
		<td align="left">
            <input id="siteUrl" name="siteUrl" type="text" 
			       class="easyui-validatebox x-text"  
			       size="80"
				   value="${wxSiteInfo.siteUrl}"/>
		</td>
		<td width="10%" align="left">&nbsp;</td>
	</tr>
	<tr>
		<td width="20%" align="left">微站地址</td>
		<td align="left">
            <input id="microUrl" name="microUrl" type="text" 
			       class="easyui-validatebox x-text" size="80"
				   value="<%=com.glaf.wechat.util.WechatUtils.getServiceUrl(request)%>/website/wx/content/index/${accountId}"/> 
		</td>
		<td width="10%" align="left"><a href="#" title="进入微站" onclick="javascript:goMicroSite();">微站</a></td>
	</tr>
	<tr>
		<td width="20%" align="left">公众账号描述</td>
		<td align="left">
            <textarea id="remark" name="remark" class="x-textarea" style="width:480px;height:120px" />${wxSiteInfo.remark}</textarea>
		</td>
		<td width="10%" align="left">&nbsp;</td>
	</tr>
 
 	<tr>
	    <td width="20%" align="left"></td>
		<td align="left" ><br>
            <input type="button" value="保存配置" onclick="javascript:saveData();" class="btnGreen">
		</td>
		<td width="10%" align="left">&nbsp;</td>
	</tr>

    </tbody>
  </table>
  </form>
  <p></p>
</div>
</div>
</body>
</html>