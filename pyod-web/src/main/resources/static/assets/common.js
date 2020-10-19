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

