(function () {
    angular.module('htBillingApp', ['ngRoute']);
    angular.module('htBillingApp').config(function ($routeProvider) {
        $routeProvider.when('/', {
                templateUrl: 'templates/pages/login/login.html',
                controller: 'FormController',
                controllerAs: 'fc'
            })
            .when('/home', {
                templateUrl: 'templates/pages/home/home.html',
                controller: 'HomeController',
                controllerAs: 'homeCtrl'
            }).when('/operatorhome', {
                templateUrl: 'templates/pages/home/operatorhome.html',
                controller: 'OperatorHomeController',
                controllerAs: 'homeCtrl'
            }).when('/circlehome', {
                templateUrl: 'templates/pages/home/circlehome.html',
                controller: 'CircleHomeController',
                controllerAs: 'homeCtrl'
            }).when('/developerhome', {
                templateUrl: 'templates/pages/home/developerhome.html',
                controller: 'DeveloperController',
                controllerAs: 'developerCtrl'
            })
            .when('/enterreading', {
                templateUrl: 'templates/pages/meter/meterreading.html',
                controller: 'MeterReadingController',
                controllerAs: 'readingCtrl'
            }).when('/saved/:message', {
                templateUrl: 'templates/pages/meter/metersaved.html',
                controller: 'SaveController',
                controllerAs: 'saveCtrl'
            }).when('/viewmeterreadings', {
                templateUrl: 'templates/pages/meter/viewmeterreading2.html',
                controller: 'ViewMeterReadingsController',
                controllerAs: 'viewMeterReadingsCtrl'
            }).when('/addmeter', {
                templateUrl: 'templates/pages/meter/addmeter.html',
                controller: 'AddMeterController',
                controllerAs: 'addMeterCtrl'
            }).when('/viewdeveloperreading', {
                templateUrl: 'templates/pages/meter/viewdeveloperreading.html',
                controller: 'DeveloperViewMeterReadingsController',
                controllerAs: 'viewMeterReadingsCtrl'
            }).when('/splitreadings/:plantId/:meterNo', {
                templateUrl: 'templates/pages/meter/readingsbifircationpage.html',
                controller: 'BifircateReadingsController',
                controllerAs: 'viewMeterReadingsCtrl'
            }).when('/viewsplitedreadings/:plantId/:meterNo', {
                templateUrl: 'templates/pages/meter/viewbifircationpage.html',
                controller: 'ViewBifircateReadingsController',
                controllerAs: 'viewMeterReadingsCtrl'
            }).when('/viewbill/:billDetailsId', {
                templateUrl: 'templates/pages/meter/viewbill.html',
                controller: 'ViewBillController',
                controllerAs: 'viewBillCtrl'
            }).when('/viewcirclereadings', {
                templateUrl: 'templates/pages/meter/viewcirclereading.html',
                controller: 'ViewMeterReadingsController',
                controllerAs: 'viewMeterReadingsCtrl'
            }).when('/viewcircleconsumptions', {
                templateUrl: 'templates/pages/meter/circle_consumption_validation_page.html',
                controller: 'CircleConsumptionValidationController',
                controllerAs: 'circleConsumptionValidationController'
            });
    });
})();