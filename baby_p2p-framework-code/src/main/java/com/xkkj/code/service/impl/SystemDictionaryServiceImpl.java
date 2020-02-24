package com.xkkj.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xkkj.code.pojo.SystemDictionary;
import com.xkkj.code.mapper.SystemDictionaryMapper;
import com.xkkj.code.query.DictQuery;
import com.xkkj.code.service.SystemDictionaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class SystemDictionaryServiceImpl extends ServiceImpl<SystemDictionaryMapper, SystemDictionary> implements SystemDictionaryService {

    @Override
    public IPage<SystemDictionary> query(Page<SystemDictionary> page, DictQuery dictQuery) {
        QueryWrapper<SystemDictionary> queryWrapper=new QueryWrapper<>();
        // 根据时间排序
        queryWrapper.orderByDesc("create_time");
        if (StringUtils.isNotBlank(dictQuery.getKeyword())) {
            queryWrapper.gt("name", dictQuery.getKeyword());
        }
        return baseMapper.selectPage(page,queryWrapper);
    }
}
