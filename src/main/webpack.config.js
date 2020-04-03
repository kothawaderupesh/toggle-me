var path = require('path');


const MiniCssExtractPlugin = require('mini-css-extract-plugin');

module.exports = {
    entry: './src/index.js',
    devtool: 'sourcemaps',
    cache: true,
    mode: 'development',
    output: {
        path: __dirname,
        filename: 'public/static/built/bundle.js'
    },
    plugins: [
        new MiniCssExtractPlugin({
            filename: 'public/static/built/[name].css',
            chunkFilename: 'public/static/built/[id].css'
        }),
    ]

    ,
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: [/(node_modules)/,/css/],
                use: [{
                    loader: 'babel-loader',
                    options: {
                        presets: ["@babel/preset-env", "@babel/preset-react"]
                    }
                }]
            },
            {
                test: /\.css$/,
                use: [
                    {
                        loader: MiniCssExtractPlugin.loader,
                        options: {
                            publicPath: (resourcePath, context) => {
                                // publicPath is the relative path of the resource to the context
                                // e.g. for ./css/admin/main.css the publicPath will be ../../
                                // while for ./css/main.css the publicPath will be ../
                                return path.relative(path.dirname(resourcePath), context) + '/';
                            },
                        },
                    },
                    'css-loader',
                ]
            }

        ]
    }
};