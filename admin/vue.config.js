module.exports = {
    publicPath: process.env.NODE_ENV === 'production'
        ? '/admin/'
        : '/',
    devServer:{
        port:8011,
        proxy: 'http://localhost:8010'
    }
}
