<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="db.DeptMsDAO" %>
<%@ page import="db.ClassMsDAO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../lib/bootstrap.min.css">
<link rel="stylesheet" href="../lib/sweetalert.css">
<script type="text/javascript" language="javascript" src="//code.jquery.com/jquery-1.12.4.js">
</script>
<script type="text/javascript" language="javascript" src="../lib/sweetalert-dev.js"></script>

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
</head>
<body>
<!-- 表格控制 -->
<%
DeptMsDAO deptDao = new DeptMsDAO();
ArrayList<Map<String, String>> deptMsgs = deptDao.queryDeptAll();
ClassMsDAO classDao = new ClassMsDAO();
ArrayList<Map<String, String>> classMsgs = classDao.queryClassAll(); 
%>

    <%@ include file="../public/navbar.html" %>
	<span class="nav">管理员>>添加信息</span>
	<div class="main">
		<form action="addInfo" method="post" style="width: 50%">
		<input name="userType" type="hidden" value="teacher" />
			<div class="panel" style="width: 100%">
			    <div class="panel-heading">
			        <h3 class="panel-title">
			                           添加单个教师
			        </h3>
			    </div>
			    <div class="panel-body">
				<table style="width: 100%">
					<tr>
						<td>
							工号
						</td>
				    	<td><input name="TeacherID" type="text" id="TeacherID"></td>
					</tr>
					<tr>
						<td>
							姓名
						</td>
				    	<td><input name="TeacherName" type="text" id="TeacherName"></td>
					</tr>
					<tr>
						<td>
							性别
						</td>
				    	<td>
				    	 <input type="radio" name="Sex" value="M" checked>男
                         <input type="radio" name="Sex" value="F">女
                        </td>
					</tr>
					
					<tr>
						<td>
							系
						</td>
				    	<td>
				    	<select name="DeptID" id="DeptID">
					      <%
							for (Map<String, String> deptMsg : deptMsgs) {	
						  %>
						     <option value =<%=deptMsg.get("deptid") %>><%=deptMsg.get("deptname") %></option>
						  <%
						    }
							deptDao.close();
					      %>
					    </select>
				    	</td>
					</tr>
					<tr>
						<td>
							职称: 
						</td>
				    	<td>
				    	  <input type="radio" name="Title" value="副教授" checked>副教授
                             <input type="radio" name="Title" value="教授">教授
				    	</td>
					</tr>
					<tr>
						<td>
							电话号码
						</td>
				    	<td><input name=tel type="text" id="tel"></td>
					</tr>
					<tr>
						<td>
							自我介绍
						</td>
				    	<td><textarea name="Intro" rows="3" cols="20"></textarea></td>
					</tr>
					<tr>
					  <td>
					   <div style="text-align: center">
						<input type="submit" name="submit" value="插入" float="right">
					   </div>
					 </td>	
					</tr>
				</table>
			    </div>
			</div>
		</form>
		
		<form action="addInfo" method="post" style="width: 50%">
		    <input name="userType" type="hidden" value="student" />
			<div class="panel" style="width: 100%">
			    <div class="panel-heading">
			        <h3 class="panel-title">
			                           添加单个学生
			        </h3>
			    </div>
			    <div class="panel-body">
				<table style="width: 100%">
					<tr>
						<td>
							学号
						</td>
				    	<td><input name="StuID" type="text" id="StuID"></td>
					</tr>
					<tr>
						<td>
							姓名
						</td>
				    	<td><input name="StuName" type="text" id="StuName"></td>
					</tr>
					<tr>
						<td>
							性别
						</td>
				    	<td>
				    	 <input type="radio" name="Sex" value="M" checked>男
                            <input type="radio" name="Sex" value="F">女
                           </td>
					</tr>
					
					<tr>
						<td>
							专业
						</td>
				    	<td>
				    	<select name="DeptID" id="DeptID">
					      <%
							for (Map<String, String> deptMsg : deptMsgs) {	
						  %>
						     <option value =<%=deptMsg.get("deptid") %>><%=deptMsg.get("deptname") %></option>
						  <%
						    }
							deptDao.close();
					      %>
					    </select>
				    	</td>
					</tr>
					<tr>
						<td>
							班级
						</td>
				    	<td>
				    	<select name="ClassID" id="ClassID">
					      <%
							for (Map<String, String> classMsg : classMsgs) {	
						  %>
						     <option value =<%=classMsg.get("classid") %>><%=classMsg.get("classname") %></option>
						  <%
						    }
							classDao.close();
					      %>
					     </select>
				    	</td>
					</tr>
					<tr>
						<td>
							电话号码
						</td>
				    	<td><input name="tel" type="text" id="tel"></td>
					</tr>
					<tr>
						<td>
							绩点
						</td>
				    	<td><input name="Grade" type="text" id="Grade"></td>
					</tr>
					<tr>
						<td>
							自我介绍
						</td>
				    	<td><textarea name="Intro" rows="3" cols="20"></textarea></td>
					</tr>
					<tr>
					<td>
					   <div style="text-align: center">
						<input type="submit" name="submit" value="插入" float="right">
					   </div>
					 </td>
					</tr>
				</table>
			   </div>
			</div>
		</form>

		<form action="addInfo" method="post" style="width: 50%">
		<input name="userType" type="hidden" value="dept" />
			<div class="panel" style="width: 100%">
			    <div class="panel-heading">
			        <h3 class="panel-title">
			                           添加单个系
			        </h3>
			    </div>
			    <div class="panel-body">
				<table style="width: 100%">
					<tr>
						<td>
							系编号
						</td>
				    	<td><input name="DeptID" type="text" id="DeptID"></td>
					</tr>
					<tr>
						<td>
							系名
						</td>
				    	<td><input name="DeptName" type="text" id="DeptName"></td>
					</tr>
					<tr>
					 <td>
					   <div style="text-align: center">
						<input type="submit" name="submit" value="插入" float="right">
					   </div>
					 </td>
					</tr>				
				</table>
				</div>
			</div>
		</form>

		<form action="addInfo" method="post" style="width: 50%">
		<input name="userType" type="hidden" value="class" />
			<div class="panel" style="width: 100%">
			   <div class="panel-heading">
			        <h3 class="panel-title">
			                           添加单个班级
			        </h3>
			    </div>
			    <div class="panel-body">
				<table style="width: 100%">
					<tr>
						<td>
							班级编号
						</td>
				    	<td><input name="ClassID" type="text" id="ClassID"></td>
					</tr>
					<tr>
						<td>
							班级名
						</td>
				    	<td><input name="ClassName" type="text" id="ClassName"></td>
					</tr>		
					<tr>
	   				    <td class="subtitle">系： </td>
					    <td>
					       <select name="DeptID" id="DeptID">
					      <%
							for (Map<String, String> deptMsg : deptMsgs) {	
						  %>
						     <option value =<%=deptMsg.get("deptid") %>><%=deptMsg.get("deptname") %></option>
						  <%
						    }
					      %>
					      </select>
					     </td>
					</tr>	
					<tr>
					 <td>
					   <div style="text-align: center">
						<input type="submit" name="submit" value="插入" float="right">
					   </div>
					 </td>
					</tr>	
				</table>
				</div>
			</div>
		</form>
    </div>
<%
deptDao.close(); 
classDao.close();
%>
</body>
</html>