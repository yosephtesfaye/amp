var fs = require('fs');
var _ = require('underscore');
var BaseToolView = require('../../base-control/base-control-view');
var Template = fs.readFileSync(path.join(__dirname, '../templates/settings-template.html'));


module.exports = BaseToolView.extend({
  id: 'tool-settings',
  title:  'Settings',
  iconClass:  'ampicon-settings',
  description:  '',

  template:  _.template(Template),

  initialize: function() {
    BaseToolView.prototype.initialize.apply(this);
  },

  render: function() {
    BaseToolView.prototype.render.apply(this);

    // add content
    this.$('.content').html(this.template({title: this.title}));

    return this;
  }
});
