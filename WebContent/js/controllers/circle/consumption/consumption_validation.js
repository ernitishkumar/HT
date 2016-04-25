angular.module("htBillingApp").controller('CircleConsumptionValidationController', ['$http', '$scope', '$location', '$routeParams', function ($http, $scope, $location, $routeParams) {
    $scope.user = {};

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
                if ($scope.user.username != null && $scope.user.user_role === 'circle') {
                    loadCircleConsumptions();
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

    this.loadCircleHome = function () {
        $location.path("/circlehome");
    };

    function loadCircleConsumptions() {
        $http({
            method: 'GET',
            url: 'InvestorConsumptionController',
            params: {
                action: 'getinvestorconsumption',
                circle: $scope.user.circle
            }
        }).then(function (response) {
            var result = response.data.Result;
            if (result === 'OK') {
                $scope.consumptions = response.data.MeterConsumptionData;
                console.log($scope.consumptions);
            }
        });
    };

    this.validateReading = function (consumptionData) {
        $http({
            method: 'GET',
            url: 'InvestorConsumptionController',
            params: {
                action: 'validate_consumption',
                consumptionId: consumptionData.consumption.id
            }
        }).then(function (response) {
            var result = response.data.Result;
            if (result === 'VALIDATED') {
                $scope.consumptions.forEach(
                    function (item) {
                    	
                        if (item.consumption.id === consumptionData.consumption.id) {
                            item.investorBifurcations.forEach(
                            function(investorConsumption){
                            	investorConsumption.circleValidation = 1;
                            }		
                            );
                        }
                    }
                );
            }
            console.log("Data after validation : "+$scope.consumptions);
        });
    };

}]);