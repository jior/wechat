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
<title>首页幻灯片</title>
<%@ include file="/WEB-INF/views/wx/inc/wx_styles.jsp"%>
<%@ include file="/WEB-INF/views/wx/inc/wx_scripts.jsp"%>
<script type="text/javascript">
   var contextPath="<%=request.getContextPath()%>";

   jQuery(function(){
		jQuery('#mydatagrid').datagrid({
				width:1000,
				height:480,
				fit:true,
				fitColumns: true,
				nowrap: false,
				striped: true,
				collapsible: true,
				url: '<%=request.getContextPath()%>/mx/wx/wxContent/json/${accountId}?type=PPT',
				remoteSort: false,
				singleSelect: true,
				idField: 'id',
				columns:[[
				    {title:'序号', field:'startIndex', width:80, sortable:false},
					{title:'图片', field:'icon', width:180, formatter:formatterCover},
					{title:'标题', field:'title', width:220, formatter:formatterTitle},
					{title:'跳转地址', field:'url', width:180, formatter:formatterLink},
					{title:'排序', field:'sort', width:80},
					{title:'状态', field:'status', width:90, formatter:formatterStatus},
					{title:'功能键', field:'functionKey', width:90, formatter:formatterKeys}
				]],
				rownumbers: false,
				pagination: true,
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
        return "<img src='${contextPath}"+row.icon+"' width='180' height='180' border='0'>";
	}


	function formatterTitle(val, row){
		return "<label title='"+val+"'>"+val+"</label>";
	}

	function formatterLink(val, row){
       return "<a href='"+val+"' target='blank'>"+val+"</a>";
	}

	function formatterStatus(val, row){
       if(val == 1){
			return '<span style="color:green; font: bold 13px 宋体;">发布</span>';
	   } else  {
			return '<span style="color:red; font: bold 13px 宋体;">未发布</span>';
	   }  
	}

	function formatterKeys(val, row){
		return "<a href='javascript:editRow("+row.id+");'>修改</a>&nbsp;<a href='javascript:deleteRow("+row.id+");'>删除</a>";
	}


	function editRow(rowId){
	    var link = '<%=request.getContextPath()%>/mx/wx/wxContent/editPPT?accountId=${accountId}&type=${type}&fromUrl=${fromUrl}&id='+rowId;
		location.href=link;
	    //art.dialog.open(link, { height: 420, width: 780, title: "修改记录", lock: true, scrollbars:"no" }, false);
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
	    //location.href="<%=request.getContextPath()%>/wx/wxContent/edit";
	    var link="<%=request.getContextPath()%>/mx/wx/wxContent/editPPT?accountId=${accountId}&type=${type}&fromUrl=${fromUrl}";
		location.href=link;
	    //art.dialog.open(link, { height: 420, width: 780, title: "添加记录", lock: true, scrollbars:"no" }, false);
	}

	function onRowClick(rowIndex, row){
        //window.open('<%=request.getContextPath()%>/wx/wxContent/edit?id='+row.id);
	    var link = '<%=request.getContextPath()%>/mx/wx/wxContent/editPPT?accountId=${accountId}&type=${type}&fromUrl=${fromUrl}&id='+row.id;
		location.href=link;
	    //art.dialog.open(link, { height: 420, width: 780, title: "修改记录", lock: true, scrollbars:"no" }, false);
	}

	function searchWin(){
	    jQuery('#dlg').dialog('open').dialog('setTitle','查询');
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
		//location.href="<%=request.getContextPath()%>/wx/wxContent/edit?id="+selected.id;
		  var link = "<%=request.getContextPath()%>/mx/wx/wxContent/editPPT?accountId=${accountId}&type=${type}&fromUrl=${fromUrl}&id="+selected.id;
		  //art.dialog.open(link, { height: 420, width: 780, title: "修改记录", lock: true, scrollbars:"no" }, false);
		  location.href=link;
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
		    location.href="<%=request.getContextPath()%>/mx/wx/wxContent/edit?accountId=${accountId}&type=${type}&fromUrl=${fromUrl}&id="+selected.id;
		}
	}

	function deleteSelections(){
		var ids = [];
		var rows = jQuery('#mydatagrid').datagrid('getSelections');
		for(var i=0;i<rows.length;i++){
			ids.push(rows[i].id);
		}
		if(ids.length > 0 && confirm("数据删除后不能恢复，确定删除吗？")){
		    var str = ids.join(',');
			jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/mx/wx/wxContent/delete?ids='+str,
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

	function loadGridData(url){
            jQuery.post(url,{qq:'xx'},function(data){
            //var text = JSON.stringify(data); 
            //alert(text);
            jQuery('#mydatagrid').datagrid('loadData', data);
            },'json');
	  }

	function searchData(){
        var title = document.getElementById("title").value.trim();
		document.getElementById("titleLike").value = title;
		var params = jQuery("#iForm").formSerialize();
        jQuery.ajax({
                      type: "POST",
                      url: '<%=request.getContextPath()%>/mx/wx/wxContent/json/${accountId}?type=PPT',
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
		 
</script>
</head>
<body style="margin:1px;">  
<div style="margin:0;"></div>  
<div class="easyui-layout" data-options="fit:true">  
   <div data-options="region:'north',split:true,border:true" style="height:40px"> 
    <div class="toolbar-backgroud"  > 
	<img src="<%=request.getContextPath()%>/images/window.png">
	&nbsp;<span class="x_content_title">首页幻灯片</span>
    <a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-add'" 
	   onclick="javascript:addNew();">新增</a>  
    <a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-edit'"
	   onclick="javascript:editSelected();">修改</a>  
	<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-remove'"
	   onclick="javascript:deleteSelections();">删除</a> 
	<input id="title" name="title" type="text" 
	       class="x-searchtext" size="50" maxlength="200"/>
	<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-search'"
	   onclick="javascript:searchData();">查找</a>
   </div> 
  </div> 
  <div data-options="region:'center',border:true">
	 <table id="mydatagrid"></table>
  </div>  
</div>
<form id="iForm" name="iForm" method="post">
<input type="hidden" id="titleLike" name="titleLike">
</form>  
</body>
</html>
