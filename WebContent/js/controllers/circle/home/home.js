angular.module("htBillingApp").controller('CircleHomeController', ['$http', '$scope', '$location', function ($http, $scope, $location) {
    $scope.user = {};
    var checkUser = function () {
        $http({
            method: 'GET',
            url: 'ValidateSession'
        }).then(function (response) {
            var user = response.data;
            if (user.username === null || user.username === "undefined") {
                $location.path("/");
            } else if (user.user_role === "circle") {
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

    this.loadCircleReadingViewPage = function () {
        //$location.path("/viewcirclereadings");
    	$location.path("/viewcircleconsumptions");
    };

    this.loadCircleHome = function () {
        $location.path("/circlehome");
    };
}]);