package com.acme.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.acme.algafood.core.validation.Groups;
import com.acme.algafood.core.validation.ValorZeroIncluiDescricao;

import lombok.Data;
import lombok.EqualsAndHashCode;

@ValorZeroIncluiDescricao(valorField = "taxaFrete", descricaoField = "nome", descricaoObrigatoria = "Frete Grátis")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

        @Id
        @EqualsAndHashCode.Include
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        @NotBlank
        private String nome;

        // @Multiplo(numero = 5)
        // @TaxaFrete
        @PositiveOrZero(message = "{TaxaFrete.invalida}")
        @NotNull
        @Column(name = " taxa_frete", nullable = false)
        private BigDecimal taxaFrete;

        @NotNull
        @Valid
        @ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
        @ManyToOne // (fetch = FetchType.LAZY)
        @JoinColumn(name = "cozinha_id", nullable = false)
        private Cozinha cozinha;

        @Embedded
        private Endereco endereco;

        @CreationTimestamp
        @Column(nullable = false, columnDefinition = "datetime")
        private OffsetDateTime dataCadastro;

        @UpdateTimestamp
        @Column(nullable = false, columnDefinition = "datetime")
        private OffsetDateTime dataAtualizacao;

        @ManyToMany
        @JoinTable(name = "restaurante_forma_pagamento", joinColumns = @JoinColumn(name = "restaurante_id"), inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
        private List<FormaPagamento> formasPagamento = new ArrayList<>();

        @OneToMany(mappedBy = "restaurante")
        private List<Produto> produtos = new ArrayList<>();
}
