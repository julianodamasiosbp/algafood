package com.acme.algafood.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.acme.algafood.domain.model.Cidade;
import com.acme.algafood.domain.model.Cozinha;
import com.acme.algafood.domain.model.FormaPagamento;
import com.acme.algafood.domain.model.Produto;
import com.acme.algafood.domain.model.Restaurante;
import com.acme.algafood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaService cozinhaService;

	@Autowired
	private CidadeService cidadeService;

	@Autowired
	private FormaPagamentoService formaPagamentoService;

	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Long cidadeId = restaurante.getEndereco().getCidade().getId();

		Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);
		Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);

		restaurante.setCozinha(cozinha);
		restaurante.getEndereco().setCidade(cidade);

		return restauranteRepository.save(restaurante);
	}

	@Transactional
	public void ativar(Long restauranteId) {
		Restaurante restauranteAtual = buscarOuFalhar(restauranteId);

		restauranteAtual.ativar();
	}

	@Transactional
	public void inativar(Long restauranteId) {
		Restaurante restauranteAtual = buscarOuFalhar(restauranteId);

		restauranteAtual.inativar();
	}

	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
				.orElseThrow(() -> new RestauranteNaoEncontradoException(
						restauranteId));
	}

	@Transactional
	public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);
		restaurante.vincularFormaPagamento(formaPagamento);
	}

	@Transactional
	public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);
		restaurante.desvincularFormaPagamento(formaPagamento);
	}

	@Transactional
	public void fechar(Long restauranteId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		restaurante.encerrarAtividades();
	}

	@Transactional
	public void abrir(Long restauranteId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		restaurante.iniciarAtividades();
	}

}
