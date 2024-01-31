//package com.acme.algafood.jpa;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.ApplicationContext;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import com.acme.algafood.domain.model.Cozinha;
//
//@Component
//@Order(2)
//public class ConsultaCommandLineRunner implements CommandLineRunner{
//	
//	private final ApplicationContext applicationContext;
//	
//	public ConsultaCommandLineRunner(ApplicationContext applicationContext) {
//		this.applicationContext = applicationContext;
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println("Let's inspect the beans provided by Spring Boot:");
//		CadastroCozinha cadastroCozinha = this.applicationContext.getBean(CadastroCozinha.class);
//		System.out.println(cadastroCozinha);
//
//		Cozinha cozinha = new Cozinha();
//		cozinha.setNome("Brasileira");
//		cadastroCozinha.adicionar(cozinha);
//		
//	}
//
//}
