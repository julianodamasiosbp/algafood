package com.acme.algafood.domain.service;

import java.beans.Transient;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.algafood.domain.exception.GrupoNaoEncontradoException;
import com.acme.algafood.domain.model.Grupo;
import com.acme.algafood.domain.repository.GrupoRepository;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    public Grupo buscarOuFalhar(Long grupoId) {
        return grupoRepository.findById(grupoId)
                .orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
    }

    @Transactional
    public Grupo salvar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Transactional
    public void excluir(Long grupoId) {
        Grupo grupo = buscarOuFalhar(grupoId);
        grupoRepository.delete(grupo);
    }

}
