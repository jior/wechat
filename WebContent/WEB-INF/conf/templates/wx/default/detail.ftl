<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>详细页</title> 
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta charset="utf-8">
<link href="${serviceUrl}/templates/01/css/news4.css" rel="stylesheet" type="text/css" />
 
<style type="text/css">
<!--
.STYLE1 {
	font-family: Verdana;
	font-weight: bold;
	font-size: 12pt;
}
-->
</style>
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
<body id="news2">
<#if category?exists> 
<div id="ui-header">
<div class="fixed">
<a class="ui-title" id="popmenu">选择分类</a>
<a class="ui-btn-left_pre" href="${serviceUrl}/website/wx/content/list/${category.stringId}"></a>
<a class="ui-btn-right" href="${serviceUrl}/website/wx/content/detail/${content.uuid}"></a>
</div>
</div>
</#if>
<div id="overlay"></div>
<div id="win">
<ul class="dropdown"> 
<#if categories?exists>
<#list categories as category>
<#if category.url?exists && category.url != ''>
<li><a href="${category.url}"><span>${category.name}</span></a></li>
<#else>
<li><a href="${serviceUrl}/website/wx/content/list/${category.stringId}"><span>${category.name}</span></a></li>
</#if>
</#list> 
</#if>   	
<div class="clr"></div>
</ul>
</div>
<div class="Listpage">
<div class="top46"></div>
<div class="page-bizinfo">
<div class="header" style="position: relative;">
<h1 id="activity-name">${content.title}</h1>
<span id="post-date">${content.createDate?string("yyyy-MM-dd")}</span>　<span class="commentNum"></span>
</div>
<#if content.bigIcon?exists>
<div class="showpic"><img src="${serviceUrl}/${content.bigIcon}" ></div>
</#if>
<div class="text" id="content">
  ${content.content?if_exists}
</div>
 

<div class="page-content" ></div>

</div>


<div class="list">
<div id="olload">
<span>关联事件</span>
</div>

<div id="oldlist">
<ul>
<#if content.recommendations?exists>
<#list  content.recommendations as recommendation>    
  <li class="newsmore">
   <a href="${serviceUrl}/website/wx/content/detail/${recommendation.uuid}" >
    <div class="olditem">
     <div class="title">${recommendation.title}</div> 
    </div>
   </a>
  </li>
 </#list>
</#if>    
</ul>
   <a class="more" href="#">更多精彩内容</a>	
</div>
</div>
    
<a class="footer" href="#"  target="_self"><span class="top">返回顶部</span></a>

<script>
function dourl(url){
    location.href= url;
}
</script>
<div style="display:none"> </div>
</div>
 
</body>
</html>
