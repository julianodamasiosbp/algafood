package com.acme.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.acme.algafood.api.assembler.RestauranteApenasNomeModelAssembler;
import com.acme.algafood.api.assembler.RestauranteBasicoModelAssembler;
import com.acme.algafood.api.assembler.RestauranteModelAssembler;
import com.acme.algafood.api.disassembler.RestauranteInputDisassembler;
import com.acme.algafood.api.model.request.RestauranteInput;
import com.acme.algafood.api.model.response.RestauranteApenasNomeModel;
import com.acme.algafood.api.model.response.RestauranteBasicoModel;
import com.acme.algafood.api.model.response.RestauranteModel;
import com.acme.algafood.api.model.view.RestauranteView;
import com.acme.algafood.api.openapi.controller.RestauranteControllerOpenApi;
import com.acme.algafood.domain.exception.CidadeNaoEncontradaException;
import com.acme.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.acme.algafood.domain.exception.NegocioException;
import com.acme.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.acme.algafood.domain.model.Restaurante;
import com.acme.algafood.domain.repository.RestauranteRepository;
import com.acme.algafood.domain.service.RestauranteService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(path = "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController implements RestauranteControllerOpenApi {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private RestauranteService restauranteService;

    // @Autowired
    // private SmartValidator validator;

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;

    @Autowired
    private RestauranteInputDisassembler restauranteInputDisassembler;

    @Autowired
    private RestauranteBasicoModelAssembler restauranteBasicoModelAssembler;

    @Autowired
    private RestauranteApenasNomeModelAssembler restauranteApenasNomeModelAssembler;

    @Override
    // @JsonView(RestauranteView.Resumo.class)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<RestauranteBasicoModel> listar() {
        return restauranteBasicoModelAssembler
                .toCollectionModel(restauranteRepository.findAll());
    }

    @Override
    // @JsonView(RestauranteView.ApenasNome.class)
    @GetMapping(params = "projecao=apenas-nome", produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<RestauranteApenasNomeModel> listarApenasNomes() {
        return restauranteApenasNomeModelAssembler
                .toCollectionModel(restauranteRepository.findAll());
    }

    @Override
    @GetMapping(value = "/{restauranteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestauranteModel buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);

        return restauranteModelAssembler.toModel(restaurante);
    }

    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);

            return restauranteModelAssembler.toModel(restauranteService.salvar(restaurante));
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    @PutMapping(value = "/{restauranteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestauranteModel atualizar(@PathVariable Long restauranteId,
            @RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restauranteAtual = restauranteService.buscarOuFalhar(restauranteId);

            restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);

            return restauranteModelAssembler.toModel(restauranteService.salvar(restauranteAtual));
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    @PutMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> ativar(@PathVariable Long restauranteId) {
        restauranteService.ativar(restauranteId);

        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> inativar(@PathVariable Long restauranteId) {
        restauranteService.inativar(restauranteId);

        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
        try {
            restauranteService.ativar(restauranteIds);
        } catch (RestauranteNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @Override
    @DeleteMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativarMultiplos(@RequestBody List<Long> restauranteIds) {
        try {
            restauranteService.inativar(restauranteIds);
        } catch (RestauranteNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @Override
    @PutMapping("/{restauranteId}/abertura")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> abrir(@PathVariable Long restauranteId) {
        restauranteService.abrir(restauranteId);

        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/{restauranteId}/fechamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> fechar(@PathVariable Long restauranteId) {
        restauranteService.fechar(restauranteId);

        return ResponseEntity.noContent().build();
    }
}
