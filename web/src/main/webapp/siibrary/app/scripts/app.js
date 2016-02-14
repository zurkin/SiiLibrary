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
  
	.run(function($rootScope, $templateCache) {
	    $rootScope.$on('$routeChangeStart', function(event, next, current) {
	        if (typeof(current) !== 'undefined') {
	            $templateCache.remove(current.templateUrl);
	        }
	    });
	})
  
  .config(function ($httpProvider, $routeProvider) {
      $httpProvider.interceptors.push(function ($q, $location, $window, notificationService) {
          return {
              'response': function (response) {
            	  var responseHeaders;
            	  if (typeof(response) !== 'undefined' && response) {
            		  responseHeaders = response.headers();
            	  }
            	  var test = typeof(responseHeaders) !== 'undefined' && responseHeaders && typeof(responseHeaders['content-type']) !== 'undefined' && responseHeaders['content-type'] && responseHeaders['content-type'].indexOf('text/html') !== -1 && typeof(response.data) !== 'undefined' && response.data && response.data.indexOf('<meta name="unauthorized" content="true">') !== -1;
                  if (test) {
//                	  alert('responseRedirect');
                	  $window.location.reload();
                	  return $q.reject(response);
                  }
                  return response;
              },
              'responseError': function (rejection) {
            	  if (typeof(rejection) !== 'undefined' && rejection) {
            		  notificationService.error(rejection.statusText);
            	  }
            	  return $q.reject(rejection);
              }
          };
      });
      /* jshint ignore:end */
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
