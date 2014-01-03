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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件列表</title>
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
<script type="text/javascript">

    var contextPath="<%=request.getContextPath()%>";

    var setting = {
		async: {
			enable: true,
			url: getUrl,
			dataFilter: filter
		},
		callback: {
			onClick: zTreeOnClick
		}
	};
  

  	function filter(treeId, parentNode, childNodes) {
		if (!childNodes) return null;
		for (var i=0, l=childNodes.length; i<l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			childNodes[i].icon="<%=request.getContextPath()%>/images/folder.png";
		}
		return childNodes;
	}


	function getUrl(treeId, treeNode) {
		if(treeNode != null){
		    var param = "parentId="+treeNode.id;
		    return "<%=request.getContextPath()%>/rs/wx/category/treeJson/${accountId}?"+param;
		}
		return "<%=request.getContextPath()%>/rs/wx/category/treeJson/${accountId}?type=category";
	}


    function zTreeOnClick(event, treeId, treeNode, clickFlag) {
		jQuery("#path").val(treeNode.path);
		jQuery("#nodeId").val(treeNode.id);
		loadMxData('<%=request.getContextPath()%>/mx/wx/wxFile/json/${accountId}?categoryId='+treeNode.id);
	}

	function loadMxData(url){
		  jQuery.get(url+'&randnum='+Math.floor(Math.random()*1000000),{qq:'xx'},function(data){
		      //var text = JSON.stringify(data); 
              //alert(text);
			  jQuery('#mydatagrid').datagrid('loadData', data);
		  },'json');
	}

    jQuery(document).ready(function(){
			jQuery.fn.zTree.init(jQuery("#myTree"), setting);
	});

    jQuery(function(){
		jQuery('#mydatagrid').datagrid({
				width:1000,
				height:480,
				fit:true,
				fitColumns:true,
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'<%=request.getContextPath()%>/mx/wx/wxFile/json/${accountId}',
				remoteSort: false,
				singleSelect:true,
				idField:'id',
				columns:[[
	                {title:'序号',field:'startIndex',width:80,sortable:false},
					{title:'名称',field:'title',width:220,sortable:false},
					{title:'文件名',field:'filename', align:'center', valign:'middle', width:380,sortable:false, formatter:formatterUrl},
					{title:'路径',field:'path',width:180,sortable:false}
				]],
				rownumbers:false,
				pagination:true,
				pageSize: <%=com.glaf.core.util.Paging.DEFAULT_PAGE_SIZE%>,
				pagePosition: 'both',
				pageList: [10,15,20,25,30,40,50,100]
			});

			var p = jQuery('#mydatagrid').datagrid('getPager');
			jQuery(p).pagination({
				onBeforeRefresh:function(){
					//alert('before refresh');
				}
		    });
	});


	String.prototype.startsWith = function (substring) {   
		var reg = new RegExp("^" + substring);   
		return reg.test(this);
	}

	String.prototype.endsWith = function (substring) {  
		var reg = new RegExp(substring + "$"); 
		return reg.test(this);
	}

	function formatterUrl(val, row){
	  if(row.path.endsWith(".jpg") || row.path.endsWith(".jpeg") || row.path.endsWith(".gif") || row.path.endsWith(".png")){
          return "<img src='${contextPath}"+row.path+"' border='0'>";
	  } else {
		  return row.originalFilename;
	  }
	}

 
	function resize(){
		jQuery('#mydatagrid').datagrid('resize', {
			width:800,
			height:400
		});
	}

	function reloadGrid(){
	    jQuery('#mydatagrid').datagrid('reload');
	}

	function selectedFile(){
	    var selected = jQuery('#mydatagrid').datagrid('getSelected');
	    if (selected){
		    //alert(selected.code+":"+selected.name+":"+selected.addr+":"+selected.col4);
			var parent_window = getOpener();
			var x_elementId = parent_window.document.getElementById("${elementId}");
            var x_element_name = parent_window.document.getElementById("${elementName}");
			x_elementId.value=selected.path;
			x_element_name.value=selected.path;
			window.close();
	    }
	}

	function uploadFile(){
        var nodeId = jQuery("#nodeId").val();
		var link = "<%=request.getContextPath()%>/mx/wx/wxFile/edit?accountId=${accountId}&type=category&categoryId="+nodeId;
		window.location.href=link;
	}

	function reloadPic(){
		var type = jQuery('#type').val();
		$.getJSON("<%=request.getContextPath()%>/rs/wx/file/jsonArray?type="+type, function(data) {
                $("#pic_layer").empty(); //先清空标记中的内容
                var strHTML = ""; //初始化保存内容变量
				var i=0;
                $.each(data, function(InfoIndex, Info) { //遍历获取的数据
                    strHTML += "<img src='" +contextPath+ Info["filename"] + "' border='0' width='160' height='240' ";
                    strHTML += " onclick=javascript:chooseSysPic('"+Info["originalFilename"]+"'); >";
					i++;
					if(i%4 == 0){
						strHTML +="<br>";
					}
                });
				//alert(strHTML);
                $("#pic_layer").html(strHTML); //显示处理后的数据
          });
	}

	function chooseSysPic(filename){
        var parent_window = getOpener();
        var x_elementId = parent_window.document.getElementById("${elementId}");
        var x_element_name = parent_window.document.getElementById("${elementName}");
	    x_elementId.value=filename;
		x_element_name.value=filename;
		window.close();
	}

	function selectPic(type, index){
		var filename="";
		if(type == "orgi"){
           filename="/wx/images/pic"+index+".jpg";
		} if(type == "big"){
           filename="/wx/big/big.pic"+index+".jpg";
		} else if(type == "medium"){
           filename="/wx/medium/medium.pic"+index+".jpg";
		} else if(type == "small"){
           filename="/wx/small/small.pic"+index+".jpg";
		}
		//alert(filename);
		var parent_window = getOpener();
        var x_elementId = parent_window.document.getElementById("${elementId}");
        var x_element_name = parent_window.document.getElementById("${elementName}");
	    x_elementId.value=filename;
		x_element_name.value=filename;
		window.close();
	}
 
</script>
</head>
<body style="margin:1px;">  
<input type="hidden" id="nodeId" name="nodeId" value="" >
<div style="margin:0;"></div>  
<div class="easyui-tabs" style="width:825px;height:580px;">
	<div title="我的素材库" data-options="closable:false" style="padding:1px">
		<div class="easyui-layout" data-options="fit:true"> 
		    <div data-options="region:'north',split:true,border:true" style="height:40px"> 
			<div class="toolbar-backgroud"  > 
			    <img src="<%=request.getContextPath()%>/images/window.png">
				&nbsp;<span class="x_content_title">文件列表</span>
				<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-ok'"
				   onclick="javascript:selectedFile();">选择</a> 
				<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-reload'"
				   onclick="javascript:reloadGrid();">重载</a> 
				<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-upload'"
				   onclick="javascript:uploadFile();">上传</a> 
			</div> 
		</div> 
		   
		<div data-options="region:'west',split:true" style="width:195px;">
			<ul id="myTree" class="ztree"></ul>  
		</div>  

		<div data-options="region:'center',border:true">
			<table id="mydatagrid"></table>
		</div>  
			 
		</div>
    </div>

	<div title="系统内置图片" data-options="closable:false" style="padding:2px">
	    <select id="type" name="type" onchange="javascript:reloadPic();">
			<option value="sys_images">原始图片(960*640)</option>
			<option value="sys_big_images">较大图片(720*400)</option>
			<option value="sys_medium_images">中等图片(480*320)</option>
			<option value="sys_small_images">较小图片(240*160)</option>
	    </select>
		<div id="pic_layer" >
	      
		</div>

    </div>

</div>
<script type="text/javascript">
    reloadPic();
</script>
</body>
</html>
