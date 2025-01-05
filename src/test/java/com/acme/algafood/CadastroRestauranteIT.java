package com.acme.algafood;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.acme.algafood.domain.model.Cozinha;
import com.acme.algafood.domain.model.Restaurante;
import com.acme.algafood.domain.repository.CozinhaRepository;
import com.acme.algafood.domain.repository.RestauranteRepository;
import com.acme.algafood.util.DatabaseCleaner;
import com.acme.algafood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroRestauranteIT {

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    private String restauranteJson;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        restauranteJson = ResourceUtils.getContentFromResource(
                "/json/create-restaurante.json");

        RestAssured.port = port;
        RestAssured.basePath = "/restaurantes";

        databaseCleaner.clearTables();
        prepararDados();
    }

    @Test
    public void deveRetornarStatus200_QuandoConsultarRestaurantes() {
        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveRetornarStatus201_QuandoCadastrarRestaurante() {
        RestAssured
                .given()
                .body(restauranteJson)
                .contentType(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    private void prepararDados() {
        Cozinha cozinhaTailandesa = new Cozinha();
        cozinhaTailandesa.setNome("Tailandesa");
        cozinhaRepository.save(cozinhaTailandesa);

        Restaurante restauranteTakami = new Restaurante();
        restauranteTakami.setNome("Takami");
        restauranteTakami.setTaxaFrete(BigDecimal.valueOf(15.22));
        restauranteTakami.setCozinha(cozinhaTailandesa);
    }

}
