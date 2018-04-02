package daag.service.v1.impl;

import daag.dao.mapper.v1.UserMapper;
import daag.model.v1.User;
import daag.model.v1.UserInfo;
import daag.service.v1.UserService;
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
    public User findByUsername(String username) {
        User user = this.userMapper.findByUsername(username);
        return user;
    }

    @Override
    public int insert(User user) {
        return this.userMapper.insert(user);
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
    public int delete(Integer id, Boolean available) {
        return this.userMapper.delete(id,available);
    }

}
