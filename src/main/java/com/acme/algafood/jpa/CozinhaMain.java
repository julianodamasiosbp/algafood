//package com.acme.algafood.jpa;
//
//import java.util.List;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//
//import com.acme.algafood.domain.model.Cozinha;
//
//@SpringBootApplication
//public class CozinhaMain {
//	public static void main(String[] args) {
//		SpringApplication.run(CozinhaMain.class, args);
//	}
//
//	@Bean
//	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//		return args -> {
//
//			System.out.println("Let's inspect the beans provided by Spring Boot:");
//			CadastroCozinha cadastroCozinha = ctx.getBean(CadastroCozinha.class);
//
//			Cozinha cozinha = new Cozinha();
//			cozinha.setNome("Brasileira");
//			cadastroCozinha.adicionar(cozinha);
//			
//		};
//	}
//
//}