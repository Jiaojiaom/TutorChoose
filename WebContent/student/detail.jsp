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
	<span class="nav">学生端>>导师详情页</span>
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
						<tr>
							<td>
								选择情况
							</td>
					    	<td><input type="text" placeholder="" value="3/3" id="selectCon" name="selectCon"></td>
						</tr>
						<tr>
							<td>
								选择详情
							</td>
					    	<td><input type="text" placeholder="" value="aa,bb,cc" id="selectCon" name="selectCon"></td>
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
				<button type="submit" class="btn btn-primary">选择此老师</button>
				<button type="button" class="btn btn-danger" onclick="changePassword()">取消选择 </button>
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
</html>