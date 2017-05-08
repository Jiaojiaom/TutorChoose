<!-- 解决了中文文件名导致文件不能下载的问题 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>导入信息</title>
<style type="text/css">
ul{
  list-style: none;
}
li{
  padding:5px;
}
</style>
</head>
<body>
	<script type="text/javascript">
		function validateStu() {
			if (formStu.file.value == "") {
				alert("请选择要上传的文件");
				return false;
			}
		}
		function validateTeacher() {
			if (formTeacher.file.value == "") {
				alert("请选择要上传的文件");
				return false;
			}
		}
		function validateDept() {
			if (formDept.file.value == "") {
				alert("请选择要上传的文件");
				return false;
			}
		}
		function validateClass() {
			if (formClass.file.value == "") {
				alert("请选择要上传的文件");
				return false;
			}
		}
	</script>
	<form action="uploadFile" method="post" name="formStu" id="formStu"
		enctype="multipart/form-data" onsubmit="return validateStu()">
		<ul>
			<li>导入学生信息：  <input type="file" name="file"/> <!-- 文件上传组件 --><li>
			<li><input type="submit" name="Submit" value="导入学生信息" /> 
			    <input type="reset" name="Submit2" value="重置" />
		    </li>
		</ul>
	</form>
	</hr>
	<form action="uploadFile" method="post" name="formTeacher" id="formTeacher"
		enctype="multipart/form-data"  onsubmit="return validateTeacher()">
		<ul>
			<li>导入教师信息： <input type="file" name="file"/> <!-- 文件上传组件 --></li>
			<li><input type="submit" name="Submit" value="导入教师信息" /> 
			    <input type="reset" name="Submit2" value="重置" />
		    </li>
		</ul>
	</form>
	</hr>
	<form action="uploadFile" method="post" name="formDept" id="formDept"
		enctype="multipart/form-data"  onsubmit="return validateDept()">
		<ul>
			<li>导入系信息： <input type="file" name="file" id="importDeptFile"/> <!-- 文件上传组件 --></li>
			<li><input type="submit" name="Submit" value="导入系信息" /> 
			    <input type="reset" name="Submit2" value="重置" />
		    </li>
		</ul>
	</form>
	</hr>
	<form action="uploadFile" method="post" name="formClass" id="formClass"
		enctype="multipart/form-data"  onsubmit="return validateClass()">
		<ul>
			<li>导入班级信息： <input type="file" name="file" id="importClassFile"/> <!-- 文件上传组件 --></li>
			<li><input type="submit" name="Submit" value="导入班级信息" /> 
			    <input type="reset" name="Submit2" value="重置" />
		    </li>
		</ul>
	</form>
</body>
</html>
