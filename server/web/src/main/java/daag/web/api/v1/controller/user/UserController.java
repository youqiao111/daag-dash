package daag.web.api.v1.controller.user;


import daag.model.v1.User;
import daag.model.v1.UserInfo;
import daag.model.v1.request.ReqUser;
import daag.service.v1.UserService;
import daag.web.api.v1.BaseController;
import daag.web.api.v1.controller.user.validator.UserValidator;
import daag.web.utils.StringUtil;
import daag.web.utils.Utils;
import daag.model.v1.ResultJson;
import daag.web.utils.exception.DaagException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by yq on 2018/3/14.
 */
@Api(tags = "用户相关")
@RestController
@RequestMapping("/api/v1/user")
public class UserController extends BaseController {

    private static final transient Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 注册接口
     * @param reqUser
     * @return
     */
    @ApiOperation (value = "用户注册")
    @PostMapping("/add")
    @RequiresRoles("admin")
    public ResultJson user_add(ReqUser reqUser){
        log.info("^^^^^^^^^^^^^^^^^^^^用户注册  begin");
        Integer status = -1;
        String msg = "";
        try {
            User newUserInstance = this.userService.findByUsername(reqUser.getUsername(),reqUser.getEmail());
            if (newUserInstance != null){
                msg = "用户名或邮箱已注册";
            }else {
                User user = new User();
                BeanUtils.copyProperties(reqUser,user);
                Utils.entryptPassword(user);
                if (this.userService.insert(user) == -1) {
                    msg = "用户注册失败";
                } else {
                    log.info("^^^^^^^^^^^^^^^^^^^^注册成功  end");
                    status = 0;
                    msg = "用户注册成功";
                }
            }
            return resultJson(status,msg);
        }catch (Exception e){
            e.printStackTrace();
            return resultJson(status,msg);
        }
    }

    @ApiOperation (value = "用户列表")
    @GetMapping("/list")
    @RequiresRoles("admin")
    public ResultJson user_list(){
        Integer status = -1;
        String msg = "";
        List<UserInfo> userList = this.userService.findAll();
        if(userList.size() > 0){
            status = 0;
        }else {
            msg = "暂无数据";
        }
        return resultJson(status,msg,userList);
    }

    /**
     * 管理员权限修改
     * @param reqUser
     * @return
     */
    @ApiOperation (value = "修改用户信息（管理员）")
    @PostMapping("/edit")
    @RequiresRoles("admin")
    public ResultJson user_edit(ReqUser reqUser){
        Integer status = -1;
        String msg = "";
        try {
            if(UserValidator.convert(reqUser)) {
                User byUsername = this.userService.findByUsername(reqUser.getUsername(), reqUser.getEmail());
                if (byUsername != null){
                    return resultJson(status, "用户名或邮箱已注册");
                }
                User user = new User();
                BeanUtils.copyProperties(reqUser, user);
                if (!StringUtil.isEmpty(reqUser.getNewpassword())) {
                    user.setPlainpassword(reqUser.getNewpassword());
                    Utils.entryptPassword(user);
                } else {
                    User byId = this.userService.findById(user.getId());
                    user.setPassword(byId.getPassword());
                }
                if (this.userService.update(user) > 0) {
                    status = 0;
                    msg = "修改成功";
                } else {
                    msg = "修改失败";
                }
            }
        } catch (DaagException e) {
            e.printStackTrace();
            return resultJson(-1,"传入参数错误");
        }
        return resultJson(status,msg);
    }

    @ApiOperation (value = "用户个人修改")
    @PostMapping("/update")
    public ResultJson user_update(ReqUser reqUser){
        Integer status = -1;
        String msg = "";
        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        if(userInfo != null) {
            try {
                if (UserValidator.convert(reqUser)) {
                    User byUsername = this.userService.findByUsername(reqUser.getUsername(), reqUser.getEmail());
                    if (this.userService.findByUsername(reqUser.getUsername(), reqUser.getEmail()) != null) {
                        return resultJson(status, "用户名或邮箱已注册");
                    }
                    reqUser.setId(userInfo.getId());
                    User user = new User();
                    BeanUtils.copyProperties(reqUser, user);
                    if (!StringUtil.isEmpty(reqUser.getNewpassword())) {
                        user.setPlainpassword(reqUser.getNewpassword());
                        Utils.entryptPassword(user);
                    } else {
                        User byId = this.userService.findById(user.getId());
                        user.setPassword(byId.getPassword());
                    }
                    Utils.entryptPassword(user);
                    if (this.userService.update(user) > 0) {
                        status = 0;
                        msg = "修改成功";
                    } else {
                        msg = "修改失败";
                    }
                }
            } catch (DaagException e) {
                e.printStackTrace();
                return resultJson(-1, "传入参数错误");
            }
        }
        return resultJson(status,msg);
    }

    @ApiOperation (value = "用户启用/禁用")
    @PostMapping("/available")
    @RequiresRoles("admin")
    public ResultJson user_available(Integer id,Boolean available){
        Integer status = -1;
        String msg = "";
        if (this.userService.available(id,available) > 0){
            status = 0;
            msg = "修改成功";
        }else {
            msg = "修改失败";
        }
        return resultJson(status,msg);
    }

    @ApiOperation (value = "用户信息")
    @GetMapping("/info")
    public ResultJson user_info(){
        Integer status = -1;
        String msg = "";
        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        if(userInfo != null){
            status = 0;
        }
        return resultJson(status,msg,userInfo);
    }

}
