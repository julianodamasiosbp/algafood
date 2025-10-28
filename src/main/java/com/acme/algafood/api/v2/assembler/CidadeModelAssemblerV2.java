package com.acme.algafood.api.v2.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.acme.algafood.api.v2.AlgafoodLinksV2;
import com.acme.algafood.api.v2.controller.CidadeControllerV2;
import com.acme.algafood.api.v2.model.response.CidadeModelV2;
import com.acme.algafood.domain.model.Cidade;

@Component
public class CidadeModelAssemblerV2 extends RepresentationModelAssemblerSupport<Cidade, CidadeModelV2> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgafoodLinksV2 algaLinksV2;

    public CidadeModelAssemblerV2() {
        super(CidadeControllerV2.class, CidadeModelV2.class);
    }

    @Override
    public CidadeModelV2 toModel(Cidade cidade) {
        CidadeModelV2 cidadeModel = createModelWithId(cidade.getId(), cidade);

        modelMapper.map(cidade, cidadeModel);

        cidadeModel.add(algaLinksV2.linkToCidades("cidades"));

        return cidadeModel;
    }

    @Override
    public CollectionModel<CidadeModelV2> toCollectionModel(Iterable<? extends Cidade> entities) {
        return super.toCollectionModel(entities)
                .add(algaLinksV2.linkToCidades());
    }
}
