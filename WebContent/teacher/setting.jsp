<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
<link rel="stylesheet" type="text/css" href="../lib/bootstrap.min.css">
<link rel="stylesheet" href="../lib/sweetalert.css">
<script type="text/javascript" language="javascript" src="//code.jquery.com/jquery-1.12.4.js">
</script>
<script type="text/javascript" language="javascript" src="../lib/sweetalert-dev.js"></script>
</head>
<body>
	<%@ include file="../public/navbar.html" %>
	<span class="nav">导师端>>个人信息中心</span>
	<div class="main">
		<form action="teacherInfo" method="post" style="width: 100%">
			<div style="display: flex;flex-direction: row;width: 100%">
				<div class="panel" style="width: 40%">
					<table style="width: 100%">
						<tr>
							<td>
								姓名
							</td>
					    	<td><input type="text" value="XX" disabled></td>
						</tr>
						<tr>
							<td>
								性别
							</td>
					    	<td><input type="text" placeholder="请填写性别" value="XX" id="teacherSex" name="teacherSex"></td>
						</tr>
						<tr>
							<td>
								工号
							</td>
					    	<td><input type="text" value="XXXX" disabled></td>
						</tr>
						<tr>
							<td>
								专业
							</td>
					    	<td><input type="text" value="XXXX" disabled></td>
						</tr>
						<tr>
							<td>
								学历
							</td>
					    	<td><input type="text" value="XXXX" disabled></td>
						</tr>
						<tr>
							<td>
								能否反选
							</td>
					    	<td><input type="text" value="XXXX" disabled></td>
						</tr>
						<tr>
							<td>
								电话号码
							</td>
					    	<td><input type="text" placeholder="请填写电话号码" value="XXX" id="teacherTel" name="teacherTel"></td>
						</tr>
					</table>
				</div>
				<div style="width: 60%;margin: 10px">
					<h5>个人简介</h5>
					<textarea rows="18" class="mb-lg form-control" id="teacherIntro" name="teacherIntro">
						XXXXXXXXXXXXX
						XXXXXXXXXXXXXXXXXX
						XXX
						  XXXXXXXXXXXX
					</textarea>
				</div>
			</div>
			<div style="text-align: center">
				<button type="submit" class="btn btn-primary">保存个人信息</button>
				<button type="button" class="btn btn-danger" onclick="changePassword()">修改密码 </button>
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
	
	/*修改密码事件*/
	function changePassword() {
		swal({
		 	title: "",
		 	html: true,
		  	text: "<form action='teacherInfo' method='post'><div class='alertInput'><label><span>原密码：</span><input type='text' placeholder='请填写原密码' id='oldpw' name='oldPassword'/></label>"+
		  		"<label><span>新密码：</span><input type='text' placeholder='请填写新密码' id='newpw' name='newPassword'/></label>"+
		  		"<label><span>重复新密码：</span><input type='text' placeholder='请重复新密码' id='reNewpw' name='reNewPassword'/></label></div>"+
		  		"<button class='cancel' type='button'>取消</button><button class='confirm' type='submit' style='background-color: rgb(221, 107, 85);'>确定</button></form>",
		  	showConfirmButton: false,
		  	showCancelButton: false,
		  	confirmButtonColor: "#DD6B55",
		  	confirmButtonText: "确定",
		  	cancelButtonText: "取消",
		  	closeOnConfirm: false,
		  	closeOnCancel: true
		},
		function(isConfirm){
			
		});
	}
</script>
</html>