angular.module("htBillingApp").controller('ViewBifircateReadingsController', ['$http', '$scope', '$location', '$routeParams', function ($http, $scope, $location, $routeParams) {

    $scope.user = {};

    $scope.sno = 0;

    $scope.generating = false;
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
                    loadConsumptionData();
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

    function loadConsumptionData() {
        var plantId = $routeParams.plantId;
        var meterNo = $routeParams.meterNo;
        $http({
            method: 'GET',
            url: 'InvestorConsumptionController',
            params: {
                action: 'getinvestorconsumption',
                plantId: plantId,
                meterNo: meterNo
            }
        }).then(function (response) {
            var result = response.data.Result;
            if (result === 'OK') {
                $scope.investorConsumptions = response.data.InvestorConsumptionData;
                $scope.investorConsumptions.forEach(
                function(item){
                	item.generating = false;
                }		
                );
                console.log($scope.investorConsumptions);
            }
        });
    };

    this.back = function () {
        $location.path("/viewdeveloperreading");
    };
    
    this.generateBill = function (investorData) {
    	investorData.generating = true;
    	$http({
            method: 'GET',
            url: 'BillingController',
            params: {
                action: 'generate',
                consumptionId: investorData.consumption.id,
                investorId: investorData.investor.id,
                bifurcationId:investorData.id
            }
        }).then(function (response) {
        	var result = response.data.Result;
        	if(result === "OK"){
        		var lastInsertedId = response.data.BillId;
        		investorData.billDetailsId= lastInsertedId;
        		investorData.generating = false;
        		investorData.billGenerated = true;
        		console.log("Object after generating bill : ");
        	}
        });
    };
    
    this.viewBill = function (investorData) {
    	$location.path("/viewbill/"+investorData.billDetailsId);
    };

}]);