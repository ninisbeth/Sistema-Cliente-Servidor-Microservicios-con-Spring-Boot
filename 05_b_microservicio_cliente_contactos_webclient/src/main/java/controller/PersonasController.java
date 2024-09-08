package controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import model.Persona;

@RestController
public class PersonasController {
	@Autowired
	WebClient  webClient;
	
	String url = "http://localhost:8080";	
	@GetMapping(value="/personas/{nombre}/{email}/{edad}", 
			produces= MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> altaPersona(@PathVariable("nombre") String nombre,
			@PathVariable("email") String email,
			@PathVariable("edad") int edad){
		Persona persona = new Persona(nombre, email, edad);
		
		webClient
		.post() //RequestBodyUriSpec
		.uri(url+"/contactos") //RequestBodySpec
		.contentType(MediaType.APPLICATION_JSON) //RequestBodySpec
		.bodyValue(persona) //RequestHeadersSpec
		.retrieve() //ResponseSpec
		.bodyToMono(Void.class) //Mono<void>
		.block(); //Void
		
		Persona[] personas= webClient
		.get() //RequestHeadersUriSpec
		.uri(url+"/contactos") //RequestHeadersSpec
		.retrieve() //ResponseSpec
		.bodyToMono(Persona[].class) //Mono<Persona[]>
		.block(); //Persona[]
		
		return Arrays.asList(personas);
	}
}
