<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="db.StuMsDAO" %>
<%@ page import="javabean.StuMsg" %>
<%@ page import="db.DeptMsDAO" %>
<%@ page import="db.ClassMsDAO" %>
<%@ page import="db.TeacherMsDAO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.tableTitle{
	   text-align:center;
	   font-size:24px;
	   font-weight:bold;
	   border-bottom:1px dotted blue;
    }
    .subtitle {
	   font-weight:bold;
	   text-align:right;
	}
	td {
	   height:40px;
	}
</style>
</head>
<body>
<!-- 表格控制 -->
<table style="margin-left:auto;margin-right:auto;">
	<tr>
	    <td colspan="2" class="tableTitle">修改学生信息</td>
	</tr>
	<%
	   String stuid=request.getParameter("stuid");
	   StuMsDAO stuDao = new StuMsDAO();
       StuMsg stuMsg = stuDao.queryByStuId(stuid);
       System.out.println(stuMsg.getIntro());
	 %>
    <form id="form1" name="form1" method="post" action="updateStudentInfo">
    	<tr>
		    <td class="subtitle">学号： </td>
		     <td><input name="StuID" type="text" id="StuID" value=<%=stuMsg.getStuID()%>></td>
		</tr>
    	<tr>
		    <td class="subtitle">姓名： </td>
		    <td><input name="StuName" type="text" id="StuName" value=<%=stuMsg.getStuName()%>></td>
		</tr>
		<tr>
		    <td class="subtitle">系： </td>
		    <td>
		       <select name="DeptID" id="DeptID" value=<%=stuMsg.getDeptID()%>>
		      <%
			    DeptMsDAO deptDao = new DeptMsDAO();
				ArrayList<Map<String, String>> deptMsgs = deptDao.queryDeptAll();
				for (Map<String, String> deptMsg : deptMsgs) {	
			  %>
			     <option value =<%=deptMsg.get("deptid") %>><%=deptMsg.get("deptid") %></option>
			  <%
			    }
				deptDao.close();
		      %>
		      </select>
		     </td>
		</tr>
		<tr>
		    <td class="subtitle">班级： </td>
		    <td>
		       <select name="ClassID" id="ClassID" value=<%=stuMsg.getClassID()%>>
		      <%
			    ClassMsDAO classDao = new ClassMsDAO();
				ArrayList<Map<String, String>> classMsgs = classDao.queryClassAll();
				for (Map<String, String> classMsg : classMsgs) {	
			  %>
			     <option value =<%=classMsg.get("classid") %>><%=classMsg.get("classid") %></option>
			  <%
			    }
				classDao.close();
		      %>
		      </select>
		     </td>
		</tr>
		<tr>
		    <td class="subtitle">密码： </td>
		    <td><input name="SPassword" type="password" id="SPassword" value=<%=stuMsg.getSPassword()%>></td>
		</tr>
		<tr>
		    <td class="subtitle">性别： </td>
		    <td> 
		      <%
			    if(stuMsg.getSex().equals("F")){
			  %>
			    <input type="radio" name="Sex" value="M">男
			    <input type="radio" name="Sex" value="F" checked>女
			  <%
			    } else {
		      %>
		        <input type="radio" name="Sex" value="F" checked>男
			    <input type="radio" name="Sex" value="M">女
		      <%
			    } 
		      %>
            </td>
		</tr>
		<tr>
		    <td class="subtitle">绩点: </td>
		    <td><input name="Grade" type="text" id="Grade" value=<%=stuMsg.getGrade()%>></td>
		</tr>
		<tr>
		    <td class="subtitle">电话: </td>
		    <td><input name="tel" type="text" id="tel" value=<%=stuMsg.getTel()%>></td>
		</tr>
		<tr>
		    <td class="subtitle">自我介绍： </td>
		    <td><textarea name="Intro" rows="3" cols="20"><%=stuMsg.getIntro()%></textarea></br></td>
		</tr>
		<tr>
		    <td class="subtitle">导师编号：  </td>
		    <td>
		     <select name="TeacherID" id="TeacherID" value=<%=stuMsg.getTeacherID()%>>
		      <%
			    TeacherMsDAO teacherDao = new TeacherMsDAO();
				ArrayList<Map<String, String>> teacherMsgs = teacherDao.queryTeacherAll();
				for (Map<String, String> teacherMsg : teacherMsgs) {	
			  %>
			     <option value =<%=teacherMsg.get("teachername") %>><%=teacherMsg.get("teachername") %></option>
			  <%
			    }
				deptDao.close();
		      %>
		      </select>
		    </td>
		</tr>
		<tr>
		    <td class="subtitle">状态： </td>
		    <td> 
			    <%
				    if(stuMsg.getChoosedState()==0){
				%>
				    <input type="radio" name="ChooseState" value=0 checked>未选
				    <input type="radio" name="ChooseState" value=1 >待定
				    <input type="radio" name="ChooseState" value=2 >成功
				    <input type="radio" name="ChooseState" value=3 >淘汰
				<%
				    } else {
			    %>
			        <input type="radio" name="ChooseState" value=0 >未选
				    <input type="radio" name="ChooseState" value=1 checked>待定
				    <input type="radio" name="ChooseState" value=2 >成功
				    <input type="radio" name="ChooseState" value=3 >淘汰
			    <%
				    } 
			    %>
            </td>
		</tr>
		<tr>
		    <td class="subtitle">选择时间：  </td>
		    <td><input name="SelectDate" rows="3" cols="20" value=<%=stuMsg.getSelectDate()%>></br></td>
		</tr>
		<tr>
		  <td align="center"><input type="submit" name="submit" value="保存修改" float="right"></td>
		<tr>
	</form>
</table>
</body>
</html>