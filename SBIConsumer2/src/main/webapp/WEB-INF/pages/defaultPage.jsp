<html ng-app="userApp">
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.19/angular.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.19/angular-sanitize.min.js"></script>
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
		<script type="text/javascript">
		
			angular.module('userApp', ['ngSanitize']).controller('UserCtrl', ['$http', function($http) {
				
				var myvar = this;
				
				this.changeId = function() {
					myvar.validationError = "";
					this.cfname="";
					this.clname="";
			    };
			    this.changeFname = function() {
			    	myvar.validationError = "";
			    	this.cid="";
					this.clname="";
			    };
			    this.changeLname = function() {
			    	myvar.validationError = "";
			    	this.cid="";
					this.cfname="";
			    };
				
				this.submit = function() {
					myvar.errorMessage = "";
					myvar.validationError = "";
					
					
					var url = "";
					if(this.cid){
						url = '/'+myvar.cid;
					}else if(this.cfname){
						url = '/firstname/'+myvar.cfname;
					}else if(this.clname){
						url = '/lastname/'+myvar.clname;
					}else{
						myvar.validationError = "* Please enter a value for search";
						return;
					}
					
					$http.get(url)
					.then(function(res) {
						
						var errorExists = res.data.containsError;

						if(errorExists){						
							myvar.errorMessage = res.data.message;
						}else{			
							myvar.customers = res.data.data;
						}

					},function errorCallback(response) {
						myvar.errorMessage = response.data.error;
					});

					
				};
			}]);
		</script>
		
	</head>
	
	<body ng-controller="UserCtrl as ctrl" class="jumbotron container">

		<form ng-submit="ctrl.submit()" name="myForm">

			<div>
			  <h2>Customer Search Page</h2>
			  <div class="panel panel-default">
			    <div class="panel-heading"><strong>Fill any one of the field and then search</strong></div>
			    <div class="panel-body">
			    	<h5 style="color:red;">{{ctrl.validationError}}</h5></p>
			    	<p>Customer id: <input type="text" ng-model="ctrl.cid" ng-change="ctrl.changeId();"></p>
			    	<p>First Name: <input type="text" ng-model="ctrl.cfname" ng-change="ctrl.changeFname();"></p>
			    	<p>Last Name: <input type="text" ng-model="ctrl.clname" ng-change="ctrl.changeLname();"></p>
			    	<button type="submit" class="btn btn-primary">Search</button>
			    </div>
			  </div>
			
			  <h2>Customer Details</h2>
			  <p>The below table contains the details of the customer</p>
			  <p><h2 style="color:red;">{{ctrl.errorMessage}}</h2></p>            
			  <table class="table">
			    <thead>
			      <tr class="panel-heading">
			      	<th>id</th>
			        <th>First Name</th>
			        <th>Middle Name</th>
			        <th>Last name</th>
			        <th>Occupation</th>
			      </tr>
			    </thead>
			    <tbody ng-repeat="customer in ctrl.customers">
					<tr>
				        <td>{{customer.id}}</td>
				        <td>{{customer.firstName}}</td>
				        <td>{{customer.middleName}}</td>
				        <td>{{customer.lastName}}</td>
				        <td>{{customer.occupation}}</td>
			       	</tr>
			    </tbody>
			  </table>
		</div>				

		</form>
		
	</body>
</html>
