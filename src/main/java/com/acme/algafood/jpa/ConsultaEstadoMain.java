package com.acme.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.acme.algafood.AlgafoodApiApplication;
import com.acme.algafood.domain.model.Estado;
import com.acme.algafood.domain.repository.EstadoRepository;

@SpringBootApplication
public class ConsultaEstadoMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

		EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);

		List<Estado> estados = estadoRepository.listar();

		for (Estado estado : estados) {
			System.out.println(estado.getNome());
		}

	}

}
