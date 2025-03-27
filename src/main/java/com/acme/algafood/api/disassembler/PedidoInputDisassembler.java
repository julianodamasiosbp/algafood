package com.acme.algafood.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.acme.algafood.api.model.request.PedidoInput;
import com.acme.algafood.domain.model.Pedido;

@Component
public class PedidoInputDisassembler {

    @Autowired
    private ModelMapper modelmapper;

    public Pedido toDomainObject(PedidoInput pedidoInput) {
        return modelmapper.map(pedidoInput, Pedido.class);
    }

    public void copyToObjectDomain(PedidoInput pedidoInput, Pedido pedido) {
        modelmapper.map(pedidoInput, pedido);
    }

}
