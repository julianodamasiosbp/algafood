package com.acme.algafood.api.v1.openapi.model;

import java.math.BigDecimal;

import com.acme.algafood.api.v1.model.response.CozinhaModel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteBasicoModelOpenApi {

	private Long id;

	private String nome;

	private BigDecimal taxaFrete;

	private CozinhaModel cozinha;

}
