var htBillingApp = angular.module('htBillingApp');

htBillingApp.controller('SaveController', ['$http', '$scope', '$location', '$routeParams', function ($http, $scope, $location, $routeParams) {
    $scope.user = {};
    $scope.message = {};
    var checkUser = function () {
        $http({
            method: 'GET',
            url: 'ValidateSession'
        }).then(function (response) {
            var user = response.data;
            if (user.username === null || user.username === "undefined") {
                $location.path("/");
            } else if (user.user_role === "developer" || user.user_role === "circle") {
                $location.path("/");
            }
            $scope.user = user;
        });
    };

    checkUser();
    $scope.message = $routeParams.message;
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
        var path = '/';
        if (user.user_role === 'admin') {
            path = '/home';
        } else if (user.user_role === 'circle') {
            path = '/circlehome';
        } else if (user.user_role === 'operator') {
            path = '/operatorhome';
        }
        $location.path(path);
    };

}]);