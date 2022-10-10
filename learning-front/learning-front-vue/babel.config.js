module.exports = {
  presets: [
      [
          "@babel/preset-env",
        {
          "targets": {
            "chrome": "49"
          },
          "modules": false,
          "useBuiltIns": "usage",
          "corejs": "3.6.5"
        }
      ],
    '@vue/cli-plugin-babel/preset'
  ]
}
