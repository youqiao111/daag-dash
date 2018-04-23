package daag.service.v1.user.impl;

import daag.dao.mapper.v1.user.SysRoleMapper;
import daag.dao.mapper.v1.user.UserMapper;
import daag.model.v1.user.User;
import daag.model.v1.user.Vo.AddUser;
import daag.model.v1.user.Vo.EditUser;
import daag.model.v1.user.Vo.UpdateUser;
import daag.model.v1.user.Vo.UserInfo;
import daag.service.v1.user.UserService;
import daag.util.DassgUtil;
import daag.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yq on 2018/3/14.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public User findByUsername(Integer id,String username,String email) {
        return this.userMapper.findByUsername(id,username,email);
    }

    @Override
    public User findById(Integer id) {
        return this.userMapper.findById(id);
    }

    @Override
    public int add(AddUser addUser) {
        User user = new User();
        BeanUtils.copyProperties(addUser, user);
        DassgUtil.entryptPassword(user);
        if (this.userMapper.add(user) > 0){
            if (!StringUtil.isEmpty(addUser.getRoles())){
                String[] role_ids = addUser.getRoles().split(",");
                // 分配角色
                this.sysRoleMapper.addAll(user.getId(),role_ids);
            }
            return 1;
        }
        return -1;
    }

    @Override
    public List<UserInfo> findAll() {
        return this.userMapper.findAll();
    }

    @Override
    public int edit(EditUser editUser,User user) {
        if (this.userMapper.update(user) > 0){
            if (!StringUtil.isEmpty(editUser.getRoles())){
                // 清空原角色
                this.sysRoleMapper.deleteByUserId(user.getId());
                String[] role_ids = editUser.getRoles().split(",");
                // 分配新角色
                this.sysRoleMapper.addAll(user.getId(),role_ids);
            }
            return 1;
        }
        return -1;
    }

    @Override
    public int update(User user) {
        return this.userMapper.update(user);
    }

    @Override
    public int available(Integer id, Boolean available) {
        return this.userMapper.available(id,available);
    }

}
