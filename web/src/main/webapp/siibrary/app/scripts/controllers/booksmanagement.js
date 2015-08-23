'use strict';

/**
 * @ngdoc function
 * @name siibraryApp.controller:BooksmanagementCtrl
 * @description
 * # BooksmanagementCtrl
 * Controller of the siibraryApp
 */
angular.module('siibraryApp')
  .controller('BooksmanagementCtrl', function ($scope, httpFactory, notificationService) {
	  
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
    
    $scope.tabs = [
                   { title:'Wydania', content:'Wydaj', data: $scope.reservations, rentAction: true },
                   { title:'Zwroty', content:'Zwróć', data: $scope.rentals, releaseAction: true, showStatus: true },
                   { title:'Przeterminowane', data: $scope.expired, content:'Zwróć', showStatus: true }
                 ];
    
	$scope.setupReservations = function() {
		httpFactory.findAllReservations().$promise.then(function (result) {
			$scope.reservations = result.list;
			$scope.tabs[0].data = $scope.reservations;
		});
	};

	$scope.setupRentals = function() {
		httpFactory.findAllRentals().$promise.then(function (result) {
			$scope.rentals = result.list;
			$scope.tabs[1].data = $scope.rentals;
		});
	};
	
	$scope.setupExpired = function() {
		httpFactory.findAllExpired().$promise.then(function (result) {
			$scope.expired = result.list;
			$scope.tabs[2].data = $scope.expired;
		});
	};	
	
	$scope.setup = function() {
		$scope.setupReservations();
		$scope.setupRentals();
		$scope.setupExpired();
	};
	$scope.setup();
	
	$scope.rent = function(reservation) {
		httpFactory.rentBook({data: reservation}).$promise.then(function () {
			notificationService.success('Rent book process completed successfully');
			$scope.setup();
		});		
	};

	$scope.release = function(reservation) {
		httpFactory.releaseBook({data: reservation}).$promise.then(function () {
			notificationService.success('Return book process completed successfully');
			$scope.setup();
		});		
	};
  });