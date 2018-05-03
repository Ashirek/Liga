angular.module('myApp.addgameweek', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/admin/addgameweek', {
            templateUrl: 'admin/addgameweek/addgameweek.html',
            //controller: 'addGameWeekCtrl'
            resolve: addGameWeekCtrl.resolve
        })
            .when('/admin/addgameweek2', {
                templateUrl: 'admin/addgameweek/addgameweek2.html',
              //  controller: 'addGameWeekCtrl2'
                resolve: addGameWeekCtrl.resolve
            })

    }])
    .controller('addGameWeekCtrl',addGameWeekCtrl)
    .controller('addGameWeekCtrl2',addGameWeekCtrl2)
    .service('variablesGW', function () {
        this.model={
            'leagueTypeName':  '',
            'gameWeekNumber': '',
            'gameWeekStart': '',
            'gameWeekEnd': ''
        }

    })
    .service('variablesF', function () {
        this.model = {
            'homeTeamName':  '',
            'awayTeamName': '',
            'matchTime': '',
            'leagueTypeName':'',
            'gameWeekNumber':'',
            'homeTeamGoals': '',
            'awayTeamGoals': ''
        }
    })
addGameWeekCtrl.$inject = ['$scope', 'GetService', 'SetService', 'variablesF'];

function addGameWeekCtrl ($scope, getService, setService, variablesF)
{


    $scope.model=variablesF.model;
    getService.getAllLeagueType()
        .then(function(response) {
            $scope.allLeagueType = response.data;
        });

    $scope.getGW = function () {
        getService.getGameWeeksByLeagueTypeName($scope.model.leagueTypeName)
            .then(function (response) {
                $scope.allGameWeek = response.data;
            });
    };

    $scope.getData = function () {
        getService.getRealTeamsByLeagueName($scope.model.leagueTypeName)
            .then(function (response) {
                $scope.realTeam=response.data;

            });
    }

    $scope.next = function () {
        setService.addPath("/admin/addgameweek2");
    };



    $scope.addF = function () {
        setService.addFixture($scope.model)

    }


};

addGameWeekCtrl2.$inject = ['$scope', 'GetService', 'SetService', 'variablesGW'];

function addGameWeekCtrl2 ($scope, getService, setService, variablesGW)
{
    $scope.model=variablesGW.model;

    $scope.addGW = function () {
        setService.addGameWeek($scope.model);
    }

    getService.getAllLeagueType()
        .then(function(response) {
            $scope.allLeagueType = response.data;
        });



};

addGameWeekCtrl.resolve = {
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