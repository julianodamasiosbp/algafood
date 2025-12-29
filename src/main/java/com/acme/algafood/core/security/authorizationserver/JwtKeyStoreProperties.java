package com.acme.algafood.core.security.authorizationserver;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@Component
@ConfigurationProperties("algafood.jwt.keystore")
public class JwtKeyStoreProperties {

    @NotNull
    private Resource jksLocation;
    private String password;
    private String keypairAlias;

    public Resource getJksLocation() {
        return jksLocation;
    }

    public String getPassword() {
        return password;
    }

    public String getKeypairAlias() {
        return keypairAlias;
    }

    public void setJksLocation(Resource jksLocation) {
        this.jksLocation = jksLocation;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setKeypairAlias(String keypairAlias) {
        this.keypairAlias = keypairAlias;
    }
}