angular.module('myApp.setservices', [])
    .factory('SetService', function($http,$location,AuthService) {

        var createUser = function (cust) {
            return $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                data: cust,
                url:'/register',
                method: 'POST'
            });
        };

        var addLeagueType = function (cust) {
            $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                data: cust,
                url:'/league/add',
                method: 'POST'
            });
        };

        var addRealTeam = function (cust) {
            $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                data: cust,
                url:'/realteam/add',
                method: 'POST'
            });
        };

        var addFootballer = function (cust) {
            return $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                data: cust,
                url:'/footballer/add',
                method: 'POST'
            });
        };
        var delFootballer = function (cust) {
            $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                data: cust,
                url:'/footballer/del"',
                method: 'POST'
            });
        };

        var createTeam = function (cust) {
            $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                data: cust,
                url:'/team/create',
                method: 'POST'
            });
        };

        var addPoints = function (cust) {
            $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                data: cust,
                url:'/team/addpoints',
                method: 'PUT'
            });
        };



        var addPath = function (cust) {
            $location.path(cust);
        };

        var addGameWeek = function (cust) {
            $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                data:cust,
                url:'/gameweek/add',
                method:'POST'
            })

        };

        var addFixture = function (cust) {
            $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                data:cust,
                url:'/fixture/add',
                method: 'POST'
            })
                    };
        var putFixture = function (cust) {
            $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                data:cust,
                url:'/fixture/put',
                method: 'PUT'
            })
        };

        var addRoster = function (cust) {
            return $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                data:cust,
                url:'/roster/add',
                method:'PUT'
            })

        }

        var deleteRoster = function (cust) {
           return $http({
               headers: AuthService.createAuthorizationTokenHeader(),
                data:cust,
                url:'/roster/del',
                method:'PUT'
            })

        };

        var setStarter = function (cust) {
            return $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                data:cust,
                url:'/starter/set',
                method:'PUT'
            })

        };

        var addFootballerStats = function (cust) {
            return $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                data:cust,
                url:'/footballerstats/add',
                method:'POST'
        })
        };
        var setUniversal = function (cust, cust2) {
            return $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                data:cust,
                url:cust2,
                method:'PUT'
            }) ;

        }



        return {
            addLeagueType: addLeagueType,
            addRealTeam: addRealTeam,
            addFootballer: addFootballer,
            delFootballer:delFootballer,
            createTeam: createTeam,
            addPoints:addPoints,
            addPath: addPath,
            createUser: createUser,
            addGameWeek: addGameWeek,
            addFixture: addFixture,
            putFixture: putFixture,
            addRoster: addRoster,
            deleteRoster: deleteRoster,
            setStarter:setStarter,
            addFootballerStats:addFootballerStats,
            setUniversal: setUniversal
        };
    });