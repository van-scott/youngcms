var menu=new Object();
menu.onClick=function(event, treeId, treeNode, clickFlag) {
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
menu.setting={
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
			onClick: menu.onClick
		}
};
