package com.algafood.cursoapi.core.jackson;

import org.springframework.stereotype.Component;

import com.algafood.cursoapi.domain.model.Cidade;
import com.algafood.cursoapi.domain.model.Cozinha;
import com.algafood.cursoapi.domain.model.Restaurante;
import com.algafood.cursoapi.domain.model.mixin.CidadeMixin;
import com.algafood.cursoapi.domain.model.mixin.CozinhaMixin;
import com.algafood.cursoapi.domain.model.mixin.RestauranteMIxin;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Component
public class JacksonMixinModule extends SimpleModule{
	
	private static final long serialVersionUID = 1L;

	public JacksonMixinModule() {
		setMixInAnnotation(Restaurante.class, RestauranteMIxin.class);
		setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
		setMixInAnnotation(Cidade.class, CidadeMixin.class);
	}
}
