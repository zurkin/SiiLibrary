'use strict';

/**
 * @ngdoc directive
 * @name siibraryApp.directive:reservations
 * @description
 * # reservations
 */
angular.module('siibraryApp')
  .directive('reservations', function () {
	  return {
		restrict: 'EA',
		scope: {
			'ngReservations': '=',
			'ngTab': '=',
			'ngRentAction': '&',
			'ngReleaseAction': '&'
		},
		templateUrl: 'views/reservations.html'
	  };
  });
