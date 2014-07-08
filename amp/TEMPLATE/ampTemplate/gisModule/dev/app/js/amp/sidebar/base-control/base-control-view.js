var fs = require('fs');
var _ = require('underscore');
var Backbone = require('backbone');
var Template = fs.readFileSync(path.join(__dirname, '/../templates/base-control-template.html'));


module.exports = Backbone.View.extend({
  /*
   * Base view for all tool views
   *
   * Child views should access parent methods via the prototype:
   *    `BaseToolView.prototype.method.apply(this);`
   */

  className: 'panel sidebar-tool',

  // Required properties to be overridden by child classes
  title: undefined,  // to make falsy in a child, set to null
  iconClass: undefined,
  description: undefined,

  initialize: function() {
    this.baseTemplate = _.template(BaseTemplate);
  },

  render: function() {
    this.$el.html(this.baseTemplate({
      title: this._getFromChild('title'),
      titleID: this._getFromChild('title').replace(/ /g, '').toLowerCase(),
      iconClass: this._getFromChild('iconClass'),
      description: this._getFromChild('description')
    }));
    return this;
  },

  _getFromChild: function(property) {
    var value = this[property];
    if(typeof myVar !== 'undefined') {
      console.error('No value set for property "' + property + '" on ', this);
      return undefined;
    }
    return value;
  }

});
