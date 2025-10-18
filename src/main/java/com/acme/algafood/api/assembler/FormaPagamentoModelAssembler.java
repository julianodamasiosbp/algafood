package com.acme.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.acme.algafood.api.controller.FormaPagamentoController;
import com.acme.algafood.api.model.response.FormaPagamentoModel;
import com.acme.algafood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoModelAssembler
        extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoModel> {

    public FormaPagamentoModelAssembler() {
        super(FormaPagamento.class, FormaPagamentoModel.class);

    }

    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
        FormaPagamentoModel formaPagamentoModel = createModelWithId(formaPagamento.getId(), formaPagamento);

        modelMapper.map(formaPagamento, formaPagamentoModel);

        formaPagamentoModel.add(linkTo(FormaPagamentoController.class).withRel("formas-pagamento"));

        return formaPagamentoModel;
    }

    public List<FormaPagamentoModel> toCollectionModel(Collection<FormaPagamento> formaPagamentos) {
        return formaPagamentos
                .stream()
                .map(formaPagamento -> toModel(formaPagamento))
                .collect(Collectors.toList());
    }
}
