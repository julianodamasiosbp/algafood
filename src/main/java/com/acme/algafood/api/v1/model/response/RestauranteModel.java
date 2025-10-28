package com.acme.algafood.api.v1.model.response;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "restaurantes")
@Getter
@Setter
public class RestauranteModel extends RepresentationModel<RestauranteModel> {

    // @JsonView(RestauranteView.Resumo.class)
    @ApiModelProperty(example = "1")
    private Long id;

    // @JsonView(RestauranteView.Resumo.class)
    @ApiModelProperty(example = "Thai Gourmet")
    private String nome;

    // @JsonView(RestauranteView.Resumo.class)
    @ApiModelProperty(example = "12.00")
    private BigDecimal taxaFrete;

    // @JsonView(RestauranteView.Resumo.class)
    private CozinhaModel cozinha;

    private Boolean ativo;

    private Boolean aberto;

    private EnderecoModel endereco;

}
