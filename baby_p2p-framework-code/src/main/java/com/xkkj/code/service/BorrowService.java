package com.xkkj.code.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xkkj.code.object.QueryObject;
import com.xkkj.code.pojo.Borrow;
import com.xkkj.common.vo.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xkkj
 * @since 2020-02-11
 */
public interface BorrowService extends IService<Borrow> {
    IPage<Borrow> query(Page<Borrow> pageParam, QueryObject queryObject);

    Result saveBorrow(Borrow borrow);

    Borrow get(String id);

    Integer audit(String borrowId, Integer borrowState);
}
