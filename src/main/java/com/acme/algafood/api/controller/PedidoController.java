package com.acme.algafood.api.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.acme.algafood.api.assembler.PedidoModelAssembler;
import com.acme.algafood.api.assembler.PedidoResumoModelAssembler;
import com.acme.algafood.api.disassembler.PedidoInputDisassembler;
import com.acme.algafood.api.model.request.PedidoInput;
import com.acme.algafood.api.model.response.PedidoModel;
import com.acme.algafood.api.model.response.PedidoResumoModel;
import com.acme.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.acme.algafood.domain.exception.NegocioException;
import com.acme.algafood.domain.model.Pedido;
import com.acme.algafood.domain.model.Usuario;
import com.acme.algafood.domain.repository.PedidoRepository;
import com.acme.algafood.domain.repository.filter.PedidoFilter;
import com.acme.algafood.domain.service.EmissaoPedidoService;
import com.acme.algafood.domain.service.PedidoService;
import com.acme.algafood.infrastructure.repository.spec.PedidoSpecs;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import antlr.StringUtils;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;

    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EmissaoPedidoService emissaoPedidoService;

    // @GetMapping
    // public MappingJacksonValue listar(@RequestParam(required = false) String
    // campos) {
    // List<Pedido> pedidos = pedidoRepository.findAll();
    // List<PedidoResumoModel> pedidosModel =
    // pedidoResumoModelAssembler.toCollectionModel(pedidos);

    // MappingJacksonValue pedidosWrapper = new MappingJacksonValue(pedidosModel);

    // SimpleFilterProvider filterProvider = new SimpleFilterProvider();
    // filterProvider.addFilter("pedidoFilter",
    // SimpleBeanPropertyFilter.serializeAll());

    // if (org.apache.commons.lang3.StringUtils.isNotBlank(campos)) {
    // filterProvider.addFilter("pedidoFilter",
    // SimpleBeanPropertyFilter
    // .filterOutAllExcept(campos
    // .replaceAll("\\s", "")
    // .split(",")));
    // }

    // pedidosWrapper.setFilters(filterProvider);
    // return pedidosWrapper;
    // }

    @GetMapping
    public List<PedidoResumoModel> pesquisar(PedidoFilter filtro) {
        return pedidoResumoModelAssembler.toCollectionModel(pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro)));
    }

    @GetMapping("/{codigoPedido}")
    public PedidoModel buscar(@PathVariable String codigoPedido) {
        return pedidoModelAssembler.toModel(pedidoService.buscarOuFalhar(codigoPedido));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
        try {
            Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

            // TODO pegar usuário autenticado
            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(1L);

            novoPedido = emissaoPedidoService.emitir(novoPedido);

            return pedidoModelAssembler.toModel(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

}
