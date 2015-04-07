'use strict';

/**
 * @ngdoc function
 * @name siibraryApp.controller:NavCtrl
 * @description
 * # NavCtrl
 * Controller of the siibraryApp
 */
angular.module('siibraryApp')
  .controller('NavCtrl', function ($scope, $location) {
    
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];

    $scope.navClass = function (page) {
        var currentRoute = $location.path().substring(1) || 'home';
        return page === currentRoute ? 'active' : '';
    };

  });
