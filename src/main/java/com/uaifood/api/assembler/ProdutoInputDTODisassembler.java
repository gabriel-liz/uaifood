package com.uaifood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uaifood.api.model.input.ProdutoInputDTO;
import com.uaifood.domain.model.Produto;

@Component
public class ProdutoInputDTODisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Produto toDomainObject(ProdutoInputDTO produtoInput) {
		return modelMapper.map(produtoInput, Produto.class);
	}
	
	public void copyToDomainObject(ProdutoInputDTO produtoInptut, Produto produto) {
		modelMapper.map(produtoInptut, produto);
	}
	

}
