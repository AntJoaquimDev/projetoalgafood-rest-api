package com.algafood.cursoapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algafood.cursoapi.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

}
