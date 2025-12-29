package com.acme.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.acme.algafood.api.v1.AlgafoodLinks;
import com.acme.algafood.api.v1.controller.CozinhaController;
import com.acme.algafood.api.v1.model.response.CozinhaModel;
import com.acme.algafood.core.security.AlgaSecurity;
import com.acme.algafood.domain.model.Cozinha;

@Component
public class CozinhaModelAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModel> {

    public CozinhaModelAssembler() {
        super(CozinhaController.class, CozinhaModel.class);
    }

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgafoodLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    @Override
    public CozinhaModel toModel(Cozinha cozinha) {
        CozinhaModel cozinhaModel = createModelWithId(cozinha.getId(), cozinha);
        modelMapper.map(cozinha, cozinhaModel);

        if (algaSecurity.podeConsultarCozinhas()) {
            cozinhaModel.add(algaLinks.linkToCozinhas("cozinhas"));
        }

        return cozinhaModel;
    }

}
