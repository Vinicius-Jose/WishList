package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;

@Entity
public class ItemFavoritos implements Serializable{

	@ColumnDefault(value="null")
	@Column(nullable=true)
	private int nota;
	
	private String critica;

	@Id
	@ManyToOne
	private Entretenimento entretenimento;

	@Id
	@ManyToOne
	private Usuario usuario;

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public String getCritica() {
		return critica;
	}

	public void setCritica(String critica) {
		this.critica = critica;
	}

	public Entretenimento getEntretenimento() {
		return entretenimento;
	}

	public void setEntretenimento(Entretenimento entretenimento) {
		this.entretenimento = entretenimento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
