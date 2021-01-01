package top.yigege.shiro;

import cn.hutool.crypto.digest.DigestUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import top.yigege.model.SysPermission;
import top.yigege.model.SysRole;
import top.yigege.model.SysUser;
import top.yigege.service.ISysUserService;
import top.yigege.util.SessionUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: CustomRealm
 * @Description:TODO
 * @author: yigege
 * @date: 2020年10月04日 14:13
 */
public class CustomRealm extends AuthorizingRealm {



    @Autowired
    ISysUserService iUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //根据用户编号查询对应的权限
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        //ISysUserService iUserService = SpringUtil.getBean(ISysUserService.class);
        SysUser user = iUserService.queryUserRoles(username);

        List<SysRole> roleList = user.getRoleList();
        Set<String> roleSet = new HashSet<>();
        Set<String> permissionSet = new HashSet<>();
        for (SysRole role : roleList) {
            roleSet.add(role.getName());
            for (SysPermission permission : role.getPermissionList()) {
                permissionSet.add(permission.getName());
            }
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roleSet);
        info.setStringPermissions(permissionSet);
        return info;
    }

    /**
     * <p>
     * 获取即将需要认证的信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        String userPwd = new String((char[]) authenticationToken.getCredentials());
        //根据用户名从数据库获取密码
        SysUser user = iUserService.queryUserRoles(userName);
        if (user == null) {
            throw new AccountException(userName+"用户不存在");
        } else if (!DigestUtil.md5Hex(userPwd).equals(user.getPassword())) {
            throw new AccountException("密码不正确");
        } else if (user.getRoleList().isEmpty()) {
            throw new AccountException("您还没有角色权限，请联系管理员");
        }

        SessionUtil.setUser(user);
        if (user.getRoleList().size() == 1) {
            SessionUtil.setCurrentUserRole(user.getRoleList().get(0));
        }
        return new SimpleAuthenticationInfo(userName, userPwd,getName());
    }
}