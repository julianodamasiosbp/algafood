//package com.acme.algafood.jpa;
//
//import java.util.List;
//
//import org.springframework.boot.WebApplicationType;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.context.ApplicationContext;
//
//import com.acme.algafood.AlgafoodApiApplication;
//import com.acme.algafood.domain.model.Cidade;
//import com.acme.algafood.domain.repository.CidadeRepository;
//
//@SpringBootApplication
//public class ConsultaCidadeMain {
//	public static void main(String[] args) {
//		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
//				.web(WebApplicationType.NONE).run(args);
//
//		CidadeRepository cidadeRepository = applicationContext.getBean(CidadeRepository.class);
//
//		List<Cidade> cidades = cidadeRepository.listar();
//
//		for (Cidade cidade : cidades) {
//			System.out.printf("%s - %s\n", cidade.getNome(), cidade.getEstado().getNome());
//		}
//
//	}
//
//}
