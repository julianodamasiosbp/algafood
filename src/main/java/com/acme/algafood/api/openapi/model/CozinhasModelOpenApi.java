package com.acme.algafood.api.openapi.model;

import java.util.List;

import com.acme.algafood.api.model.response.CozinhaModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("CozinhasModel")
@Getter
@Setter
public class CozinhasModelOpenApi {

    private List<CozinhaModel> content;

    @ApiModelProperty(example = "10", value = "Quantidade de registro por página")
    private Long size;

    @ApiModelProperty(example = "50", value = "Total de registros")
    private Long totalElements;

    @ApiModelProperty(example = "5", value = "Total de páginas")
    private Long totalPages;

    @ApiModelProperty(example = "0", value = "Número da página (começa em zero)")
    private Long number;

}
