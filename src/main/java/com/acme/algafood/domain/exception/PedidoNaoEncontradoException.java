package com.acme.algafood.domain.exception;

public class PedidoNaoEncontradoException extends NegocioException {

    private static final long serialVersionUID = 1L;

    public PedidoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public PedidoNaoEncontradoException(Long pedidoId) {
        this(String.format("Não existe um cadastro de pedido com código %d", pedidoId));
    }

}
