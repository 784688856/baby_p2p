package com.xkkj.code.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xkkj.code.pojo.SystemDictionaryItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xkkj.code.query.DictQuery;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xkkj
 * @since 2020-02-11
 */
public interface SystemDictionaryItemService extends IService<SystemDictionaryItem> {

    /**
     * 分页查询字典项数据
     * @param page
     * @param dictQuery
     * @return
     */
    IPage<SystemDictionaryItem> query(Page<SystemDictionaryItem> page, DictQuery dictQuery);
}
