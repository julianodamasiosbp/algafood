package com.acme.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
        private String nome;

        @Column(name = " taxa_frete", nullable = false)
        private BigDecimal taxaFrete;

        @ManyToOne // (fetch = FetchType.LAZY)
        @JoinColumn(name = "cozinha_id", nullable = false)
        private Cozinha cozinha;

        @Embedded
        private Endereco endereco;

        private Boolean ativo = Boolean.TRUE;

        private Boolean aberto = Boolean.FALSE;

        @CreationTimestamp
        @Column(nullable = false, columnDefinition = "datetime")
        private OffsetDateTime dataCadastro;

        @UpdateTimestamp
        @Column(nullable = false, columnDefinition = "datetime")
        private OffsetDateTime dataAtualizacao;

        @ManyToMany
        @JoinTable(name = "restaurante_forma_pagamento", joinColumns = @JoinColumn(name = "restaurante_id"), inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
        private Set<FormaPagamento> formasPagamento = new HashSet<>();

        @OneToMany(mappedBy = "restaurante")
        private List<Produto> produtos = new ArrayList<>();

        @ManyToMany
        @JoinTable(name = "restaurante_usuario_responsavel", joinColumns = @JoinColumn(name = "restaurante_id"), inverseJoinColumns = @JoinColumn(name = "usuario_id"))
        private Set<Usuario> responsaveis = new HashSet<>();

        public void ativar() {
                setAtivo(true);
        }

        public void inativar() {
                setAtivo(false);
        }

        public void iniciarAtividades() {
                setAberto(true);
        }

        public void encerrarAtividades() {
                setAberto(false);
        }

        public boolean vincularFormaPagamento(FormaPagamento formaPagamento) {
                return getFormasPagamento().add(formaPagamento);
        }

        public boolean desvincularFormaPagamento(FormaPagamento formaPagamento) {
                return getFormasPagamento().remove(formaPagamento);
        }

        public boolean vincularResponsavel(Usuario usuario) {
                return getResponsaveis().add(usuario);
        }

        public boolean desvincularResponsavel(Usuario usuario) {
                return getResponsaveis().remove(usuario);
        }

        public boolean aceitaFormaPagamento(FormaPagamento formaPagamento) {
                return getFormasPagamento().contains(formaPagamento);
            }
            
            public boolean naoAceitaFormaPagamento(FormaPagamento formaPagamento) {
                return !aceitaFormaPagamento(formaPagamento);
            }

}
