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
function success(file){
	 /* $("#img_div").attr('src',file);
	 $('#img_div').Jcrop({
		 bgFade: true,
		 aspectRatio: 1,
		 boxWidth:600,
		 boxHeight:300,
		 setSelect: [c.x, c.y, c.x2, c.y2],
		 onSelect: updateCoords
	 });  */
	 /*$($("#imgIfream").contents().find("img")[0]).Jcrop({
		 bgFade: true,
		 aspectRatio: 1,
		 boxWidth:600,
		 boxHeight:300,
		 setSelect: [c.x, c.y, c.x2, c.y2],
		 onSelect: updateCoords
	 });  */
	 filePath=file;
	 $($("#imgIfream").contents().find("img")[0]).attr('src',file);
	 window.frames['imgIfream'].sub();
	 
}
function cut(){
	  if (filePath && w && h) {
		  $.ajax({
			    type : "GET",
  	            url : path+"/common/imgCut.do",
				data:{filePath:filePath,x:x,y:y,w:w,h:h},
				dataType:'json',
				success:function(data){
					if(data.success){
						parent.fileUploadSuccess(data.result);
						parent.layer.closeAll('iframe');
					}
				}
			});
	  }else{
		  alert('请先选择要裁剪的区域后，再提交。');
	  }
};
</script>
</head>
<body>
	<form action="${path }/common/fileUpload.do" method="post"
		enctype="multipart/form-data" target="ajaxUpload" name="theform">
		<table class="formtable">
			<tr>
				<td><input type="file" name="myfiles"></td>
				<td><input type="submit" name="submit" class="btn" value="上传" /></td>
				<td><input type="button"  class="btn" value="裁剪" onclick="cut()" /></td>
			</tr>
			<tr>
				<td colspan="3">
				<iframe id="imgIfream" name="imgIfream" src="${path }/view/common/imgShow.jsp"></iframe>
				</td>
			</tr>
		</table>
	</form>
	<iframe name="ajaxUpload" style="display: none"></iframe>
</body>
</html>

