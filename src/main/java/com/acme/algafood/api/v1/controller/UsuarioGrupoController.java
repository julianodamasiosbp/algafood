package com.acme.algafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.acme.algafood.api.v1.AlgafoodLinks;
import com.acme.algafood.api.v1.assembler.GrupoModelAssembler;
import com.acme.algafood.api.v1.model.response.GrupoModel;
import com.acme.algafood.api.v1.openapi.controller.UsuarioGrupoControllerOpenApi;
import com.acme.algafood.core.security.CheckSecurity;
import com.acme.algafood.domain.model.Usuario;
import com.acme.algafood.domain.service.UsuarioService;

@RestController
@RequestMapping(path = "/v1/usuarios/{usuarioId}/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi {

    @Autowired
    public GrupoModelAssembler grupoModelAssembler;

    @Autowired
    public UsuarioService usuarioService;

    @Autowired
    private AlgafoodLinks algaLinks;

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @Override
    @GetMapping
    public CollectionModel<GrupoModel> listar(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);

        CollectionModel<GrupoModel> gruposModel = grupoModelAssembler.toCollectionModel(usuario.getGrupos())
                .removeLinks()
                .add(algaLinks.linkToUsuarioGrupoAssociacao(usuarioId, "associar"));

        gruposModel.getContent().forEach(grupoModel -> {
            grupoModel.add(algaLinks.linkToUsuarioGrupoDesassociacao(
                    usuarioId, grupoModel.getId(), "desassociar"));
        });

        return gruposModel;
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.desassociarGrupo(usuarioId, grupoId);
        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.associarGrupo(usuarioId, grupoId);
        return ResponseEntity.noContent().build();
    }

}
