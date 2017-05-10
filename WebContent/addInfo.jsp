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
<%
DeptMsDAO deptDao = new DeptMsDAO();
ArrayList<Map<String, String>> deptMsgs = deptDao.queryDeptAll();
ClassMsDAO classDao = new ClassMsDAO();
ArrayList<Map<String, String>> classMsgs = classDao.queryClassAll(); 
%>
<div>
<table style="margin-left:auto;margin-right:auto;">
	<tr>
	    <td colspan="2" class="tableTitle">添加单个学生信息</td>
	</tr>
    <form id="form1" name="form1" method="post" action="addInfo">
        <input name="userType" type="hidden" value="student" />
    	<tr>
		    <td class="subtitle">学号：  </td>
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
		    <td class="subtitle">班级：</td>
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
</div>
<div>
<table style="margin-left:auto;margin-right:auto;">
	<tr>
	    <td colspan="2" class="tableTitle">添加单个老师信息</td>
	</tr>
    <form id="form1" name="form1" method="post" action="addInfo">
        <input name="userType" type="hidden" value="teacher" />
    	<tr>
		    <td class="subtitle">工号：  </td>
		     <td><input name="TeacherID" type="text" id="TeacherID"></td>
		</tr>
    	<tr>
		    <td class="subtitle">姓名： </td>
		    <td><input name="TeacherName" type="text" id="TeacherName"></td>
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
		    <td class="subtitle">密码： </td>
		    <td><input name="TPassword" type="password" id="TPassword"></td>
		</tr>
		<tr>
		    <td class="subtitle">性别： </td>
		    <td> 
		      <input type="radio" name="Sex" value="M" checked>男
              <input type="radio" name="Sex" value="F">女
            </td>
		</tr>
		<tr>
		    <td class="subtitle">职称: </td>
		    <td>
		      <input type="radio" name="Title" value="副教授" checked>副教授
              <input type="radio" name="Title" value="教授">教授
		    </td>
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
</div>
<div>
<table style="margin-left:auto;margin-right:auto;">
	<tr>
	    <td colspan="2" class="tableTitle">添加单个系</td>
	</tr>
    <form id="form1" name="form1" method="post" action="addInfo">
        <input name="userType" type="hidden" value="dept" />
    	<tr>
		    <td class="subtitle">系编号：  </td>
		     <td><input name="DeptID" type="text" id="DeptID"></td>
		</tr>
    	<tr>
		    <td class="subtitle">系名： </td>
		    <td><input name="DeptName" type="text" id="DeptName"></td>
		</tr>
		<tr>
		  <td align="center"><input type="submit" name="submit" value="插入" float="right"></td>
          <td align="center"><input type="reset" value="重置"></td>
		<tr>
	</form>
</table>
</div>
<div>
<table style="margin-left:auto;margin-right:auto;">
	<tr>
	    <td colspan="2" class="tableTitle">添加单个班级</td>
	</tr>
    <form id="form1" name="form1" method="post" action="addInfo">
        <input name="userType" type="hidden" value="class" />
    	<tr>
		    <td class="subtitle">班级编号：  </td>
		     <td><input name="ClassID" type="text" id="ClassID"></td>
		</tr>
    	<tr>
		    <td class="subtitle">班级名： </td>
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
		  <td align="center"><input type="submit" name="submit" value="插入" float="right"></td>
          <td align="center"><input type="reset" value="重置"></td>
		<tr>
	</form>
</table>
</div>
<%
deptDao.close(); 
classDao.close();%>
</body>
</html>