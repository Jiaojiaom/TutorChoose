<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="db.TeacherMsDAO" %>
<%@ page import="db.AdminMsDAO" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设置页面</title>
<link rel="stylesheet" type="text/css" href="../lib/bootstrap.min.css">
<link rel="stylesheet" href="../lib/sweetalert.css">
<link rel="stylesheet" type="text/css" href="../lib/checkBox.css">
<script type="text/javascript" language="javascript" src="../lib/jquery.min.js"></script>
<script type="text/javascript" language="javascript" src="../lib/sweetalert-dev.js"></script>
</head>
<body>
    <% TeacherMsDAO teacherDao = new TeacherMsDAO(); %>
    <% AdminMsDAO adminDao = new AdminMsDAO(); %>
	<%@ include file="navbar.jsp" %>
	<div class="main">
		<span style="line-height:40px;font-size:10px;align-self: flex-start">管理员端>>设置中心</span>
		<div>
			<div class="subSetting">
				<form action="updateInfo" method="post" style="width: 61.8%" id="setStudentForm">
					<div class="panel">
					    <input name="functionType" type="hidden" id="functionType" value="setStudentCount" />
						<p>设置导师可带学生数量</p>
						<p>
							<input type="number" value=<%=teacherDao.getStudentCount() %> class="form-control" style="width: 50%;float: left" name="StudentCount">
							<button type="button" class="btn btn-success" style="float: left;margin-left: 20px" onClick="changeStuNum()">确定</button>
							<div style="clear: both"></div>
						</p>
					</div>
				</form>
				<form action="updateInfo" method="post" style="width: 38.2%" id="setAuthorityForm">
					<div class="panel">
					    <input name="functionType" type="hidden" id="functionType" value="toggleAuthority" />
						<p>开启/关闭系统&nbsp;&nbsp;
						  <section class='model-1'>
						    <% if(adminDao.getAuthorityModel().equals("on")){ %>
							   <div class='checkbox'>
							    <input type='checkbox' class="systemAuthority" name="AuthorityModel" value="off" checked/>
							    <label></label>
							  </div>
						  	<% } else if(adminDao.getAuthorityModel().equals("off")){ %>
							  <div class='checkbox'>
							    <input type='checkbox' class="systemAuthority" name="AuthorityModel" value="on" />
							    <label></label>
							  </div>
							<% } %>
						  </section>
						</p>
					</div>
				</form>
			</div>
		</div>
		<form action="updateInfo" method="post" style="width: 100%" id="setRuleForm">
			<div class="panel">
				<img src="../images/sort_desc.png" class="pullDownBtn" onClick="showRuleInfo()" />
			    <input name="functionType" type="hidden" id="functionType" value="setRules" />
				<p>设置学生选择导师规则（除可反选导师）&nbsp;
						<%if(teacherDao.getRules()==3) { %>
						<small class="currentRule">当前规则：先到先得</small>
						<% } else if(teacherDao.getRules()==4){ %>
						<small class="currentRule">当前规则：按照学生的成绩排名</small>
						<% } %>
				</p>
				<p>
				  <%if(teacherDao.getRules()==3) { %>
					  <label class="checkbox-inline"><input type="radio" value =3 name="rules" checked/>先到先得</label>
					  <label class="checkbox-inline"><input type="radio" value =4 name="rules" />按照学生的成绩排名</label>
				  <% } else if(teacherDao.getRules()==4){ %>
				      <label class="checkbox-inline"><input type="radio" value =3 name="rules" />先到先得</label>
					  <label class="checkbox-inline"><input type="radio" value =4 name="rules" checked/>按照学生的成绩排名</label>
				  <% } %>
				  <button type="button" class="btn btn-success" style="margin-left: 20px" onClick="changeRule()">确定</button>
				</p>
				<div id="ruleInfo" hidden>
					<hr>
					<p><strong>学生选择导师规则：</strong></p>
					<p>按照专业优先级，当本专业的导师名额已满后才可以选择其他专业的老师。     <p>
					<p>“先到先得规则”：<br>学生选择导师按时间优先，名额满后不可再选择该导师，学生可退选导师，退选后其他学生仍可选择该导师。     <p>
					<p>“按照学生的成绩排名规则”：<br>学生选择导师按绩点优先，名额满后通过绩点高者淘汰绩点低者，学生可退选导师，退选后其他学生仍可选择该导师。     <p>
				</div>
			</div>
		</form>
		<div class="subSetting">
			<form action="updateInfo" method="post" style="width: 100%" id="setPrivilegeForm">
				<div class="panel">
				    <input name="functionType" type="hidden" id="functionType" value="togglePrivilege" />
					<p>开启/关闭<strong>导师反选功能</strong> &nbsp;&nbsp;
					<section class='model-1'>
					   <% if(adminDao.getPrivilegeModel().equals("on")){ %>
						  <div class='checkbox'>     
						    <input type='checkbox' class="tutorAuthority" name="PrivilegeModel" value="off" checked/>
						    <label></label>
						  </div>
						<% } else if(adminDao.getPrivilegeModel().equals("off")){ %>
						  <div class='checkbox'>     
						    <input type='checkbox' class="tutorAuthority" name="PrivilegeModel" value="on"/>
						    <label></label>
						  </div>
						<% } %> 
					</action>
					</p>
					<hr>
					<p><strong>反选导师规则：</strong></p>
					<p>有反选资格的导师不受上方规则限制。</p>
					<p>有反选资格的导师可通过或淘汰学生，学生数量不再受学生名额限制。</p>
				</div>
			</form>
			<form action="updateInfo" method="post" style="width: 100%" id="setLimitForm">
				<div class="panel">
				    <input name="functionType" type="hidden" id="functionType" value="toggleLimit" />
					<p>开启/关闭<strong>选导师功能<strong> &nbsp;&nbsp;
					<section class='model-1'>
					   <% if(adminDao.getLimitModel().equals("on")){ %>
						  <div class='checkbox'>     
						    <input type='checkbox' class="tutorLimit" name="LimitModel" value="off" checked/>
						    <label></label>
						  </div>
						<% } else if(adminDao.getLimitModel().equals("off")){ %>
						  <div class='checkbox'>     
						    <input type='checkbox' class="tutorLimit" name="LimitModel" value="on"/>
						    <label></label>
						  </div>
						<% } %> 
					</action>
					</p>
					<hr>
					<p><strong>开：</strong></p>
					<p>开启此功能，学生可以选择导师，反选导师可以选择学生</p>
					<p>关闭此功能，导师和学生只能查看自己的信息</p>
				</div>
			</form>
		</div>
	</div>
	<% adminDao.close(); %>
	<% teacherDao.close(); %>
</body>
<style type="text/css">
	body{
		margin: 0;
		padding: 0;
		height:100vh;
		overflow-y :scroll
	}
	.main{
		display: flex;
		flex-direction: column;
		width: 90%;
		margin: 0 2%;
		padding: 10px;
		margin-top: 60px;
	}
	.subSetting {
		display: flex;
		flex-direction: row;
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
	.pullDownBtn {
		position: relative;
		left: 98%;
		cursor: pointer;
	}
	.currentRule {
	    padding: 5px;
	    border-radius: 5px;
	    border: 1px solid #eee;
	    box-shadow: 1px 1px 2px #dcdcdc;
	}
</style>
<script type="text/javascript" language="javascript">
	$(document).ready(function(){
		<%
		String result = (String)session.getAttribute("result");
		String isError = (String)session.getAttribute("isError");
		if(result != null) {
			if(isError.equals("0")) {
		%>
				swal("成功", "<%=result%>", "success");
		<%  } else {%>
				swal("失败", "<%=result%>", "error");
		<%	}
		} 
		session.removeAttribute("result");
		session.removeAttribute("isError");
		%>
		$(".systemAuthority").change(function() {
			var titleMsg = "";
			if($(".systemAuthority").get(0).checked) {
				titleMsg = "确定开启系统吗？"
			}
			else {
				titleMsg = "确定关闭系统吗？"
			}
			swal({
			 	title: titleMsg,
			  	text: "",
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
			  		// 提交表单
			  		document.getElementById("setAuthorityForm").submit();
			  	} else { //取消
			  		if($(".systemAuthority").get(0).checked) {
			  			$(".systemAuthority").removeAttr("checked");
			  		}
			  		else {
			  			$(".systemAuthority").prop("checked",'true');
			  		}
			  	}
			});
		});
		$(".tutorAuthority").change(function() {
			var titleMsg = "";
			if($(".tutorAuthority").get(0).checked) {
				titleMsg = "确定开启导师反选功能吗？"
			}
			else {
				titleMsg = "确定关闭导师反选功能吗？"
			}
			swal({
			 	title: titleMsg,
			  	text: "",
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
			  		// 提交表单
			  		document.getElementById("setPrivilegeForm").submit();
			  	} else { //取消
			  		if($(".tutorAuthority").get(0).checked) {
			  			$(".tutorAuthority").removeAttr("checked");
			  		}
			  		else {
			  			$(".tutorAuthority").prop("checked",'true');
			  		} 
			  	}
			});
		});
		$(".tutorLimit").change(function() {
			var titleMsg = "";
			if($(".tutorLimit").get(0).checked) {
				titleMsg = "确定开启选导师功能？";
				$(".tutorState").val("on");
			}
			else {
				titleMsg = "确定关闭选导师功能吗？";
				$(".tutorState").val("off");
			}

			swal({
			 	title: titleMsg,
			  	text: "",
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
			  		// 提交表单
			  		document.getElementById("setLimitForm").submit();
			  	} else { //取消
			  		if($(".tutorLimit").get(0).checked) {
			  			$(".tutorLimit").removeAttr("checked");
			  		}
			  		else {
			  			$(".tutorLimit").prop("checked",'true');
			  		} 
			  	}
			});
		});
	});
    function changeStuNum() {
    	swal({
		 	title: "确定修改导师可带学生数量吗？",
		  	text: "",
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
		  		// 提交表单
		  		document.getElementById("setStudentForm").submit();
		  	} else { //取消
		  		
		  	}
		});
	}
    function changeRule() {
    	swal({
		 	title: "确定修改学生选择导师规则吗？",
		  	text: "",
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
		  		// 提交表单
		  		document.getElementById("setRuleForm").submit();
		  	} else { //取消
		  		
		  	}
		});
    }
    function showRuleInfo() {
    	if($("#ruleInfo").is(":hidden")) {
    		$("#ruleInfo").show(1000);
    	}
    	else {
    		$("#ruleInfo").hide(1000);
    	}
    }
</script>
</html>