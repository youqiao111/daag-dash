package daag.web.api.v1.controller.user;


import daag.model.v1.user.User;
import daag.model.v1.user.Vo.UserInfo;
import daag.model.v1.user.Vo.AddUser;
import daag.model.v1.user.Vo.DetailUser;
import daag.model.v1.user.Vo.EditUser;
import daag.model.v1.user.Vo.UpdateUser;
import daag.service.v1.user.UserService;
import daag.util.DassgUtil;
import daag.web.api.v1.BaseController;
import daag.web.api.v1.controller.user.validator.UserValidator;
import daag.util.StringUtil;
import daag.model.v1.ResultJson;
import daag.util.exception.DaagException;
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
@Api(tags = "User相关")
@RestController
@RequestMapping("/api/v1/user")
public class UserController extends BaseController {

    private static final transient Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * User添加
     * @param addUser
     * @return
     */
    @ApiOperation (value = "User添加")
    @PostMapping("/add")
    @RequiresRoles("admin")
    public ResultJson user_add(AddUser addUser){
        log.info("^^^^^^^^^^^^^^^^^^^^用户添加  begin");
        Integer status = -1;
        String msg = "";
        try {
            if (UserValidator.convert(addUser)) {
                if (!addUser.getPlainpassword().equals(addUser.getReplainpassword())){
                    return resultJson(status,"两次密码输入不一致");
                }
                User newUserInstance = this.userService.findByUsername(0,addUser.getUsername(), addUser.getEmail());
                if (newUserInstance != null) {
                    msg = "用户名或邮箱已注册";
                } else {
                    if (this.userService.add(addUser) > 0) {
                        log.info("^^^^^^^^^^^^^^^^^^^^用户添加成功  end");
                        status = 0;
                        msg = "用户添加成功";
                    } else {
                        msg = "用户添加失败";
                    }
                }
            }
        } catch (DaagException e) {
            e.printStackTrace();
            msg = "传入参数异常";
        }
        return resultJson(status,msg);
    }

    /**
     * User列表
     * @return
     */
    @ApiOperation (value = "User列表")
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
     * User详情
     * @param id
     * @return
     */
    @ApiOperation(value = "User详情")
    @GetMapping("/detail/{id}")
    public ResultJson user_datail(@PathVariable("id") Integer id){
        Integer status = -1;
        String msg = "";
        User byId = this.userService.findById(id);
        DetailUser user = new DetailUser();
        if (byId != null){
            BeanUtils.copyProperties(byId,user);
            status = 0;
        }
        return resultJson(status,msg,user);
    }

    /**
     * 管理员权限修改
     * @param editUser
     * @return
     */
    @ApiOperation (value = "修改用户信息（管理员）")
    @PostMapping("/edit")
    @RequiresRoles("admin")
    public ResultJson user_edit(EditUser editUser){
        Integer status = -1;
        String msg = "";
        try {
            if(UserValidator.convert(editUser)) {
                User byUsername = this.userService.findByUsername(editUser.getId(),editUser.getUsername(), editUser.getEmail());
                if (byUsername != null){
                    return resultJson(status, "用户名或邮箱已注册");
                }
                User user = new User();
                BeanUtils.copyProperties(editUser, user);
                if (!StringUtil.isEmpty(editUser.getNewpassword())) {//修改密码
                    if(editUser.getNewpassword().equals(editUser.getRenewpassword())) {
                        user.setPlainpassword(editUser.getNewpassword());
                        DassgUtil.entryptPassword(user);
                    }else {
                        msg = "两次密码输入不一致";
                    }
                }else {//不修改密码
                    User byId = this.userService.findById(user.getId());
                    user.setPassword(byId.getPassword());
                    user.setSalt(byId.getSalt());
                }
                if (this.userService.edit(editUser,user) > 0) {
                    status = 0;
                    msg = "修改成功";
                } else {
                    msg = "修改失败";
                }
            }
        } catch (DaagException e) {
            e.printStackTrace();
            msg = "传入参数异常";
        }
        return resultJson(status,msg);
    }

    /**
     * 用户个人修改
     * @param updateUser
     * @return
     */
    @ApiOperation (value = "用户个人修改")
    @PostMapping("/update")
    public ResultJson user_update(UpdateUser updateUser){
        Integer status = -1;
        String msg = "";
        User sessionUser = (User) SecurityUtils.getSubject().getPrincipal();
        if(sessionUser != null) {
            try {
                if (UserValidator.convert(updateUser)) {
                    User byUsername = this.userService.findByUsername(sessionUser.getId(),"", updateUser.getEmail());
                    if (byUsername != null) {
                        return resultJson(status, "邮箱已注册");
                    }
                    if(!sessionUser.getPlainpassword().equals(updateUser.getPlainpassword())){ //判断输入原密码是否正确
                        return resultJson(status,"原密码输入错误");
                    }
                    User user = new User();
                    BeanUtils.copyProperties(updateUser, user);
                    user.setId(sessionUser.getId());
                    user.setUsername(sessionUser.getUsername());
                    user.setAvailable(sessionUser.getAvailable());
                    if (!StringUtil.isEmpty(updateUser.getNewpassword())) {//修改密码
                        if (updateUser.getNewpassword().equals(updateUser.getRenewpassword())) {
                            user.setPlainpassword(updateUser.getNewpassword());
                            DassgUtil.entryptPassword(user);
                        }else {
                            resultJson(status,"两次密码输入不一致");
                        }
                    } else {//不修改密码
                        user.setPassword(sessionUser.getPassword());
                        user.setSalt(sessionUser.getSalt());
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
                msg = "传入参数异常";
            }
        }
        return resultJson(status,msg);
    }

    /**
     * User启用/禁用
     * @param id
     * @param available
     * @return
     */
    @ApiOperation (value = "User启用/禁用")
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

    /**
     * User信息
     * @return
     */
    @ApiOperation (value = "User信息")
    @GetMapping("/info")
    public ResultJson user_info(){
        Integer status = -1;
        String msg = "";
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        UserInfo userInfo = new UserInfo();
        if(user != null){
            user = this.userService.findById(user.getId());
            BeanUtils.copyProperties(user,userInfo);
            status = 0;
        }
        return resultJson(status,msg,userInfo);
    }

}
