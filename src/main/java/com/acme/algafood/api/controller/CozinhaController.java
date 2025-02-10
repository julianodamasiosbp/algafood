package com.acme.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.acme.algafood.api.assembler.CozinhaModelAssembler;
import com.acme.algafood.api.disassembler.CozinhaInputDisassembler;
import com.acme.algafood.api.model.request.CozinhaInput;
import com.acme.algafood.api.model.response.CozinhaModel;
import com.acme.algafood.domain.model.Cozinha;
import com.acme.algafood.domain.repository.CozinhaRepository;
import com.acme.algafood.domain.service.CozinhaService;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CozinhaService cozinhaService;

    @Autowired
    private CozinhaModelAssembler cozinhaModelAssembler;

    @Autowired
    private CozinhaInputDisassembler cozinhaInputDisassembler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CozinhaModel> listar() {
        return cozinhaModelAssembler.toCollectionModel(cozinhaRepository.findAll());
    }

    @GetMapping(value = "/{cozinhaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CozinhaModel buscar(@PathVariable("cozinhaId") Long cozinhaId) {
        return cozinhaModelAssembler.toModel(cozinhaService.buscarOuFalhar(cozinhaId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
        return cozinhaService.salvar(cozinhaInputDisassembler.toDomainObject(cozinhaInput));
    }

    @PutMapping("/{cozinhaId}")
    public Cozinha atualizar(@PathVariable("cozinhaId") Long cozinhaId, @RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinhaAtual = cozinhaService.buscarOuFalhar(cozinhaId);
        cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
        // BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
        return cozinhaService.salvar(cozinhaAtual);
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {
        cozinhaService.excluir(cozinhaId);
    }
}
