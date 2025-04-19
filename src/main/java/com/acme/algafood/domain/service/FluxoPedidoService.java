package com.acme.algafood.domain.service;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.algafood.domain.model.Pedido;
import com.acme.algafood.domain.service.EnvioEmailService.Mensagem;

@Service
public class FluxoPedidoService {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private EnvioEmailService envioEmailService;

    @Transactional
    public void confirmar(String codigoPedido) {
        Pedido pedido = pedidoService.buscarOuFalhar(codigoPedido);

        pedido.confirmar();

        var mensagem = Mensagem.builder().assunto(pedido.getRestaurante().getNome() + "-" + "Pedido confirmado")
                .corpo("O pedido de código <strong>" + pedido.getCodigo() + "</strong> foi confirmado pela loja")
                .destinatario(pedido.getCliente().getEmail()).build();

        envioEmailService.enviar(mensagem);
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
