<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="db.TeacherMsDAO"%>
<%@ page import="db.StudentMsDAO"%>
<%@ page import="db.DeptMsDAO"%>
<%@ page import="db.ClassMsDAO"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员</title>
<link rel="stylesheet" type="text/css" href="../lib/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="../lib/bootstrap.min.css">
<link rel="stylesheet" href="../lib/sweetalert.css">
<link rel="stylesheet" type="text/css" href="../lib/table.css">
<script type="text/javascript" language="javascript" src="//code.jquery.com/jquery-1.12.4.js">
</script>
<script type="text/javascript" language="javascript" src="../lib/sweetalert-dev.js"></script>
<script type="text/javascript" language="javascript" src="../lib/jquery.dataTables.min.js">
</script>
<style type="text/css">
	body{
		margin: 0;
		padding: 0;
		display:flex;
		flex-direction: column;
		height: 100vh; 
		width:100%;
	}
	.main{
		display: flex;
		flex-direction: column;
		width: 90%;
		margin-left: 2%;
		padding: 10px;
	}
	.main table{
		width: 98%;
	}
	.nav{
		margin-top: 50px;
		line-height:40px;
		font-size:10px;
		align-self: flex-start;
		margin-left:2%;
	}
</style>

</head>
<body>
    <%@ include file="../public/navbar.html" %>
	<span class="nav">管理员端>>信息中心</span>
	<div class="main">
	<%
		if (request.getAttribute("result") != null) { //判断保存在request范围内的对象是否为空
			out.println("<script >alert('" + request.getAttribute("result")
					+ "');</script>"); //页面显示提示信息    	
		}
	%>
	<div id="teacherListForm">
	    <h2>教师列表</h2>
	    <form action="uploadFile" method="post" name="formTeacher" id="formTeacher"
		enctype="multipart/form-data"  onsubmit="return validateTeacher()">
			导入教师信息： <input type="file" name="file"/>
			<input type="submit" name="Submit" value="导入教师信息" /> 
			<input type="reset" name="Submit2" value="重置" />
		</form>
		<a href='exportData?datatype=teacher'>导出教师</a>
		<table id="teacher" class="display" cellspacing="0" width="100%">
		  <thead>
			<tr>
				<th>工号</th>
				<th>姓名</th>
				<th>系编号</th>
				<th>性别</th>
				<th>密码</th>
				<th>职称</th>
				<th>学生人数</th>
				<th>选择规则</th>
				<th>电话</th>
				<th>自我介绍</th>
				<th>修改</th>
				<th>删除</th>
			</tr>
		  </thead>
		</table>
	</div>	
	 
	<div id="studentListForm">
	    <h2>学生列表</h2>
	    <form action="uploadFile" method="post" name="formTeacher" id="formTeacher"
		enctype="multipart/form-data"  onsubmit="return validateStu()">
			导入学生信息：<input type="file" name="file"/> <!-- 文件上传组件 --></li>
			<input type="submit" name="Submit" value="导入学生信息" /> 
			<input type="reset" name="Submit2" value="重置" />
    	</form>
    	<a href='exportData?datatype=student'>导出学生</a>
		<table id="student" class="display" cellspacing="0" width="100%">
		  <thead>
			<th>学号</th>
			<th>姓名</th>
			<th>系编号</th>
			<th>班级</th>
			<th>性别</th>
			<th>密码</th>
			<th>绩点</th>
			<th>电话</th>
			<th>自我介绍</th>
			<th>导师</th>
			<th>状态</th>
			<th>选择日期</th>
			<th>修改</th>
			<th>删除</th>
		</table>
	</div>
	
	<div id="deptListForm">
	    <h2>系列表 </h2>
	    <form action="uploadFile" method="post" name="formDept" id="formDept"
		enctype="multipart/form-data"  onsubmit="return validateDept()">
		     导入系信息： <input type="file" name="file" id="importDeptFile"/> <!-- 文件上传组件 --></li>
		 <input type="submit" name="Submit" value="导入系信息" /> 
		 <input type="reset" name="Submit2" value="重置" />
	    </form>
	    <a href='exportData?datatype=dept'>导出系</a>
		<table id="dept" class="display" cellspacing="0" width="100%">
		  <thead>
			<th>系编号</th>
			<th>系名</th>
			<th>修改</th>
			<th>删除</th>
		</table>
	</div>
	
	<div id="classListForm">
	    <h2>班级列表</h2>
	    <form action="uploadFile" method="post" name="formClass" id="formClass"
		enctype="multipart/form-data"  onsubmit="return validateClass()">
			导入班级信息： <input type="file" name="file" id="importClassFile"/> <!-- 文件上传组件 --></li>
			<input type="submit" name="Submit" value="导入班级信息" /> 
			<input type="reset" name="Submit2" value="重置" />
	   </form>
	   <a href='exportData?datatype=class'>导出班级</a>
		<table id="class" class="display" cellspacing="0" width="100%">
		  <thead>
			<th>班级编号</th>
			<th>班级名</th>
			<th>修改</th>
			<th>删除</th>
		</table>
	</div>
  </div>
</body>
<script type="text/javascript">
	/*获取表格中的数据*/
	var arrayTeacher = new Array();
	var k=0;
	<%   
	TeacherMsDAO teacherDao = new TeacherMsDAO();
	ArrayList<Map<String, String>> teacherMsgs = teacherDao.queryTeacherList();
	for (Map<String, String> teacherMsg : teacherMsgs) {
		String  viewTeacher = "<a href='teacherDetail.jsp?teacherid="+teacherMsg.get("teacherid")+"'>"+teacherMsg.get("teachername")+"</a>";
		String  editTeacher = "<a href='editTeacherInfo.jsp?teacherid="+teacherMsg.get("teacherid")+"'>编辑</a>";
		String  deleteTeacher = "<a href='deleteInfo.jsp?dataType=teacher&id="+teacherMsg.get("teacherid")+"'>删除</a>";
		String sex = "";
		if(teacherMsg.get("sex").equals("F")){
			sex="男";
		} else if(teacherMsg.get("sex").equals("M")){
			sex="女";
		}
	%>
		var t = new Array(12);
		t[0] = "<%=teacherMsg.get("teacherid")%>";
		t[1] = "<%=viewTeacher%>";
		t[2] = "<%=teacherMsg.get("deptid")%>";
		t[3] = "<%=sex%>";
		t[4] = "<%=teacherMsg.get("tpassword")%>";
		t[5] = "<%=teacherMsg.get("title")%>";
		t[6] = "<%=teacherMsg.get("studentcount")%>";
		t[7] = "<%=teacherMsg.get("privilege")%>";
		t[8] = "<%=teacherMsg.get("tel")%>";
		t[9] = "<%=teacherMsg.get("intro")%>";
		t[10] = "<%=editTeacher%>";
		t[11] = "<%=deleteTeacher%>";
		arrayTeacher[k] = t;
		k++;
	<%
	}
	teacherDao.close();
	%>
	
	var arrayStudent = new Array();
	var m=0;
	<%   
	StudentMsDAO studentDao = new StudentMsDAO();
	ArrayList<Map<String, String>> studentMsgs = studentDao.queryStudentList();
	for (Map<String, String> studentMsg : studentMsgs) {
		String  viewStudent = "<a href='studentDetail.jsp?stuid="+studentMsg.get("stuid")+"'>"+studentMsg.get("stuname")+"</a>";
		String  editStudent = "<a href='editStudentInfo.jsp?stuid="+studentMsg.get("stuid")+"'>编辑</a>";
		String  deleteStudent = "<a href='deleteInfo.jsp?dataType=student&id="+studentMsg.get("stuid")+"'>删除</a>";
		String sex = "";
		if(studentMsg.get("sex").equals("F")){
			sex="男";
		} else if(studentMsg.get("sex").equals("M")){
			sex="女";
		}
	%>
		var n = new Array(14);
		n[0] = "<%=studentMsg.get("stuid")%>";
		n[1] = "<%=viewStudent%>";
		n[2] = "<%=studentMsg.get("deptid")%>";
		n[3] = "<%=studentMsg.get("classid")%>";
		n[4] = "<%=sex%>";
		n[5] = "<%=studentMsg.get("spassword")%>";
		n[6] = "<%=studentMsg.get("grade")%>";
		n[7] = "<%=studentMsg.get("tel")%>";
		n[8] = "<%=studentMsg.get("intro")%>";
		n[9] = "<%=studentMsg.get("teacherid")%>";
		n[10] = "<%=studentMsg.get("choosedstate")%>";
		n[11] = "<%=studentMsg.get("selectdate")%>";
		n[12] = "<%=editStudent%>";
		n[13] = "<%=deleteStudent%>";
		arrayStudent[m] = n;
		m++;
	<%
	}
	studentDao.close();
	%>
	
	var arrayDept = new Array();
	var m=0;
	<%   
	DeptMsDAO deptDao = new DeptMsDAO();
	ArrayList<Map<String, String>> deptMsgs = deptDao.queryDeptAll();
	for (Map<String, String> deptMsg : deptMsgs) {
		String  viewDept = "<a href='deptDetail.jsp?deptid="+deptMsg.get("deptid")+"'>"+deptMsg.get("deptname")+"</a>";
		String  editDept = "<a href='editDeptInfo.jsp?deptid="+deptMsg.get("deptid")+"'>编辑</a>";
		String  deleteDept = "<a href='deleteInfo.jsp?dataType=dept&id="+deptMsg.get("deptid")+"'>删除</a>";
	%>
		var n = new Array(4);
		n[0] = "<%=deptMsg.get("deptid")%>";
		n[1] = "<%=viewDept%>";
		n[2] = "<%=editDept%>";
		n[3] = "<%=deleteDept%>";
		arrayDept[m] = n;
		m++;
	<%
	}
	deptDao.close();
	%>
	
	var arrayClass = new Array();
	var m=0;
	<%   
	ClassMsDAO classDao = new ClassMsDAO();
	ArrayList<Map<String, String>> classMsgs = classDao.queryClassAll();
	for (Map<String, String> classMsg : classMsgs) {
		String  viewClass = "<a href='classDetail.jsp?classid="+classMsg.get("classid")+"'>"+classMsg.get("classname")+"</a>";
		String  editClass = "<a href='editClassInfo.jsp?classid="+classMsg.get("classid")+"'>编辑</a>";
		String  deleteClass = "<a href='deleteInfo.jsp?dataType=class&id="+classMsg.get("classid")+"'>删除</a>";
	%>
		var n = new Array(4);
		n[0] = "<%=classMsg.get("classid")%>";
		n[1] = "<%=viewClass%>";
		n[2] = "<%=editClass%>";
		n[3] = "<%=deleteClass%>";
		arrayClass[m] = n;
		m++;
	<%
	}
	classDao.close();
	%>
	/*datatable表格设定*/
	$(document).ready(function(){
		$('#teacher').DataTable({
			data: arrayTeacher,
			"oLanguage": { 
				"sLengthMenu": "每页显示 _MENU_ 条记录", 
				"sZeroRecords": "抱歉， 没有找到", 
				"sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据", 
				"sInfoEmpty": "没有数据", 
				"sInfoFiltered": "(从 _MAX_ 条数据中检索)", 
				"sSearch": "搜索",
				"oPaginate": { 
					"sFirst": "首页", 
					"sPrevious": "前一页", 
					"sNext": "后一页", 
					"sLast": "尾页" 
				}, 
			"sZeroRecords": "没有检索到数据",
			"bStateSave": true 
			}
		});
		$('#student').DataTable({
			data: arrayStudent,
			"oLanguage": { 
				"sLengthMenu": "每页显示 _MENU_ 条记录", 
				"sZeroRecords": "抱歉， 没有找到", 
				"sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据", 
				"sInfoEmpty": "没有数据", 
				"sInfoFiltered": "(从 _MAX_ 条数据中检索)", 
				"sSearch": "搜索",
				"oPaginate": { 
					"sFirst": "首页", 
					"sPrevious": "前一页", 
					"sNext": "后一页", 
					"sLast": "尾页" 
				}, 
			"sZeroRecords": "没有检索到数据",
			//"sProcessing": "<img src='./loading.gif' />",
			"bStateSave": true //保存状态到cookie *************** 很重要 ， 当搜索的时候页面一刷新会导致搜索的消失。使用这个属性就可避免了 
			}
		});
		$('#dept').DataTable({
			data: arrayDept,
			"oLanguage": { 
				"sLengthMenu": "每页显示 _MENU_ 条记录", 
				"sZeroRecords": "抱歉， 没有找到", 
				"sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据", 
				"sInfoEmpty": "没有数据", 
				"sInfoFiltered": "(从 _MAX_ 条数据中检索)", 
				"sSearch": "搜索",
				"oPaginate": { 
					"sFirst": "首页", 
					"sPrevious": "前一页", 
					"sNext": "后一页", 
					"sLast": "尾页" 
				}, 
			"sZeroRecords": "没有检索到数据",
			//"sProcessing": "<img src='./loading.gif' />",
			"bStateSave": true //保存状态到cookie *************** 很重要 ， 当搜索的时候页面一刷新会导致搜索的消失。使用这个属性就可避免了 
			}
		});
		$('#class').DataTable({
			data: arrayClass,
			"oLanguage": { 
				"sLengthMenu": "每页显示 _MENU_ 条记录", 
				"sZeroRecords": "抱歉， 没有找到", 
				"sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据", 
				"sInfoEmpty": "没有数据", 
				"sInfoFiltered": "(从 _MAX_ 条数据中检索)", 
				"sSearch": "搜索",
				"oPaginate": { 
					"sFirst": "首页", 
					"sPrevious": "前一页", 
					"sNext": "后一页", 
					"sLast": "尾页" 
				}, 
			"sZeroRecords": "没有检索到数据",
			//"sProcessing": "<img src='./loading.gif' />",
			"bStateSave": true //保存状态到cookie *************** 很重要 ， 当搜索的时候页面一刷新会导致搜索的消失。使用这个属性就可避免了 
			}
		});
	});
</script>

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
</html>