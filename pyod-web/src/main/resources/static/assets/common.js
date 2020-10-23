var config = {
    "ip" : "localhost",
    "port": "8443",
    "contextPath":"/pyod"
};


function getBaseUrl() {
    return "http://" + config.ip + ":" + config.port  + config.contextPath;
}


/************用户模块API*************/
/**
 * 用户登入
 * @returns {string}
 */
function getUserLoginUrl() {
    return getBaseUrl() + "/user/login";
}

/**
 * 设置当前用户角色
 * @returns {string}
 */
function getSetCurrentUserRoleUrl() {
    return getBaseUrl() + "/user/setCurrentUserRole";
}

/**
 * 通过ID删除用户
 * @returns {string}
 */
function getDeleteUserByIdUrl() {
    return getBaseUrl() + "/user/deleteUserById";
}

/**
 * 新增用户
 * @returns {string}
 */
function getAddUserUrl() {
    return getBaseUrl() + "/user/addUser";
}

/**
 * 更新用户
 * @returns {string}
 */
function getUpdateUserUrl() {
    return getBaseUrl() + "/user//updateUser";
}


/**
 * 查询用户信息
 * @returns {string}
 */
function getUserInfoUrl() {
    return getBaseUrl() + "/user/queryUserInfo";
}

/**
 * 查询用户信息
 * @returns {string}
 */
function getLoadUserDetailUrl() {
    return getBaseUrl() + "/user/loadUserDetail";
}





/************菜单模块API*************/

/**
 * 查询菜单信息
 * @returns {string}
 */
function getMenuInfoUrl() {
    return getBaseUrl() + "/user/queryUserMenuByRole";
}


/**
 * 查询用户列表信息
 * @returns {string}
 */
function getQueryUserListUrl() {
    return getBaseUrl() + "/user/queryUserList";
}


/**
 * 查询树形菜单信息
 * @returns {string}
 */
function getTreeMenuUrl() {
    return getBaseUrl() + "/menu/queryTreeMenu";
}

/**
 * 查询菜单详情信息
 * @returns {string}
 */
function getQueryMenuDetailUrl() {
    return getBaseUrl() + "/menu/queryMenuDetail";
}

/**
 * 添加或更新菜单
 * @returns {string}
 */
function getAddOrUpdateMenuUrl() {
    return getBaseUrl() + "/menu/addOrUpdateMenu";
}

/**
 * 删除菜单
 * @returns {string}
 */
function getDeleteMenuByIdUrl() {
    return getBaseUrl() + "/menu/deleteMenuById";
}


/************角色模块API*************/
/**
 * 查询角色列表信息
 * @returns {string}
 */
function getQueryRoleListUrl() {
    return getBaseUrl() + "/role/queryRoleList";
}

/**
 * 查询角色详情信息
 * @returns {string}
 */
function getQueryRoleDetailUrl() {
    return getBaseUrl() + "/role/queryRoleDetail";
}

/**
 * 删除角色
 * @returns {string}
 */
function getDeleteRoleByIdsUrl() {
    return getBaseUrl() + "/role/deleteRoleByIds";
}

/**
 * 添加或更新角色
 * @returns {string}
 */
function getAddOrUpdateRoleUrl() {
    return getBaseUrl() + "/role/addOrUpdateRole";
}

/**
 * 查询选中的角色菜单
 * @returns {string}
 */
function getQueryCheckedMenuByRoleIdUrl() {
    return getBaseUrl() + "/role/queryCheckedMenuByRoleId";
}

/**
 * 查询角色下所有菜单
 * @returns {string}
 */
function getQueryAllMenuByRoleIdUrl() {
    return getBaseUrl() + "/role/queryAllMenuByRoleId";
}



/************权限模块API*************/
/**
 * 查询所有权限
 * @returns {string}
 */
function getQueryPermissionListUrl() {
    return getBaseUrl() + "/permission/queryPermissionList";
}


/**
 * 查询权限详情
 * @returns {string}
 */
function getQueryPermissionDetailUrl() {
    return getBaseUrl() + "/permission/queryPermissionDetail";
}

/**
 * 删除权限
 * @returns {string}
 */
function getDeletePermissionByIdUrl() {
    return getBaseUrl() + "/permission/deletePermissionById";
}

/**
 * 添加或修改权限
 * @returns {string}
 */
function getAddOrUpdatePermissionUrl() {
    return getBaseUrl() + "/permission/addOrUpdatePermission";
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
