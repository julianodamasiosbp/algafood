package com.acme.algafood.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.acme.algafood.api.model.request.SenhaInput;
import com.acme.algafood.domain.exception.EntidadeEmUsoException;
import com.acme.algafood.domain.exception.NegocioException;
import com.acme.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.acme.algafood.domain.model.Usuario;
import com.acme.algafood.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static final String MSG_USUARIO_EM_USO = "Usuário de código %d não pode ser removido, pois está em uso";

    public Usuario buscarOuFalhar(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
    }

    public Usuario salvar(Usuario usuario) {

        usuarioRepository.detach(usuario);

        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
            throw new NegocioException(
                    String.format("Já existe um usuário cadastro com o e-mail %s", usuario.getEmail()));
        }
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void excluir(Long usuarioId) {
        try {
            usuarioRepository.deleteById(usuarioId);
            usuarioRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new UsuarioNaoEncontradoException(
                    usuarioId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_USUARIO_EM_USO, usuarioId));
        }
    }

    public Usuario validarSenha(SenhaInput senhaInput, Long usuarioId) {
        Usuario usuarioSalvo = buscarOuFalhar(usuarioId);
        if (senhaInput.getSenhaAtual() != usuarioSalvo.getSenha()) {
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }

        return usuarioSalvo;
    }
}
