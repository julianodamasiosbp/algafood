package com.acme.algafood.domain.service;

import java.util.List;
import java.util.Objects;

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

	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

		if (cozinha == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Nao existe cadastro de cozinha com codigo: %d", cozinhaId));

		}
		restaurante.setCozinha(cozinha);
		return restauranteRepository.salvar(restaurante);
	}

	public List<Restaurante> listar() {
		return restauranteRepository.listar();
	}

	public Restaurante buscar(Long id) {
		Restaurante restaurante = restauranteRepository.buscar(id);
		if (restaurante == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Nao existe cadastro de restaurante com codigo: %d", id));
		}
		return restaurante;
	}

	public Restaurante atualizar(Long id, Restaurante restaurante) {
		Restaurante restauranteSalvo = this.restauranteRepository.buscar(id);
		if (restauranteSalvo == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Nao existe cadastro de restaurante com codigo: %d", id));
		}
		if (!Objects.equals(restaurante.getCozinha().getId(), restauranteSalvo.getCozinha().getId())) {
			Long cozinhaId = restaurante.getCozinha().getId();
			Cozinha cozinhaSalva = this.cozinhaRepository.buscar(cozinhaId);
			if (cozinhaSalva == null) {
				throw new EntidadeNaoEncontradaException(
						String.format("Nao existe cadastro de cozinha com codigo: %d", cozinhaId));
			}
			restauranteSalvo.setCozinha(cozinhaSalva);
			System.out.println("restauranteSalvo: " + restauranteSalvo.toString());
		}

		BeanUtils.copyProperties(restaurante, restauranteSalvo, "id", "cozinha");
		return restauranteRepository.salvar(restauranteSalvo);
	}

	public void excluir(Long id) {
		Restaurante restauranteSalvo = this.restauranteRepository.buscar(id);
		if (restauranteSalvo == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Nao existe cadastro de restaurante com codigo: %d", id));
		}
		this.restauranteRepository.remover(restauranteSalvo);
	}
}
