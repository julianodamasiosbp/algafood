package com.acme.algafood.infrastructure.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.acme.algafood.domain.filter.VendaDiariaFilter;
import com.acme.algafood.domain.model.Pedido;
import com.acme.algafood.domain.model.dto.VendaDiaria;
import com.acme.algafood.domain.service.VendaQueryService;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro) {
        var builder = em.getCriteriaBuilder();
        var query = builder.createQuery(VendaDiaria.class);
        var root = query.from(Pedido.class);

        var functionDateDataCriacao = builder
                .function("date", Date.class, root.get("dataCriacao"));

        var selection = builder
                .construct(VendaDiaria.class, functionDateDataCriacao,
                        builder.count(root.get("id")), builder.sum(root.get("valorTotal")));

        query.select(selection);

        query.groupBy(functionDateDataCriacao);

        return em.createQuery(query).getResultList();
    }

}
