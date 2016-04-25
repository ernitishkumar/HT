angular.module('htBillingApp').controller('DeveloperController', ['$http', '$scope', '$location', function ($http, $scope, $location) {
    $scope.user = {};
    var checkUser = function () {
        $http({
            method: 'GET',
            url: 'ValidateSession'
        }).then(function (response) {
            var user = response.data;
            if (user.username === null || user.username === "undefined" || user.user_role != "developer") {
                $location.path("/");
            } else {
                $scope.user = user;
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

    this.developerHome = function () {
        $location.path("/developerhome");
    };

    this.loadViewReadingForm = function () {
        $location.path("/viewdeveloperreading");
    }

}]);