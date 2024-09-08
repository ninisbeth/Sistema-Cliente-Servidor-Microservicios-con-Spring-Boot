package service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import model.Pais;

@Service
public class PaisesServiceImpl implements PaisesService {
	String url= "https://restcountries.com/v2/all";
	@Autowired
	RestTemplate template;
	@Override
	public List<Pais> obtenerPaises() {
		String resultado= template.getForObject(url, String.class);
		ObjectMapper maper= new ObjectMapper();
		List<Pais> paises=new ArrayList<>();
		ArrayNode array;
		try {
			//obtenemos Array
			array= (ArrayNode)maper.readTree(resultado);
			for(Object ob:array) {
				ObjectNode json=(ObjectNode)ob;
				// Validación de nulidad para el campo "name"
		        String name = json.has("name") ? json.get("name").asText() : "Nombre no disponible";

		        // Validación de nulidad para el campo "capital"
		        String capital = json.has("capital") ? json.get("capital").asText() : "Capital no disponible";

		        // Validación de nulidad para el campo "population"
		        int population = json.has("population") ? json.get("population").asInt() : 0;

		        // Validación de nulidad para el campo "flag"
		        String flag = json.has("flag") ? json.get("flag").asText() : "Bandera no disponible";

		        paises.add(new Pais(name, capital, population, flag));
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return paises;
	}
	
	@Override
	public List<Pais> buscarPaises(String name) {
		return obtenerPaises()
				.stream()
				.filter(p->p.getNombre().contains(name))
				.collect(Collectors.toList());

	}
}
