package controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import model.Contacto;
import service.AgendaService;

@CrossOrigin(origins = "*")
@RestController
public class ContactosController {
	@Autowired
	AgendaService service;
	@GetMapping(value="contactos", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Contacto> recuperarContactos() {
		return service.recuperarContactos();
	}
	
	@GetMapping(value="contactos/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Contacto recuperarContactos(@PathVariable("id") int id) {
		return service.buscarContacto(id);
	}
	
	@PostMapping(value="contactos", consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.TEXT_PLAIN_VALUE)
	public void guardarContacto(@RequestBody Contacto contacto) throws Exception {
		service.agregarContacto(contacto);
	}
	
	@PutMapping(value="contactos", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void actualizarContacto(@RequestBody Contacto contacto) {
		service.actualizarContacto(contacto);
	}
	
	@DeleteMapping(value="contactos/{id}")
	public void eliminarPorId(@PathVariable("id") int id) {
	    System.out.println("ID recibido para eliminar: " + id);  // Añadido para depuración
		service.eliminarContacto(id);
	}
}
