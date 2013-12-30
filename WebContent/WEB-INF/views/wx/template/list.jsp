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
<title>模板管理</title>
<%@ include file="/WEB-INF/views/wx/inc/wx_styles.jsp"%>
<%@ include file="/WEB-INF/views/wx/inc/wx_scripts.jsp"%>
<script type="text/javascript">

    var prevTreeNode;

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
			childNodes[i].icon="<%=request.getContextPath()%>/icons/icons/basic.gif";
		}
		return childNodes;
	}

 
    function getUrl(treeId, treeNode) {
		if(treeNode != null){
		    var param = "parentId="+treeNode.id;
		    return "<%=request.getContextPath()%>/mx/wx/wxCategory/treeJson/${accountId}?"+param;
		}
		return "<%=request.getContextPath()%>/mx/wx/wxCategory/treeJson/${accountId}?type=category";
	}


    function zTreeOnClick(event, treeId, treeNode, clickFlag) {
		jQuery("#nodeId").val(treeNode.id);
		loadData('<%=request.getContextPath()%>/mx/wx/wxTemplate/json/${accountId}?categoryId='+treeNode.id);
	}

	function loadData(url){
		  jQuery.get(url,{qq:'xx'},function(data){
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
				url:'<%=request.getContextPath()%>/mx/wx/wxTemplate/json/${accountId}',
				remoteSort: false,
				singleSelect:true,
				idField:'id',
				columns:[[
	                {title:'序号',field:'startIndex',width:80,sortable:false},
					{title:'名称',field:'name', width:120},
					{title:'类型',field:'type', width:120, formatter:formatterType},
					{title:'描述',field:'desc', width:180},
					{title:'链接',field:'url', width:320},
					{title:'是否有效',field:'locked', width:90, formatter:formatterStatus},
					{title:'功能键', field:'functionKey', width:90, formatter:formatterKeys}
				]],
				rownumbers:false,
				pagination:true,
				pageSize: <%=com.glaf.core.util.Paging.DEFAULT_PAGE_SIZE%>,
				pageList: [10,15,20,25,30,40,50,100],
				pagePosition: 'both',
				onDblClickRow: onRowClick 
			});

			var p = jQuery('#mydatagrid').datagrid('getPager');
			jQuery(p).pagination({
				onBeforeRefresh:function(){
					//alert('before refresh');
				}
		    });
	});

    function formatterType(val, row){
       if(val == 0){
			return '<span style="color:blue; font: bold 13px 宋体;">首页</span>';
	   } else if(val == 1){
			return '<span style="color:blue; font: bold 13px 宋体;">列表页</span>';
	   } else  {
			return '<span style="color:blue; font: bold 13px 宋体;">详细页</span>';
	   }  
	}
		 
	function formatterStatus(val, row){
       if(val == 0){
			return '<span style="color:green; font: bold 13px 宋体;">是</span>';
	   } else  {
			return '<span style="color:red; font: bold 13px 宋体;">否</span>';
	   }  
	}

	function formatterKeys(val, row){
		return "<a href='javascript:editRow("+row.id+");'>修改</a>&nbsp;<a href='javascript:deleteRow("+row.id+");'>删除</a>";
	}


	function editRow(rowId){
	    var link = '<%=request.getContextPath()%>/mx/wx/wxTemplate/edit?accountId=${accountId}&type=${type}&id='+rowId;
	    art.dialog.open(link, { height: 420, width: 780, title: "修改记录", lock: true, scrollbars:"no" }, false);
	}

	function deleteRow(rowId){
		if(confirm("记录删除后不能恢复，确定删除吗？")){
		    jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/mx/wx/wxTemplate/delete?id='+rowId,
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
					   jQuery('#mydatagrid').datagrid('reload');
				   }
			 });
		}
	}


	function addNew(){
		var nodeId = jQuery("#nodeId").val();
		var link = "<%=request.getContextPath()%>/mx/wx/wxTemplate/edit?accountId=${accountId}&type=category&categoryId="+nodeId;
	    art.dialog.open(link, { height: 420, width: 680, title: "添加记录", lock: true, scrollbars:"yes" }, false);
	}

	function onRowClick(rowIndex, row){
	    var link = '<%=request.getContextPath()%>/mx/wx/wxTemplate/edit?accountId=${accountId}&type=category&id='+row.id;
	    art.dialog.open(link, { height: 420, width: 680, title: "修改记录", lock: true, scrollbars:"yes" }, false);
	}

	function searchWin(){
	    jQuery('#dlg').dialog('open').dialog('setTitle','模板查询');
	    //jQuery('#searchForm').form('clear');
	}

	function resize(){
		jQuery('#mydatagrid').datagrid('resize', {
			width:800,
			height:400
		});
	}

	function editSelected(){
	    var rows = jQuery('#mydatagrid').datagrid('getSelections');
	    if(rows == null || rows.length !=1){
		alert("请选择其中一条记录。");
		return;
	    }
	    var selected = jQuery('#mydatagrid').datagrid('getSelected');
	    if (selected ){
		//location.href="<%=request.getContextPath()%>/mx/wx/wxTemplate?method=edit&rowId="+selected.id;
		var link = "<%=request.getContextPath()%>/mx/wx/wxTemplate/edit?accountId=${accountId}&type=category&id="+selected.id;
		art.dialog.open(link, { height: 420, width: 680, title: "修改记录", lock: true, scrollbars:"yes" }, false);
	    }
	}

	function functionSettings(){
		var rows = jQuery('#mydatagrid').datagrid('getSelections');
	    if(rows == null || rows.length !=1){
		  alert("请选择其中一条记录。");
		  return;
	    }
	    var selected = jQuery('#mydatagrid').datagrid('getSelected');
	    if (selected ){
		  
	    }
	}

	function viewSelected(){
		var rows = jQuery('#mydatagrid').datagrid('getSelections');
		if(rows == null || rows.length !=1){
			alert("请选择其中一条记录。");
			return;
		}
		var selected = jQuery('#mydatagrid').datagrid('getSelected');
		if (selected ){
		    location.href="<%=request.getContextPath()%>/mx/wx/wxTemplate/edit?accountId=${accountId}&type=category&id="+selected.id;
		}
	}

	function deleteSelections(){
		var ids = [];
		var rows = jQuery('#mydatagrid').datagrid('getSelections');
		for(var i=0;i<rows.length;i++){
			ids.push(rows[i].id);
		}
		if(ids.length > 0 ){
		  if(confirm("数据删除后不能恢复，确定删除吗？")){
		    var rowIds = ids.join(',');
			jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/mx/wx/wxTemplate/delete?ids='+rowIds,
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
					   jQuery('#mydatagrid').datagrid('reload');
				   }
			 });
		  }
		} else {
			alert("请选择至少一条记录。");
		}
	}

	function reloadGrid(){
	    jQuery('#mydatagrid').datagrid('reload');
	}

	function getSelected(){
	    var selected = jQuery('#mydatagrid').datagrid('getSelected');
	    if (selected){
		alert(selected.code+":"+selected.name+":"+selected.addr+":"+selected.col4);
	    }
	}

	function getSelections(){
	    var ids = [];
	    var rows = jQuery('#mydatagrid').datagrid('getSelections');
	    for(var i=0;i<rows.length;i++){
		ids.push(rows[i].code);
	    }
	    alert(ids.join(':'));
	}

	function clearSelections(){
	    jQuery('#mydatagrid').datagrid('clearSelections');
	}

	function searchData(){
	    var params = jQuery("#searchForm").formSerialize();
	    var queryParams = jQuery('#mydatagrid').datagrid('options').queryParams;
	    jQuery('#mydatagrid').datagrid('reload');	
	    jQuery('#dlg').dialog('close');
	}

	function showUpload(){
		var nodeId = jQuery("#nodeId").val();
		var link = "<%=request.getContextPath()%>/mx/wx/wxTemplate/showUpload/${accountId}?type=category&categoryId="+nodeId;
	    art.dialog.open(link, { height: 420, width: 680, title: "上传模板", lock: true, scrollbars:"yes" }, false);
	}
		 
</script>
</head>
<body style="margin:1px;">  
<input type="hidden" id="nodeId" name="nodeId" value="" >
<div class="easyui-layout" data-options="fit:true">  
    <div data-options="region:'west',split:true" style="width:195px;">
	  <div class="easyui-layout" data-options="fit:true">  
           
			 <div data-options="region:'center',border:false">
			    <ul id="myTree" class="ztree"></ul>  
			 </div> 
			 
        </div>  
	</div> 
   <div data-options="region:'center'">   
		<div class="easyui-layout" data-options="fit:true">  
		   <div data-options="region:'north',split:true,border:true" style="height:40px"> 
			<div class="toolbar-backgroud"  > 
			<img src="<%=request.getContextPath()%>/images/window.png">
			&nbsp;<span class="x_content_title">模板列表</span>
			<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-add'" 
			   onclick="javascript:addNew();">新增</a>  
			<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-add'" 
			   onclick="javascript:showUpload();">上传模板</a>  
			<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-edit'"
			   onclick="javascript:editSelected();">修改</a>  
			<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-remove'"
			   onclick="javascript:deleteSelections();">删除</a>  
		   </div> 
		  </div> 
		  <div data-options="region:'center',border:true">
			 <table id="mydatagrid"></table>
		  </div>  
      </div>
	</div>
</div>
<div id="edit_dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
	closed="true" buttons="#dlg-buttons">
    <form id="editForm" name="editForm" method="post">
         
    </form>
</div>
<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
	closed="true" buttons="#dlg-buttons">
    <form id="searchForm" name="searchForm" method="post">
	<table class="easyui-form" >
            <tbody>
	    </tbody>
        </table>
    </form>
</div>
 
</body>
</html>
