package daag.service.v1.user.impl;

import daag.dao.mapper.v1.user.UserMapper;
import daag.model.v1.user.User;
import daag.model.v1.user.Vo.UserInfo;
import daag.service.v1.user.UserService;
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

    @Override
    public User findByUsername(Integer id,String username,String email) {
        User user = this.userMapper.findByUsername(id,username,email);
        return user;
    }

    @Override
    public User findById(Integer id) {
        return this.userMapper.findById(id);
    }

    @Override
    public int add(User user) {
        return this.userMapper.add(user);
    }

    @Override
    public List<UserInfo> findAll() {
        return this.userMapper.findAll();
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
