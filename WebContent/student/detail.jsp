<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="db.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
<link rel="stylesheet" type="text/css" href="../lib/bootstrap.min.css">
<link rel="stylesheet" href="../lib/sweetalert.css">
<script type="text/javascript" language="javascript" src="../lib/jquery.min.js"></script>
<script type="text/javascript" language="javascript" src="../lib/sweetalert-dev.js"></script>
</head>
<body>
	<%@ include file="navbar.jsp" %>
	<%
	HttpSession s = request.getSession(); 
	String systemLimit = (String)application.getAttribute("LimitModel");
	if(s.getAttribute("stuId") != null){
	String stuId = (String)s.getAttribute("stuId");
	StudentDAO so = new StudentDAO();
	Map<String,String> ot = so.studentInfo(stuId);
	//导师详细信息+学生列表
	String teacherid = request.getParameter("teacherid");
	int hasTeacher = 0;
	if(ot.get("teacherid") != null && ot.get("teacherid").length() > 0){
		if(ot.get("teacherid").equals(teacherid) && (ot.get("choosedstate").equals("1") || ot.get("choosedstate").equals("3")))
		hasTeacher = 1;
	}
	//查看学生是否有待定导师
//	else{
//		if(ot.get("choosedstate").equals("1")){
//		String t = so.getNowTeacher(stuId);
//		if(t != null && teacherid.equals(t)){
//			hasTeacher = 1;
//		}
//		}
//	}
	Map<String,String> tid = so.teacherInfoDetail(teacherid);
	String sex = null;
	String privilege = null;
	if(tid.get("sex").equals("M")){
		sex = "男";
	}
	else sex = "女";
	//能否反选
	if(tid.get("privilege").equals("1")){
		privilege = "能";
	}
	else privilege = "否";
	//{stuid, classname, stuname, choosedstate}
	//已选学生数
	int studentNum = 0;
	String studentl = "";
	ArrayList<Map<String,String>> sctlist = so.studentChoosedTeacher(teacherid);
	for(Map<String, String> sct : sctlist){
		studentl += sct.get("stuname") + " ";
		studentNum++;
	}
	%>
	<span class="nav">学生端>>导师详情页</span>
	<div class="main">
		<form action="SelectTeacher" method="post" style="width: 100%">
			<div style="display: flex;flex-direction: row;width: 100%">
				<div class="panel" style="width: 40%">
					<table style="width: 100%">
						<tr>
							<td>
								姓名
							</td>
					    	<td><input type="text" value="<%=tid.get("teachername") %>" readonly></td>
						</tr>
						<tr>
							<td>
								性别
							</td>
					    	<td><input type="text" placeholder="请填写性别" value="<%=sex %>" id="teacherSex" name="teacherSex" readonly></td>
						</tr>
						<tr>
							<td>
								工号
							</td>
					    	<td><input type="text" value="<%=teacherid %>" readonly name="teacherId"></td>
						</tr>
						<tr>
							<td>
								专业
							</td>
					    	<td><input type="text" value="<%=tid.get("deptname") %>" readonly></td>
						</tr>
						<tr>
							<td>
								学历
							</td>
					    	<td><input type="text" value="<%=tid.get("title") %>" readonly></td>
						</tr>
						<tr>
							<td>
								能否反选
							</td>
					    	<td><input type="text" value="<%=privilege %>" readonly name="pri"></td>
						</tr>
						<%if(tid.get("privilege").equals("3")){ 						
						out.print("<tr><td>选择规则</td><td><input type='text' value='先到先得' readonly ></td></tr>");
						}
						else if(tid.get("privilege").equals("4")){
							out.print("<tr><td>选择规则</td><td><input type='text' value='按绩点排名' readonly ></td></tr>");			
						}%>
						<tr>
							<td>
								电话号码
							</td>
					    	<td><input type="text" placeholder="请填写电话号码" value="<%=tid.get("tel") %>" id="teacherTel" name="teacherTel" readonly></td>
						</tr>
						<tr>
							<td>
								选择情况
							</td>
					    	<td><input type="text" placeholder="" value="<%=studentNum %>/<%=tid.get("studentcount") %>" id="selectCon" name="selectCon" readonly></td>
						</tr>
						<tr>
							<td>
								选择详情
							</td>
					    	<!-- <td><input type="text" placeholder="" value="<%=studentl %>" id="selectCon" name="selectCon" readonly></td> -->
					    	<td><textarea rows="3" cols="1" value="<%=studentl %>" id="selectCon" name="selectCon" readonly><%=studentl %></textarea></td>
						</tr>
					</table>
				</div>
				<div style="width: 60%;margin: 10px">
					<h5>个人简介</h5>
					<textarea rows="18" class="mb-lg form-control" id="teacherIntro" name="teacherIntro" readonly><%=tid.get("intro") %></textarea>
				</div>
			</div>
			<div style="text-align: center">
			<%if(systemLimit.equals("on")){ %>
				<%if(hasTeacher == 0) {%>
				<button type="submit" class="btn btn-primary">选择此老师</button>
				<%}else{ %>
				<button type="button" class="btn btn-danger" onclick="window.location.href='../student/cancle.jsp?teacherid=<%=teacherid %>'">取消选择 </button>
				<%} }%>
			</div>
		</form>
	</div>
	<%}else{
		response.sendRedirect("../login.jsp");
	}
	%>
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
	table textarea{
		width: 90%;
		border: 1px solid #c8c8c8;
    	border-radius: 4px;
	}
	.main table{
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
    	width: 90%;
	}
	.sweet-alert .alertInput input {
		display: block;
		float: left;
		width: 60%;
	}
	.alertInput label span {
		float: left;
		width: 40%;
		margin: 17px 0;
	}
	.nav{
		margin-top: 50px;
		line-height:40px;
		font-size:10px;
		align-self: flex-start;
		margin-left:2%;
	}
</style>
<script type="text/javascript">
<%
String result = null, isError = null;
//request.setCharacterEncoding("utf8");
//String result = (String)request.getParameter("result");
//String isError = (String)request.getParameter("isError");
result = (String)s.getAttribute("result");
isError = (String)s.getAttribute("isError");
s.removeAttribute("result");
s.removeAttribute("isError");
if(result != null && result.length() > 0) {
	if(isError.equals("0")) {
%>
		swal("成功", "<%=result%>", "success");
<%  } else {%>
		swal("失败", "<%=result%>", "error");
<%	}
} 
%>
</script>
</html>