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
    String theme = com.glaf.core.util.RequestUtils.getTheme(request);
    request.setAttribute("theme", theme);
	int index = 0;
%>
<!DOCTYPE html>
<html>
<head>
<title>投票</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css" />
<script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
<script type="text/javascript">
     var contextPath="<%=request.getContextPath()%>";

	function submitVote(){
		var result = jQuery("#result").val();
		jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/website/wx/vote/post/${vote.id}?result='+result,
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
    <div data-role="page">
        <div data-role="header" data-theme="b">
            <h1>${vote.title}</h1>
        </div>
        <div data-role="content">
            <div style="margin-left: auto; margin-right: auto; width: 90%;">
       
                <form action="<%=request.getContextPath()%>/website/wx/vote/post/${vote.id}" method="post">
				 <input type="hidden" id="result" name="result">
				 <fieldset data-role="controlgroup">          
                 <c:forEach items="${vote.items}" var="item">
					<input type="radio" id="result_<%=index%>" name="result_1"
						   onclick="jQuery('#result').val('${item.value}');"/>
					<label for="result_<%=index++%>">${item.name}</label>
                 </c:forEach>
				</fieldset>
                <p>
                    <input type="button" value="确认" data-role="button" data-icon="star" data-theme="b" 
					       onclick="javascript:submitVote();"/>
                </p>
                </form>

            </div>
        </div>
</body>
</html>
