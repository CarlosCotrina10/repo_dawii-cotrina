package com.empresa.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Boleta;
import com.empresa.entity.BoletaJSON;
import com.empresa.service.ApiServicio;


@RestController
@RequestMapping("/rest/boleta")
public class ApiController {
	
	@Autowired
	private ApiServicio servicio;

	@PutMapping
	public ResponseEntity<Boleta> actualiza(@RequestBody BoletaJSON bol) {

		System.out.println("API REST >>>>> " + bol.getNumBoleta());
		Optional<Boleta> optBoleta = servicio.obtienePorNum(bol.getNumBoleta());
		
		if (optBoleta.isPresent()) {
			Boleta boleta = optBoleta.get();
			boleta.setEstado(0);
			boleta = servicio.insertaActualiza(boleta);
			if (boleta != null) {
				return ResponseEntity.ok(boleta);
			} else {
				System.out.println("error");
				return ResponseEntity.badRequest().build();	
			}
		} else {
			System.out.println("no hay");
			return ResponseEntity.badRequest().build();
		}

	}


}

