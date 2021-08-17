<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/main.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
	<title>Enter Category</title>	
</head>

<body>
	<div class = "container-sm mx-auto mt-5">
  		<div class="card text-white bg-secondary mb-3" style="max-width: 50rem;">
			<div class="card-header">Create Category</div>
				<div class="card-body">
					<form:form action="/category/create" method="post" modelAttribute = "category">
						<div class="form-group mb-3">
        					<h5 class="card-title">
							<form:label path="name" class= "mb-3">Category Name: </form:label>
							<form:errors path="name" class ="text-danger"/>
							<form:textarea path="name" class="form-control"/>
							</h5>
						</div>
						<div class="form-group mb-3">
							<input type="submit" value="Submit" class="my-5 btn btn-warning">
						</div>	
					</form:form>
					</div><!-- close card body -->
		</div><!-- close card -->	
		<div class="card text-white bg-secondary mb-3" style="max-width: 18rem;">
  			<div class="card-header">Add Product</div>
  				<div class="card-body bg-light">
    				<h5 class="card-title text-dark">Want to Add a New Product?</h5>
    				<a href="/product" class="card-text">Enter the info...</a>
  				</div><!-- close card body -->
		</div><!-- close card -->
 	</div><!-- close container -->
</body>
</html>