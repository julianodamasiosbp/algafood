package com.acme.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.acme.algafood.AlgafoodApiApplication;
import com.acme.algafood.domain.model.FormaPagamento;
import com.acme.algafood.domain.repository.FormaPagamentoRepository;

@SpringBootApplication
public class ConsultaFormaPagamentoMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

		FormaPagamentoRepository formaPagamentoRepository = applicationContext.getBean(FormaPagamentoRepository.class);

		List<FormaPagamento> formaPagamentos = formaPagamentoRepository.listar();

		for (FormaPagamento formaPagamento : formaPagamentos) {
			System.out.println(formaPagamento.getDescricao());
		}

	}

}
