package com.acme.algafood.core.security.authorizationserver;

import java.util.HashMap;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

@SuppressWarnings("deprecation")
public class JwtCustomClaimsTokenEnhancer implements TokenEnhancer {

    @SuppressWarnings("deprecation")
    @Override
    public OAuth2AccessToken enhance(@SuppressWarnings("deprecation") OAuth2AccessToken accessToken,
            @SuppressWarnings("deprecation") OAuth2Authentication authentication) {

        if (authentication.getPrincipal() instanceof AuthUser) {
            var authUser = (AuthUser) authentication.getPrincipal();
            var info = new HashMap<String, Object>();

            info.put("nome_completo", authUser.getFullName());
            info.put("usuario_id", authUser.getUserId());

            var OAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;
            OAuth2AccessToken.setAdditionalInformation(info);
        }

        return accessToken;
    }

}
