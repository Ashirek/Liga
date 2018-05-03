angular.module('myApp.addfootballers', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/admin/addfootballers', {
            templateUrl: 'admin/addfootballers/addfootballers.html',
            controller: 'updateFootballersCtrl',
            resolve: updateFootballersCtrl.resolve

        });

    }])
    .controller('updateFootballersCtrl',updateFootballersCtrl);

    updateFootballersCtrl.$inject = ['$scope', 'GetService', 'SetService'];

    function updateFootballersCtrl ($scope, getService, setService)
    {   $scope.credentials = {};
        $scope.selectedLiga = {};
        $scope.selectedRTeam = {};
        $scope.blad=false;
        getService.getAllLeagueType()
            .then(function(response) {
                $scope.leagueType = response.data;
            });
        getService.getAllRealTeams()
            .then(function(response) {
                $scope.realTeams = response.data;
            });
        getService.getAllFootballers()
            .then(function (response) {
                $scope.footballers = response.data;
            });
        $scope.addFootballer = function() {
            setService.addFootballer($scope.credentials)
                .then(function (response) {
                    $scope.answer=response.data;
                    if($scope.answer.result ==="success"){
                        $scope.blad = false;
                    } else
                    {
                        $scope.blad = true;
                    }
                });
        };
        $scope.delFut = function() {
            setService.delFootballer($scope.credentials);
        };



    }
updateFootballersCtrl.resolve = {
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