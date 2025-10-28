package com.acme.algafood.api.v1.controller;

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
import com.acme.algafood.api.v1.assembler.CidadeModelAssembler;
import com.acme.algafood.api.v1.disassembler.CidadeInputDisassembler;
import com.acme.algafood.api.v1.model.request.CidadeInput;
import com.acme.algafood.api.v1.model.response.CidadeModel;
import com.acme.algafood.api.v1.openapi.controller.CidadeControllerOpenApi;
import com.acme.algafood.core.web.AlgaMediaTypes;
import com.acme.algafood.domain.exception.EstadoNaoEncontradoException;
import com.acme.algafood.domain.exception.NegocioException;
import com.acme.algafood.domain.model.Cidade;
import com.acme.algafood.domain.repository.CidadeRepository;
import com.acme.algafood.domain.service.CidadeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@Api(tags = "Cidades")
@RequestMapping(path = "/cidades")
@RestController
public class CidadeController implements CidadeControllerOpenApi {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    @Autowired
    private CidadeInputDisassembler cidadeInputDisassembler;

    @GetMapping(produces = AlgaMediaTypes.V1_APPLICATION_JSON_VALUE)
    public CollectionModel<CidadeModel> listar() {
        return cidadeModelAssembler.toCollectionModel(cidadeRepository.findAll());
    }

    @GetMapping(path = "/{cidadeId}", produces = AlgaMediaTypes.V1_APPLICATION_JSON_VALUE)
    public CidadeModel buscar(@PathVariable Long cidadeId) {
        Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);
        return cidadeModelAssembler.toModel(cidade);
    }

    @PostMapping(produces = AlgaMediaTypes.V1_APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModel adicionar(
            @RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidadeSalva = cidadeService.salvar(cidadeInputDisassembler.toDomainObject(cidadeInput));
            CidadeModel cidadeModel = cidadeModelAssembler.toModel(cidadeSalva);
            ResourceUriHelper.addUriInResponseHeader(cidadeModel.getId());
            return cidadeModel;
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping(path = "/{cidadeId}", produces = AlgaMediaTypes.V1_APPLICATION_JSON_VALUE)
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
    public void remover(@ApiParam("ID de uma cidade") @PathVariable Long cidadeId) {
        cidadeService.excluir(cidadeId);
    }

}
