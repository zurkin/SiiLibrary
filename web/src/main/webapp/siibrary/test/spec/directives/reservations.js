'use strict';

describe('Directive: reservations', function () {

  // load the directive's module
  beforeEach(module('siibraryApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<reservations></reservations>');
    element = $compile(element)(scope);
    //expect(element.text()).toBe('this is the reservations directive');
  }));
});
