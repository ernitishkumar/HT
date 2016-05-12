angular.module("htBillingApp").controller('AddInvestorController', ['$http', '$scope', '$location', function ($http, $scope, $location) {

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
            url: 'InvestorController',
            params: {
                action: 'create',
                name:this.formData.name,
                code:this.formData.code,
                cin: this.formData.cin,
                tin: this.formData.tin,
                vat: this.formData.vat,
                invoiceNo: this.formData.invoiceNo,
                officeAddress: this.formData.officeAddress,
                officeContactNo: this.formData.officeContactNo,
                officeContactPerson: this.formData.officeContactPerson,
                officeEmail: this.formData.officeEmail,
                siteAddress: this.formData.siteAddress,
                siteContactNo: this.formData.siteContactNo,
                siteContactPerson: this.formData.siteContactPerson,
                siteEmail: this.formData.siteEmail
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