package com.acme.algafood;

import com.acme.algafood.domain.model.Cozinha;
import com.acme.algafood.domain.service.CozinhaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CadastroCozinhaIntegrationTests {

    @Autowired
    private CozinhaService cozinhaService;

    @Test
	public void testarCadastroCozinhaComSucesso() {
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("Chinesa");

        Cozinha cozinhaSalva = cozinhaService.salvar(novaCozinha);

        assertThat(cozinhaSalva).isNotNull();
        assertThat(cozinhaSalva.getNome()).isEqualTo("Chinesa");
    }

    @Test
    public void deveFalharAoCadastrarCozinhaSemNome() {
        Cozinha novaCozinha = new Cozinha();

        ConstraintViolationException erroEsperado =
                Assertions.assertThrows(ConstraintViolationException.class, () -> {
                    cozinhaService.salvar(novaCozinha);
                });

        assertThat(erroEsperado).isNotNull();
    }
}
