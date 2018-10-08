package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "Entretenimentoid")
public class Filme extends Entretenimento implements Serializable{

	private String nomePortugues;
	
	private double imdb;
	
	private int rottenTomatoes;
	
	private int duracao;
	
	@Column(length = 100)
	private String diretor;

	public String getNomePortugues() {
		return nomePortugues;
	}

	public void setNomePortugues(String nomePortugues) {
		this.nomePortugues = nomePortugues;
	}

	public double getImdb() {
		return imdb;
	}

	public void setImdb(double imdb) {
		this.imdb = imdb;
	}

	public int getRottenTomatoes() {
		return rottenTomatoes;
	}

	public void setRottenTomatoes(int rottenTomatoes) {
		this.rottenTomatoes = rottenTomatoes;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}

}
