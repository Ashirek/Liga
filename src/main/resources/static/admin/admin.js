angular.module('myApp.admin', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/admin', {
            templateUrl: 'admin/admin.html',
            controller: 'adminCtrl'
        });

    }])


    .controller('adminCtrl', adminCtrl);

    adminCtrl.$inject =  ['$scope', '$rootScope', '$http'];
    function adminCtrl ($scope, $rootScope, $http){



        $scope.credentials = {};





        $scope.addLeague = function () {
            $http({
                data: $scope.credentials,
                url:'/admin/addleague/setleague',
                method: 'POST'
            });
        };
    };