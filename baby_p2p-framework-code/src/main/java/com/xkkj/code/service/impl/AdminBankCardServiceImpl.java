package com.xkkj.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xkkj.code.pojo.AdminBankCard;
import com.xkkj.code.mapper.AdminBankCardMapper;
import com.xkkj.code.query.AdminBankCardQuery;
import com.xkkj.code.service.AdminBankCardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xkkj
 * @since 2020-02-25
 */
@Service
public class AdminBankCardServiceImpl extends ServiceImpl<AdminBankCardMapper, AdminBankCard> implements AdminBankCardService {

    @Override
    public IPage<AdminBankCard> query(Page<AdminBankCard> pageParam, AdminBankCardQuery adminBankCardQuery) {
        QueryWrapper<AdminBankCard> queryWrapper=new QueryWrapper<>();
        // 根据时间排序
        queryWrapper.orderByDesc("create_time");
        if (StringUtils.isNotBlank(adminBankCardQuery.getKeyword())) {
            queryWrapper.like("card_number", adminBankCardQuery.getKeyword());
        }
        return baseMapper.selectPage(pageParam,queryWrapper);
    }
}
