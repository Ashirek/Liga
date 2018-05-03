angular.module('myApp.login', ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/login', {
            templateUrl: 'login/login.html',
            controller: 'loginCtrl'
        });
    }])

    .controller('loginCtrl', loginCtrl);

loginCtrl.$inject = ['$scope', '$rootScope', '$http', '$location', 'AuthService'];
function loginCtrl($scope, $rootScope, $http, $location, authService) {
    $scope.error = false;
    $rootScope.selectedTab = $location.path() || '/';

    $scope.credentials = {};

    $scope.login = function () {
        // We are using formLogin in our backend, so here we need to serialize our form data
        $http({
            url: 'auth/login',
            method: 'POST',
            data: $scope.credentials,
            headers: authService.createAuthorizationTokenHeader()
        })
            .success(function (res) {
                $rootScope.authenticated = true;
                authService.setJwtToken(res.access_token);
                $location.path("#/");
                $rootScope.selectedTab = "/";
                $scope.error = false;
                authService.getUser()
                    .then(function (response) {
                        if(response.data.authorities.authority === "ROLE_USER"){
                            $rootScope.userLogged =true;}
                        if(response.data.authorities.authority === "ROLE_ADMIN"){
                            $rootScope.adminLogged =true;}


                    });
            })
            .catch(function () {
                authService.removeJwtToken();
                $rootScope.authenticated = false;
                $scope.error = true;
                $rootScope.adminLogged = false;
                $rootScope.userLogged = false;
            });
    };


};
