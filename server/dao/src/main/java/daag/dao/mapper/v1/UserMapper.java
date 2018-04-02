package daag.dao.mapper.v1;

import daag.model.v1.User;
import daag.model.v1.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by yq on 2018/3/14.
 */
@Mapper
public interface UserMapper {

    @Select("select id,name,username,password,email,salt,available from sys_user where username = #{username}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "id",property = "roleList",javaType = List.class,
                    many = @Many(select = "daag.dao.mapper.v1.SysRoleMapper.findByUserId"))
    })
    User findByUsername(String username);

    @Insert("insert into sys_user(name,username,password,email,salt,available) values(#{name},#{username},#{password},#{email},#{salt},#{available})")
    int insert(User user);

    @Select("select id,name,username,email,available from sys_user")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "id",property = "roleList",javaType = List.class,
                    many = @Many(select = "daag.dao.mapper.v1.SysRoleMapper.findByUserId"))
    })
    List<UserInfo> findAll();

    @Update("update sys_user set name = #{name},password = #{password},email = #{email} where id = #{id}")
    int update(User user);

    @Update("update sys_user set available = #{available} where id = #{id}")
    int delete(@Param("id") Integer id,@Param("available") Boolean available);
}
