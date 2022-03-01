package com.algafood.cursoapi.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	
	PARAMETRO_INVALIDO("/parametro-url-invalido", "Parametro de URL inválido"),
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado","Recurso não Eencontrado"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_DE_SISTEMA("/erro-interno-do-sistema","Erro interno de sistema."),
	DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title){
		this.uri = "http://algafood.com.br" + path;
		this.title = title;
	}
}
