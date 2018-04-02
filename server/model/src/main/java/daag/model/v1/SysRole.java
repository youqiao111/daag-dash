package daag.model.v1;

import java.util.List;

/**
 * 角色表，决定用户可以访问的页面
 * Created by yq on 2018/3/14.
 */
public class SysRole {
	
    private Integer id;
    private String name; 		// 角色
    private String remark; // 角色描述
    private Boolean available = Boolean.FALSE; // 状态,默认不可用

    //角色 -- 权限关系：多对多关系; 取出这条数据时，把它关联的数据也同时取出放入内存中
    private List<SysPermission> permissionList;

    // 用户 - 角色关系：多对多关系;
    private List<User> userList;

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public List<SysPermission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<SysPermission> permissionList) {
		this.permissionList = permissionList;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}
