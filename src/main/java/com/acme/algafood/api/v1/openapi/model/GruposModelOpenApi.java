package com.acme.algafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.acme.algafood.api.v1.model.response.GrupoModel;

import lombok.Data;

@Data
public class GruposModelOpenApi {

    private GruposEmbeddedModelOpenApi _embedded;
    private Links _links;

    @Data
    public class GruposEmbeddedModelOpenApi {

        private List<GrupoModel> grupos;

    }

}
