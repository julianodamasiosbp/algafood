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
    public void confirmar(String codigoPedido) {
        Pedido pedido = pedidoService.buscarOuFalhar(codigoPedido);

        pedido.confirmar();
    }

    @Transactional
    public void entregar(String codigoPedido) {
        Pedido pedido = pedidoService.buscarOuFalhar(codigoPedido);

        pedido.entregar();
    }

    @Transactional
    public void cancelar(String codigoPedido) {
        Pedido pedido = pedidoService.buscarOuFalhar(codigoPedido);

        pedido.cancelar();
    }

}
