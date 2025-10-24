package com.acme.algafood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.acme.algafood.api.model.response.FormaPagamentoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

public class FormasPagamentoModelOpenApi {

    private FormasPagamentoEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("FormasPagamentoEmbeddedModel")
    @Data
    public class FormasPagamentoEmbeddedModelOpenApi {

        private List<FormaPagamentoModel> formasPagamento;

    }

}
