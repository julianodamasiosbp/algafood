package com.acme.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.algafood.api.assembler.PedidoModelAssembler;
import com.acme.algafood.api.assembler.PedidoResumoModelAssembler;
import com.acme.algafood.api.model.response.PedidoModel;
import com.acme.algafood.api.model.response.PedidoResumoModel;
import com.acme.algafood.domain.repository.PedidoRepository;
import com.acme.algafood.domain.service.PedidoService;

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
    private PedidoRepository pedidoRepository;

    @GetMapping
    public List<PedidoResumoModel> listar() {
        return pedidoResumoModelAssembler.toCollectionModel(pedidoRepository.findAll());
    }

    @GetMapping("/{pedidoId}")
    public PedidoModel buscar(@PathVariable Long pedidoId) {
        return pedidoModelAssembler.toModel(pedidoService.buscarOuFalhar(pedidoId));
    }

}
