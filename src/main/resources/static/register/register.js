angular.module('myApp.register', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/register', {
            templateUrl: 'register/register.html',
            controller: 'registerCtrl'
        });

    }])
    .controller('registerCtrl', registerCtrl);

    registerCtrl.$inject = ['$scope','$rootScope','SetService'];

    function registerCtrl($scope,$rootScope, setService) {
        $scope.dane = {
            'username' : '',
            'password':'',
            'firstName':'',
            'lastName':'',
            'email':''
        }

        $scope.register = function () {
            setService.createUser($scope.dane)
         /// $scope.odpowiedz=setService.createUser($scope.zmienna)
        };
    }



