import { setToken, getToken } from '@/utils/auth'
export const actions = {
  nuxtServerInit(store, { req, res }) {

    // 通过utils下的cookie工具类获取不到cookie
    let cookiestr = req.headers.cookie
    let cookie = ''
    if (cookiestr) {
      var arr = cookiestr.split("=");
      cookie = arr[1]
    }
    if (cookie) {
      store.commit('token/SET_TOKEN', cookie)
      console.log(store.state.token)
    }
    const initAppData = [
      store.dispatch('user/getUserInfo')
    ]
    return Promise.all(initAppData)
  }
};


