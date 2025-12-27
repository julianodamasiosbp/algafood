package com.acme.algafood.api.v1.controller;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import com.acme.algafood.api.v1.assembler.FormaPagamentoModelAssembler;
import com.acme.algafood.api.v1.disassembler.FormaPagamentoInputDisassembler;
import com.acme.algafood.api.v1.model.request.FormaPagamentoInput;
import com.acme.algafood.api.v1.model.response.FormaPagamentoModel;
import com.acme.algafood.api.v1.openapi.controller.FormaPagamentoControllerOpenApi;
import com.acme.algafood.core.security.CheckSecurity;
import com.acme.algafood.domain.model.FormaPagamento;
import com.acme.algafood.domain.repository.FormaPagamentoRepository;
import com.acme.algafood.domain.service.FormaPagamentoService;

@RestController
@RequestMapping("/v1/formas-pagamento")
public class FormaPagamentoController implements FormaPagamentoControllerOpenApi {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

    @Autowired
    private FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;

    @CheckSecurity.FormasPagamento.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<FormaPagamentoModel>> listar(ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getDataUltimaAtualizacao();

        if (dataUltimaAtualizacao != null) {
            eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
        }

        if (request.checkNotModified(eTag)) {
            return null;
        }

        List<FormaPagamento> formaPagamentos = formaPagamentoRepository.findAll();
        CollectionModel<FormaPagamentoModel> formasPagamentosModel = formaPagamentoModelAssembler
                .toCollectionModel(formaPagamentos);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(20, TimeUnit.SECONDS).cachePublic())
                .eTag(eTag)
                .body(formasPagamentosModel);
    }

    @CheckSecurity.FormasPagamento.PodeConsultar
    @GetMapping(path = "/{formaPagamentoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FormaPagamentoModel> buscar(@PathVariable Long formaPagamentoId, ServletWebRequest request) {

        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);

        if (formaPagamento != null) {
            eTag = String.valueOf(formaPagamento.getDataAtualizacao().toEpochSecond());
        }

        if (request.checkNotModified(eTag)) {
            return null;
        }

        FormaPagamentoModel formaPagamentoModel = formaPagamentoModelAssembler
                .toModel(formaPagamento);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(20, TimeUnit.SECONDS).cachePublic())
                // .cacheControl(CacheControl.maxAge(20, TimeUnit.SECONDS).cachePrivate())
                // .cacheControl(CacheControl.noCache())
                // .cacheControl(CacheControl.noStore())
                .body(formaPagamentoModel);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoModel adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamentoSalvo = formaPagamentoService
                .salvar(formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInput));
        return formaPagamentoModelAssembler.toModel(formaPagamentoSalvo);
    }

    @CheckSecurity.FormasPagamento.PodeEditar
    @PutMapping(path = "/{formaPagamentoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FormaPagamentoModel atualizar(@PathVariable Long formaPagamentoId,
            @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamentoSalvo = formaPagamentoService.buscarOuFalhar(formaPagamentoId);
        formaPagamentoInputDisassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoSalvo);
        return formaPagamentoModelAssembler.toModel(formaPagamentoService.salvar(formaPagamentoSalvo));
    }

    @CheckSecurity.FormasPagamento.PodeEditar
    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long formaPagamentoId) {
        formaPagamentoService.excluir(formaPagamentoId);
    }

    // @GetMapping
    // public List<FormaPagamentoModel> listar() {
    // return
    // formaPagamentoModelAssembler.toCollectionModel(formaPagamentoRepository.findAll());
    // }

    // @GetMapping("/{formaPagamentoId}")
    // public FormaPagamentoModel buscar(@PathVariable Long formaPagamentoId) {
    // return
    // formaPagamentoModelAssembler.toModel(formaPagamentoService.buscarOuFalhar(formaPagamentoId));
    // }
}
