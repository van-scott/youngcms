<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/view/common/basePath.jsp"%>
<%@ include file="/view/admin/common/import.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>上传文件</title>
<script type="text/javascript">
var functionName;
$(function(){
	functionName=getParams("functionName");
});
function sub(){
	document.theform.submit();
}
function success(file){
	eval("parent."+functionName+"")(file);
	parent.layer.closeAll('iframe');
}
</script>
</head>
<body>
	<form action="${path }/common/fileUpload.do" method="post"
		enctype="multipart/form-data" target="ajaxUpload" name="theform">
		<table class="formtable">
			<tr>
				<td>
				<input type="hidden" name="directory" value="upload">
				<input type="file" name="myfiles" multiple="multiple">
				</td>
			</tr>
		</table>
	</form>
	<iframe name="ajaxUpload" style="display: none"></iframe>
</body>
</html>

