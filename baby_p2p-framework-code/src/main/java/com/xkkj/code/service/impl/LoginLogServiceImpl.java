package com.xkkj.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xkkj.code.mapper.LoginLogMapper;
import com.xkkj.code.pojo.LoginLog;
import com.xkkj.code.query.LoginLogQuery;
import com.xkkj.code.service.LoginLogService;
import org.apache.commons.lang3.StringUtils;
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
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

    @Override
    public IPage<LoginLog> query(Page<LoginLog> pageParam, LoginLogQuery loginLogQuery) {
        // 条件
        QueryWrapper<LoginLog> queryWrapper = new QueryWrapper<>();
        // 根据时间排序
        queryWrapper.orderByDesc("create_time");
        //开始时间
         String beginDate=loginLogQuery.getBeginDate();
        //结束时间
         String endDate=loginLogQuery.getEndDate();
        //登录结果
         Integer loginResult=loginLogQuery.getLoginResult();
        //用户名
         String username=loginLogQuery.getUsername();
        //用户类型
         Integer accountType=loginLogQuery.getAccountType();
        if (StringUtils.isNotBlank(beginDate)) {
            queryWrapper.gt("login_time", beginDate);
        }
        if (StringUtils.isNotBlank(beginDate)) {
            queryWrapper.lt("login_time", endDate);
        }
        if (loginResult==0||loginResult==1) {
            queryWrapper.eq("login_result", loginResult);
        }
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }

        if (accountType==1||accountType==2) {
            queryWrapper.eq("account_type", accountType);
        }
        IPage<LoginLog> loginLogIPage = baseMapper.selectPage(pageParam, queryWrapper);
        return loginLogIPage;
    }
}
