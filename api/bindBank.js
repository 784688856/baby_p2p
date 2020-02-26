import ax from './axios'


// 绑定银行卡信息
export function add(bankCard) {
    return ax.post('/user/bankcard/add', bankCard).then(res => res.data)
}



// 查询银行卡信息
export function getInfo(accountId) {
    return ax.get(`/user/bankcard/get/${accountId}`).then(res => res.data)
}
