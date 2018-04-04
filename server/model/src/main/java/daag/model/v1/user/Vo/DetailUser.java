package daag.model.v1.user.Vo;

import daag.model.v1.user.SysRole;

import java.util.List;

/**
 * Created by yq on 2018/4/3.
 */
public class DetailUser {

    private Integer id;						// 自增长主键
    private String name;					// 真实姓名
    private String username;				// 用户名
    private String email;					// 邮箱
    private Boolean available;				// 用户状态
    private List<SysRole> roleList;			// 一个用户拥有多个角色

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }
}
