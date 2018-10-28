package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
@PrimaryKeyJoinColumn(name = "Entretenimentoid")
public class Filme extends Entretenimento implements Serializable{

	private String nomePortugues;
	
	@SerializedName("imdbRating")
	@Expose
	private double imdb; 
	
	@SerializedName ("Rotten Tomatoes" )
	@Expose
	private int rottenTomatoes;
	
	
	private int duracao;
	
	
	@SerializedName("Director")
	@Expose
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
