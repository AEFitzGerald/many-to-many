<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/main.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
	<title>Show Product</title>	
</head>
<body>
<div class = "container-sm mx-auto mt-5">
  		<div class="card text-white bg-secondary mb-3" style="max-width: 50rem;">
			<div class="card-header"> ${ product.name }</div>
				<div class="card-body">
					<h5 class="card-title">Categories associated with ${ product.name }:</h5>
					<ul>
						<c:forEach items="${ product.categories }" var = "category">
						<li>${ category.name }</li>
						</c:forEach>
					</ul>
			</div><!-- close card body -->
		</div><!-- close card -->	
	<div class="card text-white bg-secondary mb-3" style="max-width: 50rem;">
  		<div class="card-header">Add a category to this product</div>
  			<div class="card-body bg-light">
  				<h5 class="card-title text-dark"></h5>
  				<form action="/product/${product.id}/add" method="post">
  					<select name ="categoryId" id= "">
  						<c:forEach items="${ toBeCategories }" var="category">
						<option value ="${ category.id }">${ category.name }</option>
						</c:forEach>
					</select>
					<input type="submit" value="Add">
				</form>
			</div><!-- close card body -->
		</div><!-- close card -->
 	</div><!-- close container -->
</body>
</html>