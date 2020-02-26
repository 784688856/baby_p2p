import ax from './axios'


// 发送验证码
export function bandEmail(data) {
    return ax.post('email/sendCode', data).then(res => res.data)
}

// 校验验证码
export function checkEmail(data) {
    return ax.post('email/checkEmail', data).then(res => res.data)
}