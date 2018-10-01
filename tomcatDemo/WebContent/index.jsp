<%@ page import=" java.util.*"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<style>
</style>

<script src="${pageContext.request.contextPath}/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="//rubaxa.github.io/Sortable/Sortable.js"></script>
  
<script>
$(document).ready(function () {

	Sortable.create(sortTrue, {
	  group: "sorting",
	  sort: true
	});
 	
});

function insert(){
	$('#insertDemoForm').submit();
}


function demoEditShow(num){
	$('.trBtn'+num).attr('style', '');
	$('.trText'+num).attr('style', 'display:none');
	
}

function demoEdit(num){
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


function demoUpdate(){
	if (window.confirm("Are you sure Update?")) {
		var URL="${pageContext.request.contextPath}/update";
		$('#testDemoForm').attr('action', URL).submit();
	}
}

function dragEnd() {
	getRows();
}


function getRows(){
	var thisSort=0;
	for (var i = 1; i < $("#dtBasicExample tr").size(); i++) {
			var thisInput = document.getElementById("dtBasicExample").rows[(i)].cells[0].children[0];
			var thisText = document.getElementById("dtBasicExample").rows[(i)].cells[1];
			if (thisInput!=undefined){
				thisInput.value=thisSort;
				thisText.innerHTML = thisSort + 1;
				thisSort = thisSort+1;
			}
		}
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
						<th scope="col">index</th>
			  			<th scope="col">id</th>
			  			<th scope="col">name</th>
			  			<th scope="col">years</th>
			  			<th scope="col">teamName</th>
			  			<th scope="col">Edit</th>
		  			</tr>
	  			 </thead>
	  			<tbody id="sortTrue" ondragend="dragEnd(event)">
					<c:forEach items="${demo}" var="item" varStatus="myIndex">
				  		
				  		<tr id='trText${myIndex.index}' >
							<td style='display:none'>>
								<input type="hidden" value="${myIndex.index}" name="myIndex" id='${myIndex.index}'>
							</td>
							<td>${myIndex.index+1}</td>
				  			<td class='trBtn${myIndex.index}' style='display:none'><input type="number" value="${item[0]}" name="id" id='id_${myIndex.index}' min='0'></td>
				  			<td class='trBtn${myIndex.index}' style='display:none'><input type="text" value="${item[1]}" name="name" id='name_${myIndex.index}'></td>
				  			<td class='trBtn${myIndex.index}' style='display:none'><input type="number" value="${item[2]}" name="years" id='years_${myIndex.index}' min='0'></td>
				  			<td class='trBtn${myIndex.index}' style='display:none'><input type="text" value="${item[3]}" name="teamName" id='teamName_${myIndex.index}'></td>
				  			<td class='trBtn${myIndex.index}' style='display:none' ><input type="button" value="Submit" onclick="demoEdit('${myIndex.index}')" class="btn btn-success my-1"></td>
				  			<td class='trText${myIndex.index}'>${item[0]}</td>
				  			<td class='trText${myIndex.index}'>${item[1]}</td>
				  			<td class='trText${myIndex.index}'>${item[2]}</td>
				  			<td class='trText${myIndex.index}'>${item[3]}</td>
				  			<td class='trText${myIndex.index}'><input type="button" value="Edit" onclick="demoEditShow('${myIndex.index}')" class="btn btn-success my-1"></td>
				  		</tr>
					</c:forEach>
				</tbody>
		  	</table>
			<div style='float: right'>
				<nav aria-label="Page navigation example">
				  <ul class="pagination">
				  
				  <c:forEach  var = "i" begin = "1" end = "${pageNum}" >
			  		<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/TestServlet?thisPage=${i}">${i}</a></li>
				  </c:forEach>
				  </ul>
				</nav>
		  	<input type="button" value="Insert" onclick="insert()" class="btn btn-default my-1" id='btnInsert'>
		  	<input type="button" value="Update" onclick="demoUpdate()" class="btn btn-default my-1" id='btnUpdate'>
		  	<input type="button" value="Rest" onclick="demoRest()" class="btn btn-default my-1" id='btnRest'>
		</div>
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
		<input type="text" value="${thisPage}" name="thisPage" id="thisPage">
	</form>
	
	<form action="${pageContext.request.contextPath}/Insert" method="post" class="needs-validation" id="insertDemoForm" style='display: none'>
	</form>
	
</body>
</html>