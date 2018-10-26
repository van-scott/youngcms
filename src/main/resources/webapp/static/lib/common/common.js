var common = {
		getUrlRequest:function(url) {
            var theRequest = new Object();
            if (url.indexOf("?") != -1) {  
                var str = url.substring(url.indexOf("?")+1,url.length);  
                strs = str.split("&");  
                for(var i = 0; i < strs.length; i ++) {  
                   theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);  
                }  
             }  
            return theRequest;
        },
        getUrlWithoutParams:function(url) {
            var url = url;
            if (url.indexOf("?") != -1) {  
                url = url.substring(0,url.indexOf("?"));  
             }  
            return url;
        }
}