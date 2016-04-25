angular.module("htBillingApp").controller('FormController', ['$http', '$scope', '$location', function ($http, $scope, $location) {
    $scope.formData = {};
    this.processForm = function () {
        $http({
            method: 'GET',
            url: 'Login',
            data: this.formData,
            params: {
                username: this.formData.username,
                password: this.formData.password
            }
        }).then(function (response) {
            var message = response.data.loginResult;
            var userRoles = response.data.userRoles;
            if (message === "Failure") {
                $scope.error = "Incorrect username/password. Try Again!";
            } else if (userRoles.role === "admin") {
                $location.path("/home");
            } else if (userRoles.role === "operator") {
                $location.path("/operatorhome");
            } else if (userRoles.role === "circle") {
                $location.path("/circlehome");
            } else if (userRoles.role === "developer") {
                $location.path("/developerhome");
            }
        });
    };
}]);