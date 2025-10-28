package com.acme.algafood.api.v1.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.acme.algafood.api.v1.assembler.PedidoModelAssembler;
import com.acme.algafood.api.v1.assembler.PedidoResumoModelAssembler;
import com.acme.algafood.api.v1.disassembler.PedidoInputDisassembler;
import com.acme.algafood.api.v1.model.request.PedidoInput;
import com.acme.algafood.api.v1.model.response.PedidoModel;
import com.acme.algafood.api.v1.model.response.PedidoResumoModel;
import com.acme.algafood.api.v1.openapi.controller.PedidoControllerOpenApi;
import com.acme.algafood.core.data.PageWrapper;
import com.acme.algafood.core.data.PageableTranslator;
import com.acme.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.acme.algafood.domain.exception.NegocioException;
import com.acme.algafood.domain.filter.PedidoFilter;
import com.acme.algafood.domain.model.Pedido;
import com.acme.algafood.domain.model.Usuario;
import com.acme.algafood.domain.repository.PedidoRepository;
import com.acme.algafood.domain.service.EmissaoPedidoService;
import com.acme.algafood.domain.service.PedidoService;
import com.acme.algafood.infrastructure.repository.spec.PedidoSpecs;

@RestController
@RequestMapping(path = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController implements PedidoControllerOpenApi {

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

    @Autowired
    private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro, @PageableDefault(size = 10) Pageable pageable) {
        Pageable pageableTraduzido = traduzirPageable(pageable);

        Page<Pedido> pedidosPage = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro), pageableTraduzido);

        pedidosPage = new PageWrapper<>(pedidosPage, pageable);

        return pagedResourcesAssembler.toModel(pedidosPage,
                pedidoResumoModelAssembler);
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

    private Pageable traduzirPageable(Pageable apiPageable) {
        var mapeamento = Map.of(
                "codigo", "codigo",
                "subtotal", "subtotal",
                "taxaFrete", "taxaFrete",
                "valorTotal", "valorTotal",
                "dataCriacao", "dataCriacao",
                "nomerestaurante", "restaurante.nome",
                "restaurante.id", "restaurante.id",
                "cliente.id", "cliente.id",
                "cliente.nome", "cliente.nome");

        return PageableTranslator.translate(apiPageable, mapeamento);
    }

    // @GetMapping
    // public PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro,
    // @PageableDefault(size = 10) Pageable pageable) {

    // Page<Pedido> pedidosPage = pedidoRepository.findAll(pageable);

    // PagedModel<PedidoResumoModel> pedidosPagedModel =
    // pagedResourcesAssembler.toModel(pedidosPage,
    // pedidoResumoModelAssembler);

    // return pedidosPagedModel;
    // }

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
}
