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
	int index = 0;
%>
<!DOCTYPE html>
<html>
<head>
<title>反馈信息</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="/WEB-INF/views/wx/inc/wx_mobile_include.jsp" %>
<script type="text/javascript">
     var contextPath="<%=request.getContextPath()%>";

	function submitRequest(){
		if(document.getElementById("content").value.trim().length>2000){
			alert("意见内容超过2000字节！");
			document.getElementById("content").focus();
			return ;
		}
		var params = jQuery("#iForm").formSerialize();
		jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/website/wx/message/post/${accountId}',
				   dataType:  'json',
				   data: params,
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
    <div data-role="page">
        <div data-role="header" data-theme="b">
            <h1>反馈信息</h1>
        </div>
        <div data-role="content">
            <div style="margin-left: auto; margin-right: auto; width: 90%;">
       
                <form id="iForm" name="iForm" action="" method="post">
				<p>
                    <b>您的姓名：</b>
                </p>
                <p>
                    <input id="name" name="name" type="text" value="" maxlength="50" />
                </p>
                <p>
                    <b>您的手机：</b>
                </p>
                <p>
                    <input id="mobile" name="mobile" type="text" value="" maxlength="50"/>
                </p> 
				<p>
                    <b>反馈主题：</b>
                </p>
                <p>
                    <input id="title" name="title" type="text" value="" maxlength="200"/>
                </p> 
				<p>
                    <b>您的意见：</b>
                </p>
                <p>
                     <textarea id="content" name="content" cols="40" rows="8" name="textarea"></textarea>
                </p> 
                <p>
                    <input type="button" value="确认" data-role="button" data-icon="star" data-theme="b" 
					       onclick="javascript:submitRequest();"/>
                </p>
                </form>

            </div>
        </div>
</body>
</html>
