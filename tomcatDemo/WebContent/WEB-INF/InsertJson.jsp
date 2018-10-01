<%@ page import=" java.util.*"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<style>
body {
    background-color: coral;
}
</style>
<script src="${pageContext.request.contextPath}/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script>
$(document).ready(function () {
	
	demoAdd();
 	
});
function demoAdd(){
	$('#dtBasicExample').append('<tr class=""><td><input type="number" value="" name="id" min=0></td> <td><input type="text" value="" name="name"></td> <td><input type="number" value="" name="years" min=0></td> <td><input type="text" value="" name="teamName"></td>  </tr>');
}

function demoSubmit(){
	if (window.confirm("Are you sure Submit?")) {
		$('#testDemoForm').submit();
	}
}


function demoSelect(){
	$('#selectUpdate').submit();
}



</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tomcat Web Demo</title>
</head>
<body style='background-color: #000000'>


		<form action="${pageContext.request.contextPath}/YourServletURL" method="post" class="needs-validation" id="testDemoForm">
			<input type="hidden" value="${thisPage}" name="thisPage" id="thisPage">

		  	<table id="dtBasicExample" class="table table-bordered table-dark" >
	  			<thead class="thead-dark">
						<tr class="table-dark">
			  			<th scope="col">id</th>
			  			<th scope="col">name</th>
			  			<th scope="col">years</th>
			  			<th scope="col">teamName</th>
		  			</tr>
	  			 </thead>
				
		  	</table>
			<div style='float: right'>
				<nav aria-label="Page navigation example">
				  <ul class="pagination">
				  
				  <c:forEach  var = "i" begin = "1" end = "${pageNum}" >
			  		<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/TestServlet?thisPage=${i}">${i}</a></li>
				  </c:forEach>
				  </ul>
				</nav>
		  	<input type="button" value="Select/Update" onclick="demoSelect()" class="btn btn-default my-1" id='btnSelect'>
		  	<input type="button" value="ADD" onclick="demoAdd()" class="btn btn-default my-1" id='btnADD'>
		  	<input type="button" value="Submit" onclick="demoSubmit()" class="btn btn-default my-1" id='btnSubmit'>
		</div>
		</form >
	<form action="${pageContext.request.contextPath}/TestServlet" method="post" class="needs-validation" id="selectUpdate" style='display: none'>
	</form>
	
</body>
</html>