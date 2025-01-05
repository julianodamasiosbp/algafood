package com.acme.algafood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.acme.algafood.domain.exception.EntidadeEmUsoException;
import com.acme.algafood.domain.exception.EstadoNaoEncontradoException;
import com.acme.algafood.domain.model.Estado;
import com.acme.algafood.domain.repository.EstadoRepository;

@Service
public class EstadoService {

    private static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removido, pois está em uso";

    @Autowired
    private EstadoRepository estadoRepository;

    @Transactional
    public Estado salvar(Estado estado) {
        return this.estadoRepository.save(estado);
    }

    public Estado buscar(Long id) {
        return this.buscarOuFalhar(id);
    }

    @Transactional
    public Estado atualizar(Long id, Estado estado) {
        Estado estadoSalvo = buscarOuFalhar(id);
        BeanUtils.copyProperties(estado, estadoSalvo, "id");
        return this.estadoRepository.save(estadoSalvo);
    }

    @Transactional
    public void excluir(Long estadoId) {
        try {
            estadoRepository.deleteById(estadoId);

        } catch (EmptyResultDataAccessException e) {
            throw new EstadoNaoEncontradoException(
                    estadoId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_ESTADO_EM_USO, estadoId));
        }
    }

    public Estado buscarOuFalhar(Long estadoId) {
        return estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EstadoNaoEncontradoException(
                        estadoId));
    }

}
