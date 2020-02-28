package com.xkkj.code.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xkkj.code.pojo.RepaymentDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xkkj.code.query.RepaymentQuery;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xkkj
 * @since 2020-02-11
 */
public interface RepaymentDetailService extends IService<RepaymentDetail> {

    //分页查询收款明细
    IPage<RepaymentDetail> getQuery(RepaymentQuery repaymentQuery);
}
