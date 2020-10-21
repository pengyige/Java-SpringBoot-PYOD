var config = {
    "ip" : "localhost",
    "port": "8443",
    "contextPath":"/pyod"
};


function getBaseUrl() {
    return "http://" + config.ip + ":" + config.port  + config.contextPath;
}

/**
 * 用户登入
 * @returns {string}
 */
function getUserLoginUrl() {
    return getBaseUrl() + "/user/login";
}

/**
 * 新增用户
 * @returns {string}
 */
function getAddUserUrl() {
    return getBaseUrl() + "/user/addUser";
}


/**
 * 查询用户信息
 * @returns {string}
 */
function getUserInfoUrl() {
    return getBaseUrl() + "/user/queryUserInfo";
}

/**
 * 查询菜单信息
 * @returns {string}
 */
function getMenuInfoUrl() {
    return getBaseUrl() + "/user/queryUserMenu";
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


/**
 * 查询所有权限
 * @returns {string}
 */
function getQueryPermissionListUrl() {
    return getBaseUrl() + "/permission/queryPermissionList";
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