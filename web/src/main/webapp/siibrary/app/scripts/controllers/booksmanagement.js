'use strict';

/**
 * @ngdoc function
 * @name siibraryApp.controller:BooksmanagementCtrl
 * @description
 * # BooksmanagementCtrl
 * Controller of the siibraryApp
 */
angular.module('siibraryApp')
  .controller('BooksmanagementCtrl', function ($scope, httpFactory) {
	  
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
    
    $scope.test = [
                   { 
                	   id: 1, 
                	   person: {
                		   nickName: 'Nickname 1',
                		   email: 'Email 1'
                	   },
                	   book: {
                		   title: 'Test', 
                		   description: 'Opis ksiazki' 
                	   }
                   },
                   { 
                	   id: 2, 
                	   person: {
                		   nickName: 'Nickname 2',
                		   email: 'Email 2'
                	   },
                	   book: {
                		   title: 'Test 2', 
                		   description: 'Opis ksiazki 2' 
                	   }
                   }
    ];
    
    $scope.tabs = [
                   { title:'Wydania', content:'Wydaj', data: $scope.reservations },
                   { title:'Zwroty', content:'Zwróć', data: $scope.test },
                   { title:'Przeterminowane', content:'Zwróć' }
                 ];
    
	$scope.setupReservations = function() {
		httpFactory.findAllReservations().$promise.then(function (result) {
			$scope.reservations = result.list;
			$scope.tabs[0].data = $scope.reservations;
		});
	};
	$scope.setupReservations();    
    
  });