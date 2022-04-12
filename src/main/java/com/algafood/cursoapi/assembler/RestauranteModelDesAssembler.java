package com.algafood.cursoapi.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.algafood.cursoapi.domain.model.Restaurante;
import com.algafood.cursoapi.model.input.RestauranteInput;

@Component
public class RestauranteModelDesAssembler {

	@Autowired
	private ModelMapper modelMapper;
		
	public Restaurante toDomainObject(RestauranteInput restauranteInput) {

		return modelMapper.map(restauranteInput, Restaurante.class);
	}

}
