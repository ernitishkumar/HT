angular.module("htBillingApp").controller('BifircateReadingsController', ['$http', '$scope', '$location', '$routeParams', function ($http, $scope, $location, $routeParams) {

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
            url: 'InvestorController',
            params: {
                action: "getinvestorforbifurcation",
                plantId: plantId
            }
        }).then(function (response) {
            var result = response.data.Result;
            if (result === "OK") {
                $scope.investorsData = response.data.InvestorsData;
                console.log($scope.investorsData);
            }
        });
        $http({
            method: 'GET',
            url: 'ConsumptionsController',
            params: {
                action: 'getconsumption',
                meterNo: meterNo
            }
        }).then(function (response) {
            var result = response.data.Result;
            if (result === 'OK') {
                $scope.consumption = response.data.Consumption;
            }
        });
    };

    this.saveDistribution = function () {
        var investors = $scope.investorsData;
        var consumption = $scope.consumption;
        var totalActive = 0;
        var totalReactive = 0;
        investors.forEach(function (item) {
            totalActive += item.activeConsumption;
            totalReactive += item.reactiveConsumption;
            item.consumptionId = consumption.id;
            item.investorId = item.investor.id;
        });
        console.log("After preparing bean");
        console.log(investors);
        if (totalActive === consumption.activeConsumption && totalReactive === consumption.reactiveConsumption) {
            $http({
                method: 'POST',
                url: 'InvestorConsumptionController',
                headers: {
                    "Content-Type": "application/json"
                },
                data: JSON.stringify(investors)
            }).then(function (response) {
                var result = response.data.Result;
                if (result === "OK") {
                    var id = consumption.id;
                    updateBifercationFlag(id);
                } else {
                    alert("Unable to save consumptions");
                }
            });
        } else {
            alert("Readings sum is not equal to total Consumption. Please check !");
        }
    };

    function updateBifercationFlag(id) {
        var id = id;
        var flag = '1';
        $http({
            method: 'GET',
            url: 'ConsumptionsController',
            params: {
                action: "updateBifercationFlag",
                id: id,
                flag: '1'
            }
        }).then(function (response) {
            var result = response.data.Result;
            if (result === "OK") {
                $location.path("/viewdeveloperreading");
            }
        });
    }
}]);