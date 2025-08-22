package com.uaifood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import com.uaifood.domain.service.CadastroCozinhaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uaifood.api.model.FormaPagamentoDTO;
import com.uaifood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoDTOAssembler {

    private final CadastroCozinhaService cadastroCozinhaService;
	
	@Autowired
	private ModelMapper modelMapper;

    FormaPagamentoDTOAssembler(CadastroCozinhaService cadastroCozinhaService) {
        this.cadastroCozinhaService = cadastroCozinhaService;
    }
	
	public FormaPagamentoDTO toModel(FormaPagamento formaPagamento) {
		return modelMapper.map(formaPagamento, FormaPagamentoDTO.class);
	}
	
	public List<FormaPagamentoDTO> toCollectionModel(List<FormaPagamento> formasPagamentos){
		return formasPagamentos.stream()
				.map(formaPagamento -> toModel(formaPagamento))
				.collect(Collectors.toList());
	}

}
