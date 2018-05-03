angular.module('myApp.classification', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/classification', {
            templateUrl: 'classification/classification.html'

        });

    }]);
