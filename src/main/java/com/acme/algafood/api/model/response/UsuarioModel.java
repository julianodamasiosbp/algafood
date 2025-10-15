package com.acme.algafood.api.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "usuarios")
@Getter
@Setter
public class UsuarioModel extends RepresentationModel<UsuarioModel> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "João Maria")
    private String nome;

    @ApiModelProperty(example = "joaomaria@acme.com")
    private String email;

}
