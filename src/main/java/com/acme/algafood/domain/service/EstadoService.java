package com.acme.algafood.domain.service;

import com.acme.algafood.domain.exception.EntidadeEmUsoException;
import com.acme.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.acme.algafood.domain.model.Estado;
import com.acme.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado salvar(Estado estado) {
        return this.estadoRepository.save(estado);
    }

    public Estado buscar(Long id) {
        return this.buscarOuFalhar(id);
    }

    public Estado atualizar(Long id, Estado estado) {
        Estado estadoSalvo = buscarOuFalhar(id);
        BeanUtils.copyProperties(estado, estadoSalvo, "id");
        return this.estadoRepository.save(estadoSalvo);
    }

    public void excluir(Long id) {
        Estado estadoSalvo = this.buscarOuFalhar(id);

        try {
            estadoRepository.delete(estadoSalvo);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Estado de codigo %d nao pode ser removido, pois esta em uso", id));
        }
    }

    public Estado buscarOuFalhar(Long id) {
        return this.estadoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Nao existe um cadastro de estado com codigo: %d", id)));
    }

}
