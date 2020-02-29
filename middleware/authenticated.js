import service from '~/api'
// import { setToken, getToken } from '@/utils/auth' 此种方法获取token不管用，这是服务端，没有window对象，只能通过req获取cookie

// 此中间件配置了nuxt.config.js的router中了，这是全局的，所有的路由组件只要变化了都会触发此中间件
// 如果想给单个组件配置路由拦截，则需要在组件中引用middleware
export default function ({ store, route, redirect, req }) {
  const whiteList = ['/home', '/home/investment', '/register'] // 免登白名单（放行首页 我要投资等路由）

  if (route.path == '/') {
    return redirect('/home')
  }

  // 没有用户信息就表示没有登录，除了白名单以外的路由，如果没有登录就跳到登录页面
  if (store.state.user.userInfo == null) {
    if (route.path != '/login' && whiteList.indexOf(route.path) === -1) {
      // alert('没登陆就想看，想多了！')
      return redirect('/login')
    }
  }

  // 此中间件浏览器刷新服务器执行，路由跳转为客户端执行 ，作用就是判断用户登录状态
  // 获取token和用户的信息为什么不在此声明，要交给nuxtServerInit方法了，理由：代码好看些，每个js干自己的事，降低耦合

  // 这儿还需要将token实时的传给后端来验证当前token是否有效，只有有效，路由跳转或者刷新才能让他访问相应的网页
  // 可以前端验证token时效，或者后端通过jwt类型的token验证时效，或者可以两种一起写，这儿为了方便没有判断

  // 否则就需要跳到登录页

}
