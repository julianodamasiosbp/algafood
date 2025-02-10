package com.acme.algafood.api.model.request;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class produtoInput {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotNull
    @Min(value = 0, message = "O preço não pode ser menor do que 0")
    private BigDecimal preco;

    @NotNull
    private boolean ativo;

}
