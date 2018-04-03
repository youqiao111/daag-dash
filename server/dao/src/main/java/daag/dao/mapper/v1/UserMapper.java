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

    @Select("select id,name,username,password,email,salt,available from sys_user where username = #{username} or email = #{email}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "id",property = "roleList",javaType = List.class,
                    many = @Many(select = "daag.dao.mapper.v1.SysRoleMapper.findByUserId"))
    })
    User findByUsername(@Param("username") String username, @Param("email") String email);

    @Select("select id,name,username,password,email,salt,available from sys_user where id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "id",property = "roleList",javaType = List.class,
                    many = @Many(select = "daag.dao.mapper.v1.SysRoleMapper.findByUserId"))
    })
    User findById(Integer id);

    @Insert("insert into sys_user(name,username,password,email,salt,available) values(#{name},#{username},#{password},#{email},#{salt},#{available})")
    @Options(useGeneratedKeys = true)
    int add(User user);

    @Select("select id,name,username,email,available from sys_user")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "id",property = "roleList",javaType = List.class,
                    many = @Many(select = "daag.dao.mapper.v1.SysRoleMapper.findByUserId"))
    })
    List<UserInfo> findAll();

    @Update("update sys_user set name = #{name},username = #{username},password = #{password},salt = #{salt},email = #{email} where id = #{id}")
    int update(User user);

    @Update("update sys_user set available = #{available} where id = #{id}")
    int available(@Param("id") Integer id,@Param("available") Boolean available);
}
