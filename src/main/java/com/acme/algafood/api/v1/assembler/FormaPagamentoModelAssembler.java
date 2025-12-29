package com.acme.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.acme.algafood.api.v1.AlgafoodLinks;
import com.acme.algafood.api.v1.controller.FormaPagamentoController;
import com.acme.algafood.api.v1.model.response.FormaPagamentoModel;
import com.acme.algafood.core.security.AlgaSecurity;
import com.acme.algafood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoModelAssembler
        extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoModel> {

    public FormaPagamentoModelAssembler() {
        super(FormaPagamentoController.class, FormaPagamentoModel.class);

    }

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgafoodLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    @Override
    public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
        FormaPagamentoModel formaPagamentoModel = createModelWithId(formaPagamento.getId(), formaPagamento);

        modelMapper.map(formaPagamento, formaPagamentoModel);

        if (algaSecurity.podeConsultarFormasPagamento()) {
            formaPagamentoModel.add(algaLinks.linkToFormasPagamento("formasPagamento"));
        }

        return formaPagamentoModel;
    }

    @Override
    public CollectionModel<FormaPagamentoModel> toCollectionModel(Iterable<? extends FormaPagamento> entities) {
        CollectionModel<FormaPagamentoModel> collectionModel = super.toCollectionModel(entities);

        if (algaSecurity.podeConsultarFormasPagamento()) {
            collectionModel.add(algaLinks.linkToFormasPagamento());
        }

        return collectionModel;
    }
}
