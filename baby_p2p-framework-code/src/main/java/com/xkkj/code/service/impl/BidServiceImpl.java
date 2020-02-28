package com.xkkj.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xkkj.code.pojo.Bid;
import com.xkkj.code.mapper.BidMapper;
import com.xkkj.code.pojo.Borrow;
import com.xkkj.code.pojo.UserWallet;
import com.xkkj.code.query.BidQuery;
import com.xkkj.code.service.BidService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xkkj.code.service.BorrowService;
import com.xkkj.code.service.UserWalletService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
public class BidServiceImpl extends ServiceImpl<BidMapper, Bid> implements BidService {
    @Autowired
    BidMapper bidMapper;
    @Autowired
    BorrowService borrowService;
    @Autowired
    UserWalletService userWalletService;

    /**
     * 投资明细分页查询
     * @param bidQuery
     * @return
     */
    @Override
    public IPage<Bid> page(BidQuery bidQuery) {
        //当前页数，每页个数
        Page<Bid> page = new Page<>(bidQuery.getCurrentPage(),bidQuery.getPageSize());
        //条件
        QueryWrapper<Bid> queryWrapper = new QueryWrapper<>();
        // 根据时间排序
        queryWrapper.orderByDesc("create_time");
        //开始时间
        String beginDate=bidQuery.getBeginDate();
        //结束时间
        String endDate=bidQuery.getEndDate();
        //用户id
        String userId=bidQuery.getUserId();
        //借款状态码
        Integer borrowStates = bidQuery.getBorrowStates();
        if (StringUtils.isNotBlank(beginDate)) {
            queryWrapper.gt("create_time", beginDate);
        }
        if (StringUtils.isNotBlank(beginDate)) {
            queryWrapper.lt("create_time", endDate);
        }
        if (StringUtils.isNotEmpty(userId)) {
            queryWrapper.like("bid_user_id", userId);
        }
        if (borrowStates!=null){
            queryWrapper.eq("borrow_state",borrowStates);
        }
        IPage<Bid> bidIPage = bidMapper.selectPage(page, queryWrapper);
        return bidIPage;
    }

    /**
     * 查询标的投标列表信息
     * @param borrowId
     * @return
     */
    @Override
    public List<Bid> getByBorrowId(String borrowId) {
        return bidMapper.getByBorrowId(borrowId);
    }

    /**
     *发起投标
     * @param bidUserId 投资人id
     * @param bidUsername 投资人名称
     * @param borrowId 借款标id
     * @param bidAmount 投资金额
     */
    @Override
    public void add(String bidUserId, String bidUsername, String borrowId, Integer bidAmount) {
        //在前端判断金额是否符合要求(大于50且是50的倍数且小于等于借款金额)
        //1.查询该借款标的信息，和用户金额(UserWallet)
        Borrow borrow = new Borrow();//调用查询接口(borrowId)
        UserWallet userWallet = new UserWallet();//调用查询接口(bidUserId)
        if ((borrow.getBorrowAmount()-borrow.getCurrentBidAmount())>=bidAmount&&bidAmount<=userWallet.getAvailableAmount()){
            //2.判断是否是同一投资人在同一借款标上投资
            Bid bid = bidMapper.selectByborrowIdAndbidUserId(borrowId, bidUserId);
            if (bid!=null){//是：在此基础上修改投资表(bid)金额、投标时间
                bid.setBidAmount(bidAmount);
                bid.setBidTime(new Date());
                bidMapper.updateById(bid);
            }else{//否：新建bid表数据
                Bid bid1 = new Bid();
                bid1.setId(borrow.getId()+bidUserId);
                bid1.setBorrowId(borrow.getId());
                bid1.setBorrowTitle(borrow.getTitle());
                bid1.setBidAmount(bidAmount);
                bid1.setBidInterest(null);//需要计算
                bid1.setYearRate(borrow.getYearRate());
                bid1.setBorrowState(borrow.getBorrowState());
                bid1.setBidUserId(bidUserId);
                bid1.setBidUsername(bidUsername);
                bid1.setBidTime(new Date());
                bid1.setCreateTime(new Date());
                bidMapper.insert(bid1);
            }
            /*3.修改借款表(borrow)的以投资数量(bid_num)加1、当前以投资金额(current_bid_amount)，
                以bidAmount为条件调用借款表修改接口*/
//            borrow.setBidNum(borrow.getBidNum()+1);
//            borrow.setCurrentBidAmount(borrow.getCurrentBidAmount()+bidAmount);
//            borrowService.updateById(borrow);
            /*4.修改用户可用金额       需要提供接口*/
//            userWallet.setAvailableAmount(userWallet.getAvailableAmount()-bidAmount);
//            userWalletService.updateById(userWallet);

        }else{
            System.out.println("头多钱了，报错");
        }




    }

    /**
     * 当借款标流标或状态发生变化时，改变标(bid表)的借款状态和用户可用金额
     * @param borrowId
     * @param borrowState
     */
    @Override
    public void updateByborrowState(String borrowId,Integer borrowState) {
        //修改查询出来的每一条bid数据
        List<Bid> bidlist = bidMapper.getByBorrowId(borrowId);
        for (Bid bid:bidlist) {
            //判断当前借款状态是否流标
//            if (borrowState==21){
//                UserWallet userWallet = userWalletService.getById(bid.getBidUserId());
//                userWallet.setAvailableAmount(bid.getBidAmount()+userWallet.getAvailableAmount());
//                userWalletService.saveOrUpdate(userWallet);
//            }
            bid.setBorrowState(borrowState);
            bidMapper.updateById(bid);
        }



    }

}
