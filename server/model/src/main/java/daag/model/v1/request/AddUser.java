package daag.model.v1.request;

/**
 * 用户实体类
 * Created by yq on 2018/3/20.
 */
public class AddUser {

	private String name;					// 真实姓名
	private String username;				// 用户名
	private String plainpassword; 			// 登录时的密码，不持久化到数据库
	private String email;					// 邮箱
	private Boolean available;					// 用户状态

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

	public String getPlainpassword() {
		return plainpassword;
	}

	public void setPlainpassword(String plainpassword) {
		this.plainpassword = plainpassword;
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


}
