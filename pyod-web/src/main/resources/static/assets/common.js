var config = {
    "ip" : "admin.yigege.top",
    //"ip" : "localhost",
    "port": "9999",
    "contextPath":"/pyod/web"
};


function getBaseUrl() {
    return "http://" + config.ip + ":" + config.port  + config.contextPath;
}

function getLoginBaseUrl() {
    return "http://" + config.ip + ":" + config.port  + "/pyod/login.html";

}


/************用户模块API*************/
/**
 * 用户登入
 * @returns {string}
 */
function getUserLoginUrl() {
    return getBaseUrl() + "/sysUser/login";
}

/**
 * 用户注销
 * @returns {string}
 */
function getUserLogoutUrl() {
    return getBaseUrl() + "/sysUser/logout";
}



/**
 * 设置当前用户角色
 * @returns {string}
 */
function getSetCurrentUserRoleUrl() {
    return getBaseUrl() + "/sysUser/setCurrentUserRole";
}

/**
 * 通过ID删除用户
 * @returns {string}
 */
function getDeleteUserByIdUrl() {
    return getBaseUrl() + "/sysUser/deleteUserById";
}

/**
 * 新增用户
 * @returns {string}
 */
function getAddUserUrl() {
    return getBaseUrl() + "/sysUser/addUser";
}

/**
 * 更新用户
 * @returns {string}
 */
function getUpdateUserUrl() {
    return getBaseUrl() + "/sysUser/updateUser";
}


/**
 * 查询用户信息
 * @returns {string}
 */
function getUserInfoUrl() {
    return getBaseUrl() + "/sysUser/queryUserInfo";
}

/**
 * 查询用户信息
 * @returns {string}
 */
function getLoadUserDetailUrl() {
    return getBaseUrl() + "/sysUser/loadUserDetail";
}

/**
 * 查询用户菜单信息
 * @returns {string}
 */
function getMenuInfoUrl() {
    return getBaseUrl() + "/sysUser/queryUserMenuByRole";
}

/**
 * 查询用户列表信息
 * @returns {string}
 */
function getQueryUserListUrl() {
    return getBaseUrl() + "/sysUser/queryUserList";
}

/************用户模块api end****************/




/************菜单模块API start*************/

/**
 * 查询树形菜单信息
 * @returns {string}
 */
function getTreeMenuUrl() {
    return getBaseUrl() + "/sysMenu/queryTreeMenu";
}

/**
 * 查询所有菜单
 * @returns {string}
 */
function getQueryAllMenuListUrl() {
    return getBaseUrl() + "/sysMenu/queryAllMenuList";
}

/**
 * 查询菜单详情信息
 * @returns {string}
 */
function getQueryMenuDetailUrl() {
    return getBaseUrl() + "/sysMenu/queryMenuDetail";
}

/**
 * 添加菜单
 * @returns {string}
 */
function getAddMenuUrl() {
    return getBaseUrl() + "/sysMenu/addMenu";
}

/**
 * 修改菜单
 * @returns {string}
 */
function getModifyMenuUrl() {
    return getBaseUrl() + "/sysMenu/modifyMenu";
}

/**
 * 删除菜单
 * @returns {string}
 */
function getDeleteMenuByIdUrl() {
    return getBaseUrl() + "/sysMenu/deleteMenuById";
}


/************角色模块API*************/
/**
 * 查询角色列表信息
 * @returns {string}
 */
function getQueryRoleListUrl() {
    return getBaseUrl() + "/sysRole/queryRoleList";
}

/**
 * 查询角色详情信息
 * @returns {string}
 */
function getQueryRoleDetailUrl() {
    return getBaseUrl() + "/sysRole/queryRoleDetail";
}

/**
 * 删除角色
 * @returns {string}
 */
function getDeleteRoleByIdsUrl() {
    return getBaseUrl() + "/sysRole/deleteRoleByIds";
}

/**
 * 添加角色
 * @returns {string}
 */
function getAddRoleUrl() {
    return getBaseUrl() + "/sysRole/addRole";
}

/**
 * 修改角色
 * @returns {string}
 */
function getModifyRoleUrl() {
    return getBaseUrl() + "/sysRole/modifyRole";
}

/**
 * 查询选中的角色菜单
 * @returns {string}
 */
function getQueryCheckedMenuByRoleIdUrl() {
    return getBaseUrl() + "/sysRole/queryCheckedMenuByRoleId";
}

/**
 * 查询角色下所有菜单
 * @returns {string}
 */
function getQueryAllMenuByRoleIdUrl() {
    return getBaseUrl() + "/sysRole/queryAllMenuByRoleId";
}
/*******************角色模块******************/




/************权限模块API*************/
/**
 * 查询所有权限
 * @returns {string}
 */
function getQueryPermissionListUrl() {
    return getBaseUrl() + "/sysPermission/queryPermissionList";
}


/**
 * 查询权限详情
 * @returns {string}
 */
function getQueryPermissionDetailUrl() {
    return getBaseUrl() + "/sysPermission/queryPermissionDetail";
}

/**
 * 删除权限
 * @returns {string}
 */
function getDeletePermissionByIdUrl() {
    return getBaseUrl() + "/sysPermission/deletePermissionById";
}

/**
 * 添加或修改权限
 * @returns {string}
 */
function getAddOrUpdatePermissionUrl() {
    return getBaseUrl() + "/permission/addOrUpdatePermission";
}
/**************权限模块 end*****************/


/**************轮播模块 start****************/
/**
 * 查询banner列表
 * @returns {string}
 */
function getBannerListUrl() {
    return getBaseUrl() + "/banner/queryAllBannerList";
}

/**
 * 查询banner详情
 */
function getQueryBannerDetailUrl() {
    return getBaseUrl() + "/banner/queryBannerDetail";

}

/**
 * 添加banner
 * @returns {string}
 */
function getAddBannerUrl() {
    return getBaseUrl() + "/banner/addBanner";

}

/**
 * 修改banner
 */
function getModifyBannerUrl() {
    return getBaseUrl() + "/banner/modifyBanner";

}

/**
 * 删除banner
 */
function getDeleteBannerByIdsUrl() {
    return getBaseUrl() + "/banner/deleteBannerByIds";
}

/**************轮播模块 end****************/


/**************封面模块 start****************/
/**
 * 查询封面列表
 * @returns {string}
 */
function getCardCoverListUrl() {
    return getBaseUrl() + "/cardCover/queryAllCardCoverList";
}

/**
 * 查询封面详情
 */
function getQueryCardCoverDetailUrl() {
    return getBaseUrl() + "/cardCover/queryCardCoverDetail";

}

/**
 * 添加封面
 * @returns {string}
 */
function getAddCardCoverUrl() {
    return getBaseUrl() + "/cardCover/addCardCover";

}

/**
 * 修改封面
 */
function getModifyCardCoverUrl() {
    return getBaseUrl() + "/cardCover/modifyCardCover";

}

/**
 * 删除封面
 */
function getDeleteCardCoverByIdsUrl() {
    return getBaseUrl() + "/cardCover/deleteCardCoverByIds";
}

/**************封面模块 end****************/



/**************商品模块 start****************/
/**
 * 查询商品列表
 * @returns {string}
 */
function getQueryProductPageListUrl() {
    return getBaseUrl() + "/product/queryProductPageList";
}

/**
 * 查询商品详情
 */
function getQueryProductDetailUrl() {
    return getBaseUrl() + "/product/queryProductDetail";

}

/**
 * 添加商品
 * @returns {string}
 */
function getAddProductUrl() {
    return getBaseUrl() + "/product/addProduct";

}

/**
 * 修改商品
 */
function getModifyProductUrl() {
    return getBaseUrl() + "/product/modifyProduct";

}

/**
 * 删除商品
 */
function getDeleteProductByIdsUrl() {
    return getBaseUrl() + "/product/deleteProductByIds";
}

/**
 * 查下所有商品
 */
function getQueryAllProductListUrl() {
    return getBaseUrl() + "/product/queryAllProductList";
}

/**************商品模块 end****************/



/**************定时任务模块 start****************/
/**
 * 查询任务列表
 * @returns {string}
 */
function getQuerySysJobPageListUrl() {
    return getBaseUrl() + "/sysJob/querySysJobPageList";
}

/**
 * 查询任务详情
 */
function getQuerySysJobDetailUrl() {
    return getBaseUrl() + "/sysJob/querySysJobDetail";

}

/**
 * 添加任务
 * @returns {string}
 */
function getAddSysJobUrl() {
    return getBaseUrl() + "/sysJob/addSysJob";
}

/**
 * 修改任务
 */
function getModifySysJobUrl() {
    return getBaseUrl() + "/sysJob/modifySysJob";

}

/**
 * 删除任务
 */
function getDeleteSysJobByIdsUrl() {
    return getBaseUrl() + "/sysJob/deleteSysJobByIds";
}

/**************任务模块 end****************/


/**************标签模块 start****************/
/**
 * 查询标签列表
 * @returns {string}
 */
function getQueryLabelPageListUrl() {
    return getBaseUrl() + "/label/queryLabelPageList";
}

/**
 * 查询标签详情
 */
function getQueryLabelDetailUrl() {
    return getBaseUrl() + "/label/queryLabelDetail";

}

/**
 * 添加标签
 * @returns {string}
 */
function getAddLabelUrl() {
    return getBaseUrl() + "/label/addLabel";
}

/**
 * 修改标签
 */
function getModifyLabelUrl() {
    return getBaseUrl() + "/label/modifyLabel";

}

/**
 * 删除标签
 */
function getDeleteLabelByIdsUrl() {
    return getBaseUrl() + "/label/deleteLabelByIds";
}

/**************标签模块 end****************/


/**************等级模块 start****************/
/**
 * 查询等级列表
 * @returns {string}
 */
function getQueryLevelPageListUrl() {
    return getBaseUrl() + "/level/queryLevelPageList";
}

/**
 * 查询所有等级列表
 * @returns {string}
 */
function getQueryAllLevelListUrl() {
    return getBaseUrl() + "/level/queryAllLevelList";
}

/**
 * 查询等级详情
 */
function getQueryLevelDetailUrl() {
    return getBaseUrl() + "/level/queryLevelDetail";

}

/**
 * 添加等级
 * @returns {string}
 */
function getAddLevelUrl() {
    return getBaseUrl() + "/level/addLevel";
}

/**
 * 修改等级
 */
function getModifyLevelUrl() {
    return getBaseUrl() + "/level/modifyLevel";

}

/**
 * 删除等级
 */
function getDeleteLevelByIdsUrl() {
    return getBaseUrl() + "/level/deleteLevelByIds";
}

/**************等级模块 end****************/

/**************福利模块 start****************/
/**
 * 查询福利分页列表
 * @returns {string}
 */
function getQueryWelfarePageListUrl() {
    return getBaseUrl() + "/welfare/queryWelfarePageList";
}

/**
 * 查询福利详情
 */
function getQueryWelfareDetailUrl() {
    return getBaseUrl() + "/welfare/queryWelfareDetail";

}

/**
 * 添加福利
 * @returns {string}
 */
function getAddWelfareUrl() {
    return getBaseUrl() + "/welfare/addWelfare";
}


/**
 * 修改福利
 */
function getModifyWelfareUrl() {
    return getBaseUrl() + "/welfare/modifyWelfare";
}

/**
 * 删除福利
 */
function getDeleteWelfareByIdsUrl() {
    return getBaseUrl() + "/welfare/deleteWelfareByIds";

}


/**************福利模块 end****************/


/**************城市模块 start****************/
/**
 * 查询城市列表
 * @returns {string}
 */
function getQueryCityPageListUrl() {
    return getBaseUrl() + "/city/queryCityPageList";
}

/**
 * 查询城市详情
 */
function getQueryCityDetailUrl() {
    return getBaseUrl() + "/city/queryCityDetail";

}

/**
 * 添加城市
 * @returns {string}
 */
function getAddCityUrl() {
    return getBaseUrl() + "/city/addCity";
}

/**
 * 修改城市
 */
function getModifyCityUrl() {
    return getBaseUrl() + "/city/modifyCity";

}

/**
 * 删除等级
 */
function getDeleteCityByIdsUrl() {
    return getBaseUrl() + "/level/deleteCityByIds";
}

/**************城市模块 end****************/

/**************优惠券模块 start****************/
/**
 * 查询优惠券列表
 * @returns {string}
 */
function getQueryCouponPageListUrl() {
    return getBaseUrl() + "/coupon/queryCouponPageList";
}

/**
 * 查询优惠券详情
 */
function getQueryCouponDetailUrl() {
    return getBaseUrl() + "/coupon/queryCouponDetail";

}

/**
 * 添加优惠券
 * @returns {string}
 */
function getAddCouponUrl() {
    return getBaseUrl() + "/coupon/addCoupon";
}

/**
 * 修改优惠券
 */
function getModifyCouponUrl() {
    return getBaseUrl() + "/coupon/modifyCoupon";

}

/**
 * 删除优惠券
 */
function getDeleteCouponByIdsUrl() {
    return getBaseUrl() + "/cooupon/deleteCouponByIds";
}

/**
 * 查询所有优惠券列表
 * @returns {string}
 */
function getQueryAllCouponListUrl() {
    return getBaseUrl() + "/coupon/queryAllCouponList";

}

/**************优惠券模块 end****************/


/**************活动模块 start****************/
/**
 * 查询活动分页列表
 * @returns {string}
 */
function getQueryCouponActivityPageListUrl() {
    return getBaseUrl() + "/couponActivity/queryCouponActivityPageList";
}

/**
 * 查询活动详情
 */
function getQueryCouponActivityDetailUrl() {
    return getBaseUrl() + "/couponActivity/queryCouponActivityDetail";

}

/**
 * 添加活动
 * @returns {string}
 */
function getAddCouponActivityUrl() {
    return getBaseUrl() + "/couponActivity/addCouponActivity";
}

/**
 * 修改活动
 */
function getModifyCouponActivityUrl() {
    return getBaseUrl() + "/couponActivity/modifyCouponActivity";

}

/**
 * 删除活动
 */
function getDeleteCouponActivityByIdsUrl() {
    return getBaseUrl() + "/couponActivity/deleteCouponActivityByIds";
}

/**
 * 查询所有活动通过类型
 * @returns {string}
 */
function getQueryCouponActivityListByActivityTypeUrl(){
    return getBaseUrl() +"/couponActivity/queryCouponActivityListByActivityType"
}

/**************活动模块 end****************/


/**************新用户注册赠券模块 start****************/
/**
 * 查询新用户注册用户赠券分页列表
 * @returns {string}
 */
function getQueryCouponActivityRegisterPageListUrl() {
    return getBaseUrl() + "/couponActivityRegister/queryCouponActivityRegisterPageList";
}

/**
 * 查询新用户注册用户赠券详情
 */
function getQueryCouponActivityRegisterDetailUrl() {
    return getBaseUrl() + "/couponActivityRegister/queryCouponActivityRegisterDetail";

}

/**
 * 添加新用户注册用户赠券
 * @returns {string}
 */
function getAddCouponActivityRegisterUrl() {
    return getBaseUrl() + "/couponActivityRegister/addCouponActivityRegister";
}

/**
 * 修改新用户注册用户赠券
 */
function getModifyCouponActivityRegisterUrl() {
    return getBaseUrl() + "/couponActivityRegister/modifyCouponActivityRegister";

}

/**
 * 删除新用户注册用户赠券
 */
function getDeleteCouponActivityRegisterByIdsUrl() {
    return getBaseUrl() + "/couponActivityRegister/deleteCouponActivityRegisterByIds";
}

/**************新用户注册赠券模块 end****************/

/**************购买商品赠券模块 start****************/
/**
 * 查询购买商品赠券模块分页列表
 * @returns {string}
 */
function getQueryCouponActivityProductPageListUrl() {
    return getBaseUrl() + "/couponActivityProduct/queryCouponActivityProductPageList";
}

/**
 * 查询购买商品赠券详情
 */
function getQueryCouponActivityProductDetailUrl() {
    return getBaseUrl() + "/couponActivityProduct/queryCouponActivityProductDetail";

}

/**
 * 添加购买商品赠券
 * @returns {string}
 */
function getAddCouponActivityProductUrl() {
    return getBaseUrl() + "/couponActivityProduct/addCouponActivityProduct";
}

/**
 * 修改购买商品赠券
 */
function getModifyCouponActivityProductUrl() {
    return getBaseUrl() + "/couponActivityProduct/modifyCouponActivityProduct";

}

/**
 * 删除购买商品赠券
 */
function getDeleteCouponActivityProductByIdsUrl() {
    return getBaseUrl() + "/couponActivityProduct/deleteCouponActivityProductByIds";
}

/**************购买商品赠券模块 end****************/

/**************升级赠券模块 start****************/
/**
 * 查询升级赠券模块分页列表
 * @returns {string}
 */
function getQueryCouponActivityUpgradePageListUrl() {
    return getBaseUrl() + "/couponActivityUpgrade/queryCouponActivityUpgradePageList";
}

/**
 * 查询升级赠券详情
 */
function getQueryCouponActivityUpgradeDetailUrl() {
    return getBaseUrl() + "/couponActivityUpgrade/queryCouponActivityUpgradeDetail";

}

/**
 * 添加升级赠券
 * @returns {string}
 */
function getAddCouponActivityUpgradeUrl() {
    return getBaseUrl() + "/couponActivityUpgrade/addCouponActivityUpgrade";
}

/**
 * 修改升级赠券
 */
function getModifyCouponActivityUpgradeUrl() {
    return getBaseUrl() + "/couponActivityUpgrade/modifyCouponActivityUpgrade";

}

/**
 * 删除升级赠券
 */
function getDeleteCouponActivityUpgradeByIdsUrl() {
    return getBaseUrl() + "/couponActivityUpgrade/deleteCouponActivityUpgradeByIds";
}

/**************升级赠券模块 end****************/


/**************cdkey赠券模块 start****************/
/**
 * 查询CDKEY分页列表
 * @returns {string}
 */
function getQueryCouponActivityCdkeyPageListUrl() {
    return getBaseUrl() + "/couponActivityCdkey/queryCouponActivityCdkeyPageList";
}

/**
 * 查询CDKEY赠券详情
 */
function getQueryCouponActivityCdkeyDetailUrl() {
    return getBaseUrl() + "/couponActivityCdkey/queryCouponActivityCdkeyDetail";

}

/**
 * 添加CDKEY赠券
 * @returns {string}
 */
function getAddCouponActivityCdkeyUrl() {
    return getBaseUrl() + "/couponActivityCdkey/addCouponActivityCdkey";
}

/**
 * 修改CDKEY赠券
 */
function getModifyCouponActivityCdkeyUrl() {
    return getBaseUrl() + "/couponActivityCdkey/modifyCouponActivityCdkey";

}

/**
 * 删除CDKEY券
 */
function getDeleteCouponActivityCdkeyByIdsUrl() {
    return getBaseUrl() + "/couponActivityUpgrade/deleteCouponActivityCdkeyByIds";
}

/**************cdkey模块 end****************/


/**************生日赠券模块 start****************/
/**
 * 查询生日赠券分页列表
 * @returns {string}
 */
function getQueryCouponActivityBirthdayPageListUrl() {
    return getBaseUrl() + "/couponActivityBirthday/queryCouponActivityBirthdayPageList";
}

/**
 * 查询生日赠券详情
 */
function getQueryCouponActivityBirthdayDetailUrl() {
    return getBaseUrl() + "/couponActivityBirthday/queryCouponActivityBirthdayDetail";

}

/**
 * 添加生日赠券
 * @returns {string}
 */
function getAddCouponActivityBirthdayUrl() {
    return getBaseUrl() + "/couponActivityBirthday/addCouponActivityBirthday";
}

/**
 * 修改生日赠券
 */
function getModifyCouponActivityBirthdayUrl() {
    return getBaseUrl() + "/couponActivityBirthday/modifyCouponActivityBirthday";

}

/**
 * 删除生日赠券
 */
function getDeleteCouponActivityBirthdayByIdsUrl() {
    return getBaseUrl() + "/couponActivityBirthday/deleteCouponActivityBirthdayByIds";
}

/**************生日赠券模块 end****************/

/**************节气赠券模块 start****************/
/**
 * 查询节气赠券分页列表
 * @returns {string}
 */
function getQueryCouponActivitySolartermPageListUrl() {
    return getBaseUrl() + "/couponActivitySolarterm/queryCouponActivitySolartermPageList";
}

/**
 * 查询节气用户赠券详情
 */
function getQueryCouponActivitySolartermDetailUrl() {
    return getBaseUrl() + "/couponActivitySolarterm/queryCouponActivitySolartermDetail";

}

/**
 * 添加节气用户赠券
 * @returns {string}
 */
function getAddCouponActivitySolartermUrl() {
    return getBaseUrl() + "/couponActivitySolarterm/addCouponActivitySolarterm";
}

/**
 * 修改节气赠券
 */
function getModifyCouponActivitySolartermUrl() {
    return getBaseUrl() + "/couponActivitySolarterm/modifyCouponActivitySolarterm";

}

/**
 * 删除节气赠券
 */
function getDeleteCouponActivitySolartermByIdsUrl() {
    return getBaseUrl() + "/couponActivitySolarterm/deleteCouponActivitySolartermByIds";
}

/**************节气赠券模块 end****************/

/**************积分赠券模块 start****************/
/**
 * 查询积分赠券分页列表
 * @returns {string}
 */
function getQueryCouponActivityIntegralPageListUrl() {
    return getBaseUrl() + "/couponActivityIntegral/queryCouponActivityIntegralPageList";
}

/**
 * 查询积分赠券详情
 */
function getQueryCouponActivityIntegralDetailUrl() {
    return getBaseUrl() + "/couponActivityIntegral/queryCouponActivityIntegralDetail";

}

/**
 * 添加积分赠券
 * @returns {string}
 */
function getAddCouponActivityIntegralUrl() {
    return getBaseUrl() + "/couponActivityIntegral/addCouponActivityIntegral";
}

/**
 * 修改积分赠券
 */
function getModifyCouponActivityIntegralUrl() {
    return getBaseUrl() + "/couponActivityIntegral/modifyCouponActivityIntegral";

}

/**
 * 删除积分赠券
 */
function getDeleteCouponActivityIntegralByIdsUrl() {
    return getBaseUrl() + "/couponActivityIntegral/deleteCouponActivityIntegralByIds";
}

/**************积分赠券模块 end****************/

/**************积豆赠券模块 start****************/
/**
 * 查询积豆赠券分页列表
 * @returns {string}
 */
function getQueryCouponActivityPeaPageListUrl() {
    return getBaseUrl() + "/couponActivityPea/queryCouponActivityPeaPageList";
}

/**
 * 查询积豆赠券详情
 */
function getQueryCouponActivityPeaDetailUrl() {
    return getBaseUrl() + "/couponActivityPea/queryCouponActivityPeaDetail";

}

/**
 * 添加积豆赠券
 * @returns {string}
 */
function getAddCouponActivityPeaUrl() {
    return getBaseUrl() + "/couponActivityPea/addCouponActivityPea";
}

/**
 * 修改积豆赠券
 */
function getModifyCouponActivityPeaUrl() {
    return getBaseUrl() + "/couponActivityPea/modifyCouponActivityPea";

}

/**
 * 删除积豆赠券
 */
function getDeleteCouponActivityPeaByIdsUrl() {
    return getBaseUrl() + "/couponActivityPea/deleteCouponActivityPeaByIds";
}

/**************积豆赠券模块 end****************/

/**************节日赠券模块 start****************/
/**
 * 查询节日赠券分页列表
 * @returns {string}
 */
function getQueryCouponActivityFestivalPageListUrl() {
    return getBaseUrl() + "/couponActivityFestival/queryCouponActivityFestivalPageList";
}

/**
 * 查询节日赠券详情
 */
function getQueryCouponActivityFestivalDetailUrl() {
    return getBaseUrl() + "/couponActivityFestival/queryCouponActivityFestivalDetail";

}

/**
 * 添加节日赠券
 * @returns {string}
 */
function getAddCouponActivityFestivalUrl() {
    return getBaseUrl() + "/couponActivityFestival/addCouponActivityFestival";
}

/**
 * 修改节日赠券
 */
function getModifyCouponActivityFestivalUrl() {
    return getBaseUrl() + "/couponActivityFestival/modifyCouponActivityFestival";

}

/**
 * 删除积豆赠券
 */
function getDeleteCouponActivityFestivalByIdsUrl() {
    return getBaseUrl() + "/couponActivityFestival/deleteCouponActivityFestivalByIds";
}
/**************节日赠券模块 end****************/

/**************商家赠券模块 start****************/
/**
 * 查询商家赠券分页列表
 * @returns {string}
 */
function getQueryCouponActivityMerchantgivePageListUrl() {
    return getBaseUrl() + "/couponActivityMerchantgive/queryCouponActivityMerchantgivePageList";
}

/**
 * 查询商家赠券详情
 */
function getQueryCouponActivityMerchantgiveDetailUrl() {
    return getBaseUrl() + "/couponActivityMerchantgive/queryCouponActivityMerchantgiveDetail";

}

/**
 * 添加商家赠券
 * @returns {string}
 */
function getAddCouponActivityMerchantgiveUrl() {
    return getBaseUrl() + "/couponActivityMerchantgive/addCouponActivityMerchantgive";
}

/**
 * 修改商家赠券
 */
/*function getModifyCouponActivityFestivalUrl() {
    return getBaseUrl() + "/couponActivityMerchantgive/modifyCouponActivityFestival";

}*/

/**
 * 删除商家赠券
 */
function getDeleteCouponActivityMerchantgiveByIdsUrl() {
    return getBaseUrl() + "/couponActivityMerchantgive/deleteCouponActivityMerchantgiveByIds";
}

/**
 * 查询当前用户数量
 * @returns {string}
 */
function getQueryUserNumUrl() {
    return getBaseUrl() + "/couponActivityMerchantgive/queryUserNum";
}

/**************商家赠券模块 end****************/


/**************店铺模块 start****************/
/**
 * 查询店铺详情
 */
function getQueryShopDetailUrl() {
    return getBaseUrl() + "/shop/queryShopDetail";

}

/**
 * 查询所有店铺
 * @returns {string}
 */
function getQueryAllShopList() {
    return getBaseUrl() + "/shop/queryAllShopList";
}

/**
 * 修改店铺
 * @returns {string}
 */
function getModifyShopUrl() {
    return getBaseUrl() + "/shop/modifyShop";
}

/**************店铺模块 end****************/


/**************城市模块 start****************/
/**
 * 查询所有城市
 */
function getQueryAllCityListUrl() {
    return getBaseUrl() + "/city/queryAllCityList";

}

/**************城市模块 end****************/

/**************控制台 start****************/
function getQueryHomeDataUrl() {
    return getBaseUrl() + "/console/queryHomeData";
}
/**************控制台 end****************/

function f() {

}

/**
 * 上传文件
 * @returns {string}
 */
function getCommonUploadFile() {
    return getBaseUrl() + "/upload/uploadFile"
}




/**
 * 获取url参数
 * @param name
 * @returns {*}
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg); //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}
