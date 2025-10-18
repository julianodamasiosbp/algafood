package com.acme.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.acme.algafood.api.controller.RestauranteController;
import com.acme.algafood.api.model.response.RestauranteModel;
import com.acme.algafood.domain.model.Restaurante;

@Component
public class RestauranteModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel> {

    public RestauranteModelAssembler() {
        super(RestauranteController.class, RestauranteModel.class);
    }

    @Autowired
    private ModelMapper modelMapper;

    public RestauranteModel toModel(Restaurante restaurante) {
        RestauranteModel restauranteModel = createModelWithId(restaurante.getId(), restaurante);

        modelMapper.map(restaurante, restauranteModel);

        restauranteModel.add(linkTo(RestauranteController.class).withRel("restaurantes"));

        return restauranteModel;
    }

    // public List<RestauranteModel> toCollectionModel(List<Restaurante>
    // restaurantes) {
    // return restaurantes
    // .stream()
    // .map(restaurante -> toModel(restaurante))
    // .collect(Collectors.toList());
    // }

}
