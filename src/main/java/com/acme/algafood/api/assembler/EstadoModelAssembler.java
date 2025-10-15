package com.acme.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.acme.algafood.api.controller.EstadoController;
import com.acme.algafood.api.model.response.EstadoModel;
import com.acme.algafood.domain.model.Estado;

@Component
public class EstadoModelAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoModel> {

    public EstadoModelAssembler() {
        super(EstadoController.class, EstadoModel.class);
    }

    @Autowired
    private ModelMapper modelMapper;

    public EstadoModel toModel(Estado estado) {
        EstadoModel estadoModel = modelMapper.map(estado, EstadoModel.class);

        estadoModel.add(linkTo(EstadoController.class)
                .slash(estadoModel.getId()).withSelfRel());

        estadoModel.add(linkTo(EstadoController.class)
                .withRel("estados"));

        return estadoModel;
    }

    public CollectionModel<EstadoModel> toCollectionModel(Iterable<? extends Estado> entities) {
        return super.toCollectionModel(entities).add(linkTo(EstadoController.class).withSelfRel());

    }

}
