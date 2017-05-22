<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="db.StudentMsDAO" %>
<%@ page import="com.bean.StudentMsg" %>
<%@ page import="db.DeptMsDAO" %>
<%@ page import="db.ClassMsDAO" %>
<%@ page import="db.TeacherMsDAO" %>
<%@ page import="com.bean.TeacherMsg" %>
<%@ page import="db.StudentMsDAO" %>
<%@ page import="db.SelectTeacherDAO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导师端>>教师信息</title>
<link rel="stylesheet" type="text/css" href="../lib/bootstrap.min.css">
<script type="text/javascript" language="javascript" src="../lib/jquery.min.js">
</script>
<link rel="stylesheet" href="../lib/sweetalert.css">
<script type="text/javascript" language="javascript" src="../lib/sweetalert-dev.js"></script>
<link rel="stylesheet" type="text/css" href="../lib/checkBox.css">
<link rel="stylesheet" type="text/css" href="../lib/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="../lib/table.css">
<script type="text/javascript" language="javascript" src="../lib/jquery.dataTables.min.js"></script>
<style type="text/css">
	body{
		margin: 0;
		padding: 0;
		height:100vh;
	}
	.main{
		display: flex;
		flex-direction: column;
		width: 90%;
		margin-left: 2%;
		padding: 10px;
	}
/* 	.main table{
		height: 60vh;
	} */
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
	.changeAvatar{
		border: 1px solid rgba(59,194,29,.7);
    	color: #42c02e!important;
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
    	background-color: hsla(0,0%,71%,.1);
    	height:35px;
	}
	select {
    	padding: 5px 10px;
    	font-size: 15px;
    	border: 1px solid #c8c8c8;
    	border-radius: 4px;
    	background-color: hsla(0,0%,71%,.1);
    	width: 61%;
    	height:35px;
	}
	.nav{
		margin-top: 50px;
		line-height:40px;
		font-size:10px;
		align-self: flex-start;
		margin-left:2%;
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
	<%@ include file="navbar.jsp" %>
	<span class="nav">导师端>>个人信息中心</span>
	<div class="main">
	<%
	   String teacherid=request.getParameter("teacherid");
	   TeacherMsDAO teacherDao = new TeacherMsDAO();
	   TeacherMsg teacherMsg = teacherDao.findByTeacherID(teacherid);
	 %>
	   <form action="updateInfo" method="post" style="width: 100%" id="setSinglePrivilegeForm">
			<div>
			    <input name="functionType" type="hidden" id="functionType" value="toggleSinglePrivilege" />
		        <input name="TeacherName" type="hidden" value="<%=teacherMsg.getTeacherName()%>" />
		        <input name="TeacherID" type="hidden" value="<%=teacherMsg.getTeacherID()%>" />
				<div style="float:right;margin:0px 10px;">
						<span style="line-height: 30px;vertical-align: super;">导师反选资格</span>
						<section class='model-1' style="display:inline-block;">
						    <% if(teacherDao.getPrivilege(teacherMsg.getTeacherID())==1){ %>
							  <div class='checkbox' style="width:100%;">     
							    <input type='checkbox' class="tutorState" name="SinglePrivilege" value=1 checked/>
							    <label></label>
							  </div>
							<% } else { %>
							  <div class='checkbox' style="width:100%;">     
							    <input type='checkbox' class="tutorState" name="SinglePrivilege" value=0 />
							    <label></label>
							  </div>
							<% } %> 
						</section>
				</div>
			</div>
		</form>
		<form action="updateInfo" method="post" style="width: 100%" id="updateInfoForm">
			<div style="display: flex; flex-direction: row; width: 100%">
				<div class="panel" style="width: 40%;min-height: 480px;max-height: 480px;height: 480px;">
					<div class="table" style="margin-top: 60px;">
						<div class="name">
							<p>姓名</p>
							<p>性别</p>
							<p>工号</p>
							<p>专业</p>
							<p>职称</p>
							<p>学生人数</p>
							<p>电话号码</p>
						</div>
						<div class="detail">
							<input type="text" value="<%=teacherMsg.getTeacherName()%>" name="TeacherName">
							<% if(teacherMsg.getSex().equals("M")) { %>
							<div style="display: flex;flex-direction: row;margin-bottom: 10px;">
								<label class="checkbox-inline" style="display:flex;width:30%">
									<input type="radio" placeholder="请填写性别" value="男" name="Sex" checked />男
								</label>
								<label class="checkbox-inline" style="display:flex;width:30%">
									<input type="radio" placeholder="请填写性别" value="女" name="Sex" />女
								</label>
							</div>
							<% } else { %>
							<div style="display: flex;flex-direction: row;  margin-bottom: 10px;">
								<label class="checkbox-inline" style="display:flex;width:30%">
									<input type="radio" placeholder="请填写性别" value="男" name="Sex" />男
								</label>
								<label class="checkbox-inline" style="display:flex;width:30%">
									<input type="radio" placeholder="请填写性别" value="女" name="Sex" checked/>女
								</label>
							</div>
							<% } %>
							<input name="userType" type="hidden" value="teacher" />
							<input name="TeacherID" type="text" value="<%=teacherMsg.getTeacherID()%>" readonly> 
						    <select name="DeptID" id="DeptID" value=<%=teacherMsg.getDeptID()%>>
						      <%
							    DeptMsDAO deptDao = new DeptMsDAO();
								ArrayList<Map<String, String>> deptMsgs = deptDao.queryDeptAll();
								for (Map<String, String> deptMsg : deptMsgs) {	
							  %>
								<%
								  if(deptMsg.get("deptid").equals(teacherMsg.getDeptID())){
								%>
								   <option value=<%=deptMsg.get("deptid")%> selected = "selected"><%=deptMsg.get("deptname")%></option>
								<%
								  }else {
								 %>
								   <option value=<%=deptMsg.get("deptid")%>><%=deptMsg.get("deptname")%></option>
							    <%
								  }
								}
								deptDao.close();
						      %>
						    </select>
						    <!-- /*职能*/ -->
						     <select name="Title" id="Title"  value="<%=teacherMsg.getTitle()%>">
						    	 <%
						    	 if(teacherMsg.getTitle().equals("教授")){
								 %>
									 <option value="教授" selected>教授</option>
									 <option value="副教授">副教授</option>
								 <%
								  } else if(teacherMsg.getTitle().equals("副教授")){
								 %>
								      <option value="教授">教授</option>
									  <option value="副教授" selected>副教授</option>  
						    	<% } %>
						    </select>
						    <input type="text" value=<%=teacherMsg.getStudentCount() %> name="StudentCount" readonly>
						    <input type="hidden" value=<%=teacherMsg.getPrivilege() %> name="Privilege">
							<input type="text" placeholder="请填写电话号码" value="<%=teacherMsg.getTel()%>" name="tel">
						</div>
					</div>
				</div>
					
				<div class="panel" style="width: 60%;margin-left:20px; text-align:left;min-height: 480px;max-height: 480px;height: 480px;">
					
					
					<h5>个人简介</h5>
					<textarea name="Intro" rows="3" class="mb-lg form-control" 
					style="resize: none;height: 100px;max-height: 100px;"><%=teacherMsg.getIntro()%></textarea>
					<div style="margin-top:45px;">
					<table id="selectStuTable" class="display" cellspacing="0" width="100%" >
						<thead>
							<tr>
								<th>学号</th>
								<th>姓名</th>
								<th>绩点</th>
								<th>状态</th>
								<th>选择时间</th>
							</tr>
						</thead>
					</table>
					</div>
				</div>
			</div>
			
			<div style="margin-left: 33%;margin-top: 20px;">
			    <input name="functionType" type="hidden" id="functionType" value="updateInfo" />
				<button type="button" class="btn btn-primary" onClick="saveInfo()" style="margin-right: 10%;">保存个人信息</button>
				<button type="button" class="btn btn-danger" onclick="resetPassword()">重置密码 </button>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" language="javascript">
	/*获取从servlet返回的信息，显示成功或失败*/
	<%
	String result = (String)session.getAttribute("result");
	String isError = (String)session.getAttribute("isError");
	if(result != null) {
		if(isError.equals("0")) {
	%>
			swal("成功", "<%=result%>", "success");
	<%  } else {%>
			swal("失败", "<%=result%>", "error");
	<%	}
	} 
	session.removeAttribute("result");
	session.removeAttribute("isError");
	%>
	
	/*获取表格中的数据*/
	var array = new Array();
	var k=0;
	<%   
	SelectTeacherDAO dao = new SelectTeacherDAO();
	ArrayList<Map<String, String>> selectTeacher = dao.querySelectStu(teacherMsg.getTeacherID());
	dao.close();
	for (Map<String, String> map : selectTeacher) {
		String state = "0";
		String oper = "";
		if(map.get("choosedstate").equals("0"))
			state = "<strong><font color='#777'>待定</font></strong>";
		if(map.get("choosedstate").equals("1"))
				state = "<strong><font color='#777'>淘汰</font></strong>";
		else if(map.get("choosedstate").equals("2"))
			state = "<strong><font color='#5d9cec'>成功</font></strong>";
		%>
		var t = new Array();
		t.push("<%=map.get("stuid")%>");
		t.push("<%=map.get("stuname")%>");
		t.push("<%=map.get("grade")%>");
		t.push("<%=state%>");
		t.push("<%=map.get("selectdate")%>");
		console.log(t);
		array[k] = t;
		k++;
	<% } %>
	
	/*datatable表格设定*/
	$(document).ready(function() {
		$('#selectStuTable').DataTable( {
			data: array,
			"iDisplayLength":3,
			/*sScrollY:true, */
			/* "sScrollY": "100px", 
			"sScrollX": "98%", */
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
			"sZeroRecords": "还未有学生选择您",
			//"sProcessing": "<img src='./loading.gif' />",
			"bStateSave": true //保存状态到cookie *************** 很重要 ， 当搜索的时候页面一刷新会导致搜索的消失。使用这个属性就可避免了 
			}
		});
	});
	
	function saveInfo() {
		swal({
		 	title: "确定保存信息吗?",
		  	text: "确定后将不可修改!",
		  	type: "warning",
		  	showCancelButton: true,
		  	confirmButtonColor: "#DD6B55",
		  	confirmButtonText: "确定",
		  	cancelButtonText: "取消",
		  	closeOnConfirm: false,
		  	closeOnCancel: true
		},
		function(isConfirm){
		  if (isConfirm) { //确定
			// 提交表单
			document.getElementById("functionType").value="updateInfo";
		  	document.getElementById("updateInfoForm").submit();
		  } else {
		   
		  }
		});
	}
	function resetPassword() {
		swal({
		 	title: "是否将密码重置为123456?",
		  	text: "确定后将不可修改!",
		  	type: "warning",
		  	showCancelButton: true,
		  	confirmButtonColor: "#DD6B55",
		  	confirmButtonText: "确定",
		  	cancelButtonText: "取消",
		  	closeOnConfirm: false,
		  	closeOnCancel: true
		},
		function(isConfirm){
		  if (isConfirm) { //确定
			// 提交表单
			document.getElementById("functionType").value="resetPassword";
		  	document.getElementById("updateInfoForm").submit();
		  } else {
		    	//取消
		  }
		});
	}
	
	$(".tutorState").change(function() {
		var titleMsg = "";
		if($(".tutorState").get(0).checked) {
			titleMsg = "确定开启导师反选功能吗？";
			$(".tutorState").val(1);
		}
		else {
			titleMsg = "确定关闭导师反选功能吗？"
			$(".tutorState").val(0);
		}
		console.log($(".tutorState").val());
		swal({
		 	title: titleMsg,
		  	text: "",
		  	type: "warning",
		  	showCancelButton: true,
		  	confirmButtonColor: "#DD6B55",
		  	confirmButtonText: "确定",
		  	cancelButtonText: "取消",
		  	closeOnConfirm: false,
		  	closeOnCancel: true
		},
		function(isConfirm){
		  	if (isConfirm) { //确定
		  		// 提交表单
		  		console.log("提交表单"+"setSinglePrivilegeForm");
		  		document.getElementById("setSinglePrivilegeForm").submit();
		  	} else { //取消
		  		if($(".tutorState").checked) {
		  			$(".tutorState").prop("checked",false);
		  		}
		  		else {
		  			$(".tutorState").prop("checked","checked"); 
		  		} 
		  	}
		});
	});
</script>
</html>