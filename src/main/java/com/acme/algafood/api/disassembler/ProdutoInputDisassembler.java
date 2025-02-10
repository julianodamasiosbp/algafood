package com.acme.algafood.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.acme.algafood.api.model.response.ProdutoModel;
import com.acme.algafood.domain.model.Produto;

@Component
public class ProdutoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Produto toDomainObject(ProdutoModel produtoModel) {
        return modelMapper.map(produtoModel, Produto.class);
    }

    public void copyToDomainObject(ProdutoModel produtoModel, Produto produto) {
        modelMapper.map(produtoModel, produto);
    }

}
