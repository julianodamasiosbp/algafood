package com.acme.algafood.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoModel extends RepresentationModel<EstadoModel> {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

}
