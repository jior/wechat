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
<link href="<%=request.getContextPath()%>/scripts/layer/skin/layer.css"  />
<script rel="stylesheet" type="text/javascript" src="<%=request.getContextPath()%>/scripts/layer/layer.min.js"></script>
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
				url: '<%=request.getContextPath()%>/mx/wx/wxFollower/json?accountId=${accountId}&x_complex_query=${x_complex_query}',
				remoteSort: false,
				singleSelect: true,
				idField: 'id',
				columns:[[
				        {title:'序号', field:'startIndex', width:60, sortable:false},
					    {title:'昵称', field:'nickName', width:150, sortable:false},
					    {title:'国家', field:'country', width:90, sortable:false},
					    {title:'省份', field:'province', width:90, sortable:false},
					    {title:'城市', field:'city', width:90, sortable:false},
					    {title:'性别', field:'sex', align:'center', width:60, formatter:formatterSex},
					    {title:'OpenID', field:'openId', width:240, sortable:false},
					    {title:'关注时间', field:'subscribeTime_datetime', width:120, sortable:false},
					    {title:'取消关注时间', field:'unsubscribeTime_datetime', width:120, sortable:false}
				]],
				rownumbers: false,
				pagination: true,
				pageSize: <%=com.glaf.core.util.Paging.DEFAULT_PAGE_SIZE%>,
				pageList: [10,15,20,25,30,40,50,100,200,500,1000],
				pagePosition: 'both',
				onClickRow: onRowClick 
			});

			var p = jQuery('#mydatagrid').datagrid('getPager');
			jQuery(p).pagination({
				onBeforeRefresh:function(){
					//alert('before refresh');
				}
		    });
	});

	function editRow(link){
		jQuery.layer({
					type: 2,
					maxmin: true,
					shadeClose: true,
					title: "编辑信息",
					closeBtn: [0, true],
					shade: [0.8, '#000'],
					border: [10, 0.3, '#000'],
					offset: ['20px',''],
					fadeIn: 100,
					area: ['680px', (jQuery(window).height() - 150) +'px'],
					iframe: {src: link}
				});
		}
		
	function addNew(){
	    var link="<%=request.getContextPath()%>/mx/wx/wxFollower/edit?fromUrl=${fromUrl}&accountId=${accountId}";
	    //art.dialog.open(link, { height: 420, width: 680, title: "添加记录", lock: true, scrollbars:"no" }, false);
		//location.href=link;
		editRow(link);
	}

	function onRowClick(rowIndex, row){
	    var link = '<%=request.getContextPath()%>/mx/wx/wxFollower/edit?id='+row.id+'&fromUrl=${fromUrl}&accountId=${accountId}';
	    //art.dialog.open(link, { height: 420, width: 680, title: "修改记录", lock: true, scrollbars:"no" }, false);
		//location.href=link;
		editRow(link);
	}

	function formatterSex(val, row){
        if(val == "1"){
			return '男';
	   } else if(val == "2") {
			return '女';
	   } else{
		    return "未知";
	   }
	}

	function searchWin(){
	    jQuery('#dlg').dialog('open').dialog('setTitle','关注用户查询');
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
		  var link = '<%=request.getContextPath()%>/mx/wx/wxFollower/edit?id='+selected.id+'&fromUrl=${fromUrl}&accountId=${accountId}';
		  //art.dialog.open(link, { height: 420, width: 680, title: "修改记录", lock: true, scrollbars:"no" }, false);
		  //location.href=link;
		  editRow(link);
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
		    location.href='<%=request.getContextPath()%>/mx/wx/wxFollower/edit?readonly=true&id='+selected.id+'&fromUrl=${fromUrl}&accountId=${accountId}';
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
		    var str = ids.join(',');
			jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/mx/wx/wxFollower/delete?ids='+str,
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

	function loadGridData(url){
        jQuery.post(url,{qq:'xx'},function(data){
            //var text = JSON.stringify(data); 
            //alert(text);
            jQuery('#mydatagrid').datagrid('loadData', data);
        },'json');
	}

	function searchData(){
		var params = jQuery("#searchForm").formSerialize();
		jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/mx/wx/wxFollower/json?accountId=${accountId}',
				   data: params,
				   dataType:  'json',
				   error: function(data){
					   alert('服务器处理错误！');
				   },
				   success: function(data){
					   jQuery('#mydatagrid').datagrid('loadData', data);
				   }
	    });
	}

	function doSearch(){
		document.searchForm.method="POST";
		document.searchForm.action="<%=request.getContextPath()%>/mx/wx/wxFollower?accountId=${accountId}&x_query=true";
        document.searchForm.submit();
	}

	function fetchFollower(type){
		if(confirm("确定从服务器获取关注者列表吗？")){
            jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/mx/wx/wxFollower/fetchFollower?accountId=${accountId}&type='+type,
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
					   window.location.reload();
				   }
			 });
		}
	}

	function fetchSingleFollower(type){
		if(confirm("确定从服务器获取关注者列表吗？")){
            jQuery.ajax({
				   type: "POST",
				   url: '<%=request.getContextPath()%>/mx/wx/wxFollower/fetchSingleFollower?accountId=${accountId}&type='+type,
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
					   window.location.reload();
				   }
			 });
		}
	}
		 
</script>
</head>
<body style="margin:1px;">  
<div style="margin:0;"></div>  
<div class="easyui-layout" data-options="fit:true">  
   <div data-options="region:'north',split:true,border:true" style="height:40px"> 
    <div class="toolbar-backgroud"  > 
	<img src="<%=request.getContextPath()%>/images/window.png">
	&nbsp;<span class="x_content_title">关注用户列表</span>
    <a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-add'" 
	   onclick="javascript:fetchFollower('weixin');">从微信服务器同步</a> 
	<!-- <a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-add'" 
	   onclick="javascript:fetchSingleFollower('weixin');">从服务器获取不完整用户列表</a> --> 
    <a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-edit'"
	   onclick="javascript:editSelected();">修改</a>  
	<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-remove'"
	   onclick="javascript:deleteSelections();">删除</a> 
	<a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-search'"
	   onclick="javascript:searchWin();">查找</a>
   </div> 
  </div> 
  <div data-options="region:'center',border:true">
	 <table id="mydatagrid"></table>
  </div>  
</div>
 
<div id="dlg" class="easyui-dialog" style="width:420px;height:380px;padding:10px 20px"
	closed="true" buttons="#dlg-buttons">
  <form id="searchForm" name="searchForm" method="post">
  <table class="easyui-form" >
    <tbody>
    <tr>
	  <td>省份</td>
	  <td>
        <input id="province" name="province" class="easyui-validatebox" type="text"></input>
      </td>
     </tr>
    <tr>
	  <td>城市</td>
	  <td>
        <input id="city" name="city" class="easyui-validatebox" type="text"></input>
      </td>
     </tr>
    <tr>
	  <td>国家</td>
	  <td>
        <input id="country" name="country" class="easyui-validatebox" type="text"></input>
      </td>
     </tr>
    <tr>
	    <td>语言</td>
	    <td>
          <input id="language" name="language" class="easyui-validatebox" type="text"></input>
         </td>
    </tr>
	<tr>
	<td>昵称</td>
	  <td>
        <input id="nickNameLike" name="nickNameLike" class="easyui-validatebox" type="text"></input>
      </td>
     </tr>
	 <tr>
	  <td>性别</td>
	  <td>
         <select name="sex">
			<option value="" selected>----不限----</option>
			<option value="1">男</option>
			<option value="2">女</option>
			<option value="0">未知</option>
         </select>
      </td>
    </tr>
	<tr>
	  <td>关注年度</td>
	  <td>
         <select id="subscribeYear" name="subscribeYear">
			<option value="" selected>----请选择----</option>
			<option value="2013">2013</option>
			<option value="2014">2014</option>
			<option value="2015">2015</option>
         </select>&nbsp;
		 <select id="subscribeMonth" name="subscribeMonth">
			<option value="" selected>----请选择----</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
         </select>&nbsp; 
		 （仅某年）
      </td>
    </tr>
	<tr>
	  <td>关注年度</td>
	  <td>
         <select id="subscribeYearGreaterThanOrEqual" name="subscribeYearGreaterThanOrEqual">
			<option value="" selected>----请选择----</option>
			<option value="2013">2013</option>
			<option value="2014">2014</option>
			<option value="2015">2015</option>
         </select>&nbsp;
		 <select id="subscribeMonthGreaterThanOrEqual" name="subscribeMonthGreaterThanOrEqual">
			<option value="" selected>----请选择----</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
         </select>
		 &nbsp;（大于某年）
      </td>
    </tr>
    <tr>
	<td>备注</td>
	<td>
        <input id="remarkLike" name="remarkLike" class="easyui-validatebox" type="text"></input>
       </td>
     </tr>
      </tbody>
    </table>
  </form>
</div>
<div id="dlg-buttons">
	<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:doSearch();">查询</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:jQuery('#dlg').dialog('close')">取消</a>
</div>
</body>
</html>
