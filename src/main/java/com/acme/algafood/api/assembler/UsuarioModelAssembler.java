package com.acme.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.acme.algafood.api.AlgafoodLinks;
import com.acme.algafood.api.controller.UsuarioController;
import com.acme.algafood.api.model.response.UsuarioModel;
import com.acme.algafood.domain.model.Usuario;

@Component
public class UsuarioModelAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioModel> {

    public UsuarioModelAssembler() {
        super(UsuarioController.class, UsuarioModel.class);
    }

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgafoodLinks algaLinks;

    public UsuarioModel toModel(Usuario usuario) {
        UsuarioModel usuarioModel = createModelWithId(usuario.getId(), usuario);
        modelMapper.map(usuario, usuarioModel);

        usuarioModel.add(algaLinks.linkToUsuarios("usuarios"));

        usuarioModel.add(algaLinks.linkToGruposUsuario(usuario.getId(), "grupos-usuario"));

        return usuarioModel;
    }

    @Override
    public CollectionModel<UsuarioModel> toCollectionModel(Iterable<? extends Usuario> entities) {
        return super.toCollectionModel(entities)
                .add(algaLinks.linkToUsuarios());
    }

}
