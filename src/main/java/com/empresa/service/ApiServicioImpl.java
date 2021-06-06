package com.empresa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.Boleta;
import com.empresa.repository.ApiRepositorio;



@Service
public class ApiServicioImpl implements ApiServicio {
	
	@Autowired
	private ApiRepositorio repo;

	@Override
	public Boleta insertaActualiza(Boleta obj) {
		return repo.save(obj);
	}

	@Override
	public Optional<Boleta> obtienePorNum(String numBoleta) {
		return repo.findById(numBoleta);
	}
	
	
	
}