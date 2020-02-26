import ax from './axios'


// 提现
export function addWithdraw(withdraw) {
    return ax.post('/user/withdraw/add', withdraw).then(res => res.data)
}
