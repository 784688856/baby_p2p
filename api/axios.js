import axios from 'axios'
const ax = axios.create({
  baseURL: 'http://localhost:8002'
})

// 拦截器
ax.interceptors.request.use(
  config => {
    // config.params = config.params || {}
    // config.params.t = Date.parse(new Date()) / 1000
    // 每次请求发送之前做的事情
    // if (store.getters.token) {
    //   config.headers['Authorization'] = `Bearer ${getToken()}` // 让每个请求携带token-- ['X-Token']为自定义key 请根据实际情况自行修改
    // }

    return config
  },
  error => {
    return Promise.reject(error);
  }
);

ax.interceptors.response.use(
  response => {
    return response;
  },
  error => {
    if (!error.response) {
      // 请求超时状态
      if (error.message.includes('timeout')) {
        console.log('超时了');
        if (window) {
          window.alert('请求超时，请检查网络是否连接正常');
        }
      } else {
        // 可以展示断网组件
        console.log('断网了');
        if (window) {
          window.alert('请求超时，请检查网络是否连接正常');
        }
      }
    }
    return Promise.reject(error);
  }
);
export default ax;
