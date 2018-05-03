angular.module('myApp.transfers', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/myteam/transfers', {
            templateUrl: 'myteam/transfers/transfers.html',
            controller: 'transferCtrl',
            resolve:transferCtrl.resolve

        })
            .when('/myteam/transfers2', {
                templateUrl: 'myteam/transfers/transfers2.html',
                controller: 'transferCtrl2',
                resolve:transferCtrl.resolve
            })
            .when('/myteam/transfers3', {
                templateUrl: 'myteam/transfers/transfers3.html',
                controller: 'transferCtrl3',
                resolve:transferCtrl.resolve

            })

    }])
    .controller('transferCtrl', transferCtrl)
    .controller('transferCtrl2', transferCtrl2)
    .controller('transferCtrl3', transferCtrl3)

    .service('variables', function () {
        this.model = {
            'leagueTypeName': "",
            'teamName':"",
            'footballerLastNameAdd':"",
            'footballerLastNameDel':"",
            'gameWeekNumber': "1",
            'bank': "",
            'username': ""
        };


    });
transferCtrl.$inject = ['$scope', 'AuthService', 'GetService', 'SetService', '$location', 'variables'];
function transferCtrl($scope, authService, getService, setService, $location, variables) {

    $scope.model = variables.model;

    authService.getUser()
        .then(function (response) {
            $scope.model.username=response.data.username;
        });

    getService.getAllLeagueType()
        .then(function (response) {
            $scope.leagueType = response.data;
        });



    $scope.next = function () {
        setService.addPath("/myteam/transfers2");
    };



}

transferCtrl2.$inject = ['$scope', 'GetService', 'SetService', 'variables'];
function transferCtrl2($scope, getService, setService, variables) {

    $scope.model = variables.model;
    getService.getTeamsByLeagueAndUsername($scope.model.leagueTypeName,$scope.model.username)
        .then(function (response) {
            $scope.teams = response.data;
        });
    /*getService.getTeamsByLeague($scope.leagueTypeName)
        .then(function (response) {
            $scope.teams = response.data;
        });*/

    $scope.back = function () {
        setService.addPath("/myteam/transfers");
    };



    $scope.next = function () {
        setService.addPath("/myteam/transfers3");
    };
}

transferCtrl3.$inject = ['$scope', 'GetService', 'SetService', 'variables', '$interval'];
function transferCtrl3($scope, getService, setService, variables, $interval) {
    $scope.answer="nic";

    $scope.model = variables.model;

    getService.getFootballersByLeague($scope.model.leagueTypeName)
        .then(function (response) {
            $scope.footballers=response.data;
        });


    $scope.confirmAddToRoster = function () {
        setService.addRoster($scope.model).then(function (response) {

            $scope.answer=response.data;
            if($scope.answer.result ==="success"){
                $scope.transfer1=true;
                $scope.duration = 0;
                $interval(function () {
                    ++$scope.duration;
                },5,100).then(function () {
                    getService.getRostersByTeam($scope.model.teamName)
                        .then(function (response) {
                            $scope.footbalersinroster=response.data;
                        });
                    getService.getTeamBank($scope.model.teamName)
                        .then(function (response) {
                            $scope.model.bank=response.data;

                        })
                })
            }
            if($scope.answer.result ==="error"){
                $scope.transfer2=true;
            }
        });



    };
    $scope.delFootballer = function (item) {
        $scope.model.footballerLastNameDel=item;
        $scope.transfer1=false;
        $scope.transfer2=false;

    };

    $scope.confirmDelFromRoster = function () {
        setService.deleteRoster($scope.model).then(function (response) {
            $scope.answer=response.data;

        });
            $scope.duration = 0;

            $interval(function () {
                ++$scope.duration;
            },5,100).then(function () {
                getService.getRostersByTeam($scope.model.teamName)
                    .then(function (response) {
                        $scope.footbalersinroster=response.data;
                    });
            });

    };

    /*getService.getRostersByTeam($scope.model.teamName)
        .then(function (response) {
            $scope.footbalersinroster=response.data;
        });*/
    $scope.addToRoster = function (item) {
        $scope.model.footballerLastNameAdd=item;
        $scope.transfer1=false;
        $scope.transfer2=false;
    };

    getService.getTeamBank($scope.model.teamName)
        .then(function (response) {
            $scope.model.bank=response.data;

        });
    $scope.back = function () {
        setService.addPath("/myteam/transfers2");
    };
    $scope.positions ={
        'goalkeeper':"Bramkarz",
        'defender':"Obronca",
        'midfielder': "Pomocnik",
        'forward': "Napastnik"
    }

};
transferCtrl.resolve = {
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

