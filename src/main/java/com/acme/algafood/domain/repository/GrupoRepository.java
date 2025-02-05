package com.acme.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acme.algafood.domain.model.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {

}
