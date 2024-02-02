package com.acme.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.acme.algafood.AlgafoodApiApplication;
import com.acme.algafood.domain.model.Permissao;
import com.acme.algafood.domain.repository.PermissaoRepository;

@SpringBootApplication
public class ConsultaPermissaoMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

		PermissaoRepository permissaoRepository = applicationContext.getBean(PermissaoRepository.class);

		List<Permissao> permissoes = permissaoRepository.listar();

		for (Permissao permissao : permissoes) {
			System.out.printf("%s - %s\n", permissao.getNome() ,permissao.getDescricao());
		}

	}

}
