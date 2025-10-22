package com.acme.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.acme.algafood.api.AlgafoodLinks;
import com.acme.algafood.api.controller.PedidoController;
import com.acme.algafood.api.model.response.PedidoModel;
import com.acme.algafood.domain.model.Pedido;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

        public PedidoModelAssembler() {
                super(PedidoController.class, PedidoModel.class);
        }

        @Autowired
        private ModelMapper modelMapper;

        @Autowired
        private AlgafoodLinks algaLinks;

        @Override
        public PedidoModel toModel(Pedido pedido) {
                PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
                modelMapper.map(pedido, pedidoModel);

                pedidoModel.add(algaLinks.linkToPedidos("pedidos"));

                if (pedido.podeSerConfirmado()) {
                        pedidoModel.add(algaLinks.linkToConfirmacaoPedido(pedido.getCodigo(), "confirmar"));
                }

                if (pedido.podeSerCancelado()) {
                        pedidoModel.add(algaLinks.linkToCancelamentoPedido(pedido.getCodigo(), "cancelar"));
                }

                if (pedido.podeSerEntregue()) {
                        pedidoModel.add(algaLinks.linkToEntregaPedido(pedido.getCodigo(), "entregar"));
                }

                pedidoModel.getRestaurante().add(
                                algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));

                pedidoModel.getCliente().add(
                                algaLinks.linkToUsuario(pedido.getCliente().getId()));

                pedidoModel.getFormaPagamento().add(
                                algaLinks.linkToFormaPagamento(pedido.getFormaPagamento().getId()));

                pedidoModel.getEnderecoEntrega().getCidade().add(
                                algaLinks.linkToCidade(pedido.getEnderecoEntrega().getCidade().getId()));

                pedidoModel.getItens().forEach(item -> {
                        item.add(algaLinks.linkToProduto(
                                        pedidoModel.getRestaurante().getId(), item.getProdutoId(), "produto"));
                });

                return pedidoModel;
        }

}
