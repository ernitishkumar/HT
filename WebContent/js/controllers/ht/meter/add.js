angular.module("htBillingApp").controller('AddMeterController', ['$http', '$scope', '$location', function ($http, $scope, $location) {

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

    this.processForm = function () {

        $http({
            method: 'GET',
            url: 'MeterController',
            params: {
                action: 'create',
                meterNo: this.formData.meterNo,
                make: this.formData.make,
                category: this.formData.category,
                type: this.formData.type,
                meterClass: this.formData.meterClass,
                ctr: this.formData.ctr,
                ptr: this.formData.ptr,
                mf: this.formData.mf,
                equipmentClass: this.formData.equipmentClass,
                phase: this.formData.phase,
                meterGroup: this.formData.meterGroup
            }
        }).success(function (response) {
            $location.path("/saved/Meter Saved Successfully!");
            $scope.formData = {};
        });
    };

    this.clearForm = function () {
        $scope.formData = {};
    };

}]);