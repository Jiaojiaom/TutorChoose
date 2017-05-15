<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="db.TeacherMsDAO"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导师列表</title>
<link rel="stylesheet" type="text/css" href="../lib/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="../lib/bootstrap.min.css">
<link rel="stylesheet" href="../lib/sweetalert.css">
<link rel="stylesheet" type="text/css" href="../lib/table.css">
<script type="text/javascript" language="javascript" src="//code.jquery.com/jquery-1.12.4.js">
</script>
<script type="text/javascript" language="javascript" src="../lib/sweetalert-dev.js"></script>
<script type="text/javascript" language="javascript" src="../lib/jquery.dataTables.min.js">
</script>
</head>
<body>
	<%@ include file="../public/navbar.html" %>
	<span class="nav">导师端>>学生选择情况</span>
	<div class="main">
		<table id="teacher" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>学号</th>
					<th>姓名</th>
					<th>性别</th>
					<th>专业</th>
					<th>班级</th>
					<th>电话</th>
					<th>绩点</th>
					<th>选择日期</th>
					<th>状态</th>
					<% if(true) { //该导师具有反选资格 %>
						<th>操作</th>
					<% } %>
				</tr>
			</thead>
		</table>
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
<script type="text/javascript" language="javascript">
	/*获取从servlet返回的信息，显示成功或失败*/
	<%
	String result = (String)request.getAttribute("result");
	String isError = (String)request.getAttribute("isError");
	if(result != null) {
		if(isError.equals("0")) {
	%>
			swal("成功", "<%=result%>", "success");
	<%  } else {%>
			swal("失败", "<%=result%>", "error");
	<%	}
	} %>
	
	/*获取表格中的数据*/
	var array = new Array();
	var k=0;
	<%   
	//SimpleDateFormat formater= new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
	TeacherMsDAO dao = new TeacherMsDAO();
	ArrayList<Map<String, String>> teacher = dao.queryTeacherList();
	for (Map<String, String> map : teacher) {
		//String date = String.valueOf(formater.format(map.get("selectdate")));
		int stuid = Integer.valueOf(map.get("stuid"));
		String sex = ":)";
		if(map.get("sex").equals("F"))
			sex = "女";
		else if(map.get("sex").equals("M"))
			sex = "男";
		String state = null;
		String oper = "";
		if(map.get("choosedstate").equals("0")) {
			state = "<strong><font color='#777'>待定</font></strong>";
			if(true) { //该导师具有反选资格 
				oper = "<div class='btn-group'><button class='btn btn-primary' onclick='chooseSuccess("+stuid+")'>通过</button><button class='btn btn-danger' onclick='chooseFail("+stuid+")'>淘汰</button></div>";
			}
		}
		else if(map.get("choosedstate").equals("1"))
			state = "<strong><font color='red'>淘汰</font></strong>";
		else if(map.get("choosedstate").equals("2"))
			state = "<strong><font color='#5d9cec'>成功</font></strong>";
		%>
		var t = new Array(10);
		t[0] = "<%=stuid%>";
		t[1] = "<%=map.get("stuname")%>";
		t[2] = "<%=sex%>";
		t[3] = "<%=map.get("deptid")%>";
		t[4] = "<%=map.get("classid")%>";
		t[5] = "<%=map.get("tel")%>";
		t[6] = "<%=map.get("grade")%>";
		t[7] = "<%=map.get("selectdate")%>";
		t[8] = "<%=state%>";
		<% if(true) { %> //该导师具有反选资格
			t[9] = "<%=oper%>";
		<% } %>
		array[k] = t;
		k++;
	<%}%>

	/*datatable表格设定*/
	$(document).ready(function() {
		$('#teacher').DataTable( {
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
			"sZeroRecords": "没有检索到数据",
			"bStateSave": true //保存状态到cookie *************** 很重要 ， 当搜索的时候页面一刷新会导致搜索的消失。使用这个属性就可避免了 
			}
		} );
	} );
	
	/*通过学生事件*/
	function chooseSuccess(stuid) {
		swal({
		 	title: "确定通过该学生吗?",
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
		    	//将学生学号传递到servlet
		    	var tmp = document.createElement("form");
			    var action = "selectStudent?stuid="+stuid; 
			    tmp.action = action; 
			    tmp.method = "post"; 
			    document.body.appendChild(tmp); 
			    tmp.submit();
			    return tmp;
		  } else {
		    	//取消
		  }
		});
	}
	
	/*淘汰学生事件*/
	function chooseFail(stuid) {
		swal({
		 	title: "确定淘汰该学生吗?",
		  	text: "淘汰后将不可修改!",
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
		    	//将学生学号传递到servlet
		    	var tmp = document.createElement("form");
			    var action = "selectStudent?stuid="+stuid; 
			    tmp.action = action; 
			    tmp.method = "post"; 
			    document.body.appendChild(tmp); 
			    tmp.submit();
			    return tmp;
		  } else {
		    	//取消
		  }
		});
	}

</script>
</html>