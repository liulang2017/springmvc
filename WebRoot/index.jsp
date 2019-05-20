<%@page import="com.springmvc.bean.WindowWindowInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="wfc.service.config.Config"%>

<%
	//List<WindowWindowInfo> windowWindowInfoList =(List)request.getAttribute("windowWindowInfoList");
	int totalPages = (Integer)request.getAttribute("cou");
	//int pageInt = Integer.parseInt(Config.get("history.page"));
	
%> 
<!DOCTYPE HTML>
<html>
<head>

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
<script src="${pageContext.request.contextPath}/js/jquery.pagination.js"></script>

<link href="${pageContext.request.contextPath}/css/bootstrap.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/pagination.css"
	rel="stylesheet" type="text/css">

</head>

<body>
	<!--  <h1 style="text-align: center;">This is a table.</h1> -->
	<form id="conditionsSelect1">
		<table style="margin:  10px 2px;">
			<tr>
				<td colspan="3">
					<div class="input-group">
						<span class="input-group-addon">窗口号（ajax方式）</span> <input
							type="text" size="20% 
								 class="form-control" id="windowId" name="stWindowId">
					</div></td>
				<td rowspan="3" width="80px;">
					<button id="mytest" type="button" onclick="myQuery()"
						class="btn btn-primary btn-lg">查询</button></td>
			</tr>
		</table>
	</form>
	<table
		class="table table-bordered table-striped table-hover table-condensed"
		contenteditable="true" style="margin-bottom: 60px;">
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
		<tbody id="histbody" >
				
			</tbody>
	</table>
	<nav class="navbar-default navbar-fixed-bottom submitbottom"
		role="navigation">
		<div style="padding-top: 5px;padding-bottom: 5px;">
			<div class="M-box" id="my"></div>
			<div style="float:right;margin-top: -25px;margin-right: 20px;">
				<b id="dqxs">当前显示</b>
			</div>
		</div>
	</nav>
<script type="text/javascript">
var dycjz = 0;
var showData = "20";
var totalDatas = "0";
var ST_WINDOW_ID = $.trim($("#windowId").val());
createPage(1);
	
	//分页函数
	function createPage(nowpage) {
		var xh = showData * nowpage - showData + 1;
		var one = xh;
		$.ajax({
			type : "post",
			url : "${pageContext.request.contextPath}/turnAjax.do",
			data : {nowpage : nowpage,pageInt : showData,ST_WINDOW_ID : ST_WINDOW_ID},
			dataType : "json",
			error : function() {},
			success : function(data) {
				totalDatas = data.total;
				if(data.total>0){
					if(dycjz == 0){
						$("#my").pagination({
							totalData:totalDatas,
							showData:showData,
							coping: true,
							jump:true,
							callback:function(api){
								createPage(api.getCurrent());
							}
						});
					}
					var html = "";
					$.each(data.rows, function(i, item) {
						//console.log(item); 
						html += "<tr><td>"+ item.stWindowId+ "</td><td>"+ item.stWindowNo+ "</td><td>"+ item.stUserId+ "</td>"
							+ "<td>"+ item.stWindowIp+ "</td><td>"+ item.nmReserVation+ "</td><td>"+ item.nmOrganNodeId+ "</td><td>"+ item.nmWindowStatus+ "</td>"
							+ "<td>"+ item.stInterActiveNo+ "</td><td>"+ item.stInterActive+ "</td><td>"+ item.stWindowName+ "</td><td>"+ item.stVoiceChanne
							+ "</td>"+ "<td>"+ item.stCall+ "</td><td>"+ item.stIspay+ "</td><tr>";
						/*对应原生写法
						html += "<tr><td>" + item.ST_WINDOW_ID + "</td><td>" + item.ST_WINDOW_NO + "</td><td>" + item.ST_USER_ID + "</td>"
					     + "<td>" + item.ST_WINDOW_IP + "</td><td>" + item.NM_RESERVATION + "</td><td>"+ item.NM_ORGAN_NODE_ID +"</td><td>" + item.NM_WINDOW_STATUS + "</td>"
					     + "<td>" + item.ST_INTERACTIVE_NO + "</td><td>" + item.ST_INTERACTIVE_IP + "</td><td>" + item.ST_WINDOW_NAME + "</td><td>" + item.ST_VOICE_CHANNEL + "</td>"
					     + "<td>" + item.ST_CALL + "</td><td>" + item.ST_ISPAY + "</td><tr>";
						*/
						xh++;
						});
						$("#histbody").html(html);
						$("#dqxs").text("当 前 显 示 "+one+" 到 "+(xh-1)+"，共 "+totalDatas+" 记 录 ");
					}else{
	        		$("#histbody").html(" ");
	        		$("#dqxs").text("当 前 显 示 0 到 0 ，共 0 记 录 ");
        			}
        			dycjz++;
        		}
        	});
	}

		
		//条件查询
		function myQuery() {
			dycjz = 0;
			ST_WINDOW_ID = $.trim( $("#windowId").val());
			createPage(1);
		}
		/*
			$.ajax({
				url:"${pageContext.request.contextPath}/myAjax.do",
				type:"post",
				datatype:"json",
				data: {"stWindowId":$.trim($("#windowId").val())},
				error: function() {},
				success : function(result) {
					//console.log(result);
					var item = eval('(' + result + ')');
					//console.log(item[0]);
					//alert(item.data[0].stWindowId);
					//$("#customers tr:gt(0)").remove();//删除之前的数据
					var s = "";
					for ( var i = 0; i < item.length; i++) {
						s += "<tr><td>"+ item.stWindowId+ "</td><td>"+ item.stWindowNo+ "</td><td>"+ item.stUserId+ "</td>"
							+ "<td>"+ item.stWindowIp+ "</td><td>"+ item.nmReserVation+ "</td><td>"+ item.nmOrganNodeId+ "</td><td>"+ item.nmWindowStatus+ "</td>"
							+ "<td>"+ item.stInterActiveNo+ "</td><td>"+ item.stInterActive+ "</td><td>"+ item.stWindowName+ "</td><td>"+ item.stVoiceChanne
							+ "</td>"+ "<td>"+ item.stCall+ "</td><td>"+ item.stIspay+ "</td><tr>";
					}
					// $("#customers").append(s);
					$("#tbodyid").html(s);
					
			
					 var s = '';
					  s += "<tr><td>" + item.ST_WINDOW_ID + "</td><td>" + item.ST_WINDOW_NO + "</td><td>" + item.ST_USER_ID + "</td>"
					     + "<td>" + item.ST_WINDOW_IP + "</td><td>" + item.NM_RESERVATION + "</td><td>"+ item.NM_ORGAN_NODE_ID +"</td><td>" + item.NM_WINDOW_STATUS + "</td>"
					     + "<td>" + item.ST_INTERACTIVE_NO + "</td><td>" + item.ST_INTERACTIVE_IP + "</td><td>" + item.ST_WINDOW_NAME + "</td><td>" + item.ST_VOICE_CHANNEL + "</td>"
					     + "<td>" + item.ST_CALL + "</td><td>" + item.ST_ISPAY + "</td><tr>";
					 $("#customers").append(s);
				
					//alert(item.ST_WINDOW_ID);
				}
			});
		}*/
	</script>
</body>
</html>
