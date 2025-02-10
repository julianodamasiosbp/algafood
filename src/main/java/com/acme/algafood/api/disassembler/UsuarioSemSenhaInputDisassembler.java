package com.acme.algafood.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.acme.algafood.api.model.request.UsuarioSemSenhaInput;
import com.acme.algafood.domain.model.Usuario;

@Component
public class UsuarioSemSenhaInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Usuario toDomainObject(UsuarioSemSenhaInput usuarioSemSenhaInput) {
        return modelMapper.map(usuarioSemSenhaInput, Usuario.class);
    }

    public void copyToDomainObject(UsuarioSemSenhaInput usuarioSemSenhaInput, Usuario usuario) {
        modelMapper.map(usuarioSemSenhaInput, usuario);
    }

}
