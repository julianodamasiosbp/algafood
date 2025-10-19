package com.acme.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.acme.algafood.api.AlgafoodLinks;
import com.acme.algafood.api.controller.PedidoController;
import com.acme.algafood.api.controller.RestauranteController;
import com.acme.algafood.api.controller.UsuarioController;
import com.acme.algafood.api.model.response.PedidoResumoModel;
import com.acme.algafood.domain.model.Pedido;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel> {

    public PedidoResumoModelAssembler() {
        super(PedidoController.class, PedidoResumoModel.class);
    }

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgafoodLinks algaLinks;

    public PedidoResumoModel toModel(Pedido pedido) {
        PedidoResumoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoModel);

        pedidoModel.add(algaLinks.linkToPedidos());

        pedidoModel.getRestaurante().add(
                algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));

        pedidoModel.getCliente().add(algaLinks.linkToUsuario(pedido.getCliente().getId()));

        return pedidoModel;
    }

    // public List<PedidoResumoModel> toCollectionModel(List<Pedido> pedidos) {
    // return pedidos.stream()
    // .map(pedido -> toModel(pedido))
    // .collect(Collectors.toList());
    // }

}
