package daag.web.api.v1;

import javax.servlet.http.HttpServletRequest;

import daag.model.v1.ResultJson;
import daag.model.v1.user.Vo.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * 异常统一处理
 * 
 * Spring @ControllerAdvice 使用方法介绍： http://blog.csdn.net/jackfrued/article/details/76710885
 * @author itdragon
 */
@ControllerAdvice
public class ExceptionController extends BaseController {

    private static final transient Logger log = LoggerFactory.getLogger(ExceptionController.class);

    @ResponseBody
    @ExceptionHandler(org.apache.shiro.authz.AuthorizationException.class)
    public ResultJson authorizationException(HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        log.info("用户权限异常^^^^^^^^^^^^^^访问用户：" + userInfo.getUsername() + "，访问资源：" + WebUtils.getRequestUri(request));
        return resultJson(-1,"抱歉！您没有权限执行这个操作，请联系管理员！");
    }

}
