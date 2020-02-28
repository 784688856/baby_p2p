package com.xkkj.code.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xkkj.code.pojo.AccountFlow;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xkkj
 * @since 2020-02-11
 */
public interface AccountFlowService extends IService<AccountFlow> {
    // TODO ----- yb专区，卢本伟专业护法，外来人员不得入侵 -----
    IPage<AccountFlow> getAccountFlowPage(Page<AccountFlow> pageParam, AccountFlow accountFlow);
    // TODO yb专区结束 ----- ----- ----- ----- ----- ----- -
}
