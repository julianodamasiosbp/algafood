package com.acme.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.acme.algafood.api.v1.AlgafoodLinks;
import com.acme.algafood.api.v1.controller.PedidoController;
import com.acme.algafood.api.v1.model.response.PedidoResumoModel;
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

        pedidoModel.add(algaLinks.linkToPedidos("pedidos"));

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
