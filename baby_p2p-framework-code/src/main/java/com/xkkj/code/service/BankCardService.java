package com.xkkj.code.service;

import com.xkkj.code.pojo.BankCard;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xkkj
 * @since 2020-02-11
 */
public interface BankCardService extends IService<BankCard> {

    // TODO yb 专区
    BankCard getInfoByUserId(String uid);
    // TODO yb 专区
}
