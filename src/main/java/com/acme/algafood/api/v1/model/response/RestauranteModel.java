package com.acme.algafood.api.v1.model.response;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "restaurantes")
@Getter
@Setter
public class RestauranteModel extends RepresentationModel<RestauranteModel> {

    // @JsonView(RestauranteView.Resumo.class)
    private Long id;

    // @JsonView(RestauranteView.Resumo.class)
    private String nome;

    // @JsonView(RestauranteView.Resumo.class)
    private BigDecimal taxaFrete;

    // @JsonView(RestauranteView.Resumo.class)
    private CozinhaModel cozinha;

    private Boolean ativo;

    private Boolean aberto;

    private EnderecoModel endereco;

}
