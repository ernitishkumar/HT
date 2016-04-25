angular.module("htBillingApp").controller('MeterReadingController', ['$http', '$scope', '$location', function ($http, $scope, $location) {

    $scope.meternotvalid = false;
    $scope.metervalid = false;
    $scope.plainmeter = false;

    $scope.user = {};

    $scope.formData = {};

    $scope.meterdata = {};

    $scope.showdetails = {
        show: false
    };


    var checkUser = function () {
        $http({
            method: 'GET',
            url: 'ValidateSession'
        }).then(function (response) {
            var user = response.data;
            if (user.username === null || user.username === "undefined" || user.user_role === "circle" || user.user_role === "developer") {
                $location.path("/");
            }
            $scope.user = user;
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
        var user = $scope.user;
        if (user.user_role === 'admin' || user.user_role === 'ht') {
            $location.path("/home");
        } else if (user.user_role === 'operator') {
            $location.path("/operatorhome");
        } else {
            $location.path("/");
        }

    };

    this.loadOperatorHome = function () {
        $location.path("/home");
    };

    this.isValidMeterno = function () {
        if ($scope.meternoForm.meterno.$dirty && $scope.meternoForm.meterno.$valid) {
            $scope.plainmeter = true;
            $scope.metervalid = false;
            $scope.meternotvalid = false;
            $http({
                method: 'GET',
                url: 'PlantController',
                params: {
                    action: 'get',
                    meterno: this.formData.meterno
                }
            }).then(function (response) {
                var validMeter = response.data.ValidMeter;
                if (validMeter === "Yes") {
                    $scope.formData.mf = response.data.MeterData.MF;
                    $scope.formData.location = response.data.MeterData.Name;
                    $scope.plainmeter = false;
                    $scope.metervalid = true;
                    $scope.meternotvalid = false;
                } else {
                    $scope.plainmeter = false;
                    $scope.metervalid = false;
                    $scope.meternotvalid = true;
                }
            });
        } else {
            $scope.plainmeter = false;
            $scope.metervalid = false;
            $scope.meternotvalid = true;
        }

    };

    this.processForm = function () {
        var d = new Date(this.formData.date);
        var year = d.getFullYear();
        var month = d.getMonth() + 1;

        if (month < 10) {
            month = "0" + month;
        }
        var day = d.getDate();
        if (day < 10) {
            day = "0" + day;
        }
        var readingDate = day + "-" + month + "-" + year;
        $http({
            method: 'GET',
            url: 'MeterReadingsController',
            params: {
                action: 'create',
                meterno: this.formData.meterno,
                mf: $scope.formData.mf,
                readingDate: readingDate,
                readingTime: d.getTime(),
                activeEnergy: this.formData.activeEnergy,
                activeTodOne: this.formData.activeTodOne,
                activeTodTwo: this.formData.activeTodTwo,
                activeTodThree: this.formData.activeTodThree,
                reactiveTodOne: this.formData.reactiveTodOne,
                reactiveTodTwo: this.formData.reactiveTodTwo,
                reactiveTodThree: this.formData.reactiveTodThree,
                reactiveTodFour: this.formData.reactiveTodFour
            }
        }).success(function (response) {
            $scope.plainmeter = false;
            $scope.metervalid = false;
            $scope.meternotvalid = false;
            $location.path("/saved/Meter Reading Saved Successfully!");
            $scope.formData = {};
        });
    };

    this.clearForm = function () {
        $scope.plainmeter = false;
        $scope.metervalid = false;
        $scope.meternotvalid = false;
        $scope.formData = {};
    };

}]);