package com.uaifood.api.model.mixin;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uaifood.domain.model.Restaurante;

public class CozinhaMixin {
	
	@JsonIgnore
	private List<Restaurante> restaurantes = new ArrayList<>();
}
