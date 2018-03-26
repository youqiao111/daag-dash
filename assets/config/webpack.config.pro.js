const nodeEnv = process.env.NODE_ENV = 'production';
const webpack = require('webpack')
const merge = require('webpack-merge')
const entry = require('./webpack.entry.js')
const ExtractTextPlugin = require('extract-text-webpack-plugin')
const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin
const UglifyJSPlugin = require('uglifyjs-webpack-plugin')


let plugins = [
    new ExtractTextPlugin({
        filename: 'styles/[name].css'
    }),
    new webpack.LoaderOptionsPlugin({
        minimize: true,
        debug: false
    }),
    new BundleAnalyzerPlugin({
        generateStatsFile: true
    }),
    new UglifyJSPlugin(),
]


module.exports = merge(entry.webpack, {
    devtool: 'source-map',
    plugins,
    module: {
        rules: [{
            test: /\.(less|css)$/,
            use: ExtractTextPlugin.extract({
                fallback: 'style-loader',
                use: ['css-loader', 'less-loader']
            })
        }]
    }
})
