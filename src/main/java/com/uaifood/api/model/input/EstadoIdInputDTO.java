package com.uaifood.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoIdInputDTO {
	
	@NotNull
	private Long id;
}
