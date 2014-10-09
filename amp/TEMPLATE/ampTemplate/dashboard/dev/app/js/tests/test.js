require('../ugly/lib-load-hacks');
var _ = require('underscore');
var assert = require('chai').assert;

var App = require('../app/app-class');


describe('Dashboards', function() {
  describe('initialization', function() {
    it('should not throw errors during initialization', function() {
      var app = new App('#test-frame');
      assert.notProperty(app, 'err', app.err);
    });
    it('should not throw errors during render', function() {
      var app = new App('#test-frame');
      if (_(app).has('err')) {
        assert.fail('has error', 'not having an error', 'app failed initialization, could not test render.');
        return;
      }
      app.render();
      assert.notProperty(app, 'err', app.err);
    });
  });
});
