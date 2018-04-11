const API = {
    "login": `${API_HOST}/api/v1/login`,
    "logout": `${API_HOST}/api/v1/logout`,
};
API.user = {
    "info": `${API_HOST}/api/v1/user/info`,
    "list": `${API_HOST}/api/v1/user/list`,
    "add": `${API_HOST}/api/v1/user/add`,
    "edit": `${API_HOST}/api/v1/user/edit`,
    "detail":`${API_HOST}/api/v1/user/detail`,
    "available":`${API_HOST}/api/v1/user/available`,
    "update":`${API_HOST}/api/v1/user/update`,
};
API.datasource={
    "list": `${API_HOST}/api/v1/datasource/list`,
    "add": `${API_HOST}/api/v1/datasource/add`,
    "edit": `${API_HOST}/api/v1/datasource/edit`,
    "detail":`${API_HOST}/api/v1/datasource/detail`,
    "delete":`${API_HOST}/api/v1/datasource/delete`,
    "test":`${API_HOST}/api/v1/datasource/test`,
};
export default API