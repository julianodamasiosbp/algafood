package com.acme.algafood.domain.repository;

import java.util.List;

import com.acme.algafood.domain.model.FormaPagamento;
import com.acme.algafood.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

}
