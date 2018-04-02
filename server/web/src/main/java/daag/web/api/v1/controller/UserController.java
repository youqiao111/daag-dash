package daag.web.api.v1.controller;


import daag.model.v1.User;
import daag.model.v1.UserInfo;
import daag.model.v1.request.ReqUser;
import daag.service.v1.UserService;
import daag.web.api.v1.BaseController;
import daag.web.utils.Utils;
import daag.model.v1.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
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
    @PostMapping("/register")
    public ResultJson user_register(ReqUser reqUser){
        log.info("^^^^^^^^^^^^^^^^^^^^用户注册  begin");
        Integer status = -1;
        String msg = "";
        try {
            User newUserInstance = this.userService.findByUsername(reqUser.getUsername());
            if (newUserInstance != null){
                msg = "用户名已存在";
            }else {
                User user = new User();
                BeanUtils.copyProperties(reqUser,user);
                Utils.entryptPassword(user);
                if (this.userService.insert(user) == -1) {
                    msg = "用户添加失败";
                } else {
                    log.info("^^^^^^^^^^^^^^^^^^^^注册成功  end");
                    status = 0;
                    msg = "用户添加成功";
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

    @ApiOperation (value = "用户修改")
    @PostMapping("/update")
    public ResultJson user_update(ReqUser reqUser){
        Integer status = -1;
        String msg = "";
        User user = new User();
        BeanUtils.copyProperties(reqUser,user);
        Utils.entryptPassword(user);
        if(this.userService.update(user) > 0){
            status = 0;
            msg = "修改成功";
        }else {
            msg = "修改失败";
        }
        return resultJson(status,msg);
    }

    @ApiOperation (value = "用户启用/禁用")
    @GetMapping("/delete/{id}/{available}")
    public ResultJson user_delete(@PathVariable("id")Integer id,@PathVariable("available")Boolean available){
        Integer status = -1;
        String msg = "";
        if (this.userService.delete(id,available) > 0){
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
