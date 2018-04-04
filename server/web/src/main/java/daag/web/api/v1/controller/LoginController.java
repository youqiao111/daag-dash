package daag.web.api.v1.controller;

import daag.model.v1.ResultJson;
import daag.model.v1.user.User;
import daag.service.v1.user.UserService;
import daag.web.api.v1.BaseController;
import daag.web.api.v1.controller.user.UserController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yq on 2018/3/21.
 */
@Api(tags = "登录相关")
@RestController
@RequestMapping("/api/v1")
public class LoginController extends BaseController {

    private static final transient Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 登录接口
     * @param username
     * @param password
     * @return
     */
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public ResultJson login(String username, String password){
        log.info("^^^^^^^^^^^^^^^^^^^^用户登录  begin");
        Integer status = -1;
        String msg = "";
        try {
            Subject currentUser = SecurityUtils.getSubject();
            User user = (User) currentUser.getPrincipal();
            if (user != null) {
                if (!user.getUsername().equals(username)){
                    currentUser.logout();
                }else {
                    return resultJson(0, "已登录");
                }
            }
            if (!currentUser.isAuthenticated()){
                UsernamePasswordToken token = new UsernamePasswordToken(username,password);
                try {
                    currentUser.login(token); // 执行登录
                    log.info("^^^^^^^^^^^^^^^^^^^^{" + username + "}登录成功  end");
                    status = 0;
                    msg = "登录成功";
                }catch (AuthenticationException ae){
                    log.info("^^^^^^^^^^^^^^^^^^^^登录失败: " + ae.getMessage());
                    ae.printStackTrace();
                    status = -1;
                    msg = "账号密码不匹配";
                }
            }
            return resultJson(status,msg);
        }catch (Exception e){
            e.printStackTrace();
            return resultJson(status,msg);
        }
    }

    /**
     * 退出登录
     * @return
     */
    @ApiOperation(value = "退出登录")
    @GetMapping("/logout")
    public ResultJson logout() {
        log.info("^^^^^^^^^^^^^^^^^^^^用户登出");
        Subject currentUser = SecurityUtils.getSubject();
        Object principal = currentUser.getPrincipal();
        if (principal != null) {
            currentUser.logout();
        }
        return resultJson(0,"已退出登录");
    }

    @ApiOperation(value = "test")
    @GetMapping("/test")
    public String test(){
        return "Hello World";
    }
}
