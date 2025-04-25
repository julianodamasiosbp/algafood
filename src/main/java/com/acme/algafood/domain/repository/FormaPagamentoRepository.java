package com.acme.algafood.domain.repository;

import com.acme.algafood.domain.model.FormaPagamento;

import java.time.OffsetDateTime;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepository extends CustomJpaRepository<FormaPagamento, Long> {

    @Query("select max(dataAtualizacao) from FormaPagamento")
    OffsetDateTime getDataUltimaAtualizacao();
}
