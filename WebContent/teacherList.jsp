<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.util.*"%>
<%@ page import="db.TeacherMsDAO" %>
<%@ page import="javabean.TeacherMsg" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="lib/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="lib/table.css">
<script type="text/javascript" language="javascript" src="//code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript" language="javascript" src="lib/jquery.dataTables.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教师信息列表</title>
</head>
<body>
	<%
		if (request.getAttribute("result") != null) { //判断保存在request范围内的对象是否为空
			out.println("<script >alert('" + request.getAttribute("result")
					+ "');</script>"); //页面显示提示信息    	
		}
	%>
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
</body>
<script type="text/javascript">
	/*获取表格中的数据*/
	var array = new Array();
	var k=0;
	<%   
	TeacherMsDAO teacherDao = new TeacherMsDAO();
	ArrayList<Map<String, String>> teacherMsgs = teacherDao.queryTeacherList();
	for (Map<String, String> teacherMsg : teacherMsgs) {
		String  editTeacher = "<a href='editTeacherInfo.jsp?teacherid="+teacherMsg.get("teacherid")+"'>编辑</a>";
		String  deleteTeacher = "<a href='deleteTeacherInfo.jsp?teacherid="+teacherMsg.get("teacherid")+"'>删除</a>";
		String sex = "";
		if(teacherMsg.get("sex").equals("F")){
			sex="男";
		} else if(teacherMsg.get("sex").equals("M")){
			sex="女";
		}
	%>
		var t = new Array(12);
		t[0] = "<%=teacherMsg.get("teacherid")%>";
		t[1] = "<%=teacherMsg.get("teachername")%>";
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
		array[k] = t;
		k++;
	<%
	}
	%>

	/*datatable表格设定*/
	$(document).ready(function(){
		$('#teacher').DataTable({
			data: array,
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
</html>
