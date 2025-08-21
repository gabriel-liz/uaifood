package com.uaifood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uaifood.domain.model.Estado;

public class CidadeMixin {
	
	@JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Estado estado;
}
