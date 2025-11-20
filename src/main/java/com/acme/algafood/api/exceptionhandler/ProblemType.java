package com.acme.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
    MENSAGEM_INCOMPREENSIVEL("Mensagem Incompreensível", "/mensagem-incompreensivel"),
    ENTIDADE_EM_USO("Entidade em uso", "/entidade-em-uso"),
    ERRO_NEGOCIO("Violação de regra de negócio", "/erro-negocio"),
    PARAMETRO_INVALIDO("Parâmetro Inválido", "/parametro-invalido"),
    DADOS_INVALIDOS("Dados inválidos", "/dados-invalidos"),
    ERRO_DE_SISTEMA("Erro de sistema", "/erro-de-sistema"),
    RECURSO_NAO_ENCONTRADO("Recurso não encontrado", "/recurso-nao-encontrado"),
    ACESSO_NEGADO("Acesso negado", "/acesso-negado"),;

    private String title;
    private String uri;

    ProblemType(String title, String path) {
        this.title = title;
        this.uri = "https://acme.com.br" + path;
    }
}
