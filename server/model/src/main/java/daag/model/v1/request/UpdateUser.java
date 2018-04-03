package daag.model.v1.request;

/**
 * 用户实体类
 * Created by yq on 2018/3/20.
 */
public class UpdateUser {

	private String name;					// 真实姓名
	private String plainpassword; 			// 登录时的密码，不持久化到数据库
	private String newpassword;				// 新密码
	private String renewpassword;				// 重复新密码
	private String email;					// 邮箱
	private Boolean available;					// 用户状态

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlainpassword() {
		return plainpassword;
	}

	public void setPlainpassword(String plainpassword) {
		this.plainpassword = plainpassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getRenewpassword() {
		return renewpassword;
	}

	public void setRenewpassword(String renewpassword) {
		this.renewpassword = renewpassword;
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
