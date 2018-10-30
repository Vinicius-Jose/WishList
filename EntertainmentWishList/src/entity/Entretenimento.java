package entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import enumeradas.Etaria;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Entretenimento implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@SerializedName(value="Title", alternate="name" )
	@Expose
	private String nomeOriginal;
	
	@SerializedName("aggregated_rating")
	private int metacritic;

	@Transient
	private double notaUsuario;

	private Date dataLancamento;

	private String sinopse;

	private Etaria classificacaoEtaria;

	@SerializedName(value="Poster", alternate="artwork")
	@Expose
	private String poster;

	@ManyToOne
	@JoinColumn(name = "Estudioid")
	private Estudio estudio;

	public String getNomeOriginal() {
		return nomeOriginal;
	}

	public void setNomeOriginal(String nomeOriginal) {
		this.nomeOriginal = nomeOriginal;
	}

	public int getMetacritic() {
		return metacritic;
	}

	public void setMetacritic(int metacritic) {
		this.metacritic = metacritic;
	}

	public double getNotaUsuario() {
		return notaUsuario;
	}

	public void setNotaUsuario(double notaUsuario) {
		this.notaUsuario = notaUsuario;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public Etaria getClassificacaoEtaria() {
		return classificacaoEtaria;
	}

	public void setClassificacaoEtaria(Etaria classificacaoEtaria) {
		this.classificacaoEtaria = classificacaoEtaria;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public Estudio getEstudio() {
		return estudio;
	}

	public void setEstudio(Estudio estudio) {
		this.estudio = estudio;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
