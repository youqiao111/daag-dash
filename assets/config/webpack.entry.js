console.log('process.env.NODE_ENV = ' + process.env.NODE_ENV);
const path = require('path')
const webpack = require('webpack')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const env = require('./env.js')[process.env.NODE_ENV];
env['process.env.NODE_ENV'] = `"${process.env.NODE_ENV}"`;
console.log('NODE_ENV:');
console.log(env);
/**
 * webpack entry list
 *  title : webpage title
 *  file :  entry file
 */
const files = {};
files['app'] = { title: 'title', file: ['index.jsx'] };
files['app1'] = { title: 'title 1', file: ['index1.jsx'] };
//local webpackserver setting 
module.exports.webpackserver = {
    host: "localhost",
    port: 2222,
};
/* DO NOT modify */
//to webpack format
const entry =
    {
        vendor: ['react',
            'react-dom',
            'jquery',
            //'bootstrap'    
        ],
    };
for (const key in files) {
    const ent = files[key];
    entry[key] = ent.file;
}
//base plugin
const plugin = [
    new webpack.optimize.CommonsChunkPlugin({
        name: 'vendor'
    }),
    new webpack.ProvidePlugin({
        $: 'jquery',
        jQuery: 'jquery',
        'window.jQuery': 'jquery'
    }),
    new webpack.DefinePlugin(env),
]
for (const key in entry) {
    if (key === 'vendor') continue;
    plugin.push(new HtmlWebpackPlugin({
        filename: `../build/${key}.html`,
        template: '../public/index.html',
        hash: true,
        title: files[key].title,
        chunks: ['vendor', key],
        minify: {
            removeComments: true,
            collapseWhitespace: false
        }
    }));
}
module.exports.webpack = {
    context: path.resolve(__dirname, '../src'),
    entry: entry,
    output: {
        path: path.join(__dirname, '../build'),
        publicPath: '/', //absolute path
        filename: 'js/[name].js',
        chunkFilename: 'js/[name].js'
    },
    plugins: plugin,
    resolve: {
        extensions: ['.js', '.jsx', '.less', '.css'],
        modules: [
            path.resolve(__dirname, '../node_modules'),
            path.join(__dirname, '../src')
        ],
        alias: {
            'actions': path.resolve(__dirname, '../src/actions'),
            'components': path.resolve(__dirname, '../src/components'),
            'containers': path.resolve(__dirname, '../src/containers'),
            'reducers': path.resolve(__dirname, '../src/reducers'),
            'utils': path.resolve(__dirname, '../src/utils')
        }
    },
    module: {
        rules: [{
            test: /\.jsx$/,
            exclude: /(node_modules|bower_components)/,
            use: 'babel-loader'
        }, {
            test: /\.(png|jpe?g|gif|svg|eot|ttf|woff|woff2)$/,
            use: ['file-loader?limit=1000&name=files/[md5:hash:base64:10].[ext]']
        }]
    }
}