angular.module('myApp.getservices', [])
    .factory('GetService', function($http,AuthService) {



        var getAllLeagueType = function() {
            return $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                method: 'GET',
                url: '/leaguetype/getall'
            });
        };

        var getAllRealTeams = function() {
            return $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                method: 'GET',
                url: '/realteam/getall'
            });
        };

        var getRealTeamsByLeagueName = function (cust) {
            return $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                method:'GET',
                url:'/realteam/getallbyleaguename/'+cust
            })

        }

        var getTeamsByLeague = function(cust) {
            return $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                method: 'GET',
                url: '/team/getallbyleague/'+cust
            });
        };

        var getTeamsByUsername = function(cust) {
            return $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                method: 'GET',
                url: '/team/getallbyusername/'+cust
            });
        };

        var getTeamsByLeagueAndUsername = function (cust,cust2) {
            return $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                method: 'GET',
                url:'/team/getallbyleagueandusername/'+cust + '/' + cust2
            })
        };

        var getAllTeams = function() {
            return $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                method: 'GET',
                url: '/team/getall'
            });
        };

        var getAllFootballers = function() {
            return $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                method: 'GET',
                url: '/footballer/getall'
            });
        };

        var getFootballersByLeague = function (cust) {
            return $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                method: 'GET',
                url: '/footballer/getallbyleague/'+cust
            })
        };

        var getAllGameWeek = function () {
            return $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                method:'GET',
                url:'/gameweek/getall'
            })

        };

        var getGameWeeksByLeagueTypeName = function (cust) {
            return $http ({
                headers: AuthService.createAuthorizationTokenHeader(),
                method:'GET',
                url:'/gameweek/getallbyleaguetypename/' + cust
            })

        }

        var getFootballersByRealTeam = function (cust) {
            return $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                method:'GET',
                url:'/footballer/getallbyrealteam/' + cust
            })

        };

        var getUniversal = function (cust) {
            return $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                method:'GET',
                url:cust
            })

        }

        var getRostersByTeam = function (cust) {
            return $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                method:'GET',
                url:'/roster/getallbyteam/'+cust
            })

        };

        var getAllRosters = function () {
            return $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                method:'GET',
                url:'/roster/getall'
            })

        };

        var getTeamBank = function (cust) {
            return $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                method:'GET',
                url:'/team/getbank/' + cust
            })
        };

        var getRostersByPositions = function (teamName,position) {
            return $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                method:'GET',
                url:'/roster/getallbyposition/' + teamName + '/' + position
            })
        };

        var getStartersByPosition = function (teamName,position) {
            return $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                method:'GET',
                url:'/starter/getallbypostion/' + teamName + '/' + position
            })
        };

        var getFixturesByLeagueAndGameWeek = function (leagueName,gameWeekNumber) {
            return $http({
                headers: AuthService.createAuthorizationTokenHeader(),
                method:'GET',
                url:'fixture/getallby/' + leagueName + '/' + gameWeekNumber
            })

        }


        return {
            getAllLeagueType: getAllLeagueType,
            getAllRealTeams: getAllRealTeams,
            getAllFootballers: getAllFootballers,
            getTeamsByLeague: getTeamsByLeague,
            getTeamsByUsername: getTeamsByUsername,
            getTeamsByLeagueAndUsername: getTeamsByLeagueAndUsername,
            getAllTeams: getAllTeams,
            getFootballersByLeague: getFootballersByLeague,
            getAllGameWeek: getAllGameWeek,
            getGameWeeksByLeagueTypeName:getGameWeeksByLeagueTypeName,
            getRealTeamsByLeagueName: getRealTeamsByLeagueName,
            getRostersByTeam: getRostersByTeam,
            getFootballersByRealTeam: getFootballersByRealTeam,
            getAllRosters:getAllRosters,
            getTeamBank:getTeamBank,
            getRostersByPositions:getRostersByPositions,
            getStartersByPosition:getStartersByPosition,
            getFixturesByLeagueAndGameWeek:getFixturesByLeagueAndGameWeek,
            getUniversal: getUniversal
        };
    });