package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
@PrimaryKeyJoinColumn(name = "Entretenimentoid")
public class Serie extends Entretenimento implements Serializable {

	@Column(length = 200)
	private String nomePortugues;

	
	private int numeroEpisodios;

	private int temporadas;

	@Column(nullable = true)
	private int rottenTomatoes;

	private Date dataFinal = new Date(Calendar.getInstance().getTimeInMillis());

	public String getNomePortugues() {
		return nomePortugues;
	}

	public void setNomePortugues(String nomePortugues) {
		this.nomePortugues = nomePortugues;
	}

	public int getNumeroEpisodios() {
		return numeroEpisodios;
	}

	public void setNumeroEpisodios(int numeroEpisodios) {
		this.numeroEpisodios = numeroEpisodios;
	}

	public int getTemporadas() {
		return temporadas;
	}

	public void setTemporadas(int temporadas) {
		this.temporadas = temporadas;
	}

	public int getRottenTomatoes() {
		return rottenTomatoes;
	}

	public void setRottenTomatoes(int rottenTomatoes) {
		this.rottenTomatoes = rottenTomatoes;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

}
