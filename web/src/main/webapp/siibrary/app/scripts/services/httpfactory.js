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
      },
      reserveBook: {
          url: 'rest/rent/reserve/:data',
          method: 'POST',
          headers: {
              'Content-Type': 'application/json'
          }
      },
      rentBook: {
          url: 'rest/rent/rent/:data',
          method: 'POST',
          headers: {
              'Content-Type': 'application/json'
          }
      },
      releaseBook: {
          url: 'rest/rent/release/:data',
          method: 'POST',
          headers: {
              'Content-Type': 'application/json'
          }
      },
      findAllReservations: {
		  url: 'rest/rent/reserved',
		  method: 'GET',
		  headers: {
		      'Content-Type': 'application/json'
		  },
		  transformResponse: function (data) {
			  return {
				  list: angular.fromJson(data)
			  };
		  }
	  },
      findAllRentals: {
		  url: 'rest/rent/rented',
		  method: 'GET',
		  headers: {
		      'Content-Type': 'application/json'
		  },
		  transformResponse: function (data) {
			  return {
				  list: angular.fromJson(data)
			  };
		  }
	  }
    });

  });
