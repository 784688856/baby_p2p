module.exports = {
  /*
  ** Headers of the page
  */
  head: {
    title: '宝贝投',
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: '{{escape description }}' }
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }
    ]
  },

  plugins: [
    // ssr: true表示这个插件只在服务端起作用
    { src: '~/plugins/ElementUI', ssr: true },
  ],
  css: [
    'element-ui/lib/theme-chalk/index.css',
    '@/assets/css/font.css',
    '@/assets/css/main.css'
  ],
  router: {
    middleware: ['authenticated']
  },
  /*
  ** Customize the progress bar color
  */
  loading: { color: '#3B8070' },
  /*
  ** Build configuration
  */
  build: {
    // 防止element-ui被多次打包
    vendor: ['element-ui'],
    /*
    ** Run ESLint on save
    */
    extend(config, { isDev, isClient }) {
      if (isDev && isClient) {
        config.module.rules.push({
          enforce: 'pre',
          test: /\.(js|vue)$/,
          loader: 'eslint-loader',
          exclude: /(node_modules)/
        })
      }
    }
  }
}

