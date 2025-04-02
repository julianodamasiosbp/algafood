package com.acme.algafood.domain.service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.algafood.domain.enums.StatusPedido;
import com.acme.algafood.domain.exception.NegocioException;
import com.acme.algafood.domain.model.Pedido;

@Service
public class FluxoPedidoService {

    @Autowired
    private PedidoService pedidoService;

    @Transactional
    public void confirmar(long pedidoId) {
        Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);

        if (!pedido.getStatus().equals(StatusPedido.CRIADO)) {
            throw new NegocioException(String.format("Status do pedido %d não pode ser alterado de %s para %s",
                    pedido.getId(), pedido.getStatus().getDescricao(), StatusPedido.CONFIRMADO.getDescricao()));
        }

        pedido.setStatus(StatusPedido.CONFIRMADO);
        pedido.setDataConfirmacao(OffsetDateTime.now());
    }

}
