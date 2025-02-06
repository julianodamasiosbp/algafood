package com.acme.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.acme.algafood.api.model.response.UsuarioModel;
import com.acme.algafood.domain.model.Usuario;

@Component
public class UsuarioInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Usuario toDomainObject(UsuarioModel usuarioModel) {
        return modelMapper.map(usuarioModel, Usuario.class);
    }

    public void CopyToDomainObject(UsuarioModel usuarioModel, Usuario usuario) {
        modelMapper.map(usuarioModel, usuario);
    }

}
