package com.acme.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.acme.algafood.api.model.response.EnderecoModel;
import com.acme.algafood.domain.model.Endereco;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoModel.class);

        enderecoToEnderecoModelTypeMap.<String>addMapping(
                enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
                (enderecoModelDest, enderecoSrcValue) -> enderecoModelDest.getCidade().setEstado(enderecoSrcValue));
        return modelMapper;
    }

}
