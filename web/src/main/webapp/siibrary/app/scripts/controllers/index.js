'use strict';

/**
 * @ngdoc function
 * @name siibraryApp.controller:IndexCtrl
 * @description
 * # IndexCtrl
 * Controller of the siibraryApp
 */
angular.module('siibraryApp')
  .controller('IndexCtrl', function ($rootScope, $scope, contextFactory) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
    
    $rootScope.context = {};  
	$scope.setupContext = function() {
		contextFactory.setupContext().$promise.then(function (result) {
			$rootScope.context = result;
		});
	};
	$scope.setupContext();    
    
  });
