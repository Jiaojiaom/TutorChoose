<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="db.StudentMsDAO"%>
<%@ page import="com.bean.StudentMsg"%>
<%@ page import="db.DeptMsDAO"%>
<%@ page import="db.ClassMsDAO"%>
<%@ page import="db.TeacherMsDAO"%>
<%@ page import="db.SelectTeacherDAO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导师端>>个人信息中心</title>
<link rel="stylesheet" type="text/css" href="../lib/bootstrap.min.css">
<link rel="stylesheet" href="../lib/sweetalert.css">
<script type="text/javascript" language="javascript"
	src="//code.jquery.com/jquery-1.12.4.js">
</script>
<script type="text/javascript" language="javascript"
	src="../lib/sweetalert-dev.js"></script>
<link rel="stylesheet" type="text/css"
	href="../lib/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="../lib/table.css">
<script type="text/javascript" language="javascript"
	src="../lib/jquery.dataTables.min.js"></script>
<style type="text/css">
body {
	margin: 0;
	padding: 0;
	height: 100vh;
}

.main {
	display: flex;
	flex-direction: column;
	width: 90%;
	margin-left: 2%;
	padding: 10px;
}

.main table {
	height: 60vh;
}

.panel {
	padding: 10px;
	margin: 10px;
	-webkit-box-shadow: 0 1px 1px #5d9cec;
	box-shadow: 0 1px 1px #5d9cec;
	text-align: center;
}

html input[disabled] {
	cursor: no-drop;
	background-color: #eee;
}

.changeAvatar {
	border: 1px solid rgba(59, 194, 29, .7);
	color: #42c02e !important;
	padding: 4px 12px;
	font-size: 12px;
	font-weight: 400;
	line-height: normal;
	border-radius: 40px;
	background: none;
}

input[type=text] {
	padding: 5px 10px;
	font-size: 15px;
	border: 1px solid #c8c8c8;
	border-radius: 4px;
	background-color: hsla(0, 0%, 71%, .1);
	height:35px;
}

select {
	padding: 5px 10px;
	font-size: 15px;
	border: 1px solid #c8c8c8;
	border-radius: 4px;
	background-color: hsla(0, 0%, 71%, .1);
	width: 58%;
	height:35px;
}

.nav {
	margin-top: 50px;
	line-height: 40px;
	font-size: 10px;
	align-self: flex-start;
	margin-left: 2%;
}

.table {
	display: flex;
	justify-content: space-around;
}

.table .name {
	display: flex;
	flex-direction: column;
	width: 25%;
}

.table .name p {
	margin-bottom: 12px;
	line-height: 35px;
}

.table .detail {
	display: flex;
	flex-direction: column;
}

.table .detail input {
	margin-bottom: 12px;
	width: 75%;
}

.table .detail select {
	margin-bottom: 12px;
	width: 75%;
}
</style>
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<span class="nav">导师端>>个人信息中心</span>
	<div class="main">
		<%
		   String stuid=request.getParameter("stuid");
		   System.out.println(stuid);
		   StudentMsDAO studentDao = new StudentMsDAO();
	       StudentMsg stuMsg = studentDao.findByStudentId(stuid);
	       System.out.println(stuMsg.getIntro());
		%>
		<form action="updateInfo" method="post" style="width: 100%"
			id="updateInfoForm">
			<div style="display: flex; flex-direction: row; width: 100%">
				<div class="panel" style="width: 40%">
					<div class="table">
						<div class="name">
							<p>姓名</p>
							<p>性别</p>
							<p>学号</p>
							<p>专业</p>
							<p>班级</p>
							<p>绩点</p>
							<p>导师工号</p>
							<p>导师姓名</p>
							<p>状态</p>
						</div>
						<div class="detail">
							<input type="text" value="<%=stuMsg.getStuName()%>"
								name="StuName">
							<%
								if(stuMsg.getSex().equals("M")) {
							%>
							<div style="display: flex;flex-direction: row;margin-bottom: 10px;">
								<label class="checkbox-inline" style="display:flex;width:30%">
									<input type="radio" placeholder="请填写性别" value="男" name="Sex" checked />男
								</label>
								<label class="checkbox-inline" style="display:flex;width:30%">
									<input type="radio" placeholder="请填写性别" value="女" name="Sex" />女
								</label>
							</div>
							<%
								} else {
							%>
							<div style="display: flex;flex-direction: row; margin-bottom: 10px;">
								<label class="checkbox-inline" style="display:flex;width:30%">
									<input type="radio" placeholder="请填写性别" value="男" name="Sex"  />男
								</label>
								<label class="checkbox-inline" style="display:flex;width:30%">
									<input type="radio" placeholder="请填写性别" value="女" name="Sex" checked/>女
								</label>
							</div>
							<%
								}
							%>
							<input name="userType" type="hidden" value="student" /> <input
								name="StuID" type="text" value="<%=stuMsg.getStuID()%>" readonly>
							<select name="DeptID" id="DeptID" value=<%=stuMsg.getDeptID()%>>
								<%
									DeptMsDAO deptDao = new DeptMsDAO();
									ArrayList<Map<String, String>> deptMsgs = deptDao.queryDeptAll();
									for (Map<String, String> deptMsg : deptMsgs) {
								%>
								<%
								  if(deptMsg.get("deptid").equals(stuMsg.getDeptID())){
								%>
								   <option value=<%=deptMsg.get("deptid")%> selected><%=deptMsg.get("deptname")%></option>
								<%
								  }else{
								%>
								  <option value=<%=deptMsg.get("deptid")%>><%=deptMsg.get("deptname")%></option>
								<%
									}
								}
								deptDao.close();
								%>
							</select> 
							<select name="ClassID" id="ClassID" value=<%=stuMsg.getClassID()%>>
								<%
									ClassMsDAO classDao = new ClassMsDAO();
									ArrayList<Map<String, String>> classMsgs = classDao.queryClassByDeptID(stuMsg.getDeptID());
									for (Map<String, String> classMsg : classMsgs) {
								%>
								<%
								  if(classMsg.get("classid").equals(stuMsg.getClassID())){
								%>
								   <option value=<%=classMsg.get("classid")%> selected = "selected"><%=classMsg.get("classname")%></option>
								<%
								  }else{
								%>
								  <option value=<%=classMsg.get("classid")%>><%=classMsg.get("classname")%></option>
								<%
									}
								}
								deptDao.close();
								%>
							</select> <input type="text" value="<%=stuMsg.getGrade()%>" name="Grade">
							<input type="text" value="<%=stuMsg.getTel()%>" name="tel">
							<% 
							  SelectTeacherDAO selectTeacherDao = new SelectTeacherDAO();
							  String TeacherID = stuMsg.getTeacherID();
							  String TeacherName = selectTeacherDao.getTeacherName(stuMsg.getTeacherID());
							  String state ="";
							  if(stuMsg.getChoosedState()==0){
								state = "未选择";
							  } else if(stuMsg.getChoosedState()==1){
								state = "待定";
							  } else if(stuMsg.getChoosedState()==2){
								state = "淘汰";
							  } else if (stuMsg.getChoosedState()==3){
								state = "成功";
							  }
							  selectTeacherDao.close();
							%>
							<input type="text" value="<%=TeacherID%>" name="TeacherID" readonly>
							<input type="text" value="<%=TeacherName%>" readonly>
							<input type="text" value="<%=state%>" name="ChoosedState" readonly>
						</div>
					</div>
				</div>
				<div class="panel" style="width: 60%; margin-left: 20px;text-align: left;">
					<h5>个人简介</h5>
					<textarea name="Intro" rows="3" class="mb-lg form-control" 
					style="resize: none;height: 100px;max-height: 100px;"><%=stuMsg.getIntro()%></textarea>
					<h5>历史记录</h5>
					<div style="margin-top: 45px;">
						<table id="selectStuTable" class="display" cellspacing="0"
							width="100%">
							<thead>
								<tr>
									<th>工号</th>
									<th>姓名</th>
									<th>状态</th>
									<th>选择时间</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
			<div style="text-align: center">
				<input name="functionType" type="hidden" id="functionType"
					value="updateInfo" />
				<button type="button" class="btn btn-primary" onClick="saveInfo()">保存个人信息</button>
				<button type="button" class="btn btn-danger"
					onclick="resetPassword()">重置密码</button>
			</div>
		</form>
		<%
	       studentDao.close();
		%>
	</div>
	<script type="text/javascript" language="javascript">
	/*获取从servlet返回的信息，显示成功或失败*/
	/*获取表格中的数据*/
	var array = new Array();
	var k=0;
	<%
	SelectTeacherDAO dao = new SelectTeacherDAO();
	ArrayList<Map<String, String>> selectTeacher = dao.querySelectTeacher(stuMsg.getStuID());
	dao.close();
	for (Map<String, String> map : selectTeacher) {
		state = "0";
		String oper = "";
		System.out.println(map.get("teacherid"));
		if(map.get("choosedstate").equals("0"))
			state = "<strong><font color='#777'>待定</font></strong>";
		if(map.get("choosedstate").equals("1"))
				state = "<strong><font color='#777'>淘汰</font></strong>";
		else if(map.get("choosedstate").equals("2"))
			state = "<strong><font color='#5d9cec'>成功</font></strong>";
	%>
		var t = new Array();
		t.push("<%=map.get("teacherid")%>");
		t.push("<%=map.get("teachername")%>");
		t.push("<%=state%>");
		t.push("<%=map.get("selectdate")%>");
		console.log(t);
		array[k] = t;
		k++;
	<%}%>
	$(document).ready(function() {
		$('#selectStuTable').DataTable( {
			data: array,
			"bLengthChange": false, //改变每页显示数据数量
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
			"sZeroRecords": "还未有教师选择您",
			//"sProcessing": "<img src='./loading.gif' />",
			"bStateSave": true //保存状态到cookie *************** 很重要 ， 当搜索的时候页面一刷新会导致搜索的消失。使用这个属性就可避免了 
			}
		});
	});
	<%String result = (String)session.getAttribute("result");
	String isError = (String)session.getAttribute("isError");
	if(result != null) {
		if(isError.equals("0")) {%>
			swal("成功", "<%=result%>", "success");
	<%} else {%>
			swal("失败", "<%=result%>", "error");
	<%}
	} 
	session.removeAttribute("result");
	session.removeAttribute("isError");%>
		function saveInfo() {
		   swal(
				{
					title : "确定保存信息吗?",
					text : "确定后将不可修改!",
					type : "warning",
					showCancelButton : true,
					confirmButtonColor : "#DD6B55",
					confirmButtonText : "确定",
					cancelButtonText : "取消",
					closeOnConfirm : false,
					closeOnCancel : true
				},
			function(isConfirm) {
				if (isConfirm) { //确定
					// 提交表单
					document.getElementById("functionType").value = "updateInfo";
					document.getElementById("updateInfoForm").submit();
				} else {

				}
			});
		}

		function resetPassword() {
			swal(
				{
					title : "是否将密码重置为123456?",
					text : "确定后将不可修改!",
					type : "warning",
					showCancelButton : true,
					confirmButtonColor : "#DD6B55",
					confirmButtonText : "确定",
					cancelButtonText : "取消",
					closeOnConfirm : false,
					closeOnCancel : true
				},
				function(isConfirm) {
					if (isConfirm) { //确定
						// 提交表单
						document.getElementById("functionType").value = "resetPassword";
						document.getElementById("updateInfoForm").submit();
					} else {
						//取消
					}
				});
		}
	</script>
</body>
</html>