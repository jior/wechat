<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>主页</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <link href="${serviceUrl}/templates/01/css/news4.css" rel="stylesheet" type="text/css">
    <script src="${serviceUrl}/scripts/iscroll.js" type="text/javascript"></script>
    <script type="text/javascript">
        var myScroll;
        function loaded() {
            myScroll = new iScroll('wrapper', {
                snap: true,
                momentum: false,
                hScrollbar: false,
                onScrollEnd: function() {
                    document.querySelector('#indicator > li.active').className = '';
                    document.querySelector('#indicator > li:nth-child(' + (this.currPageX + 1) + ')').className = 'active';
                }
            });
        }

        document.addEventListener('DOMContentLoaded', loaded, false);
    </script>
	<script>
	  window.onload = function (){
		var oWin = document.getElementById("win");
		var oLay = document.getElementById("overlay");	
		var oBtn = document.getElementById("popmenu");
		var oClose = document.getElementById("close");
		oBtn.onclick = function (){
			oLay.style.display = "block";
			oWin.style.display = "block"	
		};
		oLay.onclick = function (){
			oLay.style.display = "none";
			oWin.style.display = "none"	
	    }
	};
</script>
</head>
<body id="cate7">
    <div id="ui-header">
        <div class="fixed">
            <a class="ui-title" id="popmenu">选择分类</a> 
              <a class="ui-btn-left_pre" href="javascript:history.back(-1)"></a>
              <a class="ui-btn-right" href="#"></a>
            
        </div>
    </div>
    <div id="overlay">
    </div>
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
            
            <div class="clr">
            </div>
        </ul>
    </div>

    <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
      <tbody>
	  <tr>
        <td width="100%" height="46" align="center"> </td>
      </tr>
      </tbody>
	</table>

   <#if pptList?exists>
    <div class="banner">
        <div id="wrapper">
            <div id="scroller">
                <ul id="thelist">
                    
					  <#list pptList as ppt>  
					  <li>
					    <p></p>
						<a href="${ppt.url?if_exists}">
							<img src="${serviceUrl}${ppt.icon}" alt="${ppt.title}" style="width:100%;">
						</a>
					  </li>
					  </#list> 
                    
                </ul>
            </div>
        </div>
        <div id="nav">
            <div id="prev" onclick="myScroll.scrollToPage(&#39;prev&#39;, 0, 400, ${pptList?size});return false">
                ← prev</div>
            <ul id="indicator">
             <#list pptList as ppt>  
				<li <#if ppt_index==0> class="active"</#if>>${ppt_index+1}</li>
			 </#list> 
            </ul>
            <div id="next" onclick="myScroll.scrollToPage(&#39;next&#39;, 0);return false">
                next →</div>
        </div>
        <div class="clr">
        </div>
    </div>
	<script>
    var count = document.getElementById("thelist").getElementsByTagName("img").length;	

    var count2 = document.getElementsByClassName("menuimg").length;
    for(i=0;i<count;i++){
       document.getElementById("thelist").getElementsByTagName("img").item(i).style.cssText = " width:"+document.body.clientWidth+"px";
    }
    document.getElementById("scroller").style.cssText = " width:"+document.body.clientWidth*count+"px";

    setInterval(function(){
        myScroll.scrollToPage('next', 0, 400, ${pptList?size});
    },3500 );

	window.onresize = function(){ 
	for(i=0;i<count;i++){
	document.getElementById("thelist").getElementsByTagName("img").item(i).style.cssText = " width:"+document.body.clientWidth+"px";
	}
    document.getElementById("scroller").style.cssText = " width:"+document.body.clientWidth*count+"px";
} 

</script>

   </#if>

    <ul class="cateul">
        
        <#if categories?exists>
			<#list categories as item>  
			<li class="li${item_index%3} ">
			<#if item.url?exists && item.url != ''>
			<a href="${item.url}">
			<#else>
			<a href="${serviceUrl}/website/wx/content/list/${item.stringId}">
			</#if>
			  <div class="menubtn">
			  <#if item.coverIcon?exists>
			    <div class="menuimg"><img src="${serviceUrl}/${item.coverIcon}" /></div>
			  </#if>
			  <div class="menutitle">${item.name}</div>
			</div>
			</a>
			</li>
			</#list> 
		</#if> 
        
        <div class="clr">
        </div>
    </ul>
    <div style="display: none">
    </div>


</body></html>