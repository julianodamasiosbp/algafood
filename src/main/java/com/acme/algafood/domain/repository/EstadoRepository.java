package com.acme.algafood.domain.repository;

import java.util.List;

import com.acme.algafood.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

}
