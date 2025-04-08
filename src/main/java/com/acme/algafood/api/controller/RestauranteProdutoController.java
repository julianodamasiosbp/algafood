package com.acme.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.acme.algafood.api.assembler.ProdutoModelAssembler;
import com.acme.algafood.api.disassembler.ProdutoInputDisassembler;
import com.acme.algafood.api.model.request.ProdutoInput;
import com.acme.algafood.api.model.response.ProdutoModel;
import com.acme.algafood.domain.model.Produto;
import com.acme.algafood.domain.model.Restaurante;
import com.acme.algafood.domain.repository.ProdutoRepository;
import com.acme.algafood.domain.repository.RestauranteRepository;
import com.acme.algafood.domain.service.ProdutoService;
import com.acme.algafood.domain.service.RestauranteService;

@RequestMapping("/restaurantes/{restauranteId}/produtos")
@RestController
public class RestauranteProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoModelAssembler produtoModelAssembler;

    @Autowired
    private ProdutoInputDisassembler produtoInputDisassembler;

    @GetMapping()
    public List<ProdutoModel> listar(@RequestParam(required = false) boolean incluirInativos,
            @PathVariable Long restauranteId) {

        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);

        List<Produto> todosProdutos;

        if (incluirInativos) {
            todosProdutos = produtoRepository.findByRestaurante(restaurante);
        } else {
            todosProdutos = produtoRepository.findAtivosByRestaurante(restaurante);
        }

        return produtoModelAssembler.toCollectionModel(todosProdutos);
    }

    @GetMapping("/{produtoId}")
    public ProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        Produto produto = produtoService.buscarOuFalhar(restauranteId, produtoId);

        return produtoModelAssembler.toModel(produto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoModel adicionar(@PathVariable Long restauranteId,
            @RequestBody @Valid ProdutoInput produtoInput) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);

        Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
        produto.setRestaurante(restaurante);

        produto = produtoService.salvar(produto);

        return produtoModelAssembler.toModel(produto);
    }

    @PutMapping("/{produtoId}")
    public ProdutoModel atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
            @RequestBody @Valid ProdutoInput produtoInput) {
        Produto produtoAtual = produtoService.buscarOuFalhar(restauranteId, produtoId);

        produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);

        produtoAtual = produtoService.salvar(produtoAtual);

        return produtoModelAssembler.toModel(produtoAtual);
    }

}
