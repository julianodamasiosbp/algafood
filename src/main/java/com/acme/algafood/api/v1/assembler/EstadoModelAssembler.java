package com.acme.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.acme.algafood.api.v1.AlgafoodLinks;
import com.acme.algafood.api.v1.controller.EstadoController;
import com.acme.algafood.api.v1.model.response.EstadoModel;
import com.acme.algafood.domain.model.Estado;

@Component
public class EstadoModelAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoModel> {

    public EstadoModelAssembler() {
        super(EstadoController.class, EstadoModel.class);
    }

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgafoodLinks algaLinks;

    public EstadoModel toModel(Estado estado) {
        EstadoModel estadoModel = createModelWithId(estado.getId(), estado);
        modelMapper.map(estado, estadoModel);

        estadoModel.add(algaLinks.linkToEstados("estados"));

        return estadoModel;
    }

    @Override
    public CollectionModel<EstadoModel> toCollectionModel(Iterable<? extends Estado> entities) {
        return super.toCollectionModel(entities)
                .add(algaLinks.linkToEstados());
    }

}
