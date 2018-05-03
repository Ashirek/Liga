angular.module('myApp.addstats', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {

        $routeProvider.when('/admin/addstats', {
            templateUrl: 'admin/addstats/addstats.html',
            controller: 'addstatsCtrl',
            resolve: addstatsCtrl.resolve

        })
            .when('/admin/addstats2', {
                templateUrl: 'admin/addstats/addstats2.html',
                controller: 'addstatsCtrl2',
                resolve: addstatsCtrl.resolve
            })

    }])
    .controller('addstatsCtrl', addstatsCtrl)
    .controller('addstatsCtrl2', addstatsCtrl2)

    .service('variablesStats', function () {
        this.model = {
            'homeTeamName': "",
            'awayTeamName': "",
            'homeTeamGoals': "",
            'awayTeamGoals': ""
        }
        })
    .service('stats', function () {
    this.model = {
        'footballerFirstName': "",
        'footballerLastName':"",
        'realTeamName': "",
        'leagueTypeName':"",
        'gameWeekNumber':"",
        'gameTime': "",
        'yellowCards':"",
        'redCards':"",
        'assists':"",
        'goals': "",
        'matchResult': ""
    }
    })
    .service('stats2', function () {
        this.model = {
            'footballerFirstName': "",
            'footballerLastName':"",
            'realTeamName': "",
            'leagueTypeName':"",
            'gameWeekNumber':"",
            'gameTime': "",
            'yellowCards':"",
            'redCards':"",
            'assists':"",
            'goals': "",
            'matchResult': ""
        }
    });

addstatsCtrl.$inject = ['$scope', 'GetService', 'SetService', '$location', 'variablesStats', 'stats'];
function addstatsCtrl($scope, getService, setService, $location, variablesStats, stats) {

    $scope.model=variablesStats.model;

    $scope.model2=stats.model;



    getService.getAllLeagueType()
        .then(function (response) {
            $scope.allLeagueType= response.data;

        });

    $scope.getGW = function () {
        getService.getGameWeeksByLeagueTypeName($scope.model2.leagueTypeName)
            .then(function (response) {
                $scope.allGameWeek = response.data;

            });
    };



    $scope.getData = function () {
        getService.getFixturesByLeagueAndGameWeek($scope.model2.leagueTypeName,$scope.model2.gameWeekNumber)
            .then(function (response) {
                $scope.fixtures= response.data;
            });
        $scope.vpoint = {
            'leagueTypeName':$scope.model2.leagueTypeName,
            'gameWeekNumber':$scope.model2.gameWeekNumber,
        };
    };

    $scope.addP = function () {

        setService.addPoints($scope.vpoint)
    }

    $scope.next = function (var1,var2) {
        $scope.model.homeTeamName = var1;
        $scope.model.awayTeamName = var2;
        setService.addPath("/admin/addstats2");
    };
}

addstatsCtrl2.$inject = ['$scope', '$rootScope', 'GetService', 'SetService', '$location', 'variablesStats','stats','$interval','stats2'];
function addstatsCtrl2($scope, $rootScope, getService, setService, $location, variablesStats,stats,$interval,stats2) {

    $scope.model=variablesStats.model;
    $scope.model2 = stats.model;
    $scope.model3 = stats2.model;
    $scope.model3.leagueTypeName = $scope.model2.leagueTypeName;
    $scope.model3.gameWeekNumber = $scope.model2.gameWeekNumber;
    $scope.showH=true;
    $scope.showA=true;


    getService.getFootballersByRealTeam($scope.model.homeTeamName)
        .then(function (response) {
            $scope.homeFootballers = response.data;
            $scope.model2.footballerFirstName = $scope.homeFootballers[0].footballerFirstName;
            $scope.model2.footballerLastName = $scope.homeFootballers[0].footballerLastName;

            $scope.duration=0;
            $interval(function () {
                ++$scope.duration;
            },5,100).then(function () {

                $scope.homeFootballers.shift();
            });
        });

    getService.getFootballersByRealTeam($scope.model.awayTeamName)
        .then(function (response) {
            $scope.awayFootballers = response.data;

            $scope.model3.footballerFirstName = $scope.awayFootballers[0].footballerFirstName;
            $scope.model3.footballerLastName = $scope.awayFootballers[0].footballerLastName;

            $scope.duration=0;
            $interval(function () {
                ++$scope.duration;
            },5,100).then(function () {

                $scope.awayFootballers.shift();
            });
        });
     
    $scope.addStatsH = function (var1) {

        $scope.model2.footballersLastName=var1;
        $scope.model2.realTeamName=$scope.model.homeTeamName;
        if ($scope.model.homeTeamGoals>$scope.model.awayTeamGoals){
            $scope.model2.matchResult = "win"
        } else if ($scope.model.homeTeamGoals === $scope.model.awayTeamGoals) {
            $scope.model2.matchResult = "draw"
        } else {
            $scope.model2.matchResult = "lose"
        }
        setService.addFootballerStats($scope.model2);

        $scope.duration2=0;

        $scope.test=$scope.homeFootballers.length;
        if($scope.homeFootballers.length !== 0){
            $interval(function () {
                ++$scope.duration2;
            },5,100).then(function () {
                $scope.model2.footballerFirstName = $scope.homeFootballers[0].footballerFirstName;
                $scope.model2.footballerLastName = $scope.homeFootballers[0].footballerLastName;
            });


            $scope.duration=0;
            $interval(function () {
                ++$scope.duration;
            },5,100).then(function () {
                $scope.homeFootballers.shift();
            });
        } else
        {    $scope.showH=false;}



    }
    $scope.addStatsA = function (var1) {

        $scope.model3.footballersLastName=var1;
        $scope.model3.realTeamName=$scope.model.awayTeamName;
        if ($scope.model.awayTeamGoals>$scope.model.homeTeamGoals){
            $scope.model3.matchResult = "win"
        } else if ($scope.model.awayTeamGoals === $scope.model.homeTeamGoals) {
            $scope.model3.matchResult = "draw"
        } else {
            $scope.model3.matchResult = "lose"
        }

        setService.addFootballerStats($scope.model3);
        if($scope.awayFootballers.length !== 0){
            $scope.duration2=0;
            $interval(function () {
                ++$scope.duration2;
            },5,100).then(function () {
                $scope.model3.footballerFirstName = $scope.awayFootballers[0].footballerFirstName;
                $scope.model3.footballerLastName = $scope.awayFootballers[0].footballerLastName;
            });
            $scope.duration=0;
            $interval(function () {
                ++$scope.duration;
            },5,200).then(function () {
                $scope.awayFootballers.shift();
            });

        } else
        { $scope.showA=false;}


    }
    $scope.addFix = function () {
        setService.putFixture($scope.model);
    }
};

addstatsCtrl.resolve = {
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
