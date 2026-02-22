package com.acme.algafood.api.v2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
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

import com.acme.algafood.api.ResourceUriHelper;
import com.acme.algafood.api.v2.assembler.CidadeModelAssemblerV2;
import com.acme.algafood.api.v2.disassembler.CidadeInputDisassemblerV2;
import com.acme.algafood.api.v2.model.request.CidadeInputV2;
import com.acme.algafood.api.v2.model.response.CidadeModelV2;
import com.acme.algafood.api.v2.openapi.controller.CidadeControllerV2OpenApi;
import com.acme.algafood.domain.exception.EstadoNaoEncontradoException;
import com.acme.algafood.domain.exception.NegocioException;
import com.acme.algafood.domain.model.Cidade;
import com.acme.algafood.domain.repository.CidadeRepository;
import com.acme.algafood.domain.service.CidadeService;


@RequestMapping(path = "/v2/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class CidadeControllerV2 implements CidadeControllerV2OpenApi {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private CidadeModelAssemblerV2 cidadeModelAssemblerV2;

    @Autowired
    private CidadeInputDisassemblerV2 cidadeInputDisassemblerV2;

    @GetMapping()
    public CollectionModel<CidadeModelV2> listar() {
        List<Cidade> todasCidades = cidadeRepository.findAll();

        return cidadeModelAssemblerV2.toCollectionModel(todasCidades);
    }

    @GetMapping(path = "/{cidadeId}")
    public CidadeModelV2 buscar(@PathVariable Long cidadeId) {
        System.out.println("API V2 - buscando cidade");
        Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);
        return cidadeModelAssemblerV2.toModel(cidade);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModelV2 adicionar(
            @RequestBody @Valid CidadeInputV2 cidadeInputV2) {
        try {
            Cidade cidadeSalva = cidadeService.salvar(cidadeInputDisassemblerV2.toDomainObject(cidadeInputV2));
            CidadeModelV2 cidadeModelV2 = cidadeModelAssemblerV2.toModel(cidadeSalva);
            ResourceUriHelper.addUriInResponseHeader(cidadeModelV2.getIdCidade());
            return cidadeModelV2;
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping(path = "/{cidadeId}")
    public CidadeModelV2 atualizar(@PathVariable @Valid Long cidadeId,
            @RequestBody CidadeInputV2 cidadeInputV2) {
        Cidade cidadeAtual = cidadeService.buscarOuFalhar(cidadeId);

        cidadeInputDisassemblerV2.copyToDomainObject(cidadeInputV2, cidadeAtual);

        // BeanUtils.copyProperties(cidade, cidadeAtual, "id");
        try {
            return cidadeModelAssemblerV2.toModel(cidadeService.salvar(cidadeAtual));
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
