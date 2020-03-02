package com.xkkj.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xkkj.code.mapper.BorrowMapper;
import com.xkkj.code.object.QueryObject;
import com.xkkj.code.pojo.Borrow;
import com.xkkj.code.service.BorrowService;
import com.xkkj.common.vo.Result;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xkkj
 * @since 2020-02-11
 */
@Service
public class BorrowServiceImpl extends ServiceImpl<BorrowMapper, Borrow> implements BorrowService {

    @Override
    public IPage<Borrow> query(Page<Borrow> pageParam, QueryObject queryObject) {
        //条件
        QueryWrapper<Borrow> queryWrapper = new QueryWrapper<>();
        String beginDate = queryObject.getBeginDate();
        if (beginDate != null){
            queryWrapper.gt("create_time",beginDate);
        }
        String endDate = queryObject.getEndDate();
        if (endDate != null){
            queryWrapper.lt("create_time",endDate);
        }
        List borrowStates = queryObject.getBorrowStates();
        Integer state = Integer.parseInt(borrowStates.get(0).toString());
        if (borrowStates != null &&  state != -1){
            queryWrapper.like("borrow_state",state);
        }
        //根据时间排序
        queryWrapper.orderByDesc("publish_time");
        //分页
        IPage<Borrow> pages = baseMapper.selectPage(pageParam, queryWrapper);
        return pages;
    }

    @Override
    public Result saveBorrow(Borrow borrow) {
        int insert = baseMapper.insert(borrow);
        if (insert>0){
            //设置借款状态为待审核状态（“0”）
            borrow.setBorrowState(0);
            return Result.ok();
        }
        return Result.error();
    }

    @Override
    public Borrow get(String id) {
        return baseMapper.selectById(id);
    }

    @Override
    public Integer audit(String borrowId,Integer borrowState) {
        Borrow borrow = baseMapper.selectById(borrowId);
        borrow.setBorrowState(borrowState);
        return baseMapper.updateById(borrow);
    }

}
