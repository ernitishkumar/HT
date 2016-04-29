angular.module("htBillingApp").controller('ViewMeterReadingsController', ['$http', '$scope', '$location', '$routeParams', function ($http, $scope, $location, $routeParams) {

    $scope.user = {};

    var localUser = {};

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
                if ($scope.user.username != null && $scope.user.user_role === 'admin') {
                    loadReadings();
                } else if ($scope.user.username != null && $scope.user.user_role === 'circle') {
                    loadCircleReadings();
                } else if ($scope.user.username != null && $scope.user.user_role === 'operator') {
                    loadOperatorReadings();
                } else if ($scope.user.username != null && $scope.user.user_role === 'developer') {
                    loadDeveloperReadings();
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

    this.loadHome = function () {
        $location.path("/home");
    };

    this.loadCircleHome = function () {
        $location.path("/circlehome");
    };

    this.loadOperatorHome = function () {
        $location.path("/operatorhome");
    }

    this.loadDeveloperHome = function () {
        $location.path("/developerhome");
    }

    function loadReadings() {
        $http({
            method: 'GET',
            url: 'MeterReadingsController',
            params: {
                action: 'list'
            }
        }).then(function (response) {
            var result = response.data.Result;
            if (result === 'OK') {
                $scope.readingData = response.data.Records;
                console.log($scope.readingData);
                $scope.readingData.forEach(function (reading) {
                    calculateData(reading);
                });
            }
        });
    };

    function loadCircleReadings() {
        $http({
            method: 'GET',
            url: 'MeterReadingsController',
            params: {
                action: 'list',
                location: $scope.user.circle
            }
        }).then(function (response) {
            var result = response.data.Result;
            if (result === 'OK') {
                $scope.readingData = response.data.Records;
                $scope.readingData.forEach(function (reading) {
                    calculateData(reading);
                });
            }
        });
    };

    function calculateData(reading) {
        reading.activeEnergyDifference = (reading.currentMeterReading.activeEnergy - reading.previousMeterReading.activeEnergy).toFixed(2);

        reading.activeEnergyConsumption = (reading.activeEnergyDifference * reading.currentMeterReading.mf).toFixed(2);

        reading.todOneDifference = (reading.currentMeterReading.activeTodOne - reading.previousMeterReading.activeTodOne).toFixed(2);

        reading.todOneConsumption = (reading.todOneDifference * reading.currentMeterReading.mf).toFixed(2);

        reading.todTwoDifference = (reading.currentMeterReading.activeTodTwo - reading.previousMeterReading.activeTodTwo).toFixed(2);

        reading.todTwoConsumption = (reading.todTwoDifference * reading.currentMeterReading.mf).toFixed(2);

        reading.todThreeDifference = (reading.currentMeterReading.activeTodThree - reading.previousMeterReading.activeTodThree).toFixed(2);

        reading.todThreeConsumption = (reading.todThreeDifference * reading.currentMeterReading.mf).toFixed(2);

        reading.quadrantOneDifference = (reading.currentMeterReading.reactiveQuadrantOne - reading.previousMeterReading.reactiveQuadrantOne).toFixed(2);

        reading.quadrantOneConsumption = (reading.quadrantOneDifference * reading.currentMeterReading.mf).toFixed(2);

        reading.quadrantTwoDifference = (reading.currentMeterReading.reactiveQuadrantTwo - reading.previousMeterReading.reactiveQuadrantTwo).toFixed(2);

        reading.quadrantTwoConsumption = (reading.quadrantTwoDifference * reading.currentMeterReading.mf).toFixed(2);

        reading.quadrantThreeDifference = (reading.currentMeterReading.reactiveQuadrantThree - reading.previousMeterReading.reactiveQuadrantThree).toFixed(2);

        reading.quadrantThreeConsumption = (reading.quadrantThreeDifference * reading.currentMeterReading.mf).toFixed(2);

        reading.quadrantFourDifference = (reading.currentMeterReading.reactiveQuadrantFour - reading.previousMeterReading.reactiveQuadrantFour).toFixed(2);

        reading.quadrantFourConsumption = (reading.quadrantFourDifference * reading.currentMeterReading.mf).toFixed(2);
    };

    this.validateReading = function (reading) {
        $http({
            method: 'GET',
            url: 'MeterReadingsController',
            params: {
                action: 'validatereading',
                role: $scope.user.user_role,
                readingId: reading.currentMeterReading.id
            }
        }).then(function (response) {
            var result = response.data.Result;
            if (result === 'Success') {
                $scope.readingData.forEach(
                    function (item) {
                        if (item.meterNo === reading.meterNo) {
                            if ($scope.user.user_role === 'admin' || $scope.user.user_role === 'htcell') {
                                item.currentMeterReading.htCellValidation = 1;
                            } else if ($scope.user.user_role === 'circle') {
                                item.currentMeterReading.circleCellValidation = 1;
                            }
                        }
                    }
                );
            }
        });
    };

}]);