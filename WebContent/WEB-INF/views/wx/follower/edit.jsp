<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*"%>
<%@ page import="com.glaf.core.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>关注用户</title>
<%@ include file="/WEB-INF/views/wx/inc/wx_styles.jsp"%>
<%@ include file="/WEB-INF/views/wx/inc/wx_scripts.jsp"%>
<script type="text/javascript">
    var contextPath="<%=request.getContextPath()%>";

	function saveData(){
		var params = jQuery("#iForm").formSerialize();
		jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/mx/wx/wxFollower/saveWxFollower',
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
					   }**/
					  // location.href='<%=com.glaf.core.util.RequestUtils.decodeURL(request.getParameter("fromUrl"))%>';
				   }
			 });
	}

	function saveAsData(){
		document.getElementById("id").value="";
		document.getElementById("id").value="";
		var params = jQuery("#iForm").formSerialize();
		jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/mx/wx/wxFollower/saveWxFollower',
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
					   //location.href='<%=com.glaf.core.util.RequestUtils.decodeURL(request.getParameter("fromUrl"))%>';
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
	<span class="x_content_title">编辑关注用户</span>
	<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-save'" 
	   onclick="javascript:saveData();" >保存</a> 
	<!-- <a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-back'" 
	   onclick="javascript:history.back();">返回</a> -->
    </div> 
  </div>

  <div data-options="region:'center',border:false,cache:true">
  <form id="iForm" name="iForm" method="post">
  <input type="hidden" id="accountId" name="accountId" value="${accountId}"/>
  <input type="hidden" id="sourceId" name="sourceId" value="${wxFollower.sourceId}"/>
  <input type="hidden" id="openId" name="openId" value="${wxFollower.openId}"/>
  <input type="hidden" id="id" name="id" value="${wxFollower.openId}"/>
  <table class="easyui-form" style="width:600px;" align="center">
    <tbody>
	<tr>
		<td width="20%" align="left">昵称</td>
		<td align="left">
            <input id="nickName" name="nickName" type="text"  readonly size="50"
			       class="easyui-validatebox  x-text readonly"  
				   value="${wxFollower.nickName}"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">省份</td>
		<td align="left">
            <input id="province" name="province" type="text"  readonly size="50"
			       class="easyui-validatebox  x-text readonly"  
				   value="${wxFollower.province}"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">城市</td>
		<td align="left">
            <input id="city" name="city" type="text" readonly size="50"
			       class="easyui-validatebox  x-text readonly"  
				   value="${wxFollower.city}"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">国家</td>
		<td align="left">
            <input id="country" name="country" type="text" readonly size="50"
			       class="easyui-validatebox  x-text readonly"  
				   value="${wxFollower.country}"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">语言</td>
		<td align="left">
            <input id="language" name="language" type="text" readonly size="50"
			       class="easyui-validatebox  x-text readonly"  
				   value="${wxFollower.language}"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">手机</td>
		<td align="left">
            <input id="mobile" name="mobile" type="text" size="50"
			       class="easyui-validatebox  x-text"  
				   value="${wxFollower.mobile}"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">邮件</td>
		<td align="left">
            <input id="mail" name="mail" type="text" size="50"
			       class="easyui-validatebox  x-text"  
				   value="${wxFollower.mail}"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">电话</td>
		<td align="left">
            <input id="telephone" name="telephone" type="text"  size="50"
			       class="easyui-validatebox  x-text"  
				   value="${wxFollower.telephone}"/>
		</td>
	</tr>

	<tr>
		<td width="20%" align="left">备注</td>
		<td align="left">
            <input id="remark" name="remark" type="text" size="50"
			       class="easyui-validatebox  x-text"  
				   value="${wxFollower.remark}"/>
		</td>
	</tr>
 
    </tbody>
  </table>
  </form>
</div>
</div>
</body>
</html>