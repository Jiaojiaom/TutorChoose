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
<script src="../lib/modal.js"></script>
</head>
<body>
	<%@ include file="navbar.jsp" %>
	<%
	request.setCharacterEncoding("utf-8");
	HttpSession s = request.getSession(); 
	if(s.getAttribute("stuId") != null){
	String stuId = (String)s.getAttribute("stuId");
	StudentDAO so = new StudentDAO();
	Map<String,String> ot = so.studentInfo(stuId);
	//System.out.println(ot);
	//学生学号，学生姓名，学生班级，学生性别，学生成绩，学生简介，导师工号（可能为null），联系方式
	String sex = null;
	String teacher = null;
	String choosedState = null;
	if(ot.get("sex").equals("M")){
		sex = "男";
	}
	else sex = "女";
	//如果导师存在显示导师姓名
	if(ot.get("teacherid") != null && ot.get("teacherid").length() > 0){
		teacher = so.getTeacherName(ot.get("teacherid"));
	}
	else teacher = "无";
	//选择情况
	if(ot.get("choosedstate").equals("0")){
		choosedState = "未选择导师";
	}else if(ot.get("choosedstate").equals("1")){
		choosedState = "导师待定";
	}else if(ot.get("choosedstate").equals("2")){
		choosedState = "淘汰";
	}else{
		choosedState = "选择成功";
	}
	%>
	
	<form action="ChangePwd" method="post" class="form-horizontal" onsubmit="return checkPwd()">
		<div class="modal fade" id="changePasswordModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog" style="margin-top: 120px">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		                <h4 class="modal-title" id="changePasswordModal">修改密码</h4>
		            </div>
		            <div class="modal-body">
			            <div class="form-group">
						    <label class="col-sm-4 control-label">原密码：</label>
						    <div class="col-sm-7">
    							<input type="password" class="form-control" id="oldpw" placeholder="请填写原密码" name="oldPassword">
    						</div>
						</div>
						<div class="form-group">
						    <label class="col-sm-4 control-label">新密码：</label>
						    <div class="col-sm-7">
    							<input type="password" class="form-control" id="newpw" placeholder="请填写新密码" name="newPassword">
    						</div>
						</div>
						<div class="form-group">
						    <label class="col-sm-4 control-label">重复新密码：</label>
						    <div class="col-sm-7">
    							<input type="password" class="form-control" id="reNewpw" placeholder="请重复新密码" name="reNewPassword">
    						</div>
						</div>
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		                <button type="submit" class="btn btn-primary">确定</button>
		            </div>
		        </div><!-- /.modal-content -->
		    </div><!-- /.modal-dialog -->
		</div>
	</form>
	
	<span class="nav">学生端>>个人信息中心</span>
	<div class="main">
		<form action="ChangePwd" method="post" style="width: 100%">
			<div style="display: flex;flex-direction: row;width: 100%">
				<div class="panel" style="width: 40%">
					<table style="width: 100%">
						<tr>
							<td>
								姓名
							</td>
					    	<td><input type="text" value="<%=ot.get("stuname")%>" disabled></td>
						</tr>
						<tr>
							<td>
								性别
							</td>
					    	<td><input type="text" placeholder="请填写性别" value="<%=sex%>" id="teacherSex" name="teacherSex"disabled></td>
						</tr>
						<tr>
							<td>
								学号
							</td>
					    	<td><input type="text" value="<%=ot.get("stuid")%>" disabled></td>
						</tr>
						<tr>
							<td>
								专业
							</td>
					    	<td><input type="text" value="<%=ot.get("deptname")%>" disabled></td>
						</tr>
						<tr>
							<td>
								班级
							</td>
					    	<td><input type="text" value="<%=ot.get("classname")%>" disabled></td>
						</tr>
						<tr>
							<td>
								电话号码
							</td>
					    	<td><input type="text" placeholder="请填写电话号码" value="<%=ot.get("tel")%>" id="teacherTel" name="studentTel"></td>
						</tr>
						<tr>
							<td>
								选择导师
							</td>
					    	<td><input type="text"  value="<%=teacher%>" id="teacherTel" name="selTeacher"disabled></td>
						</tr>
						<tr>
							<td>
								当前状态
							</td>
					    	<td><input type="text"  value="<%=choosedState%>" id="states" name="states"disabled></td>
						</tr>
					</table>
				</div>
			<div style="width: 60%;margin: 10px">
					<h5>个人简介</h5>
					<textarea rows="20" class="mb-lg form-control" id="teacherIntro" name="studentIntro"><%=ot.get("intro")%></textarea>
				</div>
			</div>
			<div style="text-align: center">
				<button type="submit" class="btn btn-primary">保存个人信息</button>
				<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#changePasswordModal">修改密码 </button>
			</div>
		</form>
	</div>
	<%
	}
	else{
		//返回登录页面
		response.sendRedirect("../login.jsp");
	} %>
</body>
<style type="text/css">
	body{
		margin: 0;
		padding: 0;
		display:flex;
		flex-direction: column;
		height: 100vh; 
		overflow-y: scroll;
	}
	.main{
		display: flex;
		flex-direction: column;
		width: 90%;
		margin-left: 2%;
		padding: 10px;
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
		align-self: flex-start;
		margin-left:2%;
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
	}
</style>
<script type="text/javascript" language="javascript">
	/*获取从servlet返回的信息，显示成功或失败*/
	<%
	String result = (String)s.getAttribute("result");
	String isError = (String)s.getAttribute("isError");
	//System.out.println(result + isError);
	if(result != null) {
		if(isError.equals("0")) {
	%>
			swal("成功", "<%=result%>", "success");
	<%  } else {%>
			swal("失败", "<%=result%>", "error");
	<%	}
		s.removeAttribute("result");
		s.removeAttribute("isError");
	} %>
	$(function() {
	    $('#changePasswordModal').modal('hide');
	});
	function checkPwd(){
		var oldPwd = $('#oldpw').val();
		var old = <%=(String)s.getAttribute("stuPwd")%>;
		if(oldPwd != <%=s.getAttribute("stuPwd")%>){
			alert("旧密码错误！");
			return false;
		}
		var newPwd = $('#newpw').val();
		var reNewPwd = $('#reNewpw').val();
		if(newPwd == "" || newPwd != reNewPwd){
			alert("两次输入密码不相同！");
			return false;
		}
		return true;
	}
</script>
</html>