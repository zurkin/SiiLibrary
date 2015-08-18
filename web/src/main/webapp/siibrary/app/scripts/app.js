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
    'ui.bootstrap',
    'jlareau.pnotify'
  ])
  
  .config(function ($httpProvider, $routeProvider) {

      $httpProvider.interceptors.push(function ($q, $location, notificationService) {
          return {
              'responseError': function (rejection) {
            	  notificationService.error(rejection.statusText);
                  return $q.reject(rejection);
              }
          };
      });
	  
	  
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/add', {
        templateUrl: 'views/add.html',
        controller: 'AddCtrl'
      })
      .when('/booksManagement', {
        templateUrl: 'views/booksManagement.html',
        controller: 'BooksmanagementCtrl'
      })
      .when('/lentBooks', {
        templateUrl: 'views/lentBooks.html',
        controller: 'LentBooksCtrl'
      })
      .when('/returnBooks', {
        templateUrl: 'views/returnBooks.html',
        controller: 'ReturnBooksCtrl'
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
        templateUrl: 'popover-html-unsafe-popup.html'
      };
  })
    
  .directive('popoverHtmlUnsafe', [ '$tooltip', function($tooltip) {
	  return $tooltip('popoverHtmlUnsafe', 'popover', 'click');
  }]);
