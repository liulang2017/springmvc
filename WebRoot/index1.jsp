<%@page import="com.springmvc.bean.WindowWindowInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="wfc.service.config.Config"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	List<WindowWindowInfo> windowWindowInfoList =(List)request.getAttribute("windowWindowInfoList");
	int totalPages = (Integer)request.getAttribute("cou");
	int pageInt = Integer.parseInt(Config.get("history.page"));
	
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>练习界面（表格）</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jBootstrapPage.js"></script>

<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/jBootsrapPage.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
	$(document).ready(function(){
		//var data = $("#windowId").val();
		myFunction(<%=pageInt%>, 5, <%=totalPages%>);
		
	});
  	//分页函数
  	function myFunction(pageSize, buttons, total){
		$(".pagination").jBootstrapPage({
  	            pageSize : pageSize,
  	            total : total,
  	            maxPageButton:buttons,
  	            onPageClicked: function(obj, pageIndex) {
  	        	$.ajax({
		   			url:"${pageContext.request.contextPath}/turnAjax.do",
		   			type:"post",
		   			data: {nowpage:pageIndex+1},
		   			datatype:"json",
		   			error:function (){},
		   			success:function(result){
		   				var obj = eval('(' + result + ')');	   
		                var html = "";
		                for(var i = 0;i < obj.length;i++){
		                	html += "<tr><td>" + obj[i].stWindowId + "</td><td>" + obj[i].stWindowNo + "</td><td>" + obj[i].stUserId + "</td>"
		                    	+ "<td>" + obj[i].stWindowIp + "</td><td>" + obj[i].nmReserVation + "</td><td>"+ obj[i].nmOrganNodeId +"</td><td>" + obj[i].nmWindowStatus + "</td>"
		                    	+ "<td>" + obj[i].stInterActiveNo + "</td><td>" + obj[i].stInterActive + "</td><td>" + obj[i].stWindowName + "</td><td>" + obj[i].stVoiceChanne + "</td>"
		                    	+ "<td>" + obj[i].stCall + "</td><td>" + obj[i].stIspay + "</td><tr>";
		                    }
		                 	$("#tbodyid").html(html);
		  	        	}
					});
				}
		});
	}
  	//条件查询
  	function myQuery(){
   		$.ajax({
   			url:"${pageContext.request.contextPath}/myAjax.do",
   			type:"post",
   			datatype:"json",
   			data:{"stWindowId":$.trim($("#windowId").val())},
   			error:function (){},
   			success:function(result){
   			//console.log(result);
   			var obj = eval('(' + result + ')');
   			console.log(obj._current_page);
   			//alert(obj.data[0].stWindowId);
   			//$("#customers tr:gt(0)").remove();//删除之前的数据
                 var s = "";
                 for(var i = 0;i < obj.length;i++){
                	 s += "<tr><td>" + obj[i].stWindowId + "</td><td>" + obj[i].stWindowNo + "</td><td>" + obj[i].stUserId + "</td>"
                    	+ "<td>" + obj[i].stWindowIp + "</td><td>" + obj[i].nmReserVation + "</td><td>"+ obj[i].nmOrganNodeId +"</td><td>" + obj[i].nmWindowStatus + "</td>"
                    	+ "<td>" + obj[i].stInterActiveNo + "</td><td>" + obj[i].stInterActive + "</td><td>" + obj[i].stWindowName + "</td><td>" + obj[i].stVoiceChanne + "</td>"
                    	+ "<td>" + obj[i].stCall + "</td><td>" + obj[i].stIspay + "</td><tr>";
                    }
                   // $("#customers").append(s);
                 	$("#tbodyid").html(s);
               /* 
                var s = '';
                 s += "<tr><td>" + obj.ST_WINDOW_ID + "</td><td>" + obj.ST_WINDOW_NO + "</td><td>" + obj.ST_USER_ID + "</td>"
                    + "<td>" + obj.ST_WINDOW_IP + "</td><td>" + obj.NM_RESERVATION + "</td><td>"+ obj.NM_ORGAN_NODE_ID +"</td><td>" + obj.NM_WINDOW_STATUS + "</td>"
                    + "<td>" + obj.ST_INTERACTIVE_NO + "</td><td>" + obj.ST_INTERACTIVE_IP + "</td><td>" + obj.ST_WINDOW_NAME + "</td><td>" + obj.ST_VOICE_CHANNEL + "</td>"
                    + "<td>" + obj.ST_CALL + "</td><td>" + obj.ST_ISPAY + "</td><tr>";
                $("#customers").append(s);
                */
   				//alert(obj.ST_WINDOW_ID);
   				}
   		});
  	}	
</script>
</head>

<body >
	<!--  <h1 style="text-align: center;">This is a table.</h1> -->
	<form  id="conditionsSelect1"> 
		<table style="margin:  10px 2px;">
			<tr>
			<td colspan="3">
				<div class="input-group"><span class="input-group-addon">窗口号（ajax方式）</span> <input type="text" size="20% 
								 class="form-control" id="windowId" name="stWindowId" >
				</div>
			</td>
			<td rowspan="3" width="80px;">
				<button id="mytest" type="button"  onclick="myQuery()" class="btn btn-primary btn-lg">查询</button>
			</td>
			</tr>
		</table>
	</form>
	<form action="conditionsSelect1.do" method="post" id="conditionsSelect"> 
		<table style="margin:  10px 2px;">
			<tr>
			<td colspan="3">
				<div class="input-group"><span class="input-group-addon">窗口号(submit方式)</span> <input type="text" size="20% 
								 class="form-control" id="windowId" name="stWindowId"
								>
				</div>
			</td>
			<td rowspan="3" width="80px;">
				<button type="submit" class="btn btn-primary btn-lg">查询</button>
			</td>
			</tr>
		</table>
	</form> 
	
	<table  class="table table-bordered table-striped table-hover table-condensed"
					contenteditable="true"style="margin-bottom: 60px;">  
		<tbody>
			<thead>
			<tr>
				<th>ST_WINDOW_ID</th>
				<th>ST_WINDOW_NO</th>
				<th>ST_USER_ID</th>
				<th>ST_WINDOW_IP</th>
				<th>NM_RESERVATION</th>
				<th>NM_ORGAN_NODE_ID</th>
				<th>NM_WINDOW_STATUS</th>
				<th>ST_INTERACTIVE_NO</th>
				<th>ST_INTERACTIVE_IP</th>
				<th>ST_WINDOW_NAME</th>
				<th>ST_VOICE_CHANNE</th>
				<th>ST_CALL</th>
				<th>ST_ISPAY</th>
			</tr>
			</thead>
			<tbody id="tbodyid">
		 	<% 
				for(WindowWindowInfo temp: windowWindowInfoList){ 
			%>
			<tr>
				<td><%=temp.getStWindowId()%></td>
				<td><%=temp.getStWindowNo() %></td>
				<td><%=temp.getStUserId() %></td>
				<td><%=temp.getStWindowIp()%></td>
				<td><%=temp.getNmReserVation() %></td>
				<td><%=temp.getNmOrganNodeId() %></td>
				<td><%=temp.getNmWindowStatus() %></td>
				<td><%=temp.getStInterActiveNo() %></td>
				<td><%=temp.getStInterActive() %></td>
				<td><%=temp.getStWindowName() %></td>
				<td><%=temp.getStVoiceChanne() %></td>
				<td><%=temp.getStCall() %></td>
				<td><%=temp.getStIspay() %></td>
			</tr>
				<%
				}
			 	%>
			 </tbody>
		 </tbody>
	</table>
	<nav class="navbar-default navbar-fixed-bottom submitbottom" role="navigation" >
		<ul class="pagination">
			    
		</ul>
	</nav>
</body>
</html>
