package com.acme.algafood.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.acme.algafood.api.model.response.PedidoModel;
import com.acme.algafood.domain.model.Pedido;

@Component
public class PedidoInputDisassembler {

    @Autowired
    private ModelMapper modelmapper;

    public Pedido toDomain(PedidoModel pedidoModel) {
        return modelmapper.map(pedidoModel, Pedido.class);
    }

    public void copyToObjectDomain(PedidoModel pedidoModel, Pedido pedido) {
        modelmapper.map(pedidoModel, pedido);
    }

}
