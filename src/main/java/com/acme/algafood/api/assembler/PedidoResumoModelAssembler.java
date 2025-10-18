package com.acme.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.acme.algafood.api.controller.PedidoController;
import com.acme.algafood.api.controller.RestauranteController;
import com.acme.algafood.api.controller.UsuarioController;
import com.acme.algafood.api.model.response.PedidoResumoModel;
import com.acme.algafood.domain.model.Pedido;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel> {

    public PedidoResumoModelAssembler() {
        super(PedidoController.class, PedidoResumoModel.class);
        // TODO Auto-generated constructor stub
    }

    @Autowired
    private ModelMapper modelMapper;

    public PedidoResumoModel toModel(Pedido pedido) {
        PedidoResumoModel pedidoResumoModel = createModelWithId(pedido.getCodigo(), pedido);

        modelMapper.map(pedido, pedidoResumoModel);

        pedidoResumoModel.add(linkTo(PedidoController.class).withRel("pedidos"));

        pedidoResumoModel.getRestaurante().add(linkTo(methodOn(RestauranteController.class)
                .buscar(pedido.getRestaurante().getId())).withSelfRel());

        pedidoResumoModel.getCliente().add(linkTo(methodOn(UsuarioController.class)
                .buscar(pedido.getCliente().getId())).withSelfRel());

        return pedidoResumoModel;
    }

    // public List<PedidoResumoModel> toCollectionModel(List<Pedido> pedidos) {
    // return pedidos.stream()
    // .map(pedido -> toModel(pedido))
    // .collect(Collectors.toList());
    // }

}
