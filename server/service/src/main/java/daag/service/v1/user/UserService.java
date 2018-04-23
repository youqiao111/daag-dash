package daag.service.v1.user;

import daag.model.v1.user.User;
import daag.model.v1.user.Vo.AddUser;
import daag.model.v1.user.Vo.EditUser;
import daag.model.v1.user.Vo.UserInfo;

import java.util.List;

public interface UserService {

    User findByUsername(Integer id,String username,String email);

    User findById(Integer id);

    int add(AddUser addUser);

    List<UserInfo> findAll();

    int edit(EditUser editUser,User user);

    int update(User user);

    int available(Integer id,Boolean available);

}
