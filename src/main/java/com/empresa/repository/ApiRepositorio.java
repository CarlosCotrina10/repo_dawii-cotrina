package com.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.empresa.entity.Boleta;


public interface ApiRepositorio extends JpaRepository<Boleta, String>{
	/*
	@Query("")
	public abstract Boleta cancelaCompra(String numBoleta);
*/
}
