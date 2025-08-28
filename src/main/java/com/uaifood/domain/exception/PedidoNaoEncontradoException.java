package com.uaifood.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PedidoNaoEncontradoException(String mensagem) {
		super(mensagem);
		// TODO Auto-generated constructor stub
	}
	
	public PedidoNaoEncontradoException(Long pedidoId) {
		this(String.format("Não existe um pedido com código %d", pedidoId));
	}

	
	
}
