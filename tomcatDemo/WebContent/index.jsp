<%@ page import=" java.util.*"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<script src="${pageContext.request.contextPath}/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script>
$(document).ready(function () {
	
	var type = '${type}';
 	if(type!='select'){
		demoAdd();
 		$('#btnRest').hide();
 		$('#btnInsert').hide();
 		$('#btnUpdate').hide();
 	}else{
 		$('#btnSelect').hide();
 		$('#btnSubmit').hide();
 		$('#btnADD').hide();
 	}
 	
});

function insert(){
	$('#insertDemoForm').submit();
}

function demoAdd(){
	$('#dtBasicExample').append('<tr class="table-primary"><td><input type="number" value="" name="id"></td> <td><input type="text" value="" name="name"></td> <td><input type="number" value="" name="years"></td> <td><input type="text" value="" name="teamName"></td> <td></td> </tr>');
}

function demoSubmit(){
	if (window.confirm("Are you sure Submit?")) {
		$('#testDemoForm').submit();
	}
}

function demoEdit(num,id,name,years,teamName){
	if (window.confirm("Are you sure Edit?")) {
		$('#myIndex').val(num);
		$('#id').val($('#id_'+num).val());
		$('#name').val($('#name_'+num).val());
		$('#years').val($('#years_'+num).val());
		$('#teamName').val($('#teamName_'+num).val());
		
		var URL="${pageContext.request.contextPath}/Edit";
		$('#editDemoForm').attr('action', URL).submit();
		
	}
}

function demoRest(){
	$('#restPag').submit();
}

function demoSelect(){
	$('#restPag').submit();
}

function demoUpdate(){
	if (window.confirm("Are you sure Update?")) {
		
	}
}

</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tomcat Web Demo</title>
</head>
<body>
		<form action="${pageContext.request.contextPath}/YourServletURL" method="post" class="needs-validation" id="testDemoForm">
		  	<table id="dtBasicExample" class="table table-striped table-bordered table-sm" >
	  			<thead class="thead-dark">
						<tr class="text-c">
			  			<th scope="col">id</th>
			  			<th scope="col">name</th>
			  			<th scope="col">years</th>
			  			<th scope="col">teamName</th>
			  			<th scope="col">Edit</th>
		  			</tr>
	  			 </thead>
				<c:forEach items="${demo}" var="item" varStatus="myIndex">
					<tr class="table-primary">
			  			<td><input type="number" value="${item[0]}" name="id" id='id_${myIndex.index}'></td>
			  			<td><input type="text" value="${item[1]}" name="name" id='name_${myIndex.index}'></td>
			  			<td><input type="number" value="${item[2]}" name="years" id='years_${myIndex.index}'></td>
			  			<td><input type="text" value="${item[3]}" name="teamName" id='teamName_${myIndex.index}'></td>
			  			<td><input type="button" value="Edit" onclick="demoEdit('${myIndex.index}')" class="btn btn-primary my-1"></td>
			  		</tr>
				</c:forEach>
		  	</table>
		  	
				<nav aria-label="Page navigation example">
				  <ul class="pagination">
				  
				  <c:forEach  var = "i" begin = "1" end = "${pageNum}" >
				  	<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/TestServlet?thisPage=${i}">${i}</a></li>
				  </c:forEach>
				  </ul>
				</nav>
		  	<input type="button" value="Insert" onclick="insert()" class="btn btn-primary my-1" id='btnInsert'>
		  	<input type="button" value="Select/Update" onclick="demoSelect()" class="btn btn-primary my-1" id='btnSelect'>
		  	<input type="button" value="ADD" onclick="demoAdd()" class="btn btn-primary my-1" id='btnADD'>
		  	<input type="button" value="Submit" onclick="demoSubmit()" class="btn btn-primary my-1" id='btnSubmit'>
		  	<input type="button" value="Update" onclick="demoUpdate()" class="btn btn-primary my-1" id='btnUpdate'>
		  	<input type="button" value="Rest" onclick="demoRest()" class="btn btn-primary my-1" id='btnRest'>
		</form >
	 
	<form action="${pageContext.request.contextPath}/Edit" method="post" class="needs-validation" id="editDemoForm" style='display: none'>
			<input type="text" value="${thisPage}" name="thisPage" id="thisPage">
			<input type="text" value="" name="myIndex" id="myIndex">
  			<input type="number" value="" name="id" id="id">
  			<input type="text" value="" name="name" id="name">
  			<input type="number" value="" name="years" id="years">
  			<input type="text" value="" name="teamName" id="teamName">
	</form>
	<form action="${pageContext.request.contextPath}/TestServlet" method="post" class="needs-validation" id="restPag" style='display: none'>
	</form>
	<form action="${pageContext.request.contextPath}/Insert" method="post" class="needs-validation" id="insertDemoForm" style='display: none'>
	</form>
</body>
</html>