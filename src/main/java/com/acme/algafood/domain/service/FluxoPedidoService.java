package com.acme.algafood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.algafood.domain.model.Pedido;

@Service
public class FluxoPedidoService {

    @Autowired
    private PedidoService pedidoService;

    @Transactional
    public void confirmar(long pedidoId) {
        Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);

        pedido.confirmar();
    }

    @Transactional
    public void entregar(long pedidoId) {
        Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);

        pedido.entregar();
    }

    @Transactional
    public void cancelar(long pedidoId) {
        Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);

        pedido.cancelar();
    }

}
