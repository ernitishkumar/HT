angular.module("htBillingApp").controller('DeveloperViewMeterReadingsController', ['$http', '$scope', '$location', '$routeParams', function ($http, $scope, $location, $routeParams) {

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

    this.loadDeveloperHome = function () {
        $location.path("/developerhome");
    }

    function loadDeveloperReadings() {
        $http({
            method: 'GET',
            url: 'MeterReadingsController',
            params: {
                action: 'listdeveloperreadings',
                username: $scope.user.username
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

    function calculateData(reading) {
        reading.activeEnergyDifference = parseFloat((reading.currentMeterReading.activeEnergy - reading.previousMeterReading.activeEnergy).toFixed(2));

        reading.activeEnergyConsumption = parseFloat((reading.activeEnergyDifference * reading.currentMeterReading.mf).toFixed(2));

        reading.todOneDifference = parseFloat((reading.currentMeterReading.activeTodOne - reading.previousMeterReading.activeTodOne).toFixed(2));

        reading.todOneConsumption = parseFloat((reading.todOneDifference * reading.currentMeterReading.mf).toFixed(2));

        reading.todTwoDifference = parseFloat((reading.currentMeterReading.activeTodTwo - reading.previousMeterReading.activeTodTwo).toFixed(2));

        reading.todTwoConsumption = parseFloat((reading.todTwoDifference * reading.currentMeterReading.mf).toFixed(2));

        reading.todThreeDifference = parseFloat((reading.currentMeterReading.activeTodThree - reading.previousMeterReading.activeTodThree).toFixed(2));

        reading.todThreeConsumption = parseFloat((reading.todThreeDifference * reading.currentMeterReading.mf).toFixed(2));

        reading.quadrantOneDifference = parseFloat((reading.currentMeterReading.reactiveQuadrantOne - reading.previousMeterReading.reactiveQuadrantOne).toFixed(2));

        reading.quadrantOneConsumption = parseFloat((reading.quadrantOneDifference * reading.currentMeterReading.mf).toFixed(2));

        reading.quadrantTwoDifference = parseFloat((reading.currentMeterReading.reactiveQuadrantTwo - reading.previousMeterReading.reactiveQuadrantTwo).toFixed(2));

        reading.quadrantTwoConsumption = parseFloat((reading.quadrantTwoDifference * reading.currentMeterReading.mf).toFixed(2));

        reading.quadrantThreeDifference = parseFloat((reading.currentMeterReading.reactiveQuadrantThree - reading.previousMeterReading.reactiveQuadrantThree).toFixed(2));

        reading.quadrantThreeConsumption = parseFloat((reading.quadrantThreeDifference * reading.currentMeterReading.mf).toFixed(2));

        reading.quadrantFourDifference = parseFloat((reading.currentMeterReading.reactiveQuadrantFour - reading.previousMeterReading.reactiveQuadrantFour).toFixed(2));

        reading.quadrantFourConsumption = parseFloat((reading.quadrantFourDifference * reading.currentMeterReading.mf).toFixed(2));
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
                var consumptionData;
                var found;
                $scope.readingData.forEach(function (item) {
                    if (item.meterNo === reading.meterNo) {
                        item.currentMeterReading.developerValidation = 1;
                        consumptionData = item;
                        found = true;
                    }
                });

                if (found) {
                    $http({
                        method: 'GET',
                        url: 'ConsumptionsController',
                        params: {
                            action: 'insert',
                            meterNo: consumptionData.meterNo,
                            activeConsumption: consumptionData.activeEnergyConsumption,
                            reactiveConsumption: (consumptionData.quadrantOneConsumption + consumptionData.quadrantTwoConsumption +
                                consumptionData.quadrantThreeConsumption + consumptionData.quadrantFourConsumption),
                            plantId: consumptionData.plant.id,
                            plantCode: consumptionData.plant.code,
                            meterReadingId: reading.currentMeterReading.id
                        }
                    }).then(function (response) {
                        var result = response.data;
                        alert("Consumption inserted " + result.Result);
                    });
                }

            }
        });
    };

    this.getInvestorsData = function (reading) {
        $location.path("/splitreadings/" + reading.plant.id + "/" + reading.meterNo);
    };
    this.viewInvestorsData = function (reading) {
        $location.path("/viewsplitedreadings/" + reading.plant.id + "/" + reading.meterNo);
    };
	this.viewBill = function(){
		$location.path("/viewbill");
	}
    }]);