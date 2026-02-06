package com.acme.algafood.core.security.authorizationserver;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;

@SuppressWarnings("deprecation")
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtKeyStoreProperties jwtKeyStoreProperties;

    @Autowired
    private DataSource dataSource;

    @SuppressWarnings("deprecation")
    @Override
    public void configure(@SuppressWarnings("deprecation") ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // security.checkTokenAccess("isAuthenticated()");
        security.checkTokenAccess("permitAll()")
                .tokenKeyAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void configure(@SuppressWarnings("deprecation") AuthorizationServerEndpointsConfigurer endpoints)
            throws Exception {

        var tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(new JwtCustomClaimsTokenEnhancer(), jwtAccessTokenConverter()));

        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .reuseRefreshTokens(false)
                .accessTokenConverter(jwtAccessTokenConverter())
                .tokenEnhancer(tokenEnhancerChain)
                .approvalStore(approvalStore(endpoints.getTokenStore()))
                .tokenGranter(tokenGranter(endpoints));
    }

    private ApprovalStore approvalStore(TokenStore tokenStore) {

        var approvalStore = new TokenApprovalStore();
        approvalStore.setTokenStore(tokenStore);
        return approvalStore;
    }

    private TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
        var pkceAuthorizationCodeTokenGranter = new PkceAuthorizationCodeTokenGranter(endpoints.getTokenServices(),
                endpoints.getAuthorizationCodeServices(), endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory());

        var granters = Arrays.asList(
                pkceAuthorizationCodeTokenGranter, endpoints.getTokenGranter());

        return new CompositeTokenGranter(granters);
    }

    @Bean
    public JWKSet jwkSet() {
        RSAKey.Builder builder = new RSAKey.Builder((RSAPublicKey) keyPair().getPublic())
                .keyUse(KeyUse.SIGNATURE)
                .algorithm(JWSAlgorithm.RS256)
                .keyID("algafood-key-id");
        return new JWKSet(builder.build());
    }

    private KeyPair keyPair() {
        // HMAC SHA-256
        // jwtAccessTokenConverter.setSigningKey("esta-sua-chave-deve-ter-32-chars!");
        var keyStorePass = jwtKeyStoreProperties.getPassword();
        var keyPairAlias = jwtKeyStoreProperties.getKeypairAlias();
        var keyStoreKeyFactory = new KeyStoreKeyFactory(jwtKeyStoreProperties.getJksLocation(), keyStorePass.toCharArray());
        var keyPair = keyStoreKeyFactory.getKeyPair(keyPairAlias);
        if (keyPair == null) {
            throw new IllegalStateException("KeyPair é nulo. Verifique alias e keystore. alias=" + keyPairAlias);
        }
        return keyPair;
    }

    public JwtAccessTokenConverter jwtAccessTokenConverter() {

        var jwtAccessTokenConverter = new JwtAccessTokenConverter();

        jwtAccessTokenConverter.setKeyPair(keyPair());
        return jwtAccessTokenConverter;
    }

    // @SuppressWarnings("deprecation")
    // @Override
    // public void configure(@SuppressWarnings("deprecation") ClientDetailsServiceConfigurer clients) throws Exception {
    //     clients.inMemory()
    //             .withClient("algafood-web")
    //             .secret(passwordEncoder.encode("web123"))
    //             .authorizedGrantTypes("password", "refresh_token")
    //             .scopes("READ", "WRITE")
    //             .accessTokenValiditySeconds(6 * 60 * 60) // 6 hours
    //             .refreshTokenValiditySeconds(60 * 24 * 60 * 30) // 60 days
    //             .and()
    //             .withClient("faturamento")
    //             .secret(passwordEncoder.encode("faturamento123"))
    //             .authorizedGrantTypes("client_credentials")
    //             .scopes("READ", "WRITE")
    //             .and()
    //             .withClient("foodanalytics")
    //             .secret(passwordEncoder.encode("food123"))
    //             .authorizedGrantTypes("authorization_code")
    //             .scopes("READ", "WRITE")
    //             .redirectUris("http://localhost:8082")
    //             .and()
    //             .withClient("webadmin")
    //             .authorizedGrantTypes("implicit")
    //             .scopes("READ", "WRITE")
    //             .redirectUris("http://aplicacao-cliente")
    //             .and()
    //             .withClient("checktoken")
    //             .secret(passwordEncoder.encode("check123"));
    // }

}