package com.uaifood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");       

	private String tittle;
	private String uri;

	private ProblemType(String path, String tittle) {
		this.uri = "localhost:8080" + path;
		this.tittle = tittle;
	}

}
