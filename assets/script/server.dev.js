const path = require('path');
const webpack = require('webpack')
const WebpackDevServer = require('webpack-dev-server')
const config = require('../config/webpack.config.dev.js')
const errorOverlayMiddleware = require('react-error-overlay/middleware')
const webpackServerConfig = require('../config/webpack.entry.js').webpackserver
const proxy = require('http-proxy-middleware')

new WebpackDevServer(webpack(config), {
    contentBase: './build/',
    hot: true,
    compress: false,
    historyApiFallback: true,
    watchOptions: {
        ignored: /node_modules/
    },
    stats: {
        modules: false,
        chunks: false,
        colors: true
    },
    setup(app) {
        app.use(errorOverlayMiddleware())
        if (process.env.NODE_ENV !== 'production' && webpackServerConfig.proxypass) {
            for (const key in webpackServerConfig.proxypass) {
                const element = webpackServerConfig.proxypass[key];
                app.use(key, proxy(element));
            }

        }
    }
}).listen(webpackServerConfig.port, webpackServerConfig.host, function (err) {
    if (err) {
        return console.log(err);
    }
    console.log(`Listening at http://${webpackServerConfig.host}:${webpackServerConfig.port}/`);
});