package com.acme.algafood.domain.enums;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {
    CRIADO("CRIADO"),
    CONFIRMADO("CONFIRMADO", CRIADO),
    ENTREGUE("ENTREGUE", CONFIRMADO),
    CANCELADO("CANCELADO", CRIADO);

    private String descricao;
    private List<StatusPedido> statusAnteriores;

    StatusPedido(String descricao, StatusPedido... StatusAnteriores) {
        this.descricao = descricao;
        this.statusAnteriores = Arrays.asList(StatusAnteriores);
    }

    public String getDescricao() {
        return this.descricao;
    }

    public boolean naoPodeAlterarStatusPara(StatusPedido novoStatus) {
        return !novoStatus.statusAnteriores.contains(this);
    }

    public boolean podeAlterarStatusPara(StatusPedido novoStatus) {
        return !naoPodeAlterarStatusPara(novoStatus);
    }
}
