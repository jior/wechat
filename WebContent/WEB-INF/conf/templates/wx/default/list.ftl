<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>列表信息</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta charset="utf-8">
<link href="${serviceUrl}/templates/01/css/news4.css" rel="stylesheet" type="text/css" />
</head>
<script>
window.onload = function ()
{
var oWin = document.getElementById("win");
var oLay = document.getElementById("overlay");	
var oBtn = document.getElementById("popmenu");
var oClose = document.getElementById("close");
oBtn.onclick = function ()
{
oLay.style.display = "block";
oWin.style.display = "block"	
};
oLay.onclick = function ()
{
oLay.style.display = "none";
oWin.style.display = "none"	
}
};
</script>
<body id="listhome1">

<div id="ui-header">
<div class="fixed">
<a class="ui-title" id="popmenu">选择分类</a>
<a class="ui-btn-left_pre" href="${serviceUrl}/website/wx/content/index/${userId}"></a>
<a class="ui-btn-right" href="${serviceUrl}/website/wx/content/list/${category.id?string('####')}"></a>
</div>
</div>
<div id="overlay"></div>
<div id="win">
<ul class="dropdown"> 
<#if categories?exists>
<#list categories as item>
<#if item.url?exists && item.url != ''>
<li><a href="${item.url}"><span>${item.name}</span></a></li>
<#else>
<li><a href="${serviceUrl}/website/wx/content/list/${item.id?string('####')}"><span>${item.name}</span></a></li>
</#if>
</#list>  	
</#if>
<div class="clr"></div>
</ul>
</div>
<div class="Listpage">
<div class="top46"></div>
    <div class="focus">
<ul>
<#if pptList?exists>
<#list pptList as ppt>
<li>
<a href="${serviceUrl}/website/wx/content/detail/${ppt.uuid}">
<img alt="${ppt.title}" src="${contextPath}/${ppt.icon}">
<div class="opacity"></div>
<h2>${ppt.title}</h2>
</a>
</li>
</#list> 
</#if>
</ul>
</div>

<div id="todayList">
<ul  class="todayList">
<#if contents?exists>
<#list  contents as content>      
<li>
<a href="${serviceUrl}/website/wx/content/detail/${content.uuid}">
<#if content.icon?exists>
<div class="img"><img src="${serviceUrl}/${content.icon}"></div>
</#if>
<h2>${content.title}</h2>
<p class="onlyheight">
   ${content.summary?if_exists}
</p>
<div class="commentNum"></div>
</a>
</li>
</#list>
</#if> 
</ul>
</div>
<section id="Page_wrapper">
<div id="pNavDemo" class="c-pnav-con">
<section class="c-p-sec">
<#if (pageNo > 1) >
<div class="c-p-pre  c-p-grey  "><span class="c-p-p"><em></em></span>
<a href="${serviceUrl}/website/wx/content/list/${item.id?string('####')}?pageNo=${pageNo-1}" >上一页</a>
</div>
</#if>
<div class="c-p-cur">
<div class="c-p-arrow c-p-down"><span>${pageNo}/${pageSize}</span><span></span>
</div> 
</div>
<#if (pageNo != pageSize) && (pageNo+1 <= pageSize)>
<div class="c-p-next  c-p-grey">
<a href="${serviceUrl}/website/wx/content/list/${item.id?string('####')}?pageNo=${pageNo+1}">下一页</a>
<span class="c-p-p"><em></em></span>
</div>
</#if>
</section>
</div>
</section>
</div>
<script>
function dourl(url){
location.href= url;
}
</script>
<div style="display:none"> </div>
 
</body>
</html>
