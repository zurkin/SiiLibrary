'use strict';

/**
 * @ngdoc overview
 * @name siibraryApp
 * @description
 * # siibraryApp
 *
 * Main module of the application.
 */
angular
  .module('siibraryApp', [
    'angular-loading-bar',
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ui.bootstrap'
  ])
  
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/add', {
        templateUrl: 'views/add.html',
        controller: 'AddCtrl'
      })
      .when('/contact', {
        templateUrl: 'views/contact.html',
        controller: 'ContactCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  })
  
  .directive('popoverHtmlUnsafePopup', function () {
      return {
        restrict: 'EA',
        replace: true,
        scope: { title: '@', content: '@', placement: '@', animation: '&', isOpen: '&' },
        templateUrl: '/popover-html-unsafe-popup.html'
      };
  })
    
  .directive('popoverHtmlUnsafe', [ '$tooltip', function($tooltip) {
	  return $tooltip('popoverHtmlUnsafe', 'popover', 'click');
  }]);
