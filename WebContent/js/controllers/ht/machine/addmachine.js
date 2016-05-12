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

    var getDevelopers = function () {

        $http({
            method: 'GET',
            url: 'MachineController',
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
    	var d = new Date(this.formData.commissionedDate);
        var year = d.getFullYear();
        var month = d.getMonth() + 1;

        if (month < 10) {
            month = "0" + month;
        }
        var day = d.getDate();
        if (day < 10) {
            day = "0" + day;
        }
        var readingDate = day + "-" + month + "-" + year;
        $http({
            method: 'GET',
            url: 'MachineController',
            params: {
                action: 'create',
                plantCode: this.formData.plantCode,
                name: this.formData.name,
                address: this.formData.address,
                contactNo: this.formData.contactNo,
                contactPerson: this.formData.contactPerson,
                email: this.formData.email,
                commissionedDate: readingDate,
                type: this.formData.type,
                circuitVoltage: this.formData.circuitVoltage,
                injectingSubstation: this.formData.injectingSubstation,
                feederName: this.formData.feederName,
                region: this.formData.region,
                circle: this.formData.circle,
                division: this.formData.division,
                mainMeterNo: this.formData.mainMeterNo,
                checkMeterNo: this.formData.checkMeterNo,
                standbyMeterNo: this.formData.standbyMeterNo,
                developerId: this.formData.developerId
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