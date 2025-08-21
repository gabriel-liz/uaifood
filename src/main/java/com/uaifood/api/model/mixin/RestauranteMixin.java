package com.uaifood.api.model.mixin;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uaifood.domain.model.Cozinha;
import com.uaifood.domain.model.Endereco;
import com.uaifood.domain.model.FormaPagamento;
import com.uaifood.domain.model.Produto;

public abstract class RestauranteMixin {

	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	private Cozinha cozinha;

	@JsonIgnore	
	private Endereco endereco;

	@JsonIgnore
	private LocalDateTime dataCadastro;

	@JsonIgnore
	private LocalDateTime dataAtualizacao;

	@JsonIgnore	
	private List<FormaPagamento> formasPagamento;

	@JsonIgnore	
	private List<Produto> produtos;

}
