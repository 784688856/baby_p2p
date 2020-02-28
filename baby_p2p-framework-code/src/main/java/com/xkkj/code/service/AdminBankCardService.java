package com.xkkj.code.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xkkj.code.pojo.AdminBankCard;
import com.xkkj.code.query.AdminBankCardQuery;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xkkj
 * @since 2020-02-25
 */
public interface AdminBankCardService extends IService<AdminBankCard> {

    /**
     * 系统银行卡列表查询
     * @param pageParam
     * @param adminBankCardQuery
     * @return
     */
    IPage<AdminBankCard> query(Page<AdminBankCard> pageParam, AdminBankCardQuery adminBankCardQuery);
}
