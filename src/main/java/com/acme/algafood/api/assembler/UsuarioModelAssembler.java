package com.acme.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.acme.algafood.api.controller.CidadeController;
import com.acme.algafood.api.controller.UsuarioController;
import com.acme.algafood.api.controller.UsuarioGrupoController;
import com.acme.algafood.api.model.response.CidadeModel;
import com.acme.algafood.api.model.response.UsuarioModel;
import com.acme.algafood.domain.model.Cidade;
import com.acme.algafood.domain.model.Usuario;

@Component
public class UsuarioModelAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioModel> {

    public UsuarioModelAssembler() {
        super(UsuarioController.class, UsuarioModel.class);
    }

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioModel toModel(Usuario usuario) {
        UsuarioModel usuarioModel = modelMapper.map(usuario, UsuarioModel.class);

        usuarioModel.add(linkTo(UsuarioController.class)
                .slash(usuarioModel.getId()).withSelfRel());

        usuarioModel.add(linkTo(UsuarioController.class)
                .withRel("usuarios"));

        usuarioModel.add(linkTo(methodOn(UsuarioGrupoController.class)
                .listar(usuarioModel.getId())).withRel("grupos-usuario"));

        return usuarioModel;
    }

    public CollectionModel<UsuarioModel> toCollectionModel(Iterable<? extends Usuario> entities) {
        return super.toCollectionModel(entities).add(linkTo(UsuarioController.class).withSelfRel());
    }

}
