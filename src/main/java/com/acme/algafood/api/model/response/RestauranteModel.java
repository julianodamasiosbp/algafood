package com.acme.algafood.api.model.response;

import java.math.BigDecimal;

import com.acme.algafood.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteModel {

    @ApiModelProperty(example = "1")
    @JsonView(RestauranteView.Resumo.class)
    private Long id;

    @ApiModelProperty(example = "Thai Gourmet")
    @JsonView(RestauranteView.Resumo.class)
    private String nome;

    @ApiModelProperty(example = "12.00")
    @JsonView(RestauranteView.Resumo.class)
    private BigDecimal taxaFrete;

    @JsonView(RestauranteView.Resumo.class)
    private CozinhaModel cozinha;

    private Boolean ativo;

    private Boolean aberto;

    private EnderecoModel endereco;

}
