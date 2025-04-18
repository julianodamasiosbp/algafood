package com.acme.algafood.infrastructure.service.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Repository;

import com.acme.algafood.domain.enums.StatusPedido;
import com.acme.algafood.domain.filter.VendaDiariaFilter;
import com.acme.algafood.domain.model.Pedido;
import com.acme.algafood.domain.model.dto.VendaDiaria;
import com.acme.algafood.domain.service.VendaQueryService;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
        var predicates = new ArrayList<Predicate>();

        var builder = em.getCriteriaBuilder();

        var query = builder.createQuery(VendaDiaria.class);

        var root = query.from(Pedido.class);

        if (filtro.getRestauranteId() != null) {
            predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
        }

        if (filtro.getDataCriacaoInicio() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
        }

        if (filtro.getDataCriacaoFim() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
        }

        predicates.add(root.get("status").in(
                StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));

        var functionConvertTzDataCriacao = builder.function("convert_tz", Date.class, root.get("dataCriacao"),
                builder.literal("+00:00"),
                builder.literal(timeOffset));

        var functionDateDataCriacao = builder
                .function("date", Date.class, functionConvertTzDataCriacao);

        var selection = builder
                .construct(VendaDiaria.class, functionDateDataCriacao,
                        builder.count(root.get("id")), builder.sum(root.get("valorTotal")));

        query.select(selection);

        query.where(predicates.toArray(new Predicate[0]));

        query.groupBy(functionDateDataCriacao);

        return em.createQuery(query).getResultList();
    }

}
