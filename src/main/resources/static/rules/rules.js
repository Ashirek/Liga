angular.module('myApp.rules', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/rules', {
            templateUrl: 'rules/rules.html'

        });

    }])
