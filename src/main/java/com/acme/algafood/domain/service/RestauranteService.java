package com.acme.algafood.domain.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.acme.algafood.domain.model.Cozinha;
import com.acme.algafood.domain.model.Restaurante;
import com.acme.algafood.domain.repository.CozinhaRepository;
import com.acme.algafood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

    @Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CozinhaService cozinhaService;

    public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);
		restaurante.setCozinha(cozinha);
		return restauranteRepository.save(restaurante);
	}

	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}

	public Restaurante buscar(Long id) {
		return this.buscarOuFalhar(id);
	}


	public Restaurante atualizar(Long id, Restaurante restaurante) {
		Restaurante restauranteSalvo = this.buscarOuFalhar(id);
		if (!Objects.equals(restaurante.getCozinha().getId(), restauranteSalvo.getCozinha().getId())) {
			Long cozinhaId = restaurante.getCozinha().getId();
			Cozinha cozinhaSalva = this.cozinhaService.buscarOuFalhar(cozinhaId);
			restauranteSalvo.setCozinha(cozinhaSalva);
			System.out.println("restauranteSalvo: " + restauranteSalvo.toString());
		}

		BeanUtils.copyProperties(restaurante, restauranteSalvo, "id", "cozinha");
		return restauranteRepository.save(restauranteSalvo);
	}


	public void excluir(Long id) {
		Restaurante restauranteSalvo = this.buscarOuFalhar(id);
		this.restauranteRepository.delete(restauranteSalvo);
	}

	public Restaurante buscarOuFalhar(Long RestauranteId) {
		return this.restauranteRepository
				.findById(RestauranteId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format("Nao existe cadastro de restaurante com codigo: %d", RestauranteId)));
	}
}
