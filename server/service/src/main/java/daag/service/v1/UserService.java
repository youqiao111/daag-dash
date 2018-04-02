package daag.service.v1;

import daag.model.v1.User;
import daag.model.v1.UserInfo;

import java.util.List;

public interface UserService {

    User findByUsername(String username);

    int insert(User user);

    List<UserInfo> findAll();

    int update(User user);

    int delete(Integer id,Boolean available);

}
