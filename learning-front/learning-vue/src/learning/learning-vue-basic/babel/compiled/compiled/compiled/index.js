import _classCallCheck from "/Users/xjj/IdeaProjects/learning-anything/learning-front/learning-vue/node_modules/@babel/runtime/helpers/esm/classCallCheck.js";
import _createClass from "/Users/xjj/IdeaProjects/learning-anything/learning-front/learning-vue/node_modules/@babel/runtime/helpers/esm/createClass.js";
export var Hello = /*#__PURE__*/function () {
  function Hello(name) {
    _classCallCheck(this, Hello);

    this.name = name;
  }

  _createClass(Hello, [{
    key: "say",
    value: function say() {
      return `Hello ${this.name}`;
    }
  }]);

  return Hello;
}();