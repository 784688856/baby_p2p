import { setToken, getToken } from '@/utils/auth'
export const actions = {
  nuxtServerInit(store, { req, res }) {

    // 通过utils下的cookie工具类获取不到cookie，原因是js-cookie是浏览器对象才能调用的（window对象）
    // 这里是服务器，没有浏览器对象（window），所以不能使用js-cookie
    // console.log(getToken())


    // 可以拿到
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


