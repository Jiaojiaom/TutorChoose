<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.util.*"%>
<%@ page import="db.StuMsDAO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
<!--
.datalist {
	border: 1px solid #0058a3; /* 表格边框 */
	font-family: Arial;
	border-collapse: collapse; /* 边框重叠 */
	background-color: #eaf5ff; /* 表格背景色 */
	font-size: 14px;
	margin: 0 auto;
	width: 80%;
}

.datalist caption {
	padding-bottom: 5px;
	font-size: 18px;
	text-align: center;
	font-weight: bold;
}

.datalist th {
	border: 1px solid #0058a3; /* 行名称边框 */
	background-color: #4bacff; /* 行名称背景色 */
	color: #FFFFFF; /* 行名称颜色 */
	font-weight: bold;
	padding-top: 4px;
	padding-bottom: 4px;
	padding-left: 12px;
	padding-right: 12px;
	text-align: center;
}

.datalist td {
	border: 1px solid #0058a3; /* 单元格边框 */
	text-align: center;
	padding-top: 4px;
	padding-bottom: 4px;
	padding-left: 10px;
	padding-right: 10px;
}

.datalist tr.altrow {
	background-color: #c7e5ff; /* 隔行变色 */
}
-->
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息列表</title>
</head>
<body>
	<%
		if (request.getAttribute("result") != null) { //判断保存在request范围内的对象是否为空
			out.println("<script >alert('" + request.getAttribute("result")
					+ "');</script>"); //页面显示提示信息    	
		}
	%>
    
	<table class="datalist">
		<caption>学生信息</caption>
		<tr>
			<th style="width: 6%;">序号</th>
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
		</tr>
		<%
		    StuMsDAO stuDao = new StuMsDAO();
			ArrayList<Map<String, String>> stuMsgs = stuDao.queryStudentAll();
			int i=0;
			for (Map<String, String> stuMsg : stuMsgs) {	
			%>
		<tr>
			<td style="width: 6%;"><%=++i %></td>
			<td><%=stuMsg.get("stuid") %></td>
			<td><a href="studentDetail.jsp?stuid=<%=stuMsg.get("stuid") %>"><%=stuMsg.get("stuname")%></a></td>
			<td><%=stuMsg.get("deptid") %></td>
			<td><%=stuMsg.get("classid") %></td>
			<td><%=stuMsg.get("sex") %></td>
			<td><%=stuMsg.get("spassword") %></td>
			<td><%=stuMsg.get("grade") %></td>
			<td><%=stuMsg.get("tel") %></td>
			<td><%=stuMsg.get("intro") %></td>
			<td><%=stuMsg.get("teacherid") %></td>
			<td><%=stuMsg.get("choosedstate") %></td>
			<td><%=stuMsg.get("selectdate") %></td>
			<td><a href="editStuInfo.jsp?stuid=<%=stuMsg.get("stuid") %>">编辑</a></td>
		    <td><a href="deleteStuInfo.jsp?stuid=<%=stuMsg.get("stuid") %>">删除</a></td>
		</tr>
			<%
			}
		    %>
		 <tr>
		     <a href="excel.jsp?exportToExcel=YES">Export to Excel</a>
		     <a href="teacherList.jsp">教师列表</a>
		     <a href="importInfo.jsp">导入</a>
		 </tr>
	</table>	
	<%
	    // 导出为excel
		String exportToExcel = request.getParameter("exportToExcel");
		if (exportToExcel != null && exportToExcel.toString().equalsIgnoreCase("YES")) {
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "inline; filename=excel.xls");
		}
	%>
</body>
</html>
