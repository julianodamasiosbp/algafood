//package com.acme.algafood.jpa;
//
//import org.springframework.boot.WebApplicationType;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.context.ApplicationContext;
//
//import com.acme.algafood.AlgafoodApiApplication;
//import com.acme.algafood.domain.model.Cozinha;
//import com.acme.algafood.domain.repository.CozinhaRepository;
//
//@SpringBootApplication
//public class BuscaCozinhaMain {
//	public static void main(String[] args) {
//		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
//				.web(WebApplicationType.NONE).run(args);
//
//		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
//
//		Cozinha cozinha = cozinhaRepository.buscar(1L);
//		System.out.printf("A cozinha localizada com o ID %d foi: %s\n",
//				cozinha.getId(), cozinha.getNome());
//	}
//}