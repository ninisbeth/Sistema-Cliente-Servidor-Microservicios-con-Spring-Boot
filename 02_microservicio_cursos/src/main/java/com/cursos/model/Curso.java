package com.cursos.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement
public class Curso {
	private String nombre;
	private int duracion;
	private String horario;
	
	public Curso(String nombre, int duración, String horario) {
		super();
		this.nombre = nombre;
		this.duracion = duración;
		this.horario = horario;
	}
	
	public Curso() {
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}
	
	
}
