'use strict';

/**
 * @ngdoc service
 * @name siibraryApp.httpFactory
 * @description
 * # httpFactory
 * Factory in the siibraryApp.
 */
angular.module('siibraryApp')
  .factory('httpFactory', function ($resource) {

    return $resource(null, null, {
      findAllBooks: {
        url: 'rest/books',
        method: 'GET',
        transformResponse: function (data) {
        	return {
        		list: angular.fromJson(data)
        	};
        }
      },
      createBook: {
          url: 'rest/books/:data',
          method: 'POST',
          headers: {
              'Content-Type': 'application/json'
          }
        }      
    });

  });
