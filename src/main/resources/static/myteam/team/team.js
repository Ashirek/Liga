angular.module('myApp.team', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/myteam/team', {
            templateUrl: 'myteam/team/team.html',
            controller: 'myTeamCtrl',
            resolve: myTeamCtrl.resolve



        });

    }])
    .controller('myTeamCtrl', myTeamCtrl)
    .service('vRosters', function () {
        this.model = {
            'goalkeepers': '',
            'defenders': '',
            'midfielders': '',
            'forwards': ''

        };

    })
    .service('vStarters', function () {
        this.model ={
            'goalkeepers': '',
            'defenders':'' ,
            'midfielders': '',
            'forwards': ''
        }
    })
    .service('sStarters', function () {
        this.model={
            'teamName':"",
            'footballerLastNameAdd':"",
            'footballerLastNameDel':""
        }

    })
    .factory('SetStarters', function(SetService) {
        var setStarter = function (cust) {
            SetService.setStarter(cust)

        };
    return{
            setStarter: setStarter
    }
    });


myTeamCtrl.$inject = ['$scope', 'GetService', 'AuthService', 'vRosters', 'vStarters', 'sStarters', 'SetStarters', '$interval'];
function myTeamCtrl($scope, getService, authService, vRosters, vStarters, sStarters, setStarters, $interval){
    $scope.rosters=vRosters.model;
    $scope.starters=vStarters.model;
    $scope.starterssend=sStarters.model;

    authService.getUser()
        .then(function (response) {


            getService.getTeamsByUsername(response.data.username)
                .then(function (response2) {
                    $scope.teams = response2.data;
                })
        });

    $scope.getF = function () {
        getService.getStartersByPosition($scope.starterssend.teamName, "Bramkarz")
            .then(function (response) {
                $scope.starters.goalkeepers=response.data;
            });
        getService.getStartersByPosition($scope.starterssend.teamName, "Obronca")
            .then(function (response) {
                $scope.starters.defenders=response.data;

            });
        getService.getStartersByPosition($scope.starterssend.teamName, "Pomocnik")
            .then(function (response) {
                $scope.starters.midfielders=response.data;

            });
        getService.getStartersByPosition($scope.starterssend.teamName, "Napastnik")
            .then(function (response) {
                $scope.starters.forwards=response.data;
            });
        getService.getRostersByPositions($scope.starterssend.teamName, "Bramkarz")
            .then(function (response) {
                $scope.rosters.goalkeepers=response.data;

            });
        getService.getRostersByPositions($scope.starterssend.teamName, "Obronca")
            .then(function (response) {
                $scope.rosters.defenders=response.data;

            });
        getService.getRostersByPositions($scope.starterssend.teamName, "Pomocnik")
            .then(function (response) {
                $scope.rosters.midfielders=response.data;

            });
        getService.getRostersByPositions($scope.starterssend.teamName, "Napastnik")
            .then(function (response) {
                $scope.rosters.forwards=response.data;

            });
    };




    $scope.setGk = function () {
        $scope.duration = 0;
        setStarters.setStarter($scope.starterssend);
        $interval(function () {
            ++$scope.duration;
        },5,100).then(function () {
            getService.getStartersByPosition($scope.starterssend.teamName, "Bramkarz")
                .then(function (response) {
                    $scope.starters.goalkeepers=response.data;
                });
            getService.getRostersByPositions($scope.starterssend.teamName, "Bramkarz")
                .then(function (response) {
                    $scope.rosters.goalkeepers=response.data;
                });
        })
    };

    $scope.setDef = function () {
        $scope.duration = 0;
        setStarters.setStarter($scope.starterssend);
        $interval(function () {
            ++$scope.duration;
        },5,100).then(function () {
            getService.getStartersByPosition($scope.starterssend.teamName, "Obronca")
                .then(function (response) {
                    $scope.starters.defenders=response.data;
                });
            getService.getRostersByPositions($scope.starterssend.teamName, "Obronca")
                .then(function (response) {
                    $scope.rosters.defenders=response.data;
                });
        })
    };

    $scope.setMid = function () {

        $scope.duration = 0;
        setStarters.setStarter($scope.starterssend);
        $interval(function () {
            ++$scope.duration;
        },5,100).then(function () {
            getService.getStartersByPosition($scope.starterssend.teamName, "Pomocnik")
                .then(function (response) {
                    $scope.starters.midfielders=response.data;
                });
            getService.getRostersByPositions($scope.starterssend.teamName, "Pomocnik")
                .then(function (response) {
                    $scope.rosters.midfielders=response.data;
                });
        })
    };

    $scope.setFor = function () {
        $scope.duration = 0;
        setStarters.setStarter($scope.starterssend);
        $interval(function () {
            ++$scope.duration;
        },5,100).then(function () {
            getService.getStartersByPosition($scope.starterssend.teamName, "Napastnik")
                .then(function (response) {
                    $scope.starters.forwards=response.data;
                });
            getService.getRostersByPositions($scope.starterssend.teamName, "Napastnik")
                .then(function (response) {
                    $scope.rosters.forwards=response.data;
                });
        });
    };
}

myTeamCtrl.resolve = {
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
