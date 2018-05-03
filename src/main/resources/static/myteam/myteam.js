angular.module('myApp.myteam', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/myteam', {
            templateUrl: 'myteam/myteam.html'

        });

    }])
