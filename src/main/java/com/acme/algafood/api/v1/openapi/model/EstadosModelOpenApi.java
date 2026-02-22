package com.acme.algafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.acme.algafood.api.v1.model.response.EstadoModel;

import lombok.Data;

@Data
public class EstadosModelOpenApi {

    private EstadosEmbeddedModelOpenApi _embedded;
    private Links _links;

    @Data
    public class EstadosEmbeddedModelOpenApi {

        private List<EstadoModel> estados;

    }

}
