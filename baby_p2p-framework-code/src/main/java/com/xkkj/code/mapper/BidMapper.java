package com.xkkj.code.mapper;

import com.xkkj.code.pojo.Bid;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xkkj
 * @since 2020-02-11
 */
@Repository
@Mapper
public interface BidMapper extends BaseMapper<Bid> {
    //根据借款标id查询投资列表信息
    List<Bid> getByBorrowId(String borrowId);
    //根据借款标id和投资人id查询是否有投资数据
    Bid selectByborrowIdAndbidUserId(String borrowId,String bidUserId);
}
