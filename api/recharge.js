import ax from './axios'


// 充值
export function add(recharge) {
    return ax.post('/system/recharge/add', recharge).then(res => res.data)
}



// 查询银行卡信息
export function getInfo(accountId) {
    return ax.get(`/user/bankcard/get/${accountId}`).then(res => res.data)
}

// 分页银行卡信息
export function page(rechargeQuery) {
    return ax.get(`/finance/recharge/query/`,{params:rechargeQuery}).then(res => res.data)
}
