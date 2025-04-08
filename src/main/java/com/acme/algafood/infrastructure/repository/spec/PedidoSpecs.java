package com.acme.algafood.infrastructure.repository.spec;

import com.acme.algafood.domain.model.Pedido;
import com.acme.algafood.domain.model.Restaurante;
import com.acme.algafood.domain.repository.filter.PedidoFilter;

import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

public class PedidoSpecs {

    public static Specification<Pedido> usandoFiltro(PedidoFilter filtro) {
        return (root, query, builder) -> {
            root.fetch("restaurante");
            var predicates = new ArrayList<Predicate>();

            if (filtro.getClienteId() != null) {
                predicates.add(builder.equal(root.get("cliente"), filtro.getClienteId()));
            }

            if (filtro.getRestauranteId() != null) {
                predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
            }

            if (filtro.getDataCriacaoInicio() != null) {
                predicates.add(builder
                        .greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
            }

            if (filtro.getDataCriacaoFim() != null) {
                predicates.add(builder
                        .lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };

    }

}
