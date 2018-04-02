package daag.model.v1;

import java.util.List;

/**
 * 用户实体类
 * Created by yq on 2018/3/14.
 */
public class User {

	private Integer id;						// 自增长主键
	private String name;					// 真实姓名
	private String username;				// 用户名
	private String plainPassword; 			// 登录时的密码，不持久化到数据库
	private String password;				// 加密后的密码
	private String salt;					// 用于加密的盐
	private String email;					// 邮箱
	private Boolean available;					// 用户状态
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

	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}
}
