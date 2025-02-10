package com.acme.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.algafood.api.assembler.ProdutoModelAssembler;
import com.acme.algafood.api.disassembler.ProdutoInputDisassembler;
import com.acme.algafood.api.model.response.ProdutoModel;
import com.acme.algafood.domain.model.Restaurante;
import com.acme.algafood.domain.repository.ProdutoRepository;
import com.acme.algafood.domain.repository.RestauranteRepository;
import com.acme.algafood.domain.service.RestauranteService;

@RequestMapping("/restaurantes/{restauranteId}")
@RestController
public class RestauranteProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private ProdutoModelAssembler produtoModelAssembler;

    @Autowired
    private ProdutoInputDisassembler produtoInputDisassembler;

    @GetMapping("/produtos")
    public List<ProdutoModel> listar(@PathVariable Long restauranteId) {

        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);

        return produtoModelAssembler.toCollectionModel(restaurante.getProdutos());
    }

}
