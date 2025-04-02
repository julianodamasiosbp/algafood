package com.acme.algafood.domain.enums;

public enum StatusPedido {
    CRIADO("CRIADO"),
    CONFIRMADO("CONFIRMADO"),
    ENTREGUE("ENTREGUE"),
    CANCELADO("CANCELADO");

    private String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
