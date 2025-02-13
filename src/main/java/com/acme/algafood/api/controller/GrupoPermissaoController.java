package com.acme.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.algafood.api.assembler.PermissaoModelAssembler;
import com.acme.algafood.api.model.response.PermissaoModel;
import com.acme.algafood.domain.model.Grupo;
import com.acme.algafood.domain.service.GrupoService;

@RestController
@RequestMapping("/grupos/{grupoId}")
public class GrupoPermissaoController {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private PermissaoModelAssembler permissaoModelAssembler;

    @GetMapping("/permissoes")
    public List<PermissaoModel> listar(@PathVariable Long grupoId) {
        Grupo grupo = grupoService.buscarOuFalhar(grupoId);
        return permissaoModelAssembler.toCollectionModel(grupo.getPermissoes());
    }

}
