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

import com.acme.algafood.api.assembler.EstadoInputDisassembler;
import com.acme.algafood.api.assembler.EstadoModelAssembler;
import com.acme.algafood.api.model.request.EstadoInput;
import com.acme.algafood.api.model.response.EstadoModel;
import com.acme.algafood.domain.model.Estado;
import com.acme.algafood.domain.repository.EstadoRepository;
import com.acme.algafood.domain.service.EstadoService;

@RequestMapping("/estados")
@RestController
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private EstadoModelAssembler estadoModelAssembler;

    @Autowired
    private EstadoInputDisassembler estadoInputDisassembler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EstadoModel> listar() {
        return estadoModelAssembler.toCollectionModel(estadoRepository.findAll());
    }

    @GetMapping("/{id}")
    public EstadoModel buscar(@PathVariable Long id) {
        return estadoModelAssembler.toModel(estadoService.buscarOuFalhar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {
        Estado estadoSalvo = estadoService.salvar(estadoInputDisassembler.toDomainObject(estadoInput));
        return estadoModelAssembler.toModel(estadoSalvo);
    }

    @PutMapping("/{estadoId}")
    public EstadoModel atualizar(@PathVariable Long estadoId,
            @RequestBody @Valid EstadoInput estadoInput) {
        Estado estadoAtual = estadoService.buscarOuFalhar(estadoId);

        estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);

        // BeanUtils.copyProperties(estado, estadoAtual, "id");

        return estadoModelAssembler.toModel(estadoService.salvar(estadoAtual));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        estadoService.excluir(id);
    }

}
