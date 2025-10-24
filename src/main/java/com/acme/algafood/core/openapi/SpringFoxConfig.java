package com.acme.algafood.core.openapi;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.ServletWebRequest;

import com.acme.algafood.api.exceptionhandler.Problem;
import com.acme.algafood.api.model.response.CidadeModel;
import com.acme.algafood.api.model.response.CozinhaModel;
import com.acme.algafood.api.model.response.EstadoModel;
import com.acme.algafood.api.model.response.FormaPagamentoModel;
import com.acme.algafood.api.model.response.GrupoModel;
import com.acme.algafood.api.model.response.PedidoResumoModel;
import com.acme.algafood.api.model.response.PermissaoModel;
import com.acme.algafood.api.openapi.model.CidadesModelOpenApi;
import com.acme.algafood.api.openapi.model.CozinhasModelOpenApi;
import com.acme.algafood.api.openapi.model.EstadosModelOpenApi;
import com.acme.algafood.api.openapi.model.FormasPagamentoModelOpenApi;
import com.acme.algafood.api.openapi.model.GruposModelOpenApi;
import com.acme.algafood.api.openapi.model.LinksModelOpenApi;
import com.acme.algafood.api.openapi.model.PageableModelOpenApi;
import com.acme.algafood.api.openapi.model.PedidosResumoModelOpenApi;
import com.acme.algafood.api.openapi.model.PermissoesModelOpenApi;
import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RepresentationBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {

        @Bean
        public Docket apiDocket() {
                TypeResolver typeResolver = new TypeResolver();
                return new Docket(DocumentationType.OAS_30)
                                .select()
                                .apis(RequestHandlerSelectors.basePackage("com.acme.algafood.api"))
                                .paths(PathSelectors.any())
                                .build()
                                .useDefaultResponseMessages(false)
                                .globalResponses(HttpMethod.GET, globalGetResponseMessages())
                                .globalResponses(HttpMethod.POST, globalPostResponseMessages())
                                .globalResponses(HttpMethod.PUT, globalPutResponseMessages())
                                .globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
                                .additionalModels(typeResolver.resolve(Problem.class))
                                .ignoredParameterTypes(ServletWebRequest.class, URL.class, URI.class,
                                                URLStreamHandler.class, Resource.class, File.class, InputStream.class)
                                .directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
                                .directModelSubstitute(Links.class, LinksModelOpenApi.class)
                                .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(PagedModel.class,
                                                CozinhaModel.class), CozinhasModelOpenApi.class))
                                .alternateTypeRules(AlternateTypeRules.newRule(
                                                typeResolver.resolve(CollectionModel.class, EstadoModel.class),
                                                EstadosModelOpenApi.class))
                                .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(Page.class,
                                                PedidoResumoModel.class), PedidosResumoModelOpenApi.class))
                                .alternateTypeRules(
                                                AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class,
                                                                CidadeModel.class), CidadesModelOpenApi.class))
                                .alternateTypeRules(AlternateTypeRules.newRule(
                                                typeResolver.resolve(CollectionModel.class, FormaPagamentoModel.class),
                                                FormasPagamentoModelOpenApi.class))
                                .alternateTypeRules(AlternateTypeRules.newRule(
                                                typeResolver.resolve(CollectionModel.class, GrupoModel.class),
                                                GruposModelOpenApi.class))
                                .alternateTypeRules(AlternateTypeRules.newRule(
                                                typeResolver.resolve(CollectionModel.class, PermissaoModel.class),
                                                PermissoesModelOpenApi.class))
                                .apiInfo(apiInfo())
                                .tags(
                                                new Tag("Cidades", "Gerencia as cidade"),
                                                new Tag("Grupos", "Gerencia os grupos"),
                                                new Tag("Cozinhas", "Gerencia as cozinhas"),
                                                new Tag("Formas de Pagamento", "Gerencia as formas de pagamento"),
                                                new Tag("Pedidos", "Gerencia os pedidos"),
                                                new Tag("Restaurantes", "Gerencia os restaurantes"),
                                                new Tag("Estados", "Gerencia os estados"),
                                                new Tag("Produtos", "Gerencia os produtos de restaurantes"),
                                                new Tag("Usuários", "Gerencia os usuários"),
                                                new Tag("Estatísticas", "Estatísticas da AlgaFood"),
                                                new Tag("Permissões", "Gerencia as permissões"));
        }

        public ApiInfo apiInfo() {
                return new ApiInfoBuilder()
                                .title("Algafood API")
                                .description("API aberta para clientes e restaurantes")
                                .version("1")
                                .contact(new Contact("ACME Corp", "www.acme.com.br", "contato@acme.com.br")).build();
        }

        private List<Response> globalGetResponseMessages() {
                return Arrays.asList(
                                new ResponseBuilder()
                                                .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                                                .description("Erro interno do Servidor")
                                                .representation(MediaType.APPLICATION_JSON)
                                                .apply(getProblemaModelReference())
                                                .build(),
                                new ResponseBuilder()
                                                .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                                                .description("Recurso não possui representação que pode ser aceita pelo consumidor")
                                                .build());
        }

        private List<Response> globalDeleteResponseMessages() {
                return Arrays.asList(
                                new ResponseBuilder()
                                                .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                                                .description("Erro interno do Servidor")
                                                .representation(MediaType.APPLICATION_JSON)
                                                .apply(getProblemaModelReference())
                                                .build(),
                                new ResponseBuilder()
                                                .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                                                .description("Recurso não possui representação que pode ser aceita pelo consumidor")
                                                .build());
        }

        private List<Response> globalPutResponseMessages() {
                return Arrays.asList(
                                new ResponseBuilder()
                                                .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                                                .description("O servidor não pode ou não irá processar a requisição")
                                                .representation(MediaType.APPLICATION_JSON)
                                                .apply(getProblemaModelReference())
                                                .build(),
                                new ResponseBuilder()
                                                .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                                                .description("Erro interno do Servidor")
                                                .representation(MediaType.APPLICATION_JSON)
                                                .apply(getProblemaModelReference())
                                                .build(),
                                new ResponseBuilder()
                                                .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                                                .description("Recurso não possui representação que pode ser aceita pelo consumidor")
                                                .build(),
                                new ResponseBuilder()
                                                .code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
                                                .description("Formato do payload não é um formato suportado")
                                                .representation(MediaType.APPLICATION_JSON)
                                                .apply(getProblemaModelReference())
                                                .build());
        }

        private List<Response> globalPostResponseMessages() {
                return Arrays.asList(
                                new ResponseBuilder()
                                                .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                                                .description("O servidor não pode ou não irá processar a requisição")
                                                .representation(MediaType.APPLICATION_JSON)
                                                .apply(getProblemaModelReference())
                                                .build(),
                                new ResponseBuilder()
                                                .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                                                .description("Erro interno do Servidor")
                                                .representation(MediaType.APPLICATION_JSON)
                                                .apply(getProblemaModelReference())
                                                .build(),
                                new ResponseBuilder()
                                                .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                                                .description("Recurso não possui representação que pode ser aceita pelo consumidor")
                                                .build(),
                                new ResponseBuilder()
                                                .code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
                                                .description("Formato do payload não é um formato suportado")
                                                .representation(MediaType.APPLICATION_JSON)
                                                .apply(getProblemaModelReference())
                                                .build());
        }

        @Bean
        public JacksonModuleRegistrar springFoxJacksonConfig() {
                return objectMapper -> objectMapper.registerModule(new JavaTimeModule());
        }

        private Consumer<RepresentationBuilder> getProblemaModelReference() {
                return r -> r.model(m -> m.name("Problema")
                                .referenceModel(ref -> ref.key(k -> k.qualifiedModelName(
                                                q -> q.name("Problema").namespace(
                                                                "com.acme.algafood.api.exceptionhandler")))));
        }

}
