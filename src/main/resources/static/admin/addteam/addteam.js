'use strict';

angular.module('myApp.addteam', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/admin/addteam', {
            templateUrl: 'admin/addteam/addteam.html',
            controller: 'addTeamCtrl',
            resolve: addTeamCtrl.resolve

        });

    }])
    .controller('addTeamCtrl', addTeamCtrl);

    addTeamCtrl.$inject = ['$scope', '$rootScope','GetService', 'SetService','isAuthenticated', 'AuthService'];


    function addTeamCtrl ($scope, $rootScope, getService, setService, isAuthenticated, authService)
    {
        $rootScope.authenticated = isAuthenticated;
        $scope.credentials = {};

        $scope.selectedLiga = {};


        getService.getAllLeagueType()
            .then(function(response) {
                $scope.leagueType = response.data;
            });

        getService.getAllRealTeams()
            .then(function(response) {
                $scope.realTeams = response.data;
            });

        $scope.addRTeam= function() {
            setService.addRealTeam($scope.credentials);

        }

    };


addTeamCtrl.resolve = {
    isAuthenticated : function($q, $http, AuthService,$rootScope) {
        var deferred = $q.defer();
        var oldToken = AuthService.getJwtToken();
        if (oldToken !== null) {
            $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                method: 'POST',
                url: 'auth/refresh'
            })
                .success(function(res) {
                    AuthService.setJwtToken(res.access_token);
                    deferred.resolve(res.access_token !== null);
                    $rootScope.authenticated = true;
                    AuthService.getUser()
                        .then(function(response) {
                            if(response.data.authorities[0].authority === "ROLE_USER"){
                                $rootScope.userLogged =true;}
                            if(response.data.authorities[0].authority === "ROLE_ADMIN"){
                                $rootScope.adminLogged =true;}

                        });
                })
                .error(function(err){
                    AuthService.removeJwtToken();
                    deferred.resolve(false); // you could optionally pass error data here
                    $rootScope.authenticated = false;
                    $rootScope.adminLogged = false;
                    $rootScope.userLogged= false;
                });
        } else {
            deferred.resolve(false);
        }
        return deferred.promise;
    }
};
