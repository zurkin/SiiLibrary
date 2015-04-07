'use strict';

/**
 * @ngdoc function
 * @name siibraryApp.controller:AddCtrl
 * @description
 * # AddCtrl
 * Controller of the siibraryApp
 */
angular.module('siibraryApp')
  .controller('AddCtrl', function ($scope, httpFactory) {

    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];    

  	$scope.newBook = {id: null, title: '', author: '', description: ''};
  	$scope.addBook = function() {
  		httpFactory.createBook({data: $scope.newBook});
/*  		httpFactory.createBook({
  			title: $scope.newBook.title, 
  			author: $scope.newBook.author, 
  			description: $scope.newBook.description});*/
  	};

  });
