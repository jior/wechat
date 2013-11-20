<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
    String actorId = com.glaf.core.util.RequestUtils.getActorId(request);
	com.glaf.core.identity.User user = com.glaf.core.security.IdentityFactory.getUser(actorId);
	
	if(user.getAccountType() == 2){
%>
	<script type="text/javascript">
		location.href="<%=request.getContextPath()%>/mx/wechat/main";
	</script>
<%}else{%>
	<script type="text/javascript">
		location.href="<%=request.getContextPath()%>/mx/my/main";
	</script>
<%}%>