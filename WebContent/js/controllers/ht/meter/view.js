angular.module("htBillingApp").controller('ViewMeterDetailsController', ['$http', '$scope', '$location', function ($http, $scope, $location) {

    $scope.user = {};

    $scope.formData = {};

    $scope.showdetails = {
        show: false
    };


    var checkUser = function () {
        $http({
            method: 'GET',
            url: 'ValidateSession'
        }).then(function (response) {
            var user = response.data;
            if (user.username === null || user.username === "undefined") {
                $location.path("/");
            } else {
                $scope.user.username = user.username;
                $scope.user.name = user.name;
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

    var getMeters = function () {

        $http({
            method: 'GET',
            url: 'MeterController',
            params: {
                action: 'getAll',
            }
        }).then(function (response) {
            $location.path("/viewmeterdetails");
            $scope.meters = response.data.Meters;
        });
    };
    getMeters();

    this.clearForm = function () {
        $scope.formData = {};
    };

}]);