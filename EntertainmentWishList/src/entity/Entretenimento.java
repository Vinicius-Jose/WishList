package entity;

import java.util.Date;

import enumeradas.Etaria;

public class Entretenimento {

	private String nomeOriginal;
	private int metacritic;
	private double notaUsuario;
	private Date dataLancamento;
	private String sinopse;
	private Etaria classificacaoEtaria;
	private String poster;
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

}
