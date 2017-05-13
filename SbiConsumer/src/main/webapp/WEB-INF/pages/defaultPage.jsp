<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="CustomerApp" >
<head>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.19/angular.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.19/angular-sanitize.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript">
			
	var CustomerApp = angular.module('CustomerApp', ['ngSanitize']);
	
	
	CustomerApp.controller('CustomerControl', ['$http', function($http) {
		
		var myvar = this;
		
		this.showCustomers = function(){	
			
			var url = '/customers';
			
			$http.get(url).then(function(res) {
				
				var errorExists = res.data.containsError;
				
				if(errorExists){						
					myvar.errorMessage = res.data.message;
				}else{			
					myvar.customers = res.data.data;
				}

			},function errorCallback(response) {
				alert("error");
				myvar.errorMessage = response.data.error;
			});
		}

	}]);

	
</script>

<title>Insert title here</title>
</head>
<body class="jumbotron container">


	<div class="container" ng-controller="CustomerControl as ctrl">
		<h2>Customer Details</h2>
		<p><h2 style="color:red;">{{ctrl.errorMessage}}</h2></p>
		<div class="panel-group">
			<div class="panel panel-default">
				<a href="" ng-click="ctrl.showCustomers()"> Show all customers : </a>
			</div>
		</div>
		
		<div class="panel panel-default" ng-repeat="customer in ctrl.customers">

			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" href="#collapse{{customer.id}}"> {{customer.id}} - {{customer.firstName}} {{customer.middleName}} {{customer.lastName}} (+)</a>
				</h4>
			</div>
			<div id="collapse{{customer.id}}" class="panel-collapse collapse">
				<ul class="list-group">
					<li class="list-group-item"><strong>Customer Id:</strong> {{customer.id}}</li>
					<li class="list-group-item"><strong>First Name: </strong>{{customer.firstName}}</li>
					<li class="list-group-item"><strong>Middle Name: </strong>{{customer.middleName}}</li>
					<li class="list-group-item"><strong>Last Name: </strong>{{customer.lastName}}</li>
					<li class="list-group-item"><strong>Occupation: </strong>{{customer.occupation}}</li>
				</ul>
				<div class="panel-footer">--End of Details--</div>
			</div>
	
		</div>
		
		
	</div>

</body>
</html>