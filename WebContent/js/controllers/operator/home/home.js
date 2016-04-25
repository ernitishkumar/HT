angular.module('htBillingApp').controller('OperatorHomeController', ['$http', '$scope', '$location', function ($http, $scope, $location) {
    $scope.user = {};
    var checkUser = function () {
        $http({
            method: 'GET',
            url: 'ValidateSession'
        }).then(function (response) {
            var user = response.data;
            if (user.username === null || user.username == "undefined") {
                $location.path("/");
            } else if (user.user_role === "operator") {
                $scope.user = user;
            } else {
                $location.path("/");
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

    this.loadReadingForm = function () {
        $location.path("/enterreading");
    };

    this.loadMeterReadingViewPage = function () {
        $location.path("/viewmeterreadings");
    };

    this.loadOperatorHome = function () {
        $location.path("/operatorhome");
    };
}]);