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
	    <td colspan="2" class="tableTitle">添加单个学生信息</td>
	</tr>
    <form id="form1" name="form1" method="post" action="addStudentInfo">
    	<tr>
		    <td class="subtitle">学号： </td>
		     <td><input name="StuID" type="text" id="StuID"></td>
		</tr>
    	<tr>
		    <td class="subtitle">姓名： </td>
		    <td><input name="StuName" type="text" id="StuName"></td>
		</tr>
		<tr>
		    <td class="subtitle">系： </td>
		    <td>
		       <select name="DeptID" id="DeptID">
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
		    <td class="subtitle">班级：</td>
		    <td>
		       <select name="ClassID" id="ClassID">
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
		    <td><input name="SPassword" type="password" id="SPassword"></td>
		</tr>
		<tr>
		    <td class="subtitle">性别： </td>
		    <td> 
		      <input type="radio" name="Sex" value="男" checked>男
              <input type="radio" name="Sex" value="女">女
            </td>
		</tr>
		<tr>
		    <td class="subtitle">成绩: </td>
		    <td><input name="Grade" type="text" id="Grade"></td>
		</tr>
		<tr>
		    <td class="subtitle">电话: </td>
		    <td><input name=tel type="text" id="tel"></td>
		</tr>
		<tr>
		    <td class="subtitle">自我介绍： </td>
		    <td><textarea name="Intro" rows="3" cols="20"></textarea></br></td>
		</tr>
		<tr>
		  <td align="center"><input type="submit" name="submit" value="插入" float="right"></td>
          <td align="center"><input type="reset" value="重置"></td>
		<tr>
	</form>
</table>
</body>
</html>