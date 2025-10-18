package com.acme.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.acme.algafood.api.controller.CidadeController;
import com.acme.algafood.api.controller.FormaPagamentoController;
import com.acme.algafood.api.controller.PedidoController;
import com.acme.algafood.api.controller.RestauranteController;
import com.acme.algafood.api.controller.RestauranteProdutoController;
import com.acme.algafood.api.controller.UsuarioController;
import com.acme.algafood.api.model.response.PedidoModel;
import com.acme.algafood.domain.model.Pedido;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

        public PedidoModelAssembler() {
                super(PedidoController.class, PedidoModel.class);
        }

        @Autowired
        private ModelMapper modelMapper;

        public PedidoModel toModel(Pedido pedido) {
                PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);

                modelMapper.map(pedido, pedidoModel);

                pedidoModel.add(linkTo(PedidoController.class).withRel("pedidos"));

                pedidoModel.getRestaurante().add(linkTo(methodOn(RestauranteController.class)
                                .buscar(pedido.getRestaurante().getId())).withSelfRel());

                pedidoModel.getCliente().add(linkTo(methodOn(UsuarioController.class)
                                .buscar(pedido.getCliente().getId())).withSelfRel());

                pedidoModel.getFormaPagamento()
                                .add(linkTo(methodOn(FormaPagamentoController.class)
                                                .buscar(pedido.getFormaPagamento().getId(), null)).withSelfRel());

                pedidoModel.getEnderecoEntrega().getCidade()
                                .add(linkTo(methodOn(CidadeController.class)
                                                .buscar(pedido.getEnderecoEntrega().getCidade().getId()))
                                                .withSelfRel());

                pedidoModel.getItens().forEach(item -> {
                        item.add(linkTo(methodOn(RestauranteProdutoController.class)
                                        .buscar(pedidoModel.getRestaurante().getId(), item.getProdutoId()))
                                        .withRel("produto"));
                });

                return pedidoModel;
        }

        public List<PedidoModel> toCollectionModel(List<Pedido> pedidos) {
                return pedidos.stream()
                                .map(pedido -> toModel(pedido))
                                .collect(Collectors.toList());
        }

}
