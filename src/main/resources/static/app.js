'use strict';
// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ngRoute',
  'myApp.dashboard',
  'myApp.login',
  'myApp.services',
    'myApp.getservices',
    'myApp.setservices',
  'myApp.register',
    'myApp.admin',
    'myApp.addleague',
    'myApp.addteam',
    'myApp.myteam',
    'myApp.team',
    'myApp.transfers',
    'myApp.classification',
    'myApp.stats',
    'myApp.rules',
    'myApp.addfootballers',
    'myApp.createteam',
    'myApp.addgameweek',
    'myApp.addstats'


]).
config(['$locationProvider', '$routeProvider', "$httpProvider", function($locationProvider, $routeProvider, $httpProvider) {
	$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
  $routeProvider.otherwise({redirectTo: '/'});

}])


.controller('NavigationCtrl', ['$scope', '$rootScope', '$http', '$location', 'AuthService',
  function($scope, $rootScope, $http, $location, authService) {
    var self = this

    $rootScope.selectedTab = $location.path() || '/';






    $scope.logout = function() {
      authService.removeJwtToken();
      $rootScope.authenticated = false;
      $rootScope.adminLogged = false;
      $rootScope.userLogged = false;
      $location.path("#/");
      $rootScope.selectedTab = "/";
    }

    $scope.setSelectedTab = function(tab) {
      $rootScope.selectedTab = tab;
    }


    $scope.tabClass = function(tab) {
      if ($rootScope.selectedTab == tab) {
        return "active";
      } else {
        return "";
      }

    }




    if ($rootScope.authenticated) {
      $location.path('/');
      $rootScope.selectedTab = '/';
      return;
    }
    if ($rootScope.authenticated){
        authService.getUser()
            .then(function(response) {
                if(response.data.authorities.authority === "ROLE_USER"){
                    $rootScope.userLogged =true;}
                if(response.data.authorities.authority === "ROLE_ADMIN"){
                    $rootScope.adminLogged =true;}


            });
    }
  }
]);

