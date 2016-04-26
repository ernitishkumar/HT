angular.module("htBillingApp").controller('ViewBillController', ['$http', '$scope', '$location', '$routeParams','$window', function ($http, $scope, $location, $routeParams) {

    $scope.user = {};
    $scope.sno = 0;
    var checkUser = function () {
        $http({
            method: 'GET',
            url: 'ValidateSession'
        }).then(function (response) {
            var user = response.data;
            if (user.username === null || user.username === undefined) {
                $location.path("/");
            } else {
                $scope.user = user;
                if ($scope.user.username != null && $scope.user.user_role === 'developer') {
                    loadBill();
                } else {
                    $location.path("/");
                    $scope.user = {};
                }
            }
        });
    };

    checkUser();

    this.logout = function () {
        $http({
            method: 'GET',
            url: 'Logout'
        }).then(function (response) {
            $location.path("/");
        });
    };

    this.loadDeveloperHome = function () {
        $location.path("/developerhome");
    }

    this.back = function () {
    	window.history.back();
    };
    
    function loadBill(){
    	var billId = $routeParams.billDetailsId;
    	$http({
            method: 'GET',
            url: 'BillingController',
            params: {
                action: 'fetch_bill',
                billDetailsId: billId
            }
        }).then(function (response) {
            var result = response.data.Result;
            if(result === "OK"){
            	var billDetails = response.data.BillDetails;
            	console.log(billDetails);
            	if(billDetails.id == billId){
            		$scope.billDetails = billDetails;
            	}else{
            		alert("Got incorrect bill please check");
            	}
            }
        });
    }

}]);