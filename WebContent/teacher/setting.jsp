<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.HashMap" %>
<%@ page import="db.TeacherDAO,db.DeptDAO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
<link rel="stylesheet" type="text/css" href="../lib/bootstrap.min.css">
<link rel="stylesheet" href="../lib/sweetalert.css">
<script type="text/javascript" language="javascript" src="//code.jquery.com/jquery-1.12.4.js">
</script>
<script type="text/javascript" language="javascript" src="../lib/sweetalert-dev.js"></script>
<script src="../lib/modal.js"></script>
</head>
<body>
	<%@ include file="navbar.html" %>
	
	<div class="welcome">欢迎进入：<%=(String)session.getAttribute("teaName") %>老师&nbsp;<a href="../login.jsp"><font color="red" size="1">退出</font></a> </div>
	
	<% 
		TeacherDAO teadao = new TeacherDAO();
		ArrayList infoList = teadao.queryTeacherInfo((String)session.getAttribute("teaId"));
		HashMap info = (HashMap) infoList.get(0);
		
/* 		System.out.println("系号"+ (String)info.get("deptid"));
		DeptDAO deptdao = new DeptDAO();
		info.put("deptname", deptdao.queryDeptName((String)info.get("deptid"))); */
/* 		request.setAttribute("info", info); */
	%>
	
	<form action="PwdEdit" method="post" class="form-horizontal">
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
	
	<div class="main">
		<span style="line-height:40px;font-size:10px;align-self: flex-start">导师端>>个人信息中心</span>
		<form action="InfoEdit" method="post" style="width: 100%" onsubmit="return f();" >
			<div style="display: flex;flex-direction: row;width: 100%">
				<div class="panel" style="width: 40%">
					<table style="width: 100%">
						<tr>
							<td>
								姓名
							</td>
					    	<td><input type="text" value="<%=info.get("teachername")%>" disabled></td>
						</tr>
						<tr>
							<td>
								性别
							</td>
							<% 
							String sex = "";
							if(info.get("sex").equals("F"))
								sex = "女";
							else 
								sex = "男";
							%>
					    	<td><input type="text" value="<%=sex%>" disabled></td>
						</tr>
						<tr>
							<td>
								工号
							</td>
					    	<td><input type="text" value="<%=info.get("teacherid")%>" disabled></td>
						</tr>
						<tr>
							<td>
								专业
							</td>
					    	<td><input type="text" value="<%=info.get("deptname")%>" disabled></td>
						</tr>
						<tr>
							<td>
								学历
							</td>
					    	<td><input type="text" value="<%=info.get("title")%>" disabled></td>
						</tr>
						<tr>
							<td>
								是否反选
							</td>
							<%
							String isPrivilege = "";
							if(info.get("privilege").equals("1"))
								isPrivilege = "是";
							else
								isPrivilege = "否";
							%>
					    	<td><input type="text" value="<%=isPrivilege %>" disabled></td>
						</tr>
						<tr>
							<td>
								电话号码
							</td>
					    	<td><input type="text" placeholder="请填写电话号码" value="<%=info.get("tel")%>" id="teacherTel" name="teacherTel"></td>
						</tr>
					</table>
				</div>
				<div style="width: 60%;margin: 10px">
					<h5>个人简介</h5>
					<textarea rows="18" class="mb-lg form-control" id="teacherIntro" name="teacherIntro"><%=info.get("intro")%></textarea>
				</div>
			</div>
			<div style="text-align: center">
				<button type="submit" class="btn btn-primary">保存个人信息</button>
				<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#changePasswordModal">修改密码 </button>
			</div>
		</form>
	</div>
</body>
<style type="text/css">
	body{
		margin: 0;
		padding: 0;
	}
	.welcome {
		position: fixed;
		right: 18px;
		top: 18px;
		z-index: 99;
	}
	.main{
		display: flex;
		flex-direction: column;
		width: 100%;
		margin-top: 50px;
		font-size: 14px;
		justify-content: center;
		align-items: center;
		padding: 10px 20px;
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
	}
</style>
<script type="text/javascript" language="javascript">
	/*获取从servlet返回的信息，显示成功或失败*/
	<%
	String result = (String)session.getAttribute("result");
	String isError = (String)session.getAttribute("isError");
	if(result != null && isError != null) {
		if(isError.equals("0")) {
	%>
			swal("成功", "<%=result%>", "success");
	<%  } else {%>
			swal("失败", "<%=result%>", "error");
	<%	}
		session.removeAttribute("result");
		session.removeAttribute("isError");
	} %>
	
	$(function() {
	    $('#changePasswordModal').modal('hide');
	});
</script>
</html>