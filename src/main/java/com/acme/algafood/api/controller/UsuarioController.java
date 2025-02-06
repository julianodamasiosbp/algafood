package com.acme.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.algafood.api.assembler.UsuarioInputDisassembler;
import com.acme.algafood.api.assembler.UsuarioModelAssembler;
import com.acme.algafood.api.model.request.UsuarioInput;
import com.acme.algafood.api.model.response.UsuarioModel;
import com.acme.algafood.domain.model.Usuario;
import com.acme.algafood.domain.repository.UsuarioRepository;
import com.acme.algafood.domain.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private UsuarioInputDisassembler usuarioInputDisassembler;

    @GetMapping
    public List<UsuarioModel> listar() {
        return usuarioModelAssembler.toCollectionModel(usuarioRepository.findAll());
    }

    @GetMapping("/{usuarioId}")
    public UsuarioModel buscar(@PathVariable Long usuarioId) {
        return usuarioModelAssembler.toModel(usuarioService.buscarOuFalhar(usuarioId));
    }

    @PostMapping
    public UsuarioModel adicionar(@RequestBody UsuarioInput usuarioInput) {
        Usuario novoUsuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
        Usuario usuarioSalvo = usuarioService.salvar(novoUsuario);
        return usuarioModelAssembler.toModel(usuarioSalvo);
    }

}
