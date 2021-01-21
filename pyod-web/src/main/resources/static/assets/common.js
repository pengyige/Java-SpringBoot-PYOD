var config = {
    //"ip" : "admin.yigege.top",
    "ip" : "localhost",
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

/**************优惠券模块 end****************/



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
