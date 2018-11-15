package com.dxb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 认证服务器配置
 *
 * @author lxg
 * <p>
 * 2017年2月17日上午10:50:04
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private static String REALM = "MY_OAUTH_REALM";

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private UserApprovalHandler userApprovalHandler;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Override//定义客户端详细信息服务的配置器。客户详细信息可以初始化，或者可以引用现有的 store
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client")//客户端ID
                .secret("$2a$10$9pP7kHKV853BLfYxc8WduOiI69A.bVzXTU6XRU.4nbt8M0GxRH9Oq")//密码
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")//默认值为空
//			.authorities("ROLE_CLIENT", "admin")//授权给客户的认证（常规 Spring Security 认证）
                .scopes("read", "write", "trust")//授权用户的操作权限,客户范围限制。如果范围未定义或为空（默认），客户端将不受范围限制
                .accessTokenValiditySeconds(120).//token有效期为120秒
                refreshTokenValiditySeconds(600);//刷新token有效期为600秒
    }

    @Override//定义授权和令牌端点以及令牌服务
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        authenticationManager：通过注入 AuthenticationManager 来开启密码授权。
//        userDetailsService：如果你注入一个 UserDetailsService，或者全局地配置了一个UserDetailsService（例如在 GlobalAuthenticationManagerConfigurer中），那么刷新令牌授权将包含对用户详细信息的检查，以确保该帐户仍然是活动的
//        authorizationCodeServices：为授权代码授权定义授权代码服务（AuthorizationCodeServices 的实例）。
//        implicitGrantService：在 imlpicit 授权期间管理状态。
//        tokenGranter：TokenGranter（完全控制授予和忽略上面的其他属性）
        endpoints
                .tokenStore(tokenStore)//
                .userApprovalHandler(userApprovalHandler)
                .authenticationManager(authenticationManager);//通过注入 AuthenticationManager 来开启密码授权。
    }

    @Override//定义令牌端点上的安全约束
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//		oauthServer
//				.tokenKeyAccess("permitAll()")
//				.checkTokenAccess("permitAll()")
//				.allowFormAuthenticationForClients();

        oauthServer.allowFormAuthenticationForClients();
//		oauthServer.realm(REALM+"/client");
    }

}