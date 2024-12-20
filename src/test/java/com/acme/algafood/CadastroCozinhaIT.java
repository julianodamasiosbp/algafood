package com.acme.algafood;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.acme.algafood.domain.model.Cozinha;
import com.acme.algafood.domain.repository.CozinhaRepository;
import com.acme.algafood.util.DatabaseCleaner;
import com.acme.algafood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroCozinhaIT {

    @LocalServerPort
    private int port;

    // @Autowired
    // private Flyway flyway;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    private static final int COZINHA_ID_INEXISTENTE = 1000;

    private Cozinha cozinhaAmericana;
    private int quantidadeCozinhasCadastradas;
    private String cozinhaChinesaJson;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        cozinhaChinesaJson = ResourceUtils.getContentFromResource(
            "/json/create-cozinha-chinesa.json");

        RestAssured.port = port;
        RestAssured.basePath = "/cozinhas";

        // flyway.migrate();
        databaseCleaner.clearTables();
        prepararDados();
    }

    // @Test
    // public void deveAtribuirId_QuandoCozinhaCadastradaComDadosCorretos() {
    // Cozinha novaCozinha = new Cozinha();
    // novaCozinha.setNome("Chinesa");

    // Cozinha cozinhaSalva = cozinhaService.salvar(novaCozinha);

    // assertThat(cozinhaSalva).isNotNull();
    // assertThat(cozinhaSalva.getNome()).isEqualTo("Chinesa");
    // }

    // @Test
    // public void deveFalhar_QuandoCozinhaCadastradaSemNome() {
    // Cozinha novaCozinha = new Cozinha();

    // ConstraintViolationException erroEsperado =
    // Assertions.assertThrows(ConstraintViolationException.class, () -> {
    // cozinhaService.salvar(novaCozinha);
    // });

    // assertThat(erroEsperado).isNotNull();
    // }

    // @Test
    // public void deveFalhar_QuandoExcluirCozinhaEmUso(){
    // EntidadeEmUsoException erroEsperado =
    // Assertions.assertThrows(EntidadeEmUsoException.class, () -> {
    // cozinhaService.excluir(1L);
    // });

    // assertThat(erroEsperado).isNotNull();
    // }

    // @Test
    // public void deveFalhar_QuandoExcluirCozinhaInexistente(){
    // CozinhaNaoEncontradaException erroEsperado =
    // Assertions.assertThrows(CozinhaNaoEncontradaException.class, () -> {
    // cozinhaService.excluir(9999L);
    // });

    // assertThat(erroEsperado).isNotNull();
    // }

    @Test
    public void deveRetornarStatus200_QuandoConsultarCozinhas() {
        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveRetornarQuantidadeCorretaDeCozinhas_QuandoConsultarCozinhas() {
        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("", Matchers.hasSize(quantidadeCozinhasCadastradas));
    }

    @Test
    public void deveRetornarStatus201_QuandoCadastrarCozinha() {
        RestAssured
                .given()
                .body(cozinhaChinesaJson)
                .contentType(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente() {
        RestAssured
                .given()
                .pathParam("cozinhaId", cozinhaAmericana.getId())
                .accept(ContentType.JSON)
                .when()
                .get("/{cozinhaId}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", Matchers.equalTo(cozinhaAmericana.getNome()));
    }

    @Test
    public void deveRetornarStatus404_QuandoConsultarCozinhaExistente() {
        RestAssured
                .given()
                .pathParam("cozinhaId",  COZINHA_ID_INEXISTENTE)
                .accept(ContentType.JSON)
                .when()
                .get("/{cozinhaId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepararDados() {
        Cozinha cozinhaTailandesa = new Cozinha();
        cozinhaTailandesa.setNome("Tailandesa");
        cozinhaRepository.save(cozinhaTailandesa);
    
        cozinhaAmericana = new Cozinha();
        cozinhaAmericana.setNome("Americana");
        cozinhaRepository.save(cozinhaAmericana);

        quantidadeCozinhasCadastradas = (int) cozinhaRepository.count();
    }
}
