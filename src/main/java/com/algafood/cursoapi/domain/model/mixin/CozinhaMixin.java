package com.algafood.cursoapi.domain.model.mixin;

import java.util.List;

import com.algafood.cursoapi.domain.model.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class CozinhaMixin {

	 @JsonIgnore
	 private List<Restaurante> restaurantes;
	
}