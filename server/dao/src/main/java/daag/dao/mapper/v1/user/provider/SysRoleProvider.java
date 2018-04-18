package daag.dao.mapper.v1.user.provider;

import java.text.MessageFormat;

/**
 * Created by yq on 2018/4/3.
 */
public class SysRoleProvider {

    public String addAll(Integer user_id,String[] role_ids){
        StringBuffer sb = new StringBuffer();
        sb.append("insert into sys_user_role(user_id,role_id) values");
        MessageFormat mf = new MessageFormat("(#'{'user_id},#'{'role_ids[{0}]})");
        for (int i=0;i<role_ids.length;i++){
            sb.append(mf.format(new Object[]{i}));
            if (i < (role_ids.length-1)){
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
