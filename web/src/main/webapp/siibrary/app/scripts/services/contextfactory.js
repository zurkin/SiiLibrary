'use strict';

/**
 * @ngdoc service
 * @name siibraryApp.contextFactory
 * @description
 * # contextFactory
 * Factory in the siibraryApp.
 */
angular.module('siibraryApp')
  .factory('contextFactory', function ($resource) {
	  
	  return $resource(null, null, {
	  
		  setupContext: {
	            url: 'rest/context',
	            method: 'GET',
	            headers: {
	                'Content-Type': 'application/json'
	            }
	        }
	    });
  });
