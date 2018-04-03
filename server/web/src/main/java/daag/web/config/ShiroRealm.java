package daag.web.config;

import daag.model.v1.SysPermission;
import daag.model.v1.SysRole;
import daag.model.v1.User;
import daag.model.v1.UserInfo;
import daag.service.v1.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义安全数据Realm，重点
 *
 */
public class ShiroRealm extends AuthorizingRealm {
	
	private static final transient Logger log = LoggerFactory.getLogger(ShiroRealm.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 授权
	 */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	log.info("^^^^^^^^^^^^^^^^^^^^  配置用户权限");
		User userInfo = (User) principals.getPrimaryPrincipal();
    	User user = userService.findByUsername(0,userInfo.getUsername(),"");
    	if(null == user){
            return null;
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		for (SysRole role : user.getRoleList()) {
			authorizationInfo.addRole(role.getName());	// 添加角色
			for (SysPermission permission : role.getPermissionList()) {
				authorizationInfo.addStringPermission(permission.getPermission());	// 添加具体权限
			}
		}
        return authorizationInfo;
    }

    /**
     * 身份认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
    	log.info("^^^^^^^^^^^^^^^^^^^^  认证用户身份信息");
		String username = (String) token.getPrincipal(); // 获取用户登录账号
		char[] plainpassword = (char[]) token.getCredentials();
		User user = userService.findByUsername(0,username,""); // 通过账号查加密后的密码和盐，这里一般从缓存读取
        if(null == user){
            return null;
        }
		user.setPlainpassword(String.valueOf(plainpassword));
		// 1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
		Object principal = user;
		// 2). credentials: 加密后的密码. 
		Object credentials = user.getPassword();
		// 3). realmName: 当前 realm 对象的唯一名字. 调用父类的 getName() 方法
		String realmName = getName();
		// 4). credentialsSalt: 盐值. 注意类型是ByteSource
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
		return info;
    }

}