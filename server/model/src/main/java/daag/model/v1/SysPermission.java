package daag.model.v1;

import java.util.List;

/**
 * 权限表，决定用户的具体操作
 * Created by yq on 2018/3/14.
 */
public class SysPermission {

	private Integer id;
	private String name; 		// 名称
	private String url; 		// 资源路径
	private String permission; 	// 权限字符串 如：employees:create,employees:update,employees:delete
	private Boolean available = Boolean.FALSE; // 状态,默认不可用
	private List<SysRole> roleList;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public List<SysRole> getRoles() {
		return roleList;
	}

	public void setRoles(List<SysRole> roleList) {
		this.roleList = roleList;
	}
}
