package com.zeroone.star.oauth2.granter;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zeroone.cloud.oauth2.entity.SecurityUser;
import com.zeroone.star.oauth2.entity.Role;
import com.zeroone.star.oauth2.entity.User;
import com.zeroone.star.oauth2.service.IRoleService;
import com.zeroone.star.oauth2.service.IUserService;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 描述：自定义手机号登录授权模式
 * </p>
 * <p>版权：&copy;01星球</p>
 * <p>地址：01星球总部</p>
 * @author 阿伟学长
 * @version 1.0.0
 */
public class MobileTokenGranter extends AbstractTokenGranter {
    private static final String GRANT_TYPE = "mobile";

    private final IUserService userService;

    private final IRoleService roleService;

    public MobileTokenGranter(IUserService userService,
                              IRoleService roleService,
                              AuthorizationServerTokenServices tokenServices,
                              ClientDetailsService clientDetailsService,
                              OAuth2RequestFactory requestFactory) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        // 获取请求参数中的手机号
        Map<String, String> parameters = tokenRequest.getRequestParameters();
        String mobile = parameters.get("mobile");
        // 1 通过手机号查找用户对象
        User user = new User();
        user.setMobile(mobile);
        user = userService.getOne(new QueryWrapper<>(user));
        if (user == null) {
            throw new InvalidGrantException("无法获取用户信息");
        }
        // 2 通过用户ID获取角色列表
        List<Role> roles = roleService.listRoleByUserId(user.getId());
        // 3 构建权限角色对象
        SecurityUser su = SecurityUser.create(user, user.getUsername(), user.getPassword(), roles.stream().map(Role::getKeyword).collect(Collectors.toList()));
        // 4 构建OAuth2Authentication对象
        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(su, null, su.getAuthorities());
        authentication.setDetails(su);
        return new OAuth2Authentication(storedOAuth2Request, authentication);
    }
}
