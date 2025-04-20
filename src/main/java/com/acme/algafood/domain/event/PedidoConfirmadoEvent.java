package com.acme.algafood.domain.event;

import com.acme.algafood.domain.model.Pedido;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PedidoConfirmadoEvent {

    private Pedido pedido;

}
