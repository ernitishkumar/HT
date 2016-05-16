angular.module("htBillingApp").controller('ViewDeveloperDetailsController', ['$http', '$scope', '$location', function ($http, $scope, $location) {

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

    var getDevelopers = function () {

        $http({
            method: 'GET',
            url: 'DeveloperController',
            params: {
                action: 'getAllDeveloper',
            }
        }).then(function (response) {
            $location.path("/viewdeveloperdetails");
            $scope.developers = response.data.Developers;
        });
    };
    getDevelopers();

}]);