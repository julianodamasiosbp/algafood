package com.acme.algafood.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grupos/{grupoId}")
public class GrupoPermissaoController {

    @GetMapping("/permissao")
    public String hello() {
        return "Hello World!";
    }

}
