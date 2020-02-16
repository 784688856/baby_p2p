import { getToken } from '@/utils/auth'
import service from '~/api'

export default function ({ store, route, redirect, req }) {
  const whiteList = ['/home'] // 免登白名单

  if (process.server) {
    const explorer = req.headers['user-agent']
    // 浏览器版本兼容问题的设置
    if (explorer.indexOf("Trident") > -1) {
      if (!(explorer.indexOf("rv:11") > -1 || explorer.indexOf("rv:10") > -1)) {
        return redirect('/safe')
      }
    }
  }

  // 注意：这是当路由有变化才会触发中间件,当第一次加载首页时，这个中间件是不会真正执行的，所以需要在nuxtServerInit获取cookie
  // 判断cookie是否存了token
  if (getToken()) {
    // 判断是否有用户信息
    if (store.state.user.userInfo==null) {
      // 获取用户信息
      service.getMember(getToken()).then(mRes => {
        if (mRes.success) {
          // 存入用户信息到vuex
          store.commit('user/setUserInfo', mRes.data)
        }
      })
    }
  }

}
