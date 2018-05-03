angular.module('myApp.addleague', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/admin/addleague', {
            templateUrl: 'admin/addleague/addleague.html',
            controller: 'addLeagueCtrl',
            resolve: addLeagueCtrl.resolve
        });

    }])


    .controller('addLeagueCtrl', addLeagueCtrl);

addLeagueCtrl.$inject = ['$scope', 'GetService', 'SetService'];

function addLeagueCtrl($scope, getService, setService) {


    $scope.leagueType = {};
    $scope.credentials = {};

    getService.getAllLeagueType()
        .then(function (response) {
            $scope.leagueType = response.data;
        });


    $scope.addLeague = function () {
        setService.addLeagueType($scope.credentials);

    }
};

addLeagueCtrl.resolve = {
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