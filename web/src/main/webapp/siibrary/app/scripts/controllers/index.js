'use strict';

/**
 * @ngdoc function
 * @name siibraryApp.controller:IndexCtrl
 * @description
 * # IndexCtrl
 * Controller of the siibraryApp
 */
angular.module('siibraryApp')
  .controller('IndexCtrl', function ($scope, contextFactory) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
    
    $scope.context = {};  
	$scope.setupContext = function() {
		contextFactory.setupContext().$promise.then(function (result) {
			$scope.context = result;
		});
	};
	$scope.setupContext();    
    
  });
