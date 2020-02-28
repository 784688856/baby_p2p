package com.xkkj.code.service;

import com.xkkj.code.pojo.UserAccount;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xkkj
 * @since 2020-02-11
 */
public interface UserAccountService extends IService<UserAccount> {
    // TODO ----- yb专区，卢本伟专业护法，外来人员不得入侵 -----


    UserAccount checkLogin(UserAccount userAccount);




    // TODO yb专区结束 ----- ----- ----- ----- ----- ----- -

}
