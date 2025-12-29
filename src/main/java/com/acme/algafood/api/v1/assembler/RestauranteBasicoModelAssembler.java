package com.acme.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.acme.algafood.api.v1.AlgafoodLinks;
import com.acme.algafood.api.v1.controller.RestauranteController;
import com.acme.algafood.api.v1.model.response.RestauranteBasicoModel;
import com.acme.algafood.core.security.AlgaSecurity;
import com.acme.algafood.domain.model.Restaurante;

@Component
public class RestauranteBasicoModelAssembler
        extends RepresentationModelAssemblerSupport<Restaurante, RestauranteBasicoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgafoodLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    public RestauranteBasicoModelAssembler() {
        super(RestauranteController.class, RestauranteBasicoModel.class);
    }

    @Override
    public RestauranteBasicoModel toModel(Restaurante restaurante) {
        RestauranteBasicoModel restauranteModel = createModelWithId(
                restaurante.getId(), restaurante);

        modelMapper.map(restaurante, restauranteModel);

        if (algaSecurity.podeConsultarRestaurantes()) {
            restauranteModel.add(algaLinks.linkToRestaurantes("restaurantes"));
        }

        if (algaSecurity.podeConsultarCozinhas()) {
            restauranteModel.getCozinha().add(
                    algaLinks.linkToCozinha(restaurante.getCozinha().getId()));
        }

        return restauranteModel;
    }

    @Override
    public CollectionModel<RestauranteBasicoModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
        CollectionModel<RestauranteBasicoModel> collectionModel = super.toCollectionModel(entities);

        if (algaSecurity.podeConsultarRestaurantes()) {
            collectionModel.add(algaLinks.linkToRestaurantes());
        }

        return collectionModel;
    }
}