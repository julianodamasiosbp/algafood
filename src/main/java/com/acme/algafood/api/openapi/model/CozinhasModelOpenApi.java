package com.acme.algafood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.acme.algafood.api.model.response.CidadeModel;
import com.acme.algafood.api.model.response.CozinhaModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel("CozinhasModel")
@Getter
@Setter
public class CozinhasModelOpenApi {

    private CozinhasEmbeddedModelOpenApi _embedded;
    private Links _links;
    private PageModelOpenApi page;

    @Api(tags = "CozinhasEmbeddedModel")
    @Data
    public class CozinhasEmbeddedModelOpenApi {
        private List<CozinhaModel> cozinhas;
    }

}
