const utils = {};
utils.getParameter = function (name, url) {
    if (typeof (url) === 'undefined') {
        url = window.location.href;
    }
    const regexSearch = "[\\?&#]" + name + "=([^&#]*)";
    const regex = new RegExp(regexSearch);
    const results = regex.exec(url);
    return results ? window.decodeURIComponent(results[1]) : '';
};
export default utils;