'use strict';

describe('Controller: BooksmanagementCtrl', function () {

  // load the controller's module
  beforeEach(module('siibraryApp'));

  var BooksmanagementCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    BooksmanagementCtrl = $controller('BooksmanagementCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
