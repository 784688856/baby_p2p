package com.xkkj.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xkkj.code.mapper.UserAccountMapper;
import com.xkkj.code.pojo.UserAccount;
import com.xkkj.code.service.UserAccountService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xkkj
 * @since 2020-02-11
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {
    // TODO ----- yb专区，卢本伟专业护法，外来人员不得入侵 -----


    @Override
    public UserAccount checkLogin(UserAccount userAccount) {
        QueryWrapper<UserAccount> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",userAccount.getUsername()).eq("password",userAccount.getPassword());
        UserAccount user = baseMapper.selectOne(queryWrapper);
        if (user!=null){
            return user;
        }
        return null;
    }


    // TODO yb专区结束 ----- ----- ----- ----- ----- ----- -
}
