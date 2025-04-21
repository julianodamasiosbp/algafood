package com.acme.algafood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.acme.algafood.domain.event.PedidoCanceladoEvent;
import com.acme.algafood.domain.event.PedidoConfirmadoEvent;
import com.acme.algafood.domain.model.Pedido;
import com.acme.algafood.domain.service.EnvioEmailService;
import com.acme.algafood.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoClientePedidoCanceladoListener {

    @Autowired
    private EnvioEmailService envioEmailService;

    /*
     * @EventListener -> Os eventos serão enviados INSTANTE antes do repository
     * fazer o flush
     */
    @TransactionalEventListener // Os eventos são enviado DEPOIS do repository fazer o flush
    public void aoCancelarPedido(PedidoCanceladoEvent event) {
        Pedido pedido = event.getPedido();
        var mensagem = Mensagem.builder().assunto(pedido.getRestaurante().getNome() + " - " + "Pedido cancelado")
                .corpo("pedido-cancelado.html")
                .variavel("pedido", pedido)
                .destinatario(pedido.getCliente().getEmail()).build();

        envioEmailService.enviar(mensagem);

    }

}
