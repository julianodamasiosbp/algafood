package com.acme.algafood.api.controller;

import java.util.List;

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

import com.acme.algafood.api.assembler.UsuarioModelAssembler;
import com.acme.algafood.api.disassembler.UsuarioInputDisassembler;
import com.acme.algafood.api.disassembler.UsuarioSemSenhaInputDisassembler;
import com.acme.algafood.api.model.request.SenhaInput;
import com.acme.algafood.api.model.request.UsuarioInput;
import com.acme.algafood.api.model.request.UsuarioSemSenhaInput;
import com.acme.algafood.api.model.response.UsuarioModel;
import com.acme.algafood.api.openapi.controller.UsuarioControllerOpenApi;
import com.acme.algafood.domain.model.Usuario;
import com.acme.algafood.domain.repository.UsuarioRepository;
import com.acme.algafood.domain.service.UsuarioService;

@RestController
@RequestMapping(path = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements UsuarioControllerOpenApi {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private UsuarioInputDisassembler usuarioInputDisassembler;

    @Autowired
    private UsuarioSemSenhaInputDisassembler usuarioSemSenhaInputDisassembler;

    @GetMapping
    public CollectionModel<UsuarioModel> listar() {
        return usuarioModelAssembler.toCollectionModel(usuarioRepository.findAll());
    }

    @GetMapping("/{usuarioId}")
    public UsuarioModel buscar(@PathVariable Long usuarioId) {
        return usuarioModelAssembler.toModel(usuarioService.buscarOuFalhar(usuarioId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel adicionar(@RequestBody @Valid UsuarioInput usuarioInput) {
        Usuario novoUsuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
        Usuario usuarioSalvo = usuarioService.salvar(novoUsuario);
        return usuarioModelAssembler.toModel(usuarioSalvo);
    }

    @PutMapping("/{usuarioId}")
    public UsuarioModel atualizar(@PathVariable Long usuarioId,
            @RequestBody UsuarioSemSenhaInput usuarioSemSenhaInput) {
        Usuario usuarioSalvo = usuarioService.buscarOuFalhar(usuarioId);
        usuarioSemSenhaInputDisassembler.copyToDomainObject(usuarioSemSenhaInput, usuarioSalvo);
        Usuario usuarioAtualizado = usuarioService.salvar(usuarioSalvo);
        return usuarioModelAssembler.toModel(usuarioAtualizado);
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long usuarioId) {
        usuarioService.excluir(usuarioId);
    }

    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarSenha(@PathVariable Long usuarioId, @RequestBody SenhaInput senhaInput) {
        Usuario usuarioValidado = usuarioService.validarSenha(senhaInput, usuarioId);
        usuarioValidado.setSenha(senhaInput.getNovaSenha());
        usuarioService.salvar(usuarioValidado);
    }

}
