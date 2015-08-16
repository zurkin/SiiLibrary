'use strict';

describe('Service: contextFactory', function () {

  // load the service's module
  beforeEach(module('siibraryApp'));

  // instantiate service
  var contextFactory;
  beforeEach(inject(function (_contextFactory_) {
    contextFactory = _contextFactory_;
  }));

  it('should do something', function () {
    expect(!!contextFactory).toBe(true);
  });

});
