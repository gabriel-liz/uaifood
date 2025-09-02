package com.uaifood.domain.model;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {
	
	CRIADO("Criado"),
    CONFIRMADO("Confirmado", CRIADO),
    ENTREGUE("Entregue", CONFIRMADO),
    CANCELADO("Cancelado", CRIADO);
    
    private String descricao;
    
    
    private List<StatusPedido> statusAnteriores;
	
    //O segundo parametro é um varArgs(...) porque podemos passar 1 statusAnteriores, mais de 1 ou nenhum
	StatusPedido(String descricao, StatusPedido... statusAnteriores) {
		this.descricao = descricao;
		this.statusAnteriores = Arrays.asList(statusAnteriores);
	}
	
	public String getDescricao() {
		return this.descricao;
	} 
	
	public boolean naoPodeAlterarPara(StatusPedido novoStatus) {
		return !novoStatus.statusAnteriores.contains(this);
		//this é o status que foi enviado como parametro
	}

}
