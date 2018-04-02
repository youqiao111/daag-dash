package daag.dao.mapper.v1;

import daag.model.v1.SysPermission;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by yq on 2018/3/14.
 */
@Mapper
public interface SysPermissionMapper {

    @Select("select p.* from sys_permission p join sys_role_permission rp on p.id = rp.role_id where rp.role_id = #{roleId}")
    List<SysPermission> findByRoleId(Integer roleId);

}
