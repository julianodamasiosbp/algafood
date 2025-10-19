package com.acme.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.acme.algafood.api.AlgafoodLinks;
import com.acme.algafood.api.controller.CidadeController;
import com.acme.algafood.api.model.response.CidadeModel;
import com.acme.algafood.domain.model.Cidade;

@Component
public class CidadeModelAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeModel> {

    public CidadeModelAssembler() {
        super(CidadeController.class, CidadeModel.class);
    }

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgafoodLinks algaLinks;

    @Override
    public CidadeModel toModel(Cidade cidade) {

        CidadeModel cidadeModel = createModelWithId(cidade.getId(), cidade);

        modelMapper.map(cidade, cidadeModel);

        cidadeModel.add(algaLinks.linkToCidades("cidades"));

        cidadeModel.getEstado().add(algaLinks.linkToEstado(cidadeModel.getEstado().getId()));

        return cidadeModel;
    }

    @Override
    public CollectionModel<CidadeModel> toCollectionModel(Iterable<? extends Cidade> entities) {
        return super.toCollectionModel(entities).add(linkTo(CidadeController.class).withSelfRel());
    }

    // public List<CidadeModel> toCollectionModel(List<Cidade> cidades) {
    // return cidades
    // .stream()
    // .map(cidade -> toModel(cidade))
    // .collect(Collectors.toList());
    // }

}
