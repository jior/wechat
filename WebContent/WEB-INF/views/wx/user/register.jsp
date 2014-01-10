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
    String theme = com.glaf.core.util.RequestUtils.getTheme(request);
    request.setAttribute("theme", theme);
	List list = (List)request.getAttribute("depts");
    java.util.Random random = new java.util.Random();
	String rand = Math.abs(random.nextInt(999999))+com.glaf.core.util.UUID32.getUUID()+Math.abs(random.nextInt(999999));
	session = request.getSession(true);
	if (session != null) {
       session.setAttribute("x_y", rand);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
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

function regXY(){
   var buff = '{';
   if(jQuery("#actorId").val()==""){
       alert("请输入用户名");
	   document.getElementById("actorId").focus();
       return;
   }
   buff +='"x":"'+jQuery("#actorId").val()+'"';

   if(jQuery("#name").val()==""){
       alert("请输入姓名");
	   document.getElementById("name").focus();
       return;
   }

   buff +=',"name":"'+jQuery("#name").val()+'"';

   if(jQuery("#password").val()==""){
       alert("请输入密码");
	   document.getElementById("password").focus();
       return;
   }

   if(jQuery("#password").val()!=jQuery("#password2").val()){
       alert("密码与确认密码不一致，请重新输入");
	   document.getElementById("password2").focus();
       return;
   }

   buff +=',"y":"'+jQuery("#password").val()+'"';

   if(jQuery("#email").val()==""){
       alert("请输入邮件地址");
	   document.getElementById("email").focus();
       return;
   }

    if(!isEmail(jQuery("#email").val())){
       alert("请输入合法的邮件地址");
	   document.getElementById("email").focus();
       return;
   }

   buff +=',"email":"'+jQuery("#email").val()+'"';
   buff +=',"deptId":"'+jQuery("#deptId").val()+'"';
   buff +='}';
   //alert(buff);
   document.getElementById("json").value=buff;
   var params = jQuery("#iForm").formSerialize();
   jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/website/wx/auth/register',
				   dataType:  'json',
				   data: params,
				   error: function(data){
					   alert('服务器处理错误！');
				   },
				   success: function(data){
					   if(data != null && data.message != null){
						 alert(data.message);
						 if(data.status==200){
							 var x = jQuery("#actorId").val();
							 var y = jQuery("#password").val();
							 location.href="<%=context%>/mx/login/doLogin?x="+x+"&y=<%=rand%>"+y;
						 }
					   } 
				   }
			 });
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
	alt="填写个人资料">&nbsp;用户注册
</div>
<br>
<form id="iForm" name="iForm" method="post" > 
<input type="hidden" id="json" name="json">
  <table width="600" border="0" align="center" cellpadding="0" cellspacing="0" class="box">
      <tr>
        <td height="40">用户名*</td>
        <td>
		<input id="actorId" name="actorId" type="text" size="20" class="easyui-validatebox x-text" style="width:180px"  
		       data-options="required:true" datatype="string"  maxlength="20"
		       nullable="no" maxsize="50" chname="用户名">（只能包含数字，大小写字母及下划线）   
		</td>
      </tr>
	  <tr>
        <td height="40">姓　名*</td>
        <td>
		<input id="name" name="name" type="text" size="20" class="easyui-validatebox x-text" style="width:180px"  
		       data-options="required:true" datatype="string"  maxlength="20"
		       nullable="no" maxsize="50" chname="姓名">（可以包含数字，大小写字母及汉字）   
		</td>
      </tr>
      <tr>
        <td class="input-box2"  height="40">密　码*</td>
        <td align="left">
		<input id="password" name="password" type="password" size="20" datatype="string" nullable="no" 
		       minsize="6" maxsize="20" chname="密码" class="easyui-validatebox x-text" style="width:180px"   
			   data-options="required:true"  maxlength="20">（6至20个字符，可以使用数字，大小写字母）  
		</td>
      </tr>
      <tr>
        <td class="input-box2"  height="40">确认密码*</td>
        <td align="left">
		<input id="password2" name="password2" type="password" size="20"  datatype="string" nullable="no" 
		       minsize="6" maxsize="20" chname="确认密码" class="easyui-validatebox x-text" style="width:180px" 
			   data-options="required:true"  maxlength="20">（6至20个字符，可以使用数字，大小写字母）  
		</td>
      </tr>
      <tr>
        <td height="40">邮　件*</td>
        <td>
          <input id="email" name="email" type="text" size="20" class="easyui-validatebox x-text" style="width:180px"
		         data-options="required:true" datatype="email" 
		         nullable="no" maxsize="50" chname="邮件"  maxlength="50">（用于邮箱验证及密码重置等）        
		</td>
      </tr>
	  <tr>
        <td height="40">手　机</td>
        <td>
          <input id="mobile" name="mobile" type="text" size="20" class="easyui-validatebox x-text" style="width:180px"
		         data-options="required:false" datatype="string" 
		         nullable="yes" maxsize="50" chname="手机"  maxlength="20">（用于接收消息）         
		</td>
      </tr>
	  <tr>
        <td class="input-box">类　型</td>
        <td><select id="deptId" name="deptId" onChange="javascript:setValue(this);" class="input">
          <%
			if(list!=null){
			  Iterator iter=list.iterator();   
			  while(iter.hasNext()){
				SysDepartment bean2=(SysDepartment)iter.next();
			%>
				<option value="<%=bean2.getId()%>">
			<%
			    out.print(bean2.getName());
			%>
			   </option>
			<%    
			  }
			}
			%>
        </select>
		</td>
      </tr>

      <tr>
        <td class="input-box2"  height="30">&nbsp;</td>
        <td  align="left" height="40">&nbsp;
		   <br>
           <input name="btn_save2" type="button" value=" 确 定 " class="btnGreen" onclick="javascript:regXY();">
	    </td>
      </tr>

</table>
</form>

</body>
</html>
