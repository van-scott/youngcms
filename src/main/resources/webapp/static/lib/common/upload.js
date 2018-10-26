var upload = {
		 ajaxFileUpload:function(fileId,valueId,type){
	        $.ajaxFileUpload
	        (
	            {
	                url: ctx+'/common/fileUpload.do', //用于文件上传的服务器端请求地址
	                secureuri: false, //是否需要安全协议，一般设置为false
	                fileElementId: fileId, //文件上传域的ID
	                type: type,
	                dataType: 'json', //返回值类型 一般设置为json
	                success: function (data, status)  //服务器成功响应处理函数
	                {
	                    if (typeof (data.error) != 'undefined') {
	                        if (data.error != '') {
	                        	if("dialog"==type){//获取当前dialog下面的文本域
	                				$("#"+valueId,$.pdialog.getCurrent()).val(data.imgurl);
	                			}else{//获取当前navTab下面的文本域
	                				$("#"+valueId,navTab.getCurrentPanel()).val(data.imgurl);
	                			}
	                        } else {
	                            alert(data.msg);
	                        }
	                    }
	                },
	                error: function (data, status, e)//服务器响应失败处理函数
	                {
	                    alert(e);
	                }
	            }
	        )
	    },
	    chooseFile:function(_this){
			$("#"+_this).click();
		}
}