'use strict';

/**
 * @ngdoc function
 * @name siibraryApp.controller:NavCtrl
 * @description
 * # NavCtrl
 * Controller of the siibraryApp
 */
angular.module('siibraryApp')
    
.controller('NavCtrl', function ($scope, $rootScope, $location) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
    
    $scope.booksSize = 0;
    
    $scope.$on('bookEvent', function (event, data) {
    	$scope.booksSize = data;  
    });
    	
    $scope.navClass = function (page) {
        var currentRoute = $location.path().substring(1) || 'home';
        return page === currentRoute ? 'active' : '';
    };
    
//	$scope.context = $rootScope.context;

  });
