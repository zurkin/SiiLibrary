'use strict';

/**
 * @ngdoc function
 * @name siibraryApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the siibraryApp
 */
angular.module('siibraryApp')
  .controller('MainCtrl', function ($scope, httpFactory, notificationService) {
	
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
	
	$scope.reserveBook = function(book) {
		httpFactory.reserveBook({data: book}).$promise.then(function () {
			notificationService.success('Reservation book process completed successfully');
			$scope.setupBooks();
		});
	};
  });
