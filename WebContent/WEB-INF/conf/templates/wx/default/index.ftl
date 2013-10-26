<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微信首页</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta charset="utf-8">
<link href="${contextPath}/templates/01/css/news4.css" rel="stylesheet" type="text/css" />
<script src="${contextPath}/scripts/iscroll.js" type="text/javascript"></script>
<script type="text/javascript">
var myScroll;

function loaded() {
myScroll = new iScroll('wrapper', {
snap: true,
momentum: false,
hScrollbar: false,
onScrollEnd: function () {
document.querySelector('#indicator > li.active').className = '';
document.querySelector('#indicator > li:nth-child(' + (this.currPageX+1) + ')').className = 'active';
}
 });
 
 setInterval(function(){
     myScroll.scrollToPage('next', 0, 400, 3);
   },3500 );
}

document.addEventListener('DOMContentLoaded', loaded, false);
</script>
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
<style type="text/css">
#scroller {
width:960px;
}
</style>
</head>

<body id="cate7">
<div id="ui-header">
<div class="fixed">
<a class="ui-title" id="popmenu">选择分类</a>
<a class="ui-btn-left_pre" href="#"></a> 
<a class="ui-btn-left_pre1" href="#"></a> 
<a class="ui-btn-right2" href="#"></a>
<a class="ui-btn-right" href="${contextPath}/website/wx/content/index/${userId}"></a>
</div>
</div>
<div id="overlay"></div>
<div id="win">
<ul class="dropdown"> 
<#if categories?exists>
<#list categories as category>
<#if category.url?exists && category.url != ''>
<li><a href="${category.url}"><span>${category.name}</span></a></li>
<#else>
<li><a href="${contextPath}/website/wx/content/list/${category.id?string('####')}"><span>${category.name}</span></a></li>
</#if>
</#list> 
</#if> 
<div class="clr"></div>
</ul>
</div> 
 
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td height="60"> </td>
  </tr>
</table>

<ul class="cateul">
<#if categories?exists>
<#list categories as item>   
<li class="li0 ">
<#if item.url?exists && item.url != ''>
<a href="${item.url}">
<#else>
<a href="${contextPath}/website/wx/content/list/${item.id?string('####')}">
</#if>
<div class="menubtn">
<#if item.coverIcon?exists>
<div class="menuimg"><img src="${contextPath}/${item.coverIcon}" /></div>
</#if>
<div class="menutitle">${item.name}</div>
</div>
</a>
</li>
</#list> 
</#if> 
<div class="clr"></div>
</ul>

<div style="display:none"> </div>

</body>
</html>
