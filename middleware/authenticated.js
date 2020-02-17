import { getToken } from '@/utils/auth'
import service from '~/api'

// 此中间件配置了nuxt.config.js的router中了，这是全局的，所有的路由组件只要变化了都会触发此中间件
// 如果想给单个组件配置路由拦截，则需要在组件中引用middleware
export default function ({ store, route, redirect, req }) {
  const whiteList = ['/home', '/home/investment'] // 免登白名单（放行首页 我要投资等路由）

  // 没有用户信息就表示没有登录，除了白名单以外的路由，如果没有登录就跳到登录页面
  if (store.state.user.userInfo == null) {
    if (route.path != '/login' && whiteList.indexOf(route.path) === -1) {
      alert('没登陆就想看，想多了！')
      return redirect('/login')
    }
  }


  // 注意：这是当路由有变化才会触发中间件（客户端，浏览器输出打印信息）,当第一次加载首页时，这个中间件是server执行的（vscode终端可以看到打印信息），
  console.log('我执行了')
  // 判断cookie是否存了token
  if (getToken()) {
    // 判断是否有用户信息
    if (store.state.user.userInfo == null) {
      // 获取用户信息
      service.getMember(getToken()).then(mRes => {
        console.log('正在尝试获取用户信息')
        if (mRes.success) {
          // 存入用户信息到vuex
          store.commit('user/setUserInfo', mRes.data)
        }
      })
    }
  }

}
