package com.empresa.service;

import java.util.Optional;

import com.empresa.entity.Boleta;


public interface ApiServicio {

	public abstract Boleta insertaActualiza(Boleta obj);
	public abstract Optional<Boleta> obtienePorNum(String numBoleta);
	
}
