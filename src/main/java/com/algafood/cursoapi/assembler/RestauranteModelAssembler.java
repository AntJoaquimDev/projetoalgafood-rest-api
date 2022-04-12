package com.algafood.cursoapi.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algafood.cursoapi.domain.model.Restaurante;
import com.algafood.cursoapi.model.RestauranteModel;

@Component
public class RestauranteModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public RestauranteModel toModel(Restaurante restaurante) {
		return modelMapper.map(restaurante, RestauranteModel.class);
	}

	public List<RestauranteModel> toCollectioModel(List<Restaurante> restaurantes) {
		return restaurantes.stream().map((restaurante) -> toModel(restaurante)).collect(Collectors.toList());
	}
}
