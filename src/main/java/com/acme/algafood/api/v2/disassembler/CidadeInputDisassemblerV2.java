package com.acme.algafood.api.v2.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.acme.algafood.api.v2.model.request.CidadeInputV2;
import com.acme.algafood.domain.model.Cidade;
import com.acme.algafood.domain.model.Estado;

@Component
public class CidadeInputDisassemblerV2 {

    @Autowired
    private ModelMapper modelMapper;

    public Cidade toDomainObject(CidadeInputV2 cidadeInputV2) {
        return modelMapper.map(cidadeInputV2, Cidade.class);
    }

    public void copyToDomainObject(CidadeInputV2 cidadeInputV2, Cidade cidade) {
        cidade.setEstado(new Estado());
        modelMapper.map(cidadeInputV2, cidade);
    }

}
