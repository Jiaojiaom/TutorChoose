<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="db.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导师列表</title>
<link rel="stylesheet" type="text/css" href="../lib/table.css">
<link rel="stylesheet" type="text/css" href="../lib/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="../lib/bootstrap.min.css">
<link rel="stylesheet" href="../lib/sweetalert.css">
<script type="text/javascript" language="javascript" src="../lib/jquery.min.js"></script>
<script type="text/javascript" language="javascript" src="../lib/sweetalert-dev.js"></script>
<script type="text/javascript" language="javascript" src="../lib/jquery.dataTables.min.js">
</script>
</head>
<body>
	<%@ include file="navbar.jsp" %>
	<%
	//显示规则和反选导师说明
	HttpSession s = request.getSession();
	String stuId = (String)s.getAttribute("stuId");
	String systemPri = (String)application.getAttribute("PrivilegeModel");
	int sysPri = 0;
	if(systemPri.equals("on")){
		systemPri = "已开启，导师可对学生进行反选";
		sysPri = 1;
	}else{
		systemPri = "导师反选暂未开启";
	}
	StudentDAO so = new StudentDAO();
	ArrayList<Map<String,String>> teacherlist = so.teacherList(stuId);
	String rule = null;
	int i = 0;
	do{
		rule = teacherlist.get(i).get("privilege");
		i++;
	}while(rule.equals("1"));
	System.out.println("rule:"+rule);
	if(rule.equals("3")){
		rule = "先到先得";
	}
	else{
		rule = "成绩优先";
	}
	%>
	<span class="nav">学生端>>导师信息中心</span>
	<div class="main">
	<div>说明:<a id="toggle"><img src="../images/sort_desc.png"></a>
			<div class="detail">选择规则分别是先到先得,成绩优先和导师反选，反选导师人数不受限，请同学们主动联系相关导师。</div>
			</div>
			
<script type="text/javascript">
	$("#toggle").click(function(){
		$("div .detail").toggle(300);
	})
</script>
			<div>
			<span class="rule">当前选择规则:<%=rule %></span><br>
			<span class="rule">当前导师反选资格是否开启:&nbsp;<%=systemPri %></span>
			<table id="teacher" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>工号</th>
                    <th>姓名</th>
					<th>性别</th>
					<th>职位</th>
					<th>专业</th>
					<th>选择</th>
					<th>反选</th>
					<th>操作</th>
				</tr>
			</thead>
			 <tbody></tbody>
		</table>
	</div>
</body>
<style type="text/css">
	body{
		margin: 0;
		padding: 0;
		display:flex;
		flex-direction: column;
		height: 100vh; 
		width:100%;
		overflow-y: scroll;
	}
	.main{
		display: flex;
		flex-direction: column;
		width: 90%;
		margin-left: 2%;
		padding: 10px;
	}
	.main table{
		width: 98%;
	}
	.nav{
		margin-top: 50px;
		line-height:40px;
		font-size:10px;
		align-self: flex-start;
		margin-left:2%;
	}
	.rule{
		position: absolute;
    	color: #5e8ece;
    	line-height: 30px;
	}
	.detail{
		 display: none;
		 font-size: 14px
	}
</style>
<script type="text/javascript" language="javascript">
/*获取表格中的数据*/
var array = new Array();
var k=0;
	<% 
	for (Map<String, String> map : teacherlist) {
		//System.out.println(map);
		String teacherid = map.get("teacherid");
		String sex = ":)";
		if(map.get("sex").equals("F"))
			sex = "女";
		else if(map.get("sex").equals("M"))
			sex = "男";
		//已选学生数
		int studentNum = 0;
		ArrayList<Map<String,String>> sctlist = so.studentChoosedTeacher(teacherid);
		for(Map<String, String> sct : sctlist){
			studentNum++;
		}	
		//能否反选
		String privilege = null;
		if(map.get("privilege").equals("1") && sysPri == 1){
			privilege = "能";
		}
		else privilege = "否";
	%>
		var t = new Array(8);
		t[0] = "<%=teacherid%>";
		t[1] = "<%=map.get("teachername")%>";
		t[2] = "<%=sex%>";
		t[3] = "<%=map.get("title")%>";
		t[4] = "<%=map.get("deptname")%>";
		t[5] = "<%=studentNum %>/<%=map.get("studentcount") %>";
		t[6] = "<%=privilege%>";
		t[7] = "<a href='detail.jsp?teacherid=<%=teacherid%>'>查看详情</a>";
		array[k] = t;
		k++;
	<%}%>
	/*datatable表格设定*/
	$(document).ready(function() {
		$('#teacher').DataTable( {
			data: array,
			"bLengthChange": false, //改变每页显示数据数量
			ordering: false,
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
			"bStateSave": true //保存状态到cookie *************** 很重要 ， 当搜索的时候页面一刷新会导致搜索的消失。使用这个属性就可避免了 
			}
		} );
	} );
</script>
</html>
