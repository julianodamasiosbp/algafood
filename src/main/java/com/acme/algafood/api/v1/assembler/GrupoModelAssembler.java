package com.acme.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.acme.algafood.api.v1.AlgafoodLinks;
import com.acme.algafood.api.v1.controller.GrupoController;
import com.acme.algafood.api.v1.model.response.GrupoModel;
import com.acme.algafood.domain.model.Grupo;

@Component
public class GrupoModelAssembler
        extends RepresentationModelAssemblerSupport<Grupo, GrupoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgafoodLinks algaLinks;

    public GrupoModelAssembler() {
        super(GrupoController.class, GrupoModel.class);
    }

    @Override
    public GrupoModel toModel(Grupo grupo) {
        GrupoModel grupoModel = createModelWithId(grupo.getId(), grupo);
        modelMapper.map(grupo, grupoModel);

        grupoModel.add(algaLinks.linkToGrupos("grupos"));

        grupoModel.add(algaLinks.linkToGrupoPermissoes(grupo.getId(), "permissoes"));

        return grupoModel;
    }

    @Override
    public CollectionModel<GrupoModel> toCollectionModel(Iterable<? extends Grupo> entities) {
        return super.toCollectionModel(entities)
                .add(algaLinks.linkToGrupos());
    }

}
