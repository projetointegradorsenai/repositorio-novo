package com.projeto.integrador.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.integrador.model.Jogador;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
	
	
}