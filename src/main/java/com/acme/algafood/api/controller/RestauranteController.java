package com.acme.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.acme.algafood.api.assembler.RestauranteModelAssembler;
import com.acme.algafood.api.model.CozinhaModel;
import com.acme.algafood.api.model.RestauranteModel;
import com.acme.algafood.api.model.input.CozinhaIdInput;
import com.acme.algafood.api.model.input.RestauranteInput;
import com.acme.algafood.core.validation.ValidacaoException;
import com.acme.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.acme.algafood.domain.exception.NegocioException;
import com.acme.algafood.domain.model.Cozinha;
import com.acme.algafood.domain.model.Restaurante;
import com.acme.algafood.domain.repository.RestauranteRepository;
import com.acme.algafood.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private SmartValidator validator;

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;

    @GetMapping
    public List<RestauranteModel> listar() {
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
            Restaurante restaurante = toDomainObject(restauranteInput);
            return restauranteModelAssembler.toModel(restauranteService.salvar(restaurante));
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public RestauranteModel atualizar(@PathVariable Long restauranteId,
            @RequestBody @Valid RestauranteInput restauranteInput) {
        Restaurante restauranteAtual = restauranteService.buscarOuFalhar(restauranteId);

        Restaurante restaurante = toDomainObject(restauranteInput);

        BeanUtils.copyProperties(restaurante, restauranteAtual,
                "id", "formasPagamento", "endereco", "dataCadastro", "produtos");

        try {
            return restauranteModelAssembler.toModel(restauranteService.salvar(restauranteAtual));
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    private Restaurante toDomainObject(RestauranteInput restauranteInput) {
        Restaurante restaurante = new Restaurante();
        Cozinha cozinha = new Cozinha();
        cozinha.setId(restauranteInput.getCozinha().getId());

        restaurante.setNome(restauranteInput.getNome());
        restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
        restaurante.setCozinha(cozinha);

        return restaurante;
    }

}
