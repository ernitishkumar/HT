angular.module("htBillingApp").controller('HomeController', ['$http', '$scope', '$location', function ($http, $scope, $location) {
    $scope.user = {};
    var checkUser = function () {
        $http({
            method: 'GET',
            url: 'ValidateSession'
        }).then(function (response) {
            var user = response.data;
            if (user.username === null || user.username == "undefined") {
                $location.path("/");
            } else if (user.user_role === "admin") {
                $scope.user = user;
            } else {
                $location.path("/");
            }
        });
    };
    checkUser();

    this.logout = function () {
        console.log("Logout called");
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

    this.loadCircleReadingViewPage = function () {
        $location.path("/viewcirclereadings");
    };

    this.loadAddMeterPage = function () {
        $location.path("/addmeter");
    };
    
    this.loadAddPlantPage = function () {
        $location.path("/addplant");
    };

    this.loadAddDeveloperPage = function () {
        $location.path("/adddeveloper");
    };

    this.loadAddInvestorPage = function () {
        $location.path("/addinvestor");
    };
    
    this.loadAddMachinePage = function () {
        $location.path("/addmachine");
    };
    
    this.loadHome = function () {
        $location.path("/home");
    };
    this.loadOperatorHome = function () {
        $location.path("/operatorhome");
    };
    this.loadCircleHome = function () {
        $location.path("/circlehome");
    };
}]);