package com.acme.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.acme.algafood.api.assembler.RestauranteModelAssembler;
import com.acme.algafood.api.disassembler.RestauranteInputDisassembler;
import com.acme.algafood.api.model.request.RestauranteInput;
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

    @Autowired
    private SmartValidator validator;

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;

    @Autowired
    private RestauranteInputDisassembler restauranteInputDisassembler;

    @JsonView(RestauranteView.Resumo.class)
    @GetMapping
    public CollectionModel<RestauranteModel> listar() {
        return restauranteModelAssembler.toCollectionModel(restauranteRepository.findAll());
    }

    @GetMapping("/{restauranteId}")
    public RestauranteModel buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);

        return restauranteModelAssembler.toModel(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);
            return restauranteModelAssembler.toModel(restauranteService.salvar(restaurante));
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public RestauranteModel atualizar(@PathVariable Long restauranteId,
            @RequestBody @Valid RestauranteInput restauranteInput) {

        // Restaurante restaurante =
        // restauranteInputDisassembler.toDomainObject(restauranteInput);

        Restaurante restauranteAtual = restauranteService.buscarOuFalhar(restauranteId);

        restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);

        // BeanUtils.copyProperties(restaurante, restauranteAtual,
        // "id", "formasPagamento", "endereco", "dataCadastro", "produtos");

        try {
            return restauranteModelAssembler.toModel(restauranteService.salvar(restauranteAtual));
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long restauranteId) {
        restauranteService.ativar(restauranteId);
    }

    @DeleteMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Long restauranteId) {
        restauranteService.inativar(restauranteId);
    }

    @PutMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarMultiplos(@RequestBody List<Long> restaurantesIds) {
        try {
            restauranteService.ativar(restaurantesIds);
        } catch (RestauranteNaoEncontradoException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }

    }

    @DeleteMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desativarMultiplos(@RequestBody List<Long> restaurantesIds) {
        try {
            restauranteService.inativar(restaurantesIds);
        } catch (RestauranteNaoEncontradoException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @PutMapping("/{restauranteId}/fechamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void encerrar(@PathVariable Long restauranteId) {
        restauranteService.fecharRestaurante(restauranteId);
    }

    @PutMapping("/{restauranteId}/abertura")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void abrir(@PathVariable Long restauranteId) {
        restauranteService.abrirRestaurante(restauranteId);
    }

    /*
     * @GetMapping
     * public MappingJacksonValue listar(@RequestParam(required = false) String
     * projecao) {
     * List<RestauranteModel> restaurantesModel = restauranteModelAssembler
     * .toCollectionModel(restauranteRepository.findAll());
     * 
     * MappingJacksonValue restaurantesWrapper = new
     * MappingJacksonValue(restaurantesModel);
     * 
     * restaurantesWrapper.setSerializationView(RestauranteView.Resumo.class);
     * 
     * if ("completo".equals(projecao)) {
     * restaurantesWrapper.setSerializationView(null);
     * }
     * 
     * return restaurantesWrapper;
     * }
     */

    // @JsonView(RestauranteView.Resumo.class)
    // @GetMapping(params = "projecao=resumo")
    // public List<RestauranteModel> listarResumido() {
    // return listar();
    // }
}
