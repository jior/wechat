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
<title>微页面发布</title>
<%@ include file="/WEB-INF/views/wx/inc/wx_styles.jsp"%>
<%@ include file="/WEB-INF/views/wx/inc/wx_scripts.jsp"%>
<script type="text/javascript">

    
	function loadData(url){
		  jQuery.get(url,{qq:'xx'},function(data){
		      //var text = JSON.stringify(data); 
              //alert(text);
			  jQuery('#mydatagrid').datagrid('loadData', data);
		  },'json');
	  }

 
    jQuery(function(){
		jQuery('#mydatagrid').datagrid({
				width:1000,
				height:480,
				fit:true,
				fitColumns:true,
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'<%=request.getContextPath()%>/mx/wx/wxContent/json/${accountId}?type=P&categoryId=${categoryId}',
				remoteSort: false,
				singleSelect:true,
				idField:'id',
				columns:[[
	                {title:'序号',field:'startIndex',width:80,sortable:false},
					{title:'封面', field:'cover', width:80, formatter:formatterCover},
					{title:'标题', field:'title', align:'left', width:180, formatter:formatterTitle},
					{title:'链接',field:'url', width:320},
					<c:if test="${type eq 'K'}">
					{title:'关键词', field:'keywords', align:'left', width:120},
					{title:'关键词数', field:'keywordsCount', align:'right', width:80},
					{title:'关键词匹配', field:'keywordsMatchType', align:'center', width:120, formatter:formatterMatchType},
					</c:if>
					{title:'顺序', field:'sort', align:'right', width:60},
					{title:'时间', field:'createDate', align:'center', width:90},
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

    function formatterCover(val, row){
        return "<img src='${contextPath}"+row.icon+"' width='18' height='18' border='0'>";
	}
		 
	function formatterStatus(val, row){
       if(val == 1){
			return '<span style="color:green; font: bold 13px 宋体;">发布</span>';
	   } else  {
			return '<span style="color:black; font: bold 13px 宋体;">未发布</span>';
	   }  
	}

	function formatterKeys(val, row){
		return "<a href='javascript:editRow("+row.id+");'>修改</a>&nbsp;<a href='javascript:deleteRow("+row.id+");'>删除</a>";
	}

	function formatterMatchType(val, row){
        if(val == "1"){
			return '<span style="background:green; color:white; font: bold 13px 宋体;">完全</span>';
	   } else  {
			return '<span style="background:green; color:white; font: bold 13px 宋体;">包含</span>';
	   }  
	}

	function formatterTitle(val, row){
        return "<a href='#' onclick='javascript:preview("+row.id+");'>"+val+"</a>";
	}

	function preview(id){
		var link = '<%=request.getContextPath()%>/website/wx/content/view/'+id;
	    //art.dialog.open(link, { height: 720, width: 400, title: "预览效果", lock: true, scrollbars:"no" }, false);
		var x=200;
		var y=150;
		var fx = "height=520,width=400,status=0,toolbar=no,menubar=no,location=no,scrollbars=yes,top="+y+",left="+x+",resizable=no,modal=yes,dependent=yes,dialog=yes,minimizable=no";
		if(jQuery.browser.msie){
			window.open(link,  "预览效果", fx);
		} else {
            window.open(link, self, fx, true);
		}
	}

	function editRow(rowId){
	    var link = '<%=request.getContextPath()%>/mx/wx/wxContent/edit?accountId=${accountId}&type=${type}&id='+rowId;
	    //art.dialog.open(link, { height: 420, width: 880, title: "修改记录", lock: true, scrollbars:"no" }, false);
		location.href=link;
	}

	function deleteRow(rowId){
		if(confirm("记录删除后不能恢复，确定删除吗？")){
		    jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/mx/wx/wxContent/delete?id='+rowId,
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
		var link = "<%=request.getContextPath()%>/mx/wx/wxContent/edit?accountId=${accountId}&type=P&categoryId=${categoryId}&fromUrl=${fromUrl}";
	    //art.dialog.open(link, { height: 420, width: 880, title: "添加记录", lock: true, scrollbars:"yes" }, false);
		location.href=link;
	}


	function addMedia(){
		var nodeId = jQuery("#nodeId").val();
		var link = "<%=request.getContextPath()%>/mx/wx/wxContent/editMedia?accountId=${accountId}&type=P&fromUrl=${fromUrl}&categoryId="+nodeId;
	    //art.dialog.open(link, { height: 420, width: 880, title: "添加记录", lock: true, scrollbars:"yes" }, false);
		location.href=link;
	}

	function onRowClick(rowIndex, row){
	    var link = '<%=request.getContextPath()%>/mx/wx/wxContent/edit?accountId=${accountId}&type=P&fromUrl=${fromUrl}&id='+row.id;
	    //art.dialog.open(link, { height: 420, width: 880, title: "修改记录", lock: true, scrollbars:"yes" }, false);
		location.href=link;
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
		  //location.href="<%=request.getContextPath()%>/mx/wx/wxContent?method=edit&rowId="+selected.id;
		  var link = "<%=request.getContextPath()%>/mx/wx/wxContent/edit?accountId=${accountId}&type=P&fromUrl=${fromUrl}&id="+selected.id;
		  //art.dialog.open(link, { height: 420, width: 780, title: "修改记录", lock: true, scrollbars:"yes" }, false);
		  location.href=link;
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
		    location.href="<%=request.getContextPath()%>/mx/wx/wxContent/edit?accountId=${accountId}&type=P&fromUrl=${fromUrl}&id="+selected.id;
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
				   url: '<%=request.getContextPath()%>/mx/wx/wxContent/delete?ids='+rowIds,
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
		    var title = document.getElementById("title").value.trim();
			document.getElementById("titleLike").value = title;
			var params = jQuery("#iForm").formSerialize();
            jQuery.ajax({
                        type: "POST",
                        url: '<%=request.getContextPath()%>/mx/wx/wxContent/json/${accountId}?type=${type}',
                        dataType:  'json',
						data: params,
                        error: function(data){
                                  alert('服务器处理错误！');
                        },
                        success: function(data){
                                  jQuery('#mydatagrid').datagrid('loadData', data);
                        }
                        });
	}

	function viewSite(rowId){
	    var link = '<%=request.getContextPath()%>/website/wx/content/index/${accountId}';
	    //art.dialog.open(link, { height: 720, width: 400, title: "预览效果", lock: true, scrollbars:"no" }, false);
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
<body style="margin:1px;">  
<input type="hidden" id="nodeId" name="nodeId" value="" >
<div class="easyui-layout" data-options="fit:true">  
   <div data-options="region:'center'">   
		<div class="easyui-layout" data-options="fit:true">  
		   <div data-options="region:'north',split:true,border:true" style="height:40px"> 
			<div class="toolbar-backgroud"  > 
			<img src="<%=request.getContextPath()%>/images/window.png">
			&nbsp;<span class="x_content_title">${category.name}内容列表</span>
			<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-add'" 
			   onclick="javascript:addNew();">新增</a> 
			<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-add'" 
			   onclick="javascript:addMedia();">新增多媒体</a>  
			<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-edit'"
			   onclick="javascript:editSelected();">修改</a>  
			<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-remove'"
			   onclick="javascript:deleteSelections();">删除</a>  
			<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-view'"
			   onclick="javascript:viewSite();">预览我的微网站</a> 
			<input id="title" name="title" type="text" 
	               class="x-searchtext" size="50" maxlength="200"/>
	        <a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-search'"
	           onclick="javascript:searchData();">查找</a>
			<c:if test="${from eq 'category' }">
			<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-back'"
			   onclick="javascript:window.history.go(-1);">返回</a>    
			</c:if>
		   </div> 
		  </div> 
		  <div data-options="region:'center',border:true">
			 <table id="mydatagrid"></table>
		  </div>  
      </div>
	</div>
</div>
<form id="iForm" name="iForm" method="post">
<input type="hidden" id="titleLike" name="titleLike">
</form>
</body>
</html>