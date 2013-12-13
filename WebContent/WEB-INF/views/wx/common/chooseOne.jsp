<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	request.setAttribute("contextPath", request.getContextPath());
	String actorId = com.glaf.core.util.RequestUtils.getActorId(request);
	com.glaf.core.identity.User user = com.glaf.core.security.IdentityFactory.getUser(actorId);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微站内容</title>
<%
    String theme = com.glaf.core.util.RequestUtils.getTheme(request);
    request.setAttribute("theme", theme);
%> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/artDialog/skins/default.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/easyui/themes/${theme}/easyui.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/ztree/css/zTreeStyle/zTreeStyle.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/themes/${theme}/styles.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/icons/styles.css"/>
<%@ include file="/WEB-INF/views/wx/inc/wx_scripts.jsp"%>
<style>

.btnGreen {
	padding: 5px 20px; border-radius: 3px; border: 1px solid rgb(61, 129, 12); text-align: center; color: rgb(255, 255, 255); overflow: visible; display: inline-block; cursor: pointer; background-color: rgb(90, 170, 75); -moz-border-radius: 3px; -webkit-border-radius: 3px;
}
.btnGreen:hover {
	color: rgb(255, 255, 255); box-shadow: 0px 1px 1px #aaa; background-color: rgb(83, 160, 70); -moz-box-shadow: 0 1px 1px #aaa; -webkit-box-shadow: 0 1px 1px #aaa;
}
.btnGreenS {
	margin: 0px; padding: 3px 5px; border-radius: 3px; border: 1px solid rgb(61, 129, 12); text-align: center; color: rgb(255, 255, 255); overflow: visible; display: inline-block; cursor: pointer; background-color: rgb(90, 170, 75); -moz-border-radius: 3px; -webkit-border-radius: 3px;
}
.btnGreenS:hover {
	color: rgb(255, 255, 255); box-shadow: 0px 1px 1px #aaa; background-color: rgb(83, 160, 70); -moz-box-shadow: 0 1px 1px #aaa; -webkit-box-shadow: 0 1px 1px #aaa;
}
.btnGreenS:hover {
	color: rgb(255, 255, 255); box-shadow: 0px 1px 1px #aaa; background-color: rgb(83, 160, 70); -moz-box-shadow: 0 1px 1px #aaa; -webkit-box-shadow: 0 1px 1px #aaa;
}
.btnGreenS#saveSetting {
	margin-left: 105px;
}

.x-text {
	background-color:#fff;border:1px solid #d3d3d3;color:#666; padding:2px 2px; line-height:24px ; height:24px;font-size: 13px;
} 

</style>
<script type="text/javascript">

    var setting = {
			async: {
				enable: true,
				url: "<%=request.getContextPath()%>/mx/wx/wxContent/treeJson/${accountId}?selecteds=${selecteds}&type=${type}&accountId=${accountId}",
                dataFilter: filter
			},
			check: {
				enable: true,
			    chkStyle: "radio",
				radioType: "all",
				chkboxType: { "Y": "s", "N": "s" } 
			}
		};


    function filter(treeId, parentNode, childNodes) {
		if (!childNodes) return null;
		for (var i=0, l=childNodes.length; i<l; i++) {
            if(childNodes[i].cls='tree_folder'){
			    childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			    childNodes[i].icon="<%=request.getContextPath()%>/images/folder.png";
			} else {
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			    childNodes[i].icon="<%=request.getContextPath()%>/images/details.gif";
			}
		}
		return childNodes;
	}


    jQuery(document).ready(function(){
		jQuery.fn.zTree.init(jQuery("#myTree"), setting);
	});


	function chooseMyTreeData(){
		var zTree = $.fn.zTree.getZTreeObj("myTree");
        var selectedNodes  = zTree.getCheckedNodes(true);

		if(selectedNodes.length>1){
			alert("只能选择其中一项，请重新选择！");
			return;
		}

		if(selectedNodes[0].cls == 'tree_folder'){
			alert("您选择的是栏目，只能选择文章，请重新选择！");
			return;
		}

        var sx = '';
		var sx_name = '';
		var code='';
		var name='';
        for(var i=0; i<selectedNodes.length; i++){ 
            if (sx != ''){ 
				sx += ','; 
			}
			if (sx_name != ''){ 
				sx_name += ','; 
			}
			if(selectedNodes[i].id){
			  sx += "/website/wx/content/view/"+selectedNodes[i].id; 
              sx_name += "/website/wx/content/view/"+selectedNodes[i].id; 
			}
        }  

        //alert(sx);

		var parent_window = getOpener();
	    var x_elementId = parent_window.document.getElementById("${elementId}");
        var x_element_name = parent_window.document.getElementById("${elementName}");
		x_elementId.value=sx;
		x_element_name.value=sx_name;
		
		window.close(); 
	}

	function chooseMyHomeData(){
		var parent_window = getOpener();
	    var x_elementId = parent_window.document.getElementById("${elementId}");
        var x_element_name = parent_window.document.getElementById("${elementName}");
		x_elementId.value='/website/wx/content/index/${accountId}';
		x_element_name.value='/website/wx/content/index/${accountId}';
		window.close(); 
	}

</script>
<style type="text/css">
.ztree li span.button.tree_folder_ico_open{margin-right:2px; background: url(${contextPath}/icons/icons/folder-open.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li span.button.tree_folder_ico_close{margin-right:2px; background: url(${contextPath}/icons/icons/folder.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li span.button.tree_folder_ico_docu{margin-right:2px; background: url(${contextPath}/icons/icons/folder.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}

.ztree li span.button.tree_leaf_ico_open{margin-right:2px; background: url(${contextPath}/images/details.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li span.button.tree_leaf_ico_close{margin-right:2px; background: url(${contextPath}/images/details.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li span.button.tree_leaf_ico_docu{margin-right:2px; background: url(${contextPath}/images/details.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
</style>
</head>

<body>

<div style="margin:0;"></div>  

<div class="easyui-layout" data-options="fit:true">  
  
  <div data-options="region:'center',border:false,cache:true">
  <form id="iForm" name="iForm" method="post">
    <div class="easyui-tabs" style="width:680px;height:350px">
	   <div title="微站内容" data-options="closable:false" style="padding:10px">
	    <input type="button" value=" 确定 " onclick="javascript:chooseMyTreeData();" class="btnGreen">
		<br>
	    <ul id="myTree" class="ztree"></ul>
		<br>
		<input type="button" value=" 确定 " onclick="javascript:chooseMyTreeData();" class="btnGreen">
		<br>
	   </div>
	   <div title="微站首页" data-options="closable:false" style="padding:10px">
	     <input id="microUrl" name="microUrl" type="text" 
			    class="easyui-validatebox x-text" size="80"
				value="<%=com.glaf.wechat.util.WechatUtils.getServiceUrl(request)%>/website/wx/content/index/<%=user.getId()%>"/>
		  <br>
		  <br>
		  <input type="button" value=" 确定 " onclick="javascript:chooseMyHomeData();" class="btnGreen">
		  <br>
	   </div>
	</div>
  </form>
</div>
</div>
<script type="text/javascript">
  	
</script>

</body>
</html>