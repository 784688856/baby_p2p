package com.xkkj.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xkkj.code.mapper.SystemDictionaryItemMapper;
import com.xkkj.code.pojo.SystemDictionaryItem;
import com.xkkj.code.query.DictQuery;
import com.xkkj.code.service.SystemDictionaryItemService;
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
public class SystemDictionaryItemServiceImpl extends ServiceImpl<SystemDictionaryItemMapper, SystemDictionaryItem> implements SystemDictionaryItemService {

    @Override
    public IPage<SystemDictionaryItem> query(Page<SystemDictionaryItem> page, DictQuery dictQuery) {
        QueryWrapper<SystemDictionaryItem> queryWrapper=new QueryWrapper<>();
        // 根据时间排序
        queryWrapper.orderByDesc("create_time");
        if (StringUtils.isNotBlank(dictQuery.getKeyword())) {
            queryWrapper.like("value", dictQuery.getKeyword());
        }
        if (dictQuery.getParented()!=null) {
            queryWrapper.eq("parent_id", dictQuery.getParented());
        }
        return baseMapper.selectPage(page,queryWrapper);
    }
}
