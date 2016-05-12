angular.module("htBillingApp").controller('AddMachineController', ['$http', '$scope', '$location', function ($http, $scope, $location) {

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

    $scope.developers = [];
    $scope.formData.developerId = 0;
    
    var getDevelopers = function () {

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
    
    var getPlantsByDeveloper = function () {
    	//alert("inside method"+$scope.formData.developerId);
        $http({
            method: 'GET',
            url: 'PlantController',
            params: {
            	developerId: $scope.formData.developerId,
                action: 'getByDeveloperId',
            }
        }).then(function (response) {
            $scope.plants = response.data.Plants;
            console.log($scope.plants);
        });
    };
    
    this.getPlantsByDeveloper = getPlantsByDeveloper;
    
    getInvestorsByPlant
    var getInvestorsByPlant = function () {
    	//alert("inside method"+$scope.formData.developerId);
        $http({
            method: 'GET',
            url: 'InvestorController',
            params: {
            	plantId: $scope.formData.plantId,
                action: 'getByDeveloperId',
            }
        }).then(function (response) {
            $scope.investors = response.data.Investors;
            console.log($scope.investors);
        });
    };
    
    this.getInvestorsByPlant = getInvestorsByPlant;
    
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
    	var d1 = new Date(this.formData.commissionedDate);
        var y1 = d1.getFullYear();
        var m1 = d1.getMonth() + 1;
        if (m1 < 10) {
            m1 = "0" + m1;
        }
        var day1 = d1.getDate();
        if (day1 < 10) {
            day1 = "0" + day1;
        }
        var commissionedDate = day1 + "-" + m1 + "-" + y1;
        var d2 = new Date(this.formData.ppaDate);
        var y2 = d1.getFullYear();
        var m2 = d1.getMonth() + 1;
        if (m2 < 10) {
            m2 = "0" + m2;
        }
        var day2 = d2.getDate();
        if (day2 < 10) {
            day2 = "0" + day2;
        }
        var ppaDate = day2 + "-" + m2 + "-" + y2;
        $http({
            method: 'GET',
            url: 'MachineController',
            params: {
                action: 'create',
                code: this.formData.code,
                capacity: this.formData.capacity,
                commissionedDate: commissionedDate,
                activeRate: this.formData.activeRate,
                reactiveRate: this.formData.reactiveRate,
                ppaLetterNo: this.formData.ppaLetterNo,
                ppaDate: ppaDate,
                developerId: $scope.formData.developerId,
                plantId: $scope.formData.plantId,
                investorId: $scope.formData.investorId
                
            }
        }).success(function (response) {
            $location.path("/saved/Plant Saved Successfully!");
            $scope.formData = {};
        });
    };

    
    this.clearForm = function () {
        $scope.formData = {};
    };

}]);