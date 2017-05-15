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
<link rel="stylesheet" type="text/css" href="../lib/table.css">
<link rel="stylesheet" type="text/css" href="../lib/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="../lib/bootstrap.min.css">
<link rel="stylesheet" href="../lib/sweetalert.css">
<script type="text/javascript" language="javascript" src="//code.jquery.com/jquery-1.12.4.js">
</script>
<script type="text/javascript" language="javascript" src="../lib/sweetalert-dev.js"></script>
<script type="text/javascript" language="javascript" src="../lib/jquery.dataTables.min.js">
</script>
</head>
<body>
	<%@ include file="../public/navbar.html" %>
	<span class="nav">学生端>>导师信息中心</span>
	<div class="main">
			<table id="teacher" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>工号</th>
                    <th>姓名</th>
					<th>性别</th>
					<th>专业</th>
					<th>职位</th>
					<th>选择情况</th>
					<th>操作</th>
				</tr>
			</thead>
			 <tbody>
				<tr>
					<td>T02001</td>
			    	<td>程靖</td>
					<td>女</td>
					<td>前端</td>
					<td>副教授</td>
					<td>3/3</td>
					<td><a href="detail.jsp">详细信息</a></td>
				</tr>
				<tr>
					<td>T07002</td>
			    	<td>沈丽</td>
					<td>女</td>
					<td>前端</td>
					<td>副教授</td>
					<td>3/3</td>
					<td><a href="detail.jsp">详细信息</a></td>
				</tr>
				<tr>
					<td>T07002</td>
			    	<td>黄三清</td>
					<td>女</td>
					<td>前端</td>
					<td>副教授</td>
					<td>3/3</td>
					<td><a href="detail.jsp">详细信息</a></td>
				</tr>
				<tr>
					<td>T08002</td>
			    	<td>李琳分</td>
					<td>男</td>
					<td>java</td>
					<td>副教授</td>
					<td>3/3</td>
					<td><a href="detail.jsp">详细信息</a></td>
				</tr>
				<tr>
					<td>T07003</td>
			    	<td>龙永图</td>
					<td>女</td>
					<td>jsp</td>
					<td>副教授</td>
					<td>3/3</td>
					<td><a href="detail.jsp">详细信息</a></td>
				</tr>
				</tbody>
		</table>
	</div>
</body>
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
<%-- <script type="text/javascript" language="javascript">
	/*获取从servlet返回的信息，显示成功或失败*/
<%   
	TeacherDAO dao = new TeacherDAO();
	ArrayList<Map<String, String>> teacher = dao.queryAllTeacher();
	for (Map<String, String> map : teacher) {
		int teacherid = Integer.valueOf(map.get("teacherid"));
		String sex = ":)";
		if(map.get("sex").equals("F"))
			sex = "女";
		else if(map.get("sex").equals("M"))
			sex = "男";
		
		%>
		var t = new Array(10);
		t[0] = "<%=teacherid%>";
		t[1] = "<%=map.get("teachername")%>";
		t[2] = "<%=sex%>";
		t[3] = "<%=map.get("deptid")%>";
		t[4] = "<%=map.get("title")%>";
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
	</script> --%>
</html>