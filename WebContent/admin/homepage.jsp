<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="db.TeacherMsDAO"%>
<%@ page import="db.StudentMsDAO"%>
<%@ page import="db.DeptMsDAO"%>
<%@ page import="db.ClassMsDAO"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员</title>
<link rel="stylesheet" type="text/css"
	href="../lib/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="../lib/table.css">
<link rel="stylesheet" type="text/css" href="../lib/bootstrap.min.css">
<link rel="stylesheet" href="../lib/sweetalert.css">
<script type="text/javascript" language="javascript"
	src="//code.jquery.com/jquery-1.12.4.js">
</script>
<script type="text/javascript" language="javascript"
	src="../lib/sweetalert-dev.js"></script>
<script type="text/javascript" language="javascript"
	src="../lib/jquery-tab.js"></script>
<script type="text/javascript" language="javascript"
	src="../lib/prefixfree.min.js"></script>
<script type="text/javascript" language="javascript"
	src="../lib/jquery.dataTables.min.js">
</script>
<script src="../lib/modal.js"></script>
<style type="text/css">
body {
	margin: 0;
	padding: 0;
	display: flex;
	flex-direction: column;
	height: 100vh;
	width: 100%;
}

.main {
	display: flex;
	flex-direction: column;
	width: 90%;
	margin-left: 2%;
	padding: 10px;
}

.main table {
	width: 98%;
}

.nav {
	margin-top: 50px;
	line-height: 40px;
	font-size: 10px;
	align-self: flex-start;
	margin-left: 2%;
}

.formTeacher {
	display: flex;
	flex-direction: row;
}

.clearfix:after, .container:after, .tab-nav:after {
	content: ".";
	display: block;
	height: 0;
	clear: both;
	visibility: hidden;
}

/* ==========
		   Setup Page */
*, *:before, *:after {
	box-sizing: border-box;
}

body {
	font-family: 'Quicksand', sans-serif;
}

/* =================
		   Container Styling */
.container {
	position: relative;
	background: white;
	padding: 3em;
}

/* ===========
		   Tab Styling */
.tab-group {
	position: relative;
	border: 1px solid #eee;
	margin-top: 2.5em;
	border-radius: 0 0 10px 10px;
}

.tab-group section {
	opacity: 0;
	height: 0;
	padding: 0 1em;
	overflow: hidden;
	transition: opacity 0.4s ease, height 0.4s ease;
}

.tab-group section.active {
	opacity: 1;
	height: auto;
	overflow: visible;
}

.tab-nav {
	list-style: none;
	margin: -2.5em -1px 0 0;
	padding: 0;
	height: 2.5em;
	overflow: hidden;
}

.tab-nav li {
	display: inline;
}

.tab-nav li a {
	top: 1px;
	position: relative;
	display: block;
	float: left;
	border-radius: 10px 10px 0 0;
	background: #eee;
	line-height: 2em;
	padding: 0 1em;
	text-decoration: none;
	color: grey;
	margin-top: .5em;
	margin-right: 1px;
	transition: background .2s ease, line-height .2s ease, margin .2s ease;
}

.tab-nav li.active a {
	background: #6EB590;
	color: white;
	line-height: 2.5em;
	margin-top: 0;
}

.nav li {
	display: inline-block;
}

input[type="file"] {
	display: inline-block;
	width: 134px;
}

.exportInfo {
	margin: 20px 0 10px 10px;
}
.fileForm {
	padding:10px;
	margin:10px 0;
	border:1px solid #eee;
	box-shadow:1px 1px 5px #eee;
}
.file {
    position: relative;
    display: inline-block;
    background: #fff;
    border: 1px solid #ccc;
    border-radius: 4px;
    padding: 6px 12px;
    overflow: hidden;
    color: #333;
    text-decoration: none;
    text-indent: 0;
    line-height: 20px;
    margin-bottom: -13px;
    cursor: pointer;
}
.file input {
    position: absolute;
    right: 0;
    top: 0;
    opacity: 0;
}
.file:hover {
    background: #e6e6e6;
    border-color: #adadad;
    color: #333;
    text-decoration: none;
}
.fileName {
	background-color: transparent;
    border: 0;
}
@media screen and (max-width: 685px) {
	.fileName {
		width: 60%;
	}
    .importFileBtn {
        margin-top: 15px;
    }
    .exportInfo {
    	margin-top: 0;
    }
}
</style>

</head>
<body>
	<%@ include file="navbar.jsp"%>
	
	<!-- 添加教师模态框   -->
	<form action="addInfo" method="post" class="form-horizontal">
		<div class="modal fade" id="addTeacherModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		                <h4 class="modal-title" id="addTeacherModal">添加教师</h4>
		            </div>
		            <div class="modal-body">
		            	<input name="userType" type="hidden" value="teacher" />
			            <div class="form-group">
						    <label class="col-sm-4 control-label">教师编号：</label>
						    <div class="col-sm-7">
    							<input type='text' class="form-control" placeholder='请填写教师编号' name='TeacherID'/>
    						</div>
						</div>
						<div class="form-group">
						    <label class="col-sm-4 control-label">教师姓名：</label>
						    <div class="col-sm-7">
    							<input type='text' class="form-control" placeholder='请填写姓名' name='TeacherName'/>
    						</div>
						</div>
						<div class="form-group">
						    <label class="col-sm-4 control-label">性别：</label>
						    <div class="col-sm-7">
    							<label class='checkbox-inline'>
    								<input type='radio' name='Sex' value='M' class='sweetalertRadio' checked>男
    							</label>
    							<label class='checkbox-inline'>
    								<input type='radio' name='Sex' class='sweetalertRadio' value='F'>女
    							</label>
    						</div>
						</div>
						<div class="form-group">
						    <label class="col-sm-4 control-label">系编号：</label>
						    <div class="col-sm-7">
    							<select name='DeptID' id='DeptID' class='form-control'>
						  	    <%
						  	  	DeptMsDAO deptDao = new DeptMsDAO();
						  		ArrayList<Map<String, String>> deptMsgs = deptDao.queryDeptAll();
						  	    for (Map<String, String> deptMsg : deptMsgs) { %>
						  	    	<option value =<%=deptMsg.get("deptid") %>>
						  	    	<%=deptMsg.get("deptname") %></option>
						 		<%}%>
						 		</select>
    						</div>
						</div>
						<div class="form-group">
						    <label class="col-sm-4 control-label">职称：</label>
						    <div class="col-sm-7">
    							<input type='text' class="form-control" placeholder='请填写职称' name='Title'/>
    						</div>
						</div>
						<div class="form-group">
						    <label class="col-sm-4 control-label">电话号码：</label>
						    <div class="col-sm-7">
    							<input type='text' class="form-control" placeholder='请填写电话号码' name='tel'/>
    						</div>
						</div>
						<div class="form-group">
						    <label class="col-sm-4 control-label">自我介绍：</label>
						    <div class="col-sm-7">
    							<input type='text' class="form-control" placeholder='请填写自我介绍' name='Title'/>
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
	
	<!-- 添加学生模态框   -->		  		
	<form action="addInfo" method="post" class="form-horizontal">
		<div class="modal fade" id="addStudentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		                <h4 class="modal-title" id="addStudentModal">添加学生</h4>
		            </div>
		            <div class="modal-body">
		            	<input name="userType" type="hidden" value="student" />
			            <div class="form-group">
						    <label class="col-sm-4 control-label">学生学号：</label>
						    <div class="col-sm-7">
    							<input type='text' class="form-control" placeholder='请填写学生学号' name='StuID'/>
    						</div>
						</div>
						<div class="form-group">
						    <label class="col-sm-4 control-label">学生姓名：</label>
						    <div class="col-sm-7">
    							<input type='text' class="form-control" placeholder='请填写学生姓名' name='StuName'/>
    						</div>
						</div>
						<div class="form-group">
						    <label class="col-sm-4 control-label">性别：</label>
						    <div class="col-sm-7">
    							<label class='checkbox-inline'>
    								<input type='radio' name='Sex' value='M' class='sweetalertRadio' checked>男
    							</label>
    							<label class='checkbox-inline'>
    								<input type='radio' name='Sex' class='sweetalertRadio' value='F'>女
    							</label>
    						</div>
						</div>
						<div class="form-group">
						    <label class="col-sm-4 control-label">系编号：</label>
						    <div class="col-sm-7">
    							<select name='DeptID' id='DeptID' class='form-control'>
						  	    <%
						  	    for (Map<String, String> deptMsg : deptMsgs) { %>
						  	    	<option value =<%=deptMsg.get("deptid") %>>
						  	    	<%=deptMsg.get("deptname") %></option>
						 		<%}%>
						 		</select>
    						</div>
						</div>
						<div class="form-group">
						    <label class="col-sm-4 control-label">班级编号：</label>
						    <div class="col-sm-7">
    							<select name='ClassID' id='ClassID' class='form-control'>"+
						  	    <% 
						  	  	ClassMsDAO classDao = new ClassMsDAO();
						  		ArrayList<Map<String, String>> classMsgs = classDao.queryClassAll();
						  	    for (Map<String, String> classMsg : classMsgs) { %>
						  	    	<option value =<%=classMsg.get("classid") %>>
						  	    	<%=classMsg.get("classname") %></option>
						 		<%}%>
						 		</select>
    						</div>
						</div>
						<div class="form-group">
						    <label class="col-sm-4 control-label">绩点：</label>
						    <div class="col-sm-7">
    							<input type='text' class="form-control" placeholder='请填写绩点' name='Grade'/>
    						</div>
						</div>
						<div class="form-group">
						    <label class="col-sm-4 control-label">电话号码：</label>
						    <div class="col-sm-7">
    							<input type='text' class="form-control" placeholder='请填写电话号码' name='tel'/>
    						</div>
						</div>
						<div class="form-group">
						    <label class="col-sm-4 control-label">自我介绍：</label>
						    <div class="col-sm-7">
    							<input name='Intro' class="form-control" rows='3' cols='20'/>
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
	
	<!-- 添加系模态框   -->
	<form action="addInfo" method="post" class="form-horizontal">
		<div class="modal fade" id="addDeptModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog" style="margin-top: 120px">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		                <h4 class="modal-title" id="addDeptModal">添加系</h4>
		            </div>
		            <div class="modal-body">
		            	<input name="userType" type="hidden" value="dept" />
			            <div class="form-group">
						    <label class="col-sm-4 control-label">系编号：</label>
						    <div class="col-sm-7">
    							<input type='text' class="form-control" placeholder='请填写系编号' name='DeptID'/>
    						</div>
						</div>
						<div class="form-group">
						    <label class="col-sm-4 control-label">系名：</label>
						    <div class="col-sm-7">
    							<input type='text' class="form-control" placeholder='请填写系名'  name='DeptName'/>
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
	
	<!-- 添加班级模态框   -->
	<form action="addInfo" method="post" class="form-horizontal">
		<div class="modal fade" id="addClassModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog" style="margin-top: 120px">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		                <h4 class="modal-title" id="addClassModal">添加班级</h4>
		            </div>
		            <div class="modal-body">
		            	<input name="userType" type="hidden" value="class" />
			            <div class="form-group">
						    <label class="col-sm-4 control-label">班级编号：</label>
						    <div class="col-sm-7">
    							<input type='text' class="form-control" placeholder='请填写班级编号' name='ClassID'/>
    						</div>
						</div>
						<div class="form-group">
						    <label class="col-sm-4 control-label">系编号：</label>
						    <div class="col-sm-7">
    							<select name='DeptID' id='DeptID' class='form-control'>
						  	    <%for (Map<String, String> deptMsg : deptMsgs) { %>
						  	    	<option value =<%=deptMsg.get("deptid") %>>
						  	    	<%=deptMsg.get("deptname")%>
						  	    	</option>
						 		<%}%>
						 		</select>
    						</div>
						</div>
						<div class="form-group">
						    <label class="col-sm-4 control-label">班级名：</label>
						    <div class="col-sm-7">
    							<input type='text' class="form-control" placeholder='请填写班级名' name='ClassName'/>
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
	
	<!-- 编辑系模态框   -->
	<form action="updateInfo" method="post" class="form-horizontal">
		<div class="modal fade" id="editDeptModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog" style="margin-top: 120px">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		                <h4 class="modal-title" id="editDeptModal">编辑系</h4>
		            </div>
		            <div class="modal-body">
		            	<input name="userType" type="hidden" value="dept" />
		            	<input name="functionType" type="hidden" value="updateInfo" />
			            <div class="form-group">
						    <label class="col-sm-4 control-label">系编号：</label>
						    <div class="col-sm-7">
    							<input type='text' class="form-control" id="deptID" placeholder='请填写系编号' name='DeptID' readonly/>
    						</div>
						</div>
						<div class="form-group">
						    <label class="col-sm-4 control-label">系名：</label>
						    <div class="col-sm-7">
    							<input type='text' class="form-control" id="deptName" placeholder='请填写系名'  name='DeptName'/>
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
	
	<!-- 编辑班级模态框   -->
	<form action="updateInfo" method="post" class="form-horizontal">
		<div class="modal fade" id="editClassModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog" style="margin-top: 120px">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		                <h4 class="modal-title" id="editClassModal">编辑班级</h4>
		            </div>
		            <div class="modal-body">
		            	<input name="userType" type="hidden" value="class" />
		            	<input name="functionType" type="hidden" value="updateInfo" />
			            <div class="form-group">
						    <label class="col-sm-4 control-label">班级编号：</label>
						    <div class="col-sm-7">
    							<input type='text' class="form-control" id="classID" placeholder='请填写班级编号' name='ClassID' readonly/>
    						</div>
						</div>
						<div class="form-group">
						    <label class="col-sm-4 control-label">系编号：</label>
						    <div class="col-sm-7">
    							<select name='DeptID' id='DeptID' class='form-control'>
						  	    <%for (Map<String, String> deptMsg : deptMsgs) { %>
						  	    	<option value =<%=deptMsg.get("deptid") %>>
						  	    	<%=deptMsg.get("deptname")%>
						  	    	</option>
						 		<%}%>
						 		</select>
    						</div>
						</div>
						<div class="form-group">
						    <label class="col-sm-4 control-label">班级名：</label>
						    <div class="col-sm-7">
    							<input type='text' class="form-control" id="className" placeholder='请填写班级名' name='ClassName'/>
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
	
	<span class="nav">管理员端>>信息中心</span>
	<div class="main">
		<%
		if (request.getAttribute("result") != null) { //判断保存在request范围内的对象是否为空
			out.println("<script >alert('" + request.getAttribute("result")
					+ "');</script>"); //页面显示提示信息    	
		}
	%>
		<div class="htmleaf-container">
			<div class="container">
				<div class="tab-group">
					<section id="tab1" title="系列表">
						<h3>系列表</h3>
						<p>
						<div id="deptListForm">
							<form action="uploadFile" method="post" name="formDept" id="formDept"  onsubmit="return validate(formDept)"
							enctype="multipart/form-data"  onsubmit="console.log('1');" class="fileForm pull-left">
							     导入系信息： 
								<a href="javascript:;" class="file">选择文件
								    <input type="file" name="file" id="importDeptFile" onchange="importDeptFileFun()"/>
								</a>
								<input class="fileName" id="deptFileName" value="未选择文件" disabled/>
								<input type="hidden" name="uploadType" value="dept" id="deptid"/>
								<input type="submit" name="Submit" value="确定" class="btn btn-primary importFileBtn"/> 
								<input type="reset" name="Submit2" value="重置" class="btn btn-default importFileBtn"/>
						    </form>
						    <div class="pull-right">
							    <button class="btn btn-info exportInfo" data-toggle="modal" data-target="#addDeptModal">添加系</button>
								<button class="btn btn-info exportInfo"
									onClick="window.location.href='exportData?datatype=dept'">导出系</button>
							</div>
							<table id="dept" class="display" cellspacing="0" width="100%">
								<thead>
									<th>系编号</th>
									<th>系名</th>
									<th>编辑系</th>
									<!-- <th>删除</th> -->
							</table>
						</div>
						</p>
					</section>
					<section id="tab2" title="班级列表">
						<h3>班级列表</h3>
						<p>
						<div id="classListForm">
							<form action="uploadFile" method="post" name="formClass" id="formClass"
							enctype="multipart/form-data"  onsubmit="return validate(formClass)" class="fileForm pull-left">
							     导入班级信息： 
								<a href="javascript:;" class="file">选择文件
								    <input type="file" name="file" id="importClassFile" onchange="importClassFileFun()"/>
								</a>
								<input class="fileName" id="classFileName" value="未选择文件" disabled/>
								<input type="hidden" name="uploadType" value="class"/>
								<input type="submit" name="Submit" value="确定" class="btn btn-primary importFileBtn"/> 
								<input type="reset" name="Submit2" value="重置" class="btn btn-default importFileBtn"/>
						    </form>
						    <div class="pull-right">
							    <button class="btn btn-info exportInfo" data-toggle="modal" data-target="#addClassModal">添加班级</button>
								<button class="btn btn-info exportInfo"
								onClick="window.location.href='exportData?datatype=class'">导出班级</button>
							</div>
							<table id="class" class="display" cellspacing="0" width="100%">
								<thead>
									<th>班级编号</th>
									<th>班级名</th>
									<th>编辑班级</th>
									<!-- <th>删除</th> -->
							</table>
						</div>
						</p>
					</section>
					<section id="tab3" title="教师列表">
						<h3>教师列表</h3>
						<p>
						<div id="teacherListForm">
							<form action="uploadFile" method="post" name="formTeacher" id="formTeacher"
							enctype="multipart/form-data" onsubmit="return validate(formTeacher)" class="fileForm pull-left">
							     导入教师信息：
								<a href="javascript:;" class="file">选择文件
								    <input type="file" name="file" id="importTeacherFile" onchange="importTeacherFileFun()"/>
								</a>
								<input class="fileName" id="teacherFileName" value="未选择文件" disabled/>
								<input type="hidden" name="uploadType" value="teacher"/>
								<input type="submit" name="Submit" value="确定" class="btn btn-primary importFileBtn"/> 
								<input type="reset" name="Submit2" value="重置" class="btn btn-default importFileBtn"/>
						    </form>
						    <div class="pull-right">
						    	<button class="btn btn-info exportInfo" data-toggle="modal" data-target="#addTeacherModal">添加教师</button>
								<button class="btn btn-info exportInfo"
								onClick="window.location.href='exportData?datatype=teacher'">导出教师</button>
						    </div>
							<table id="teacher" class="display" cellspacing="0" width="100%">
								<thead>
									<tr>
										<th>工号</th>
										<th>姓名</th>
										<th>系名</th>
										<th>性别</th>
										<th>职称</th>
										<th>已选/限选人数</th>
										<th>是否具有反选资格</th>
										<th>详情</th>
										<!-- <th>删除</th> -->
									</tr>
								</thead>
							</table>
						</div>
						</p>
					</section>
					<section id="tab4" title="学生列表">
						<h3>学生列表</h3>
						<p>
						<div id="studentListForm">
							<form action="uploadFile" method="post" name="formStudent" id="formStudent"
							enctype="multipart/form-data" onsubmit="return validate(formStudent)" class="fileForm pull-left">
							   导入学生信息：
								<a href="javascript:;" class="file">选择文件
								    <input type="file" name="file" id="importStudentFile" onchange="importStudentFileFun()"/>
								</a>
								<input class="fileName" id="studentFileName" value="未选择文件" disabled/>
								<input type="hidden" name="uploadType" value="student"/>
								<input type="submit" name="Submit" value="确定" class="btn btn-primary importFileBtn"/> 
								<input type="reset" name="Submit2" value="重置" class="btn btn-default importFileBtn"/>
						    </form>
						    <div class="pull-right">
						    	<button class="btn btn-info exportInfo" data-toggle="modal" data-target="#addStudentModal">添加学生</button>
								<button class="btn btn-info exportInfo"
								onClick="window.location.href='exportData?datatype=student'">导出学生</button>
						    </div>

							<table id="student" class="display" cellspacing="0" width="100%">
								<thead>
									<th>学号</th>
									<th>姓名</th>
									<th>系名</th>
									<th>班级</th>
									<th>性别</th>
									<th>绩点</th>
									<th>导师</th>
									<th>状态</th>
									<th>修改</th>
									<th>删除</th>
							</table>
						</div>
						</p>
					</section>
					<section id="tab5" title="未选择导师">
						<div class="non-tutor-title" style="display: flex; justify-content: space-between">
							<h3>未选择导师的学生列表</h3>
							<button class="btn btn-info exportInfo"
								onClick="window.location.href='exportData?datatype=UnSelectStudent'">导出未选择导师学生</button>
						</div>
						<p>
						<div id="classListForm">
							<table id="UnSelectedStudent" class="display" cellspacing="0" width="100%">
								<thead>
									<th>学号</th>
									<th>姓名</th>
									<th>系名</th>
									<th>班级</th>
									<th>性别</th>
									<th>绩点</th>
									<th>电话</th>
							</table>
						</div>
						</p>
					</section>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function validate(formID) {
		if (formID.file.value == "") {
			swal("失败", "请选择要上传的文件", "error");
			return false;
		}
	}
	function importDeptFileFun() {
		var fileName = $("#importDeptFile").val();
		if(fileName==undefined || fileName=="")
			$("#deptFileName").val("未选择文件");
		else
			$("#deptFileName").val(fileName);
	}
	function importClassFileFun() {
		var fileName = $("#importClassFile").val();
		if(fileName==undefined || fileName=="")
			$("#classFileName").val("未选择文件");
		else
			$("#classFileName").val(fileName);
	}
	function importTeacherFileFun() {
		var fileName = $("#importTeacherFile").val();
		if(fileName==undefined || fileName=="")
			$("#teacherFileName").val("未选择文件");
		else
			$("#teacherFileName").val(fileName);
	}
	function importStudentFileFun() {
		var fileName = $("#importStudentFile").val();
		if(fileName==undefined || fileName=="")
			$("#studentFileName").val("未选择文件");
		else
			$("#studentFileName").val(fileName);
	}
	var activateTab = function(id) {
    	console.log("1");
	    $(".tab-nav li").filter('.active').removeClass('active');
	    $("section").filter('.active').removeClass('active');
	    $(".tab-nav li").has('a[href="' + id + '"]').addClass('active');
	    $("section").filter(id).addClass('active');
	  }
	$(function(){
		$('.tab-group').tabify();
		<%
		String clickType = (String)session.getAttribute("clickType");
		System.out.println("clickType:"+clickType);
		if(clickType!=null){
			if(clickType.equals("dept")){
				out.print("activateTab('#tab1');"); 
			}else if(clickType.equals("student")){
				out.print("activateTab('#tab4');"); 
			}else if(clickType.equals("teacher")){
				out.print("activateTab('#tab3');"); 
			}else if(clickType.equals("class")){
				out.print("activateTab('#tab2');"); 
			}
			session.removeAttribute("clickType");
		}
		%>
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
	})
	/*获取表格中的数据*/
	var arrayTeacher = new Array();
	var k=0;
	<%   
	TeacherMsDAO teacherDao = new TeacherMsDAO();
	ArrayList<Map<String, String>> teacherMsgs = teacherDao.queryTeacherList();
	for (Map<String, String> teacherMsg : teacherMsgs) {
		String  editTeacher = "<a class='btn btn-info' href='teacherDetail.jsp?teacherid="+teacherMsg.get("teacherid")+"'>查看</a>";
		// String  deleteTeacher = "<a class='btn btn-danger' onClick=deleteData(\'"+teacherMsg.get("teacherid")+"\',\'"+teacherMsg.get("teachername")+"\',\'"+"teacherid"+"\')>删除</a>";
      	// String  deleteTeacher = "<button type='button' class='btn btn-danger' onClick='teacherMsg.get('teacherid')'>确定</button>";
		String sex = "";
		if(teacherMsg.get("sex").equals("F")){
			sex="男";
		} else if(teacherMsg.get("sex").equals("M")){
			sex="女";
		} 
		
		String privilege = "";
		if(Integer.parseInt(teacherMsg.get("privilege"))==1){
			privilege="<span class='label label-success'>是</span>";
		} else{
			privilege="<span class='label label-info'>否</span>";
		} 
		deptDao = new DeptMsDAO();
		String  DeptName = deptDao.getDeptName(teacherMsg.get("deptid"));
		deptDao.close();
		StudentDAO sd = new StudentDAO();
		int cnt = sd.getNumOfStudentsToTeacher(teacherMsg.get("teacherid"));
	%>
		var t = new Array(9);
		t[0] = "<%=teacherMsg.get("teacherid")%>";
		t[1] = "<%=teacherMsg.get("teachername")%>";
		t[2] = "<%=DeptName%>";
		t[3] = "<%=sex%>";
		t[4] = "<%=teacherMsg.get("title")%>";
		t[5] = "<%=cnt%>/<%=teacherMsg.get("studentcount")%>";
		t[6] = "<%=privilege%>";
		t[7] = "<%=editTeacher%>";
		<%-- t[8] = "<%=deleteTeacher%>"; --%>
		arrayTeacher[k] = t;
		k++; 
	<%
	}
	teacherDao.close();
	%>
	
	var arrayStudent = new Array();
	var m=0;
	<%   
	StudentMsDAO studentDao = new StudentMsDAO();
	ArrayList<Map<String, String>> studentMsgs = studentDao.queryStudentList();
	for (Map<String, String> studentMsg : studentMsgs) {
		String  editStudent = "<a class='btn btn-info' href='studentDetail.jsp?stuid="+studentMsg.get("stuid")+"'>查看</a>";
		String  deleteStudent = "<a class='btn btn-danger' onClick=deleteData(\'"+studentMsg.get("stuid")+"\',\'"+studentMsg.get("stuname")+"\',\'"+"student"+"\')>删除</a>";
		String sex = "";
		if(studentMsg.get("sex").equals("F")){
			sex="男";
		} else if(studentMsg.get("sex").equals("M")){
			sex="女";
		}
		deptDao = new DeptMsDAO();
		String  DeptName = deptDao.getDeptName(studentMsg.get("deptid"));
		deptDao.close();
		
		classDao = new ClassMsDAO();
		String  ClassName = classDao.getClassName(studentMsg.get("classid"));
		deptDao.close();
		
		String tutor = "";
		if(studentMsg.get("teacherid")!=null&&!studentMsg.get("teacherid").equals("")){
			teacherDao = new TeacherMsDAO();
			tutor=teacherDao.getTeacherName(studentMsg.get("teacherid"));
			teacherDao.close();
		}else {
			tutor="无";
		}
		
		String choosedstate = "";
		if(studentMsg.get("choosedstate").equals("0")){
			choosedstate="<span>未选择</span>";
		} else if(studentMsg.get("choosedstate").equals("1")){
			choosedstate="<span>待定</span>";
		} else if(studentMsg.get("choosedstate").equals("2")){
			choosedstate="<span>淘汰</span>";
		} else if(studentMsg.get("choosedstate").equals("3")){
			choosedstate="<span>成功</span>";
		}
	%>
		var n = new Array(10);
		n[0] = "<%=studentMsg.get("stuid")%>";
		n[1] = "<%=studentMsg.get("stuname")%>";
		n[2] = "<%=DeptName%>";
		n[3] = "<%=ClassName%>";
		n[4] = "<%=sex%>";
		n[5] = "<%=studentMsg.get("grade")%>";
		n[6] = "<%=tutor%>";
		n[7] = "<%=choosedstate%>";
		n[8] = "<%=editStudent%>";
		n[9] = "<%=deleteStudent%>";
		arrayStudent[m] = n;
		m++;
	<%
	}
	studentDao.close();
	%>
	
	var arrayUnSelectedStudent = new Array();
	var m=0;
	<%   
	studentDao = new StudentMsDAO();
	studentMsgs = studentDao.queryUnSelectStudentList();
	for (Map<String, String> studentMsg : studentMsgs) {
		String sex = "";
		if(studentMsg.get("sex").equals("F")){
			sex="男";
		} else if(studentMsg.get("sex").equals("M")){
			sex="女";
		}
		deptDao = new DeptMsDAO();
		String  DeptName = deptDao.getDeptName(studentMsg.get("deptid"));
		deptDao.close();
		
		classDao = new ClassMsDAO();
		String  ClassName = classDao.getClassName(studentMsg.get("classid"));
		deptDao.close();
	%>
		var l = new Array(10);
		l[0] = "<%=studentMsg.get("stuid")%>";
		l[1] = "<%=studentMsg.get("stuname")%>";
		l[2] = "<%=DeptName%>";
		l[3] = "<%=ClassName%>";
		l[4] = "<%=sex%>";
		l[5] = "<%=studentMsg.get("grade")%>";
		l[6] = "<%=studentMsg.get("tel")%>";
		arrayUnSelectedStudent[m] = l;
		m++;
	<%
	}
	studentDao.close();
	%>


	
	var arrayDept = new Array();
	var m=0;
	<%   
	for (Map<String, String> deptMsg : deptMsgs) {
		String  viewDept = "<a href='deptDetail.jsp?deptid="+deptMsg.get("deptid")+"'>"+deptMsg.get("deptname")+"</a>";
		String  editDept = "<button class='btn btn-info' data-toggle='modal' data-target='#editDeptModal' onClick=editDept(\'"+deptMsg.get("deptid")+"\',\'"+deptMsg.get("deptname")+"\')>编辑系</button>";
		//String  deleteDept = "<a class='btn btn-danger' onClick=deleteData(\'"+deptMsg.get("deptid")+"\',\'"+deptMsg.get("deptname")+"\',\'"+"dept"+"\')>删除</a>";
	%>
		var n = new Array(4);
		n[0] = "<%=deptMsg.get("deptid")%>";
		n[1] = "<%=viewDept%>";
		n[2] = "<%=editDept%>";
		<%-- n[3] = "<%=deleteDept%>"; --%>
		arrayDept[m] = n;
		m++;
	<%
	}
	deptDao.close();
	%>
	
	var arrayClass = new Array();
	var m=0;
	<%   
	for (Map<String, String> classMsg : classMsgs) {
		String  viewClass = "<a href='classDetail.jsp?classid="+classMsg.get("classid")+"'>"+classMsg.get("classname")+"</a>";
		String  editClass = "<button class='btn btn-info' data-toggle='modal' data-target='#editClassModal' onClick=editClass(\'"+classMsg.get("classid")+"\',\'"+classMsg.get("classname")+"\')>编辑班级</button>";
		//String  deleteClass = "<a class='btn btn-danger' onClick=deleteData(\'"+classMsg.get("classid")+"\',\'"+classMsg.get("classname")+"\',\'"+"class"+"\')>删除</a>";
	%>
		var n = new Array(4);
		n[0] = "<%=classMsg.get("classid")%>";
		n[1] = "<%=viewClass%>";
		n[2] = "<%=editClass%>";
	<%-- 	n[3] = "<%=deleteClass%>"; --%>
		arrayClass[m] = n;
		m++;
	<%
	}
	classDao.close();
	%>
	/*datatable表格设定*/
	$(document).ready(function(){
		$('#teacher').DataTable({
			data: arrayTeacher,
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
			"bStateSave": true 
			}
		});
		$('#student').DataTable({
			data: arrayStudent,
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
		$('#UnSelectedStudent').DataTable({
			data: arrayUnSelectedStudent,
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
		$('#dept').DataTable({
			data: arrayDept,
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
		$('#class').DataTable({
			data: arrayClass,
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

<script type="text/javascript">
	$(function() {
	    $('#addTeacherModal').modal('hide');
	    $('#addStudentModal').modal('hide');
	    $('#addDeptModal').modal('hide');
	    $('#addClassModal').modal('hide');
	    $('#editDeptModal').modal('hide');
	    $('#editClassModal').modal('hide');
	});
	function editClass(classid, classname) {
		$("#classID").val(classid.toString());
		$("#className").val(classname.toString());
		
	}

	function editDept(deptid, deptname) {
		$("#deptID").val(deptid.toString());
		$("#deptName").val(deptname.toString());
	}
	
	// 删除
	function deleteData(id, name, type) {
    	swal({
		 	title: "确定删除的"+name+"的数据吗？",
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
		  		if(type=="class") {
		  			window.location.href="deleteInfo.jsp?dataType=class&id="+id;
		  		}
		  		else if(type=="dept") {
		  			window.location.href="deleteInfo.jsp?dataType=dept&id="+id;
		  		}
		  		else if(type=="teacher") {
		  			window.location.href="deleteInfo.jsp?dataType=teacher&id="+id;
		  		}
		  		else if(type=="student") {
		  			window.location.href="deleteInfo.jsp?dataType=student&id="+id;
		  		}
		  	} else { //取消
		  		
		  	}
		});
	}

</script>
</html>