/**
 * @file 用户登录状态信息
 * @module store/user
 * @author wqlthink
 */
import { setLocalStore, getLocalStore, removeLocalStore } from '../utils/storage'
import service from '../api'
import axios from 'axios'
import { Message } from 'element-ui'

export const state = () => {
    return {
        userNameInfo: {
            name: '',
            status: true   //true 为记住用户名  false--忘记用户名
        },
        userInfo: null, //通过cookie去后台获取

    }
}

export const mutations = {
    LOGIN_OUT_USERINFO(state) {
        state.userInfo = null
    },
    setRemenberUserName(state, nameObj) {
        if (nameObj.status) { //true 记住用户名
            state.userNameInfo.name = nameObj.name
            state.userNameInfo.status = nameObj.status
            setLocalStore('rememberUserName', JSON.stringify(state.userNameInfo))
        } else {
            state.userNameInfo.name = ''
            state.userNameInfo.status = nameObj.status
            setLocalStore('rememberUserName', JSON.stringify(state.userNameInfo))
        }
    },
    setOrderSettleInfo(state, data) {
        state.OrderSettleInfo.OrderNO = data.OrderNO
        state.OrderSettleInfo.OrderTotal = data.OrderTotal
    },
    setUserInfo(state, userInfo) {
        console.log('vuex中正在设置uerInfo')
        if (userInfo) {
            state.userInfo = userInfo
            console.log(state.userInfo)
            console.log('vuex设置userInfo结束')

        }
    },
    // 初始化用户名
    initRememberUser(state) {
        if (getLocalStore('rememberUserName')) {
            state.userNameInfo = JSON.parse(getLocalStore('rememberUserName'))
        }
    },

    // 更新用户头像
    updateUserAvatar(state, data) {
        if (state.userInfo) {
            state.userInfo.HeadUrl = data
        }
    },
    // 更新用户信息
    updateUserInfo(state, data) {
        if (state.userInfo) {
            state.userInfo.RealName = data.RealName
            state.userInfo.Sex = data.Sex === '男' ? 1 : 0
            state.userInfo.Birthday = data.Birthday
            state.userInfo.Mobile = data.Mobile
            state.userInfo.Email = data.Email
            state.userInfo.GName = data.GName
            state.userInfo.SKYNO = data.SKYNO
        }
    }
}

export const actions = {

    //设置用户信息到session
    async saveLoginSessionInfo({ commit }, userInfo) {
        try {
            // const { userInfo } = await axios.post('/token/setUserInfo', { userInfo:userInfo })
            let userInfoObj = JSON.parse(userInfo)
            commit('setUserInfo', userInfoObj)
        } catch (error) {
            if (error.response && error.response.status === 401) {
                throw new Error('Bad credentials')
            }
            throw error
        }
    },
    //获取用户信息
    async getUserInfo({ state, commit, rootState }) {
        console.log('正在初始化用户信息')
        if (state.userInfo) {
            return
        }
        let token = rootState.token
            const res = await service.getMember(token.token)
            if (res.success) {
                commit('setUserInfo', res.data)
            }
    },

    //更新用户头像
    async updatedUserInfoAvatar({ state, commit, rootState }, headUrl) {
        if (state.userInfo) {
            let authParams = {
                sign: rootState.token.token,
                userid: rootState.token.userid,
                pwd: rootState.token.pwd
            }
            let dat = JSON.parse(JSON.stringify(state.userInfo))
            dat.HeadUrl = headUrl
            let form = new FormData()
            form.append("json", JSON.stringify(dat))
            service.updateMember(form, authParams).then(res => {
                if (res.state != 0) {
                    Message.error(res.msg)
                } else {
                    commit('updateUserAvatar', headUrl)
                    Message.success('上传成功!')
                }
            })
        }
    },
    // 更新用户信息 
    async updateUserInfo({ state, commit, rootState }, data) {
        if (state.userInfo) {

        }
    }

}



