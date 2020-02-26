import ax from './axios'


// 登录接口
export function login(data) {
    return ax.post('/user/login', data).then(res => res.data)
}

// 获取用户基本信息
export function getMember(id) {
    return ax.get(`user/get/${id}`).then(res => res.data)
}

// 获取用户详细信息
export function getDetailInfo(accountId) {
    return ax.get(`user/userinfo/get//${accountId}`).then(res => res.data)
}

// 获取用户钱包信息
export function getWallet(id) {
    return ax.get(`user/wallet/get/${id}`).then(res => res.data)
}

// 上传文件
export function uploadFilesSave(formData) {
    return ax.post('/admin/oss/file/upload/', formData).then(res => res.data)
}

// 注册
export function registerMember(userAccount) {
    return ax.post('/user/register',userAccount).then(res => res.data)
}

// 更新个人信息
export function updateMember(userInfo) {
    return ax.put('/user/userinfo/update',userInfo).then(res => res.data)
}

//修改密码
export function updatePassword(params) {
    return ax.get('PCApi.aspx?action=UpPwd', { params: params }).then(res => res.data)
}