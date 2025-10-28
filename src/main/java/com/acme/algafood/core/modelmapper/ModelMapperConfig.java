package com.acme.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.acme.algafood.api.v1.model.request.ItemPedidoInput;
import com.acme.algafood.api.v1.model.response.EnderecoModel;
import com.acme.algafood.api.v2.model.request.CidadeInputV2;
import com.acme.algafood.domain.model.Cidade;
import com.acme.algafood.domain.model.Endereco;
import com.acme.algafood.domain.model.ItemPedido;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(CidadeInputV2.class, Cidade.class)
                .addMappings(mapper -> mapper.skip(Cidade::setId));

        var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoModel.class);

        modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
                .addMappings(mapper -> mapper.skip(ItemPedido::setId));

        enderecoToEnderecoModelTypeMap.<String>addMapping(
                enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
                (enderecoModelDest, enderecoSrcValue) -> enderecoModelDest.getCidade().setEstado(enderecoSrcValue));

        return modelMapper;
    }

}
