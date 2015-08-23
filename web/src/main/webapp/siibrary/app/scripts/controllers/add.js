'use strict';

/**
 * @ngdoc function
 * @name siibraryApp.controller:AddCtrl
 * @description
 * # AddCtrl
 * Controller of the siibraryApp
 */
angular.module('siibraryApp')
  .controller('AddCtrl', function ($rootScope, $scope, httpFactory, notificationService) {

    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];    

  	$scope.setupBook = function() {
  		$scope.newBook = {id: null, title: '', author: '', description: ''};
  	};
  	$scope.setupBook();
  	
  	$scope.addBook = function() {
  		httpFactory.createBook({data: $scope.newBook}).$promise.then(function () {
  			notificationService.success('Book added successfully');
  			$scope.setupBook();
  			$scope.setupBooks();
		});
  	};
  	
  	$scope.setupBooks = function() {
  		httpFactory.findAllBooks().$promise.then(function (result) {
  			$scope.books = result.list;
  			$rootScope.$broadcast('bookEvent', $scope.books.length);
  		});
  	};

  });
