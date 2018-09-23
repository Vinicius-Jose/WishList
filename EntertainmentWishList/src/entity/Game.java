package entity;

import java.util.Set;

public class Game extends Entretenimento {

	private String genero;
	private int numeroJogadores;
	private Set<Plataforma> plataformas;
	
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public int getNumeroJogadores() {
		return numeroJogadores;
	}
	public void setNumeroJogadores(int numeroJogadores) {
		this.numeroJogadores = numeroJogadores;
	}
	public Set<Plataforma> getPlataformas() {
		return plataformas;
	}
	public void setPlataformas(Set<Plataforma> plataformas) {
		this.plataformas = plataformas;
	}

	
	
}
