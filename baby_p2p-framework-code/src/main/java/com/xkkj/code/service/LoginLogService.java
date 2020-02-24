package com.xkkj.code.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xkkj.code.pojo.LoginLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xkkj.code.query.LoginLogQuery;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xkkj
 * @since 2020-02-11
 */
public interface LoginLogService extends IService<LoginLog> {

    /**
     * xh
     * 查询登录日志分页
     * @param pageParam
     * @param loginLogQuery
     * @return
     */
    IPage<LoginLog> query(Page<LoginLog> pageParam, LoginLogQuery loginLogQuery);
}
