package daag.service.v1.user;

public interface SysRoleService {

    int addAll(Integer user_id,String[] role_ids);

    int deleteByUserId(Integer user_id);
}
