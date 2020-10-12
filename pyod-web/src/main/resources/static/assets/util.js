var config = {
    "ip" : "localhost",
    "port": "8443",
    "contextPath":"/pyod"
};

function getBaseUrl() {
    return "http://" + config.ip + ":" + config.port  + config.contextPath;
}

function getUserLoginUrl() {
    return getBaseUrl() + "/user/login";
}

