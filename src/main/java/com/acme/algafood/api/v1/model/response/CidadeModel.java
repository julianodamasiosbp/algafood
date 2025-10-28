package com.acme.algafood.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cidades")
@ApiModel(value = "Cidade", description = "Representa uma cidade")
@Getter
@Setter
public class CidadeModel extends RepresentationModel<CidadeModel> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(value = "Nome da cidade", example = "Uberlândia")
    private String nome;

    private EstadoModel estado;

}
