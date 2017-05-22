<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="db.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生列表</title>
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
	<%@ include file="navbar.html" %>
	<% 
		TeacherMsDAO teadao = new TeacherMsDAO();
		int isPrivilage = teadao.getPrivilege((String)session.getAttribute("teaId"));
		System.out.println(application.getAttribute("PrivilegeModel")); 
		String systemLimit = (String)application.getAttribute("LimitModel");
		String systemPrivilege = (String)application.getAttribute("PrivilegeModel");
	%>
	<div class="welcome">欢迎进入：<%=(String)session.getAttribute("teaName") %>老师&nbsp;<a href="../login.jsp"><font color="red" size="1">退出</font></a> </div>
	<div class="main">
		<form action="ReverseChoose" method="post" id="chooseStuForm">
			<div>
				<span style="line-height:40px;font-size:10px;align-self: flex-start">导师端>>学生选择情况</span>
				<% if(isPrivilage == 1 && systemPrivilege.equals("on")&&systemLimit.equals("on")) {%>
					<div class="pull-right">
						<button class="btn btn-primary" type="submit" onclick="return chooseSuccess()">通过</button>
						<button class="btn btn-danger" type="submit" onclick="return chooseFail()">淘汰</button>
						<input type="hidden" name="flag" id="flag">
					</div>
				<% } %>
			</div>
			<div>
				<% if(isPrivilage ==1 && systemPrivilege.equals("on")&&systemLimit.equals("on")) { %>
					<span id="notice">说明：对学生只能操作一次，不能再重复修改</span>
				<%}%>
				<table id="selectStuTable" class="display" cellspacing="0" width="100%" >
					<thead onClick="removeTr()">
						<tr>
							<% if(isPrivilage ==1 && systemPrivilege.equals("on")&&systemLimit.equals("on")) { %>
								<th>
									<label>
										<input type="checkbox" value="" id="selectedAll">&nbsp;全选
									</label>
								</th>
							<% } %>
							<th>学号</th>
							<th>姓名</th>
							<th>性别</th>
							<th>专业</th>
							<th>班级</th>
							<th>电话</th>
							<th>绩点</th>
							<th>状态</th>
							<th>简介</th>
						</tr>
					</thead>
				</table>
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
		width: 90%;
		margin-top: 60px;
		margin-left: 4%;
		padding: 10px;
	}
	.main table{
		width: 98%;
	}
	.stuIntro {
		background-color: transparent;
		border-color: transparent;
		color: #5d9cec;
	}
	.stuIntro:hover, .stuIntro:focus {
		background-color: transparent;
		border-color: transparent;
		color: #337ab7;
	}
	#notice{
		position: absolute;
    	color: #d9534f;
    	line-height: 30px;
	}
</style>
<script type="text/javascript" language="javascript">
	/*获取从servlet返回的信息，显示成功或失败*/
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
		session.removeAttribute("result");
		session.removeAttribute("isError");
	} 
	%>
	
	/*获取表格中的数据*/
	var array = new Array();
	var k=0;
	var introMap = new Object();
	var introId = null;
	<%   
	/* SelectTeacherDAO dao = new SelectTeacherDAO();
	ArrayList<Map<String, String>> selectTeacher = dao.querySelectStu((String)session.getAttribute("teaId")); */
	/*获取选择该导师的学生列表数据*/
	StudentDAO sdao = new StudentDAO();
	ArrayList<Map<String, String>> selectTeacher = sdao.studentList((String)session.getAttribute("teaId"));
	//选择该导师的学生人数是否达到老师的可带人数的最大上限，若为false，则不能够反选学生,若为true，则能够反选学生
	TeacherDAO tdao = new TeacherDAO();
	boolean isReach = tdao.isReachLimit(selectTeacher);
	for (Map<String, String> map : selectTeacher) {
		int stuid = Integer.valueOf(map.get("stuid"));
		String sex = ":)";
		if(map.get("sex").equals("F"))
			sex = "女";
		else if(map.get("sex").equals("M"))
			sex = "男";
		String state = null;
		String oper = "";
		if(map.get("choosedstate").equals("1"))
			state = "<strong><font color='#777'>待定</font></strong>";
		else if(map.get("choosedstate").equals("3"))
			state = "<strong><font color='#5d9cec'>成功</font></strong>";
		
		String checkBox = "";
		if(isPrivilage == 1 && !map.get("choosedstate").equals("3")) {
			checkBox = "<label><input type='checkbox' name='chooseStu' value='"+map.get("stuid")+"'></label>";
		}
		%>
		
		var t = new Array();
		<%
			if(isPrivilage == 1 && systemPrivilege.equals("on")&&systemLimit.equals("on")) {
		%>
				t.push("<%=checkBox%>");
		<% } %>
		t.push("<%=stuid%>");
		t.push("<%=map.get("stuname")%>");
		t.push("<%=sex%>");
		t.push("<%=map.get("deptname")%>");
		t.push("<%=map.get("classname")%>");
		t.push("<%=map.get("tel")%>");
		t.push("<%=map.get("grade")%>");
		t.push("<%=state%>");
		t.push("<button style='cursor:pointer;text-decoration:none' type='button' class='btn stuIntro' value='<%=stuid%>'>详情</button>");
		<%-- t.push("<button style='cursor:pointer;text-decoration:none' type='button' class='btn stuIntro' value='<%=sdao.turn(map.get("intro"))%>'>详情</button>"); --%>
		introMap[<%=stuid%>] = "<%=sdao.turn(map.get("intro"))%>";
		array[k] = t;
		k++;
	<% } %>
	
	/*datatable表格设定*/
	$(document).ready(function() {
		$('#selectStuTable').DataTable( {
			data: array,
			<% if(isPrivilage == 1) { %>
				"aaSorting": [[ 1, "asc" ]], //默认以学号进行排序
			<% } %>
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
			"sZeroRecords": "还未有学生选择您",
			//"sProcessing": "<img src='./loading.gif' />",
			"bStateSave": true //保存状态到cookie *************** 很重要 ， 当搜索的时候页面一刷新会导致搜索的消失。使用这个属性就可避免了 
			}
		} );
		
		/*显示简介*/
		$(".stuIntro").click(function(){
			$(".introTr").remove();
			if(introId != $(this).val()) {
				introId = $(this).val();
				$(this).parent().parent('tr').after("<tr class='introTr'><td colspan='11' style='padding:10px!important;word-wrap:break-word;'>"+introMap[introId]+"</td></tr>");
			}
			else {
				introId = null;
			}
		});
/* 		$(".stuIntro").click(function(){
			$(".introTr").remove();
			if(introId != $(this).val()) {
				console.log($(this).val());
				introId = $(this).val();
			 	$(this).parent().parent('tr').after("<tr class='introTr'><td colspan='11' style='padding:10px!important;word-wrap:break-word;'>"+introId+"</td></tr>");
			}
			else {
				introId = null;
			}
		}); */
		
		/*点击外面部分关闭简介*/
		/*$(document).bind("click",function(e){
	        //当点击除详情按钮之外的部分时    
	        if($(e.target).closest(".stuIntro").length == 0){
	        	$(".introTr").remove();
	        }
	    });*/
	} );
	
	var titleMes = "";
	var textMes = "";
	
	function removeTr() {
		introId = null;
	}
	
	/*全选/全不选*/
	$("#selectedAll").click(function(){
		var flag = $("#selectedAll").get(0).checked;
		if(flag) {
			$("[name=chooseStu]:checkbox").each(function(){
				$(this).prop("checked",'true');
			});
		}
		else {
			$("[name=chooseStu]:checkbox").each(function(){
				$(this).removeAttr("checked");
			});
		}
	});
	
	/*通过学生*/
	function chooseSuccess() {
		titleMes = "确定通过该学生吗?";
		textMes = "确定后将不可修改!";
		document.getElementById("flag").value = "1";
		return checkOpe();
	}
	
	/*淘汰学生*/
	function chooseFail() {
		titleMes = "确定淘汰该学生吗?";
		textMes = "淘汰后将不可修改!";
		document.getElementById("flag").value = "2";
		return checkOpe();
	}
	
	/*操作学生*/
	function checkOpe() {
		var hasChooseFlag = false;
		var returnFlag = false;
		if((!<%=isReach%>)&&document.getElementById("flag").value=="2"){
			swal("提醒", "选择您的学生人数太少，不能淘汰", "warning");
     		return false; 
		}else{
    	$("[name=chooseStu]:checkbox").each(function(){
			if($(this).get(0).checked) {
				hasChooseFlag = true;
			}
		});
    	if(hasChooseFlag) {
			swal({
			 	title: titleMes,
			  	text: textMes,
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
			  		document.getElementById("chooseStuForm").submit();
			  		return true;
			  	} else { //取消
			  		return false;
			  	}
			});
			return false;
    	}
    	else {
    		swal("失败", "请选择学生", "error");
    		return false;
    	}
		}
	}

</script>
</html>