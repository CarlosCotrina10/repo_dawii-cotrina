package com.empresa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Alumno;
import com.empresa.service.AlumnoService;

//import lombok.extern.apachecommons.CommonsLog;

//@CommonsLog
@RestController
@RequestMapping("/rest/alumno")
public class AlumnoController {
	
	@Autowired
	private AlumnoService service;
	
	@GetMapping
	public ResponseEntity<List<Alumno>> lista(){
		System.out.println(">>>> lista ");

		List<Alumno> lstAlumno = service.listaAlumno();
		return ResponseEntity.ok(lstAlumno);
	}
	
	@PostMapping
	public ResponseEntity<Alumno> registra(@RequestBody Alumno obj){
		System.out.println(">>>> registra " + obj.getIdAlumno());
		Alumno objSalida = service.insertaActualizaAlumno(obj);
		if (objSalida != null) {
			return ResponseEntity.ok(obj);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping
	public ResponseEntity<Alumno> actualiza(@RequestBody Alumno obj){
		System.out.println(">>>> registra " + obj.getIdAlumno());
		Optional<Alumno> optAlumno = service.obtienePorId(obj.getIdAlumno());
		
		if (optAlumno.isPresent()) {
			Alumno objSalida = service.insertaActualizaAlumno(obj);
			if (objSalida != null) {
				return ResponseEntity.ok(obj);
			} else {
				return ResponseEntity.badRequest().build();
			}
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Alumno> elimina(@PathVariable("id") int idAlumno){
		//log.info(">>>> elimina " + idAlumno);
		Optional<Alumno> optAlumno = service.obtienePorId(idAlumno);
		if (optAlumno.isPresent()) {
			service.eliminaAlumno(idAlumno);
			return ResponseEntity.ok(optAlumno.get());
		}else {
			//log.info(">>>> elimina no existe el id : " + idAlumno);
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/buscarPorDni/{dni}")
	public ResponseEntity<Alumno> buscar(@PathVariable("dni") String dni){
		System.out.println(">>>> registra " + dni);
		List<Alumno> listAlumno = service.listaPorDni(dni);
		
		if (!CollectionUtils.isEmpty(listAlumno)) {
			return ResponseEntity.ok(listAlumno.get(0));
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
