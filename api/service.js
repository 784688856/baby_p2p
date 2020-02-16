import ax from './axios'


// 登录接口
export function login(data) {
    return ax.post('/user/login', data).then(res => res.data)
}

// 获取用户信息
export function getMember(id) {
    return ax.get(`user/get/${id}`).then(res => res.data)
}


// 上传文件
export function uploadFilesSave(formData, params) {
    return ax.post('UploadFilesSave.aspx', formData, { params: params }).then(res => res.data)
}

// 更新个人信息
export function updateMember(fromData, params) {
    return ax.post('PCApi.aspx?action=UpdateMember', fromData, { params: params }).then(res => res.data)
}

//修改密码
export function updatePassword(params) {
    return ax.get('PCApi.aspx?action=UpPwd', { params: params }).then(res => res.data)
}