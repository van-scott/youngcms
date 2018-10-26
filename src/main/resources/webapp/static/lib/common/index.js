var index=new Object();
index.onClick=function(event, treeId, treeNode, clickFlag) {
	var tabUrl=$("#"+treeId).attr("tabUrl");
	var tabName=$("#"+treeId).parent().prev().find("h2").text();
	var external=$("#"+treeId).attr("external");
	var params=common.getUrlRequest(tabUrl);
	var url=common.getUrlWithoutParams(tabUrl);
	var p="";
	for (x in params)
	  {
		if(params[x].indexOf("[") != -1){
			var v=(eval("treeNode."+params[x].replace("[",'').replace("]",'')+""));
			p+=x+"="+v+"&";
		}else{
			p+=x+"="+params[x]+"&";
		}
	  }
	var url=encodeURI(url+"?"+p)
	navTab.openTab(treeId,url, {
        title: tabName,
        fresh: true,
        external:eval("("+external+")")
    }); 
};
index.setting={
		async: {
			enable: true,
			url:"",
			autoParam:["id=parent_module_id"]
		},
		view: {  
            nameIsHTML: true  
        },
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onClick: index.onClick
		}
};
index.getChildren=function(pid){
	$.ajax({
		 type: "POST",
        url: ctx+"/admin/getMenuAjax",
        data: {pid:pid},
        dataType: "json",
        success: function(data){
       	 $(".accordion").html("");
       	 var _html="";
       	 $.each(data,function(i,second){
       		 console.info(second);
       		    _html+="<div class='accordionHeader'>";
       		         if(second.icon){
       		        	_html+="<h2>"+second.icon+second.name+"</h2>";
       		         }else{
       		        	_html+="<h2>"+second.name+"</h2>";
       		         }
       		    _html+="</div>";
   				if(second.isTree==2){
   					var tabId=second.id;
   					if(second.tabId){
   					   tabId=second.tabId;
   					}
   					_html+="<div class='accordionContent'>";
   					_html+="<ul id='"+tabId+"' type='tree' treeUrl='"+second.treeUrl+"' tabUrl='"+second.url+"' tabName='"+second.name+"' external='"+second.external+"' class='ztree'></ul>";
   					_html+="</div>";
   				}else{
   					_html+="<div class='accordionContent'>";
           			_html+="<ul class='m'>";
           			$.each(second.children,function(j,third){
           				var tabId=third.id;
       					if(third.tabId){
       					   tabId=third.tabId;
       					}
           				if(third.icon){
           					_html+="<li><a href='"+ctx+""+third.url+"' target='navTab' rel='"+tabId+"'  title='"+third.name+"' external='"+third.external+"'>"+third.icon+third.name+"</a></li>";
           				}else{
           					_html+="<li><a href='"+ctx+""+third.url+"' target='navTab' rel='"+tabId+"'  title='"+third.name+"' external='"+third.external+"'><img src='/webapp/static/themes/images/li.gif'/>"+third.name+"</a></li>";
           				}
           			});
           			_html+="</ul>";
           			_html+="</div>";
   				}
       	});
       	$(".accordion").append(_html);
       	$(".accordion").initUI();
       	index.init();
        }
	});
};
index.init=function(){
	navTab.init();
	navTab.openTab("index",ctx+"/admin/main", {
        title: "我的主页",
        fresh: true
    }); 
	var tree=$("ul[type='tree']");
	$.each(tree,function(i,val){
		var id=$(val).attr("id");
		var treeUrl=$(val).attr("treeUrl");
		index.setting.async.url=ctx+treeUrl;
		$.fn.zTree.init($("#"+id+""), index.setting);
	});
};
$(function(){
	//初始化DWZ
	DWZ.init(ctx+"/webapp/static/dwz.frag.xml", {
		loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		keys: {statusCode:"statusCode", message:"message"}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:true,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:ctx+"/webapp/static/themes"});
		}
	});
	//加载菜单
	index.getChildren($($("#navMenu a").get(0)).attr("v"));
});
