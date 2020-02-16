/**
 * @file token状态
 * @module store/cart
 * @author wqlthink
 */
import axios from 'axios'

export const state = () => {
    return {
        token: 'fd4ebdfe5bf8259f2294eb9f44dbff2f',
    }
}

export const mutations = {
    SET_TOKEN(state, data) {
        if (data) {
            // data 就是cookie，cookie是后端传来的凭证，为什么不直接将凭证给vuex，因为vuex刷新凭证就没了
            // 没什么要使用vuex，每个页面也可以直接获取cookie，因为组件之间传值没有vuex方便
            state.token = data
        }
    },
    LOGIN_OUT_TOKEN(state) {
        state.token = 'fd4ebdfe5bf8259f2294eb9f44dbff2f'
    }
}

export const actions = {

    async loginSucSetToken({ commit }, { userToken }) {
        try {
            commit('SET_TOKEN', userToken)
        } catch (error) {
            if (error.response && error.response.status === 401) {
                throw new Error('Bad credentials')
            }
            throw error
        }
    },
    async LOGIN_OUT_TOKEN({ commit }) {
        try {
            // 告诉后端删除凭证
            // let res = await axios.post('/token/logout')
            commit('LOGIN_OUT_TOKEN')
        } catch (error) {
            if (error.response && error.response.status === 401) {
                throw new Error('Bad credentials')
            }
            throw error
        }
    },
}
