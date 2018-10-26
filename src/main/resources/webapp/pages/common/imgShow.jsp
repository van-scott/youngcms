<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/view/common/basePath.jsp"%>
<%@ include file="/view/admin/common/import.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>上传文件</title>
<link rel="stylesheet" href="${path}/view/admin/resource/js/Jcrop/css/jquery.Jcrop.css">
<script type="text/javascript" src="${path }/view/admin/resource/js/Jcrop/js/jquery.Jcrop.js"></script>
<script type="text/javascript">
var filePath='';
var x=0;
var y=0;
var w=200;
var h=200;
var c = {
	    'x': 0,
	    'y': 0,
	    'x2': 200,
	    'y2': 200,
	    'w': 200,
	    'h': 200
	  };
function sub(){
	 $('#img_div').Jcrop({
		 bgFade: true,
		 setSelect: [c.x, c.y, c.x2, c.y2],
		 onSelect: updateCoords
	 });
}
function updateCoords(c){
	x=c.x;
	y=c.y;
	w=c.w;
	h=c.h;
	
};
function cut(){
	  
};
</script>
</head>
<body>
  <div style="background: red;"><img id="img_div" alt="" src=""></div>
</body>
</html>

