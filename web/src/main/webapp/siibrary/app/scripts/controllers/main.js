'use strict';

/**
 * @ngdoc function
 * @name siibraryApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the siibraryApp
 */
angular.module('siibraryApp')
  .controller('MainCtrl', function ($scope, httpFactory) {
	
	$scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];


	$scope.setupBooks = function() {
		httpFactory.findAllBooks().$promise.then(function (result) {
			$scope.books = result.list;
		});
	};

	$scope.setupBooks();
	console.log('books' + $scope.books);
	
  });
