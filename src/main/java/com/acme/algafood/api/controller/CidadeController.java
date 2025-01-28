package com.acme.algafood.api.controller;

import com.acme.algafood.api.assembler.CidadeInputDisassembler;
import com.acme.algafood.api.assembler.CidadeModelAssembler;
import com.acme.algafood.api.model.request.CidadeInput;
import com.acme.algafood.api.model.response.CidadeModel;
import com.acme.algafood.domain.exception.EstadoNaoEncontradoException;
import com.acme.algafood.domain.exception.NegocioException;
import com.acme.algafood.domain.model.Cidade;
import com.acme.algafood.domain.repository.CidadeRepository;
import com.acme.algafood.domain.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/cidades")
@RestController
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    @Autowired
    private CidadeInputDisassembler cidadeInputDisassembler;

    @GetMapping
    public List<CidadeModel> listar() {
        return cidadeModelAssembler.toCollectionModel(cidadeRepository.findAll());
    }

    @GetMapping("/{cidadeId}")
    public CidadeModel buscar(@PathVariable Long cidadeId) {
        return cidadeModelAssembler.toModel(cidadeService.buscarOuFalhar(cidadeId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidadeSalva = cidadeService.salvar(cidadeInputDisassembler.toDomainObject(cidadeInput));
            return cidadeModelAssembler.toModel(cidadeSalva);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cidadeId}")
    public CidadeModel atualizar(@PathVariable @Valid Long cidadeId,
            @RequestBody CidadeInput cidadeInput) {
        Cidade cidadeAtual = cidadeService.buscarOuFalhar(cidadeId);

        cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);

        // BeanUtils.copyProperties(cidade, cidadeAtual, "id");
        try {
            return cidadeModelAssembler.toModel(cidadeService.salvar(cidadeAtual));
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
        cidadeService.excluir(cidadeId);
    }

}
