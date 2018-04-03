package daag.service.v1;

public interface SysRoleService {

    int addAll(Integer user_id,String roleIds);

    int deleteByUserId(Integer user_id);
}
