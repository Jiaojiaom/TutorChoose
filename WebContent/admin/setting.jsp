<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="db.TeacherMsDAO"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设置页面</title>
<link rel="stylesheet" type="text/css" href="../lib/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="../lib/bootstrap.min.css">
<link rel="stylesheet" href="../lib/sweetalert.css">
<link rel="stylesheet" type="text/css" href="../lib/table.css">
<script type="text/javascript" language="javascript" src="//code.jquery.com/jquery-1.12.4.js">
</script>
<script type="text/javascript" language="javascript" src="../lib/sweetalert-dev.js"></script>
<script type="text/javascript" language="javascript" src="../lib/jquery.dataTables.min.js"></script>
</head>
<body>
	<%@ include file="../public/navbar.html" %>
	<span class="nav">管理员端>>设置中心</span>
	<div class="main">
		<form action="teacherInfo" method="post" style="width: 100%">
			<div class="panel">
				<p>设置导师可带人数数量</p>
				<p><input type="number" value=5></p>
				<p>
				<div id="teacherListForm">
					<table id="teacher" class="display" cellspacing="0" width="100%">
					  <thead>
						<tr>
							<th>工号</th>
							<th>姓名</th>
							<th>职称</th>
							<th>设置可选学生数量</th>
							<th>开启反选</th>
						</tr>
					  </thead>
					</table>
				</div>	
				</p>
			</div>
		</form>
		<form action="teacherInfo" method="post" style="width: 100%">
			<div class="panel">
				<p>是否开启导师反选模式</p>
				<p><button type="submit" class="btn btn-primary">是否开启导师反选模式</button></p>
				<strong>学生选择导师方式为：</strong>
				<p>按照专业优先级，当本专业的导师名额已满后才可以选择其他专业的老师。     <p>
				<p>如果导师反选功能开启，有反选资格的导师不再受学生名额限制。</p>
				<p>此时由导师自己决定带多少个学生，具体哪几个学生。</p>
			</div>
		</form>
		<form action="teacherInfo" method="post" style="width: 100%">
			<div class="panel">
				<p>设置学生成功选择导师的规则</p>
				<p>
				  <input type="radio" value ="1"/>先到先得
				  <input type="radio" value ="2"/>按照学生的成绩排名
				</p>
				<p>是否开启选导师功能</p>
				<p>
				 <button type="button" class="btn btn-danger" onclick="startChooseTutor()">开启选导师</button>
				 <button type="button" class="btn btn-danger" onclick="endChooseTutor()">结束选导师 </button>
				</p>
			</div>
		</form>
	</div>
</body>
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
	 .main table{
		height: 20vh;
	}
	.panel {
	  	padding: 10px;
	  	margin: 10px;
	  	-webkit-box-shadow: 0 1px 1px #5d9cec;
	  	box-shadow: 0 1px 1px #5d9cec;
	}
	input[type=text] {
    	padding: 5px 10px;
    	font-size: 15px;
    	border: 1px solid #c8c8c8;
    	border-radius: 4px;
    	background-color: hsla(0,0%,71%,.1);
	}
	.nav{
		margin-top: 50px;
		line-height:40px;
		font-size:10px;
		margin-left:2%;
	}
</style>
<script type="text/javascript" language="javascript">
	function startChangeTutor() {
		
	}
    function endChangeTutor() {
		
	}
    /*获取表格中的数据*/
	var arrayTeacher = new Array();
	var k=0;
	<%   
	TeacherMsDAO teacherDao = new TeacherMsDAO();
	ArrayList<Map<String, String>> teacherMsgs = teacherDao.queryTeacherList();
	for (Map<String, String> teacherMsg : teacherMsgs) {
		String  viewTeacher = "<a href='teacherDetail.jsp?teacherid="+teacherMsg.get("teacherid")+"'>"+teacherMsg.get("teachername")+"</a>";
		String  setStudentcount = "<input type='number' value='5'/>";
		String  setPrivilege = "<a class='btn btn-success'>开启反选资格</a>";
	%>
		var t = new Array(5);
		t[0] = "<%=teacherMsg.get("teacherid")%>";
		t[1] = "<%=viewTeacher%>";
		t[2] = "<%=teacherMsg.get("title")%>";
		t[3] = "<%=setStudentcount%>";
		t[4] = "<%=setPrivilege%>";
		arrayTeacher[k] = t;
		k++;
	<%
	}
	teacherDao.close();
	%>
	
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
	});
</script>
</html>