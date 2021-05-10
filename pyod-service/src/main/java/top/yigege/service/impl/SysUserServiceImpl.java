package top.yigege.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.yigege.config.WxConfig;
import top.yigege.constant.BusinessFlagEnum;

import top.yigege.constant.ChareOffStatusEnum;
import top.yigege.constant.ConsumeRecordTypeEnum;
import top.yigege.constant.CouponStatusEnum;
import top.yigege.constant.PyodConstant;
import top.yigege.constant.ResultCodeEnum;
import top.yigege.dto.modules.coupon.ChargeOffReqDTO;
import top.yigege.dto.modules.sysUser.AddUserDTO;
import top.yigege.dto.modules.sysUser.MerchantUserLoginReqDTO;
import top.yigege.dto.modules.sysUser.MerchantUserLoginResDTO;
import top.yigege.dto.modules.sysUser.QueryUserPageListDTO;
import top.yigege.dto.modules.userVipCard.GatheringReqDTO;
import top.yigege.exception.BusinessException;
import top.yigege.model.CouponActivity;
import top.yigege.model.CouponDeduction;
import top.yigege.model.Shop;
import top.yigege.model.SysMenu;
import top.yigege.model.SysUser;

import top.yigege.model.User;
import top.yigege.model.UserCoupon;
import top.yigege.model.UserVipCard;
import top.yigege.service.ICouponDeductionService;
import top.yigege.service.IGenerateIDService;
import top.yigege.service.IShopService;
import top.yigege.service.ISysUserService;
import top.yigege.dao.*;
import top.yigege.service.ITokenService;
import top.yigege.service.IUserCouponService;
import top.yigege.service.IUserService;
import top.yigege.service.IUserVipCardService;
import top.yigege.util.PageUtil;
import top.yigege.util.Utils;
import top.yigege.util.WeixinUtil;
import top.yigege.vo.PageBean;
import top.yigege.vo.wx.WxSendMessageDataBean;
import top.yigege.vo.wx.WxValueBean;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static top.yigege.constant.PyodConstant.Common.PARENT_MENU_FLAG;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-23
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Resource
    SysUserMapper sysUserMapper;

    @Autowired
    IGenerateIDService iGenerateIDService;

    @Autowired
    IShopService iShopService;

    @Autowired
    ITokenService iTokenService;

    @Autowired
    IUserCouponService iUserCouponService;

    @Autowired
    ICouponDeductionService iCouponDeductionService;

    @Autowired
    IUserService iUserService;

    @Autowired
    IUserVipCardService iUserVipCardService;

    @Autowired
    WeixinUtil weixinUtil;

    @Override
    public List<SysUser> queryUserByNickname(List<String> nickname) {
        return sysUserMapper.queryUserByNickname(nickname);
    }

    @Override
    public WxConfig queryWxConfigByMerchantId(Long merchantId) {
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper();
        SysUser sysUser = getById(merchantId);
        WxConfig wxConfig = new WxConfig();
        wxConfig.setAppId(sysUser.getWxAppId());
        wxConfig.setSecret(sysUser.getWxSecret());
        wxConfig.setMchId(sysUser.getWxMchId());
        wxConfig.setMchKey(sysUser.getWxMchKey());
        wxConfig.setNotifyUrl(PyodConstant.WeiXin.NOTIFY_URL);
        wxConfig.setTemplateId(sysUser.getWxTemplateId());
        return wxConfig;
    }

    @Override
    public List<SysUser> querySysUserByRoleNo(String roleNo) {
        return sysUserMapper.querySysUserByRoleNo(roleNo);
    }

    @Override
    public SysUser queryUserRoles(String no) {
        return sysUserMapper.queryUserRoles(no);
    }

    @Override
    public SysUser queryUserRolesById(Integer userId) {
        return sysUserMapper.queryUserRolesById(userId);
    }

    @Transactional
    @Override
    public void bindUserRoles(Integer userId, List<Integer> roleIds) {
        //1.先解绑所有角色
        if (null != userId) {
            sysUserMapper.deleteUserRoles(userId);
        }
        //2. 绑定现有角色
        if (!roleIds.isEmpty()) {
            sysUserMapper.addUserRoleRecord(userId, roleIds);
        }
    }

    @Override
    public MerchantUserLoginResDTO login(MerchantUserLoginReqDTO merchantUserLoginReqDTO) {
        MerchantUserLoginResDTO merchantUserLoginResDTO = new MerchantUserLoginResDTO();

        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUser::getNo, merchantUserLoginReqDTO.getNo());
        lambdaQueryWrapper.eq(SysUser::getPassword, DigestUtil.md5Hex(merchantUserLoginReqDTO.getPassword()));

        SysUser sysUser = getOne(lambdaQueryWrapper);
        if (null == sysUser) {
            throw new BusinessException(ResultCodeEnum.NO_USER);
        }
        BeanUtil.copyProperties(sysUser, merchantUserLoginResDTO);

        List<Shop> shopList = iShopService.queryShopListByMerchantId(Long.valueOf(sysUser.getUserId()));
        //第一版本只有一个店铺
        if (!shopList.isEmpty()) {
            merchantUserLoginResDTO.setShop(shopList.get(0));
        }
        //刷新token
        merchantUserLoginResDTO.setToken(iTokenService.getToken(sysUser));
        merchantUserLoginResDTO.setPassword(merchantUserLoginReqDTO.getPassword());
        sysUser.setLastLoginTime(new Date());
        updateById(sysUser);
        return merchantUserLoginResDTO;
    }

    @Override
    public MerchantUserLoginResDTO logout(Long userId) {
        MerchantUserLoginResDTO merchantUserLoginResDTO = new MerchantUserLoginResDTO();
        merchantUserLoginResDTO.setToken("");
        return merchantUserLoginResDTO;
    }

    @Transactional
    @Override
    public void chargeOff(ChargeOffReqDTO chargeOffReqDTO, Long merchantId) {
        List<Integer> ids = Utils.parseIntegersList(Utils.splitStringToList(chargeOffReqDTO.getUserCouponIds()));
        List<UserCoupon> userCouponList = (List<UserCoupon>) iUserCouponService.listByIds(ids);
        String couponDeductionNo = iGenerateIDService.getNo(BusinessFlagEnum.COUPON_CHARGE_OFF.getMsg());
        User tempUser = null;
        for (UserCoupon userCoupon : userCouponList) {
            if (null == userCoupon) {
                throw new BusinessException(ResultCodeEnum.ILLEGAL_BUSINESS);
            }

            User user = iUserService.getById(userCoupon.getUserId());
            tempUser = user;
            if (!user.getMerchantId().equals(merchantId)) {
                throw new BusinessException(ResultCodeEnum.ILLEGAL_BUSINESS);
            }

            if (!userCoupon.getStatus().equals(CouponStatusEnum.AVAILABLE.getCode())) {
                throw new BusinessException(ResultCodeEnum.CHARGE_FAIL);
            }


            userCoupon.setStatus(CouponStatusEnum.AREADY_USED.getCode());

            iUserCouponService.updateById(userCoupon);

            CouponDeduction couponDeduction = new CouponDeduction();
            couponDeduction.setCouponDeductionNo(couponDeductionNo);
            couponDeduction.setShopId(chargeOffReqDTO.getShopId());
            couponDeduction.setUserCouponId(userCoupon.getUserCouponId());
            couponDeduction.setUserId(userCoupon.getUserId());
            couponDeduction.setVipCardId(userCoupon.getVipCardId());
            couponDeduction.setCouponId(userCoupon.getCouponId());
            couponDeduction.setStatus(ChareOffStatusEnum.FINISH.getCode());
            couponDeduction.setType(ConsumeRecordTypeEnum.CHARGE.getCode());
            iCouponDeductionService.save(couponDeduction);
        }
       /* if (null != tempUser) {
            WxSendMessageDataBean wxSendMessageDataBean = new WxSendMessageDataBean();
            wxSendMessageDataBean.setThing1(new WxValueBean("核销优惠券"));
            wxSendMessageDataBean.setThing7(new WxValueBean("商户会员平台"));
            ;
            wxSendMessageDataBean.setTime2(new WxValueBean(DateUtil.format(new Date(),"yyyy年MM月dd日 HH:mm")));
            wxSendMessageDataBean.setCharacter_string5(new WxValueBean(couponDeductionNo));
            wxSendMessageDataBean.setThing4(new WxValueBean("核销成功,前往小程序核实~"));

            weixinUtil.sendWxMessage(queryWxConfigByMerchantId(merchantId),
                    tempUser.getOpenid(),
                    wxSendMessageDataBean);
        }*/
    }

    @Transactional
    @Override
    public void backChargeOff(Long couponDeductionId) {
        CouponDeduction couponDeduction = iCouponDeductionService.getById(couponDeductionId);
        if (null == couponDeduction) {
            throw new BusinessException(ResultCodeEnum.ILLEGAL_BUSINESS);
        }
        UserCoupon userCoupon = iUserCouponService.getById(couponDeduction.getUserCouponId());
        if (null != userCoupon) {
            userCoupon.setStatus(CouponStatusEnum.AVAILABLE.getCode());
            iUserCouponService.updateById(userCoupon);

            couponDeduction.setStatus(ChareOffStatusEnum.BACK.getCode());
            iCouponDeductionService.updateById(couponDeduction);
        }
       /* List<CouponDeduction> couponDeductionList = iCouponDeductionService.queryCouponDedctionByNo(couponDeductionNo);
        for (CouponDeduction couponDeduction : couponDeductionList) {
            if (null == couponDeduction) {
                throw new BusinessException(ResultCodeEnum.ILLEGAL_BUSINESS);
            }
            UserCoupon userCoupon = iUserCouponService.getById(couponDeduction.getUserCouponId());
            if (null != userCoupon) {
                userCoupon.setStatus(CouponStatusEnum.AVAILABLE.getCode());
                iUserCouponService.updateById(userCoupon);
            }
        }*/
       /* User user = iUserService.getById(userCoupon.getUserId());
        if (null != user) {
            WxSendMessageDataBean wxSendMessageDataBean = new WxSendMessageDataBean();
            wxSendMessageDataBean.setThing1(new WxValueBean("退还优惠券"));
            wxSendMessageDataBean.setThing7(new WxValueBean("商户会员平台"));
            wxSendMessageDataBean.setTime2(new WxValueBean(DateUtil.format(new Date(),"yyyy年MM月dd日 HH:mm")));
            wxSendMessageDataBean.setCharacter_string5(new WxValueBean(couponDeduction.getCouponDeductionNo()));
            wxSendMessageDataBean.setThing4(new WxValueBean("退还成功,前往小程序核实~"));

            weixinUtil.sendWxMessage(queryWxConfigByMerchantId(user.getMerchantId()),
                    user.getOpenid(),
                    wxSendMessageDataBean);
        }*/
    }

    @Transactional
    @Override
    public void gathering(GatheringReqDTO gatheringReqDTO) {
        UserVipCard userVipCard = iUserVipCardService.queryUserVipCard(gatheringReqDTO.getVipCardId());
        if (userVipCard.getBalance() < gatheringReqDTO.getAmount()) {
            throw new BusinessException(ResultCodeEnum.NOT_SUFFICIENT_FUNDS);
        }

        userVipCard.setBalance(userVipCard.getBalance() - gatheringReqDTO.getAmount());
        iUserVipCardService.updateById(userVipCard);

        String couponDeductionNo = iGenerateIDService.getNo(BusinessFlagEnum.GATHERING.getMsg());
        CouponDeduction couponDeduction = new CouponDeduction();
        couponDeduction.setCouponDeductionNo(couponDeductionNo);
        couponDeduction.setShopId(gatheringReqDTO.getShopId());
        couponDeduction.setUserId(userVipCard.getUserId());
        couponDeduction.setVipCardId(userVipCard.getVipCardId());
        couponDeduction.setGatheringAmount(gatheringReqDTO.getAmount());
        couponDeduction.setStatus(ChareOffStatusEnum.FINISH.getCode());
        couponDeduction.setType(ConsumeRecordTypeEnum.GATHERING.getCode());
        iCouponDeductionService.save(couponDeduction);
    }

    @Transactional
    @Override
    public void backGathering(Long couponDeductionId) {
        CouponDeduction couponDeduction = iCouponDeductionService.getById(couponDeductionId);
        if (null == couponDeduction) {
            throw new BusinessException(ResultCodeEnum.ILLEGAL_BUSINESS);
        }
        UserVipCard userVipCard = iUserVipCardService.queryUserVipCard(couponDeduction.getVipCardId());
        if (null != userVipCard) {
            userVipCard.setBalance(userVipCard.getBalance()+couponDeduction.getGatheringAmount());
            iUserVipCardService.updateById(userVipCard);

            couponDeduction.setStatus(ChareOffStatusEnum.BACK.getCode());
            iCouponDeductionService.updateById(couponDeduction);
        }
    }

    @Override
    public PageBean queryUserList(QueryUserPageListDTO queryUserPageListDTO) {

        Page pageInfo = new Page(queryUserPageListDTO.getPage(),
                queryUserPageListDTO.getPageSize() == 0 ? 10 : queryUserPageListDTO.getPageSize());
        List<SysUser> userList = sysUserMapper.queryAllUser(queryUserPageListDTO, pageInfo);
        return PageUtil.getPageBean(pageInfo, userList);
    }

    @Override
    public List<SysMenu> queryMenusByRoleNo(String roleNo) {

        List<SysMenu> totalMenuList = sysUserMapper.queryMenusByRoleNo(roleNo);

        List<SysMenu> menuTree = new ArrayList<>();
        for (SysMenu menu : totalMenuList) {
            if (PARENT_MENU_FLAG == menu.getPid()) {
                menuTree.add(findChildren(menu, totalMenuList));
            }
        }

        if (!menuTree.isEmpty()) {
            //对该菜单按照sort排序
            Collections.sort(menuTree);
        }


        return menuTree;
    }


    @Transactional
    @Override
    public SysUser addUser(AddUserDTO addUserDTO) {

        SysUser user = new SysUser();
        BeanUtil.copyProperties(addUserDTO, user);
        //为新增时、添加NO
        user.setNo(iGenerateIDService.getNo(BusinessFlagEnum.USER.getMsg()));
        user.setPassword(DigestUtil.md5Hex(user.getPassword()));
        save(user);

        //添加一个默认的店铺
        iShopService.addDefaultShop(user);

        List<Integer> roleIds = Utils.parseIntegersList(Utils.splitStringToList(addUserDTO.getRoleIds()));
        if (null != user.getUserId()) {
            bindUserRoles(user.getUserId(), roleIds);

        }

        return queryUserRoles(user.getNo());
    }

    @Transactional
    @Override
    public void deleteUserById(Integer id) {
        removeById(id);

        //清空该用户的角色记录
        sysUserMapper.deleteUserRoles(id);
    }

    /**
     * 递归查找子节点
     *
     * @param menu
     * @param menuList
     * @return
     */
    private SysMenu findChildren(SysMenu menu, List<SysMenu> menuList) {

        for (SysMenu menuItem : menuList) {
            //是否存在menu的子节点
            if (menu.getMenuId().equals(menuItem.getPid())) {
                menu.getSubMenu().add(findChildren(menuItem, menuList));
            }
        }

        if (!menu.getSubMenu().isEmpty()) {
            //对该菜单的所有子节点按照sort排序
            Collections.sort(menu.getSubMenu());
        }

        return menu;
    }
}
