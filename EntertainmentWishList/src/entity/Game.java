package entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "Entretenimentoid")
public class Game extends Entretenimento implements Serializable{

	@Column(length = 100)
	private String genero;
	
	private int numeroJogadores;

	@ManyToMany
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
