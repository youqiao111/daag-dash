package daag.dao.mapper.v1.user.provider;

/**
 * Created by yq on 2018/4/3.
 */
public class SysRoleProvider {

    public String addAll(Integer user_id,String roleIds){
        StringBuffer sb = new StringBuffer();
        sb.append("insert into sys_user_role(user_id,role_id) values");
        String[] role_ids = roleIds.split(",");
        for (int i=0;i<role_ids.length;i++){
            sb.append("(" + user_id + "," + role_ids[i] + ")");
            if (i != (role_ids.length-1)){
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
