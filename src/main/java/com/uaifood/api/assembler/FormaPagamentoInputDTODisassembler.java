package com.uaifood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.uaifood.api.model.input.FormaPagamentoInputDTO;
import com.uaifood.domain.model.FormaPagamento;

public class FormaPagamentoInputDTODisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public FormaPagamento toDomainObject(FormaPagamentoInputDTO formaPagamentoInputDTO) {
		return modelMapper.map(formaPagamentoInputDTO, FormaPagamento.class);
	}
	
	public void copyToDomainObject(FormaPagamentoInputDTO formaPagamentoInputDTO, FormaPagamento formaPagamento) {
		modelMapper.map(formaPagamentoInputDTO, formaPagamento);
	}

}
