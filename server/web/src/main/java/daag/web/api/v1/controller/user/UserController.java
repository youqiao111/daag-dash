package daag.web.api.v1.controller.user;


import daag.model.v1.user.User;
import daag.model.v1.user.Vo.UserInfo;
import daag.model.v1.user.Vo.AddUser;
import daag.model.v1.user.Vo.DetailUser;
import daag.model.v1.user.Vo.EditUser;
import daag.model.v1.user.Vo.UpdateUser;
import daag.service.v1.user.SysRoleService;
import daag.service.v1.user.UserService;
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
import org.apache.shiro.subject.Subject;
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

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 添加接口
     * @param addUser
     * @return
     */
    @ApiOperation (value = "用户添加")
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
                    User user = new User();
                    BeanUtils.copyProperties(addUser, user);
                    Utils.entryptPassword(user);
                    if (this.userService.add(user) > 0) {
                        msg = "用户添加失败";
                    } else {
                        log.info("^^^^^^^^^^^^^^^^^^^^用户添加成功  end");
                        if (!StringUtil.isEmpty(addUser.getRoles())){
                            String[] role_ids = addUser.getRoles().split(",");
                            // 分配角色
                            this.sysRoleService.addAll(user.getId(),role_ids);
                        }
                        status = 0;
                        msg = "用户添加成功";
                    }
                }
            }
        } catch (DaagException e) {
            e.printStackTrace();
            msg = "传入参数异常";
        }
        return resultJson(status,msg);
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

    @ApiOperation(value = "用户详情")
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
                        Utils.entryptPassword(user);
                    }else {
                        resultJson(status,"两次密码输入不一致");
                    }
                } else {//不修改密码
                    User byId = this.userService.findById(user.getId());
                    user.setPassword(byId.getPassword());
                    user.setSalt(byId.getSalt());
                }
                if (this.userService.update(user) > 0) {
                    if (!StringUtil.isEmpty(editUser.getRoles())){
                        // 清空原角色
                        this.sysRoleService.deleteByUserId(user.getId());
                        String[] role_ids = editUser.getRoles().split(",");
                        // 分配新角色
                        this.sysRoleService.addAll(user.getId(),role_ids);
                    }
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
        Subject subject = SecurityUtils.getSubject();
        User sessionUser = (User) SecurityUtils.getSubject().getPrincipal();
        if(sessionUser != null) {
            try {
                if (UserValidator.convert(updateUser)) {
                    User byUsername = this.userService.findByUsername(sessionUser.getId(),"", updateUser.getEmail());
                    if (byUsername != null) {
                        return resultJson(status, "邮箱已注册");
                    }
                    User user = new User();
                    BeanUtils.copyProperties(updateUser, user);
                    user.setId(sessionUser.getId());
                    user.setUsername(sessionUser.getUsername());
                    user.setAvailable(sessionUser.getAvailable());
                    if(!sessionUser.getPlainpassword().equals(user.getPlainpassword())){ //判断输入原密码是否正确
                        return resultJson(status,"原密码输入错误");
                    }
                    if (!StringUtil.isEmpty(updateUser.getNewpassword())) {//修改密码
                        if (updateUser.getNewpassword().equals(updateUser.getRenewpassword())) {
                            user.setPlainpassword(updateUser.getNewpassword());
                            Utils.entryptPassword(user);
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
