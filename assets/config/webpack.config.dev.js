const nodeEnv =process.env.NODE_ENV =  'development';
const webpack = require('webpack')
const merge = require('webpack-merge')
const entry = require('./webpack.entry.js')


//hot-loader
const hotpath={};
for (const key in entry.webpack.entry) {
    if (key ==='vendor') continue;
    hotpath[key]=[
        'react-hot-loader/patch',
        `webpack-dev-server/client?http://${entry.webpackserver.host}:${entry.webpackserver.port}`,
        'webpack/hot/only-dev-server',
    ];
};

module.exports = merge(entry.webpack, {
    devtool: 'inline-source-map',
    entry: hotpath,
    plugins: [
        new webpack.HotModuleReplacementPlugin(),
        new webpack.NamedModulesPlugin(),
        new webpack.NoEmitOnErrorsPlugin()
    ],
    module: {
        rules: [{
            test: /\.(less|css)$/,
            use: ['style-loader', 'css-loader', 'less-loader']
        }]
    }
})