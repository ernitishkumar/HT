angular.module("htBillingApp").controller('AddDeveloperController', ['$http', '$scope', '$location', function ($http, $scope, $location) {

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

    /*var getDevelopers = function () {

        $http({
            method: 'GET',
            url: 'DeveloperController',
            params: {
                action: 'getalldeveloper',
            }
        }).then(function (response) {
            $scope.developers = response.data.Developers;
            console.log($scope.developers);
        });
    };
    
    getDevelopers();
    
    var getMetersNotInUse = function () {

        $http({
            method: 'GET',
            url: 'MeterController',
            params: {
                action: 'getMetersNotInUse',
            }
        }).then(function (response) {
            $scope.metersNotInUse = response.data.MetersNotInUse;
            console.log($scope.metersNotInUse);
        });
    };
    
    getMetersNotInUse();
*/    
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
            url: 'DeveloperController',
            params: {
                action: 'create',
                developerName: this.formData.developerName,
                cin: this.formData.cin,
                officeAddress: this.formData.officeAddress,
                officeContactNo: this.formData.officeContactNo,
                officeContactPerson: this.formData.officeContactPerson,
                officeEmail: this.formData.officeEmail,
                siteAddress: this.formData.siteAddress,
                siteContactNo: this.formData.siteContactNo,
                siteContactPerson: this.formData.siteContactPerson,
                siteEmail: this.formData.siteEmail,
                username: this.formData.username
            }
        }).then(function (response) {
        	 var result = response.data;
        	 console.log(result);
             if(result.Result === 'Success'){
	            $location.path("/saved/"+result.Message);
             }else{
             	alert(result.Message);
             }
             $scope.formData = {};
        });
    };

    
    this.clearForm = function () {
        $scope.formData = {};
    };

}]);