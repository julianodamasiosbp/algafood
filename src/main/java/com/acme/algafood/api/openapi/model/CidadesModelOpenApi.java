package com.acme.algafood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.acme.algafood.api.model.response.CidadeModel;

import io.swagger.annotations.Api;
import lombok.Data;

@Api(tags = "CidadesModel")
@Data
public class CidadesModelOpenApi {

    private CidadeEmbeddedModelOpenApi _embedded;

    private Links _links;

    @Api(tags = "CidadesEmbeddedModel")
    @Data
    public class CidadeEmbeddedModelOpenApi {
        private List<CidadeModel> cidades;
    }

}
