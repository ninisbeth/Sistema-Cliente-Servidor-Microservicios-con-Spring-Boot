package controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloAnthosController {
	 @GetMapping(value="/", produces = MediaType.TEXT_PLAIN_VALUE)
	    public String hello() {
	        return "Hello, Anthos!";
	 }
}
