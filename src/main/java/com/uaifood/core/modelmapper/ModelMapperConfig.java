package com.uaifood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.uaifood.api.model.EnderecoDTO;
import com.uaifood.api.model.input.ItemPedidoInputDTO;
import com.uaifood.domain.model.Endereco;
import com.uaifood.domain.model.ItemPedido;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();

//		modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
//			.addMapping(Restaurante::getTaxaFrete, RestauranteModel::setPrecoFrete);

		//Conversao de ItemPedidoInput para itemPedido e falamos para adicionar o mapeamento
		//e Dizemos para ignorar o setId, pois o itemPedidoId é o id do item no pedido, não o id do item. Sem isso ocorre uma exception de detach, pois tentara alterar o id do item. 
		modelMapper.createTypeMap(ItemPedidoInputDTO.class, ItemPedido.class)
				.addMappings(mapper -> mapper.skip(ItemPedido::setId));

		var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoDTO.class);

		// codigo para que na request de localhost:8080/restaurantes, abaixo do nome da
		// cidade apareça a palavra "estado", com o nome do estado. Sem este trecho iria
		// ficar um toString(com os atributos de estado) onde deveria ser o nome do
		// estado
		enderecoToEnderecoModelTypeMap.<String>addMapping(enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
				(enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));

		return modelMapper;
	}

}
