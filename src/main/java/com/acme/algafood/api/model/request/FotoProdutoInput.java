package com.acme.algafood.api.model.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoInput {

    private MultipartFile arquivo;

    private String descricao;

}
