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
//public class ExclusaoCozinhaMain {
//	public static void main(String[] args) {
//		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
//				.web(WebApplicationType.NONE)
//				.run(args);
//
//		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
//
//		Cozinha cozinha = new Cozinha();
//		cozinha.setId(1L);
//		cozinhaRepository.remover(cozinha.getId());
//	}
//
//}