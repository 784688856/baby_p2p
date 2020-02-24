package com.xkkj.code.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xkkj.code.pojo.SystemDictionary;
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
public interface SystemDictionaryService extends IService<SystemDictionary> {

    /**
     * 分页查询字典组信息
     * @param page
     * @param dictQuery
     * @return
     */
    IPage<SystemDictionary> query(Page<SystemDictionary> page, DictQuery dictQuery);
}
