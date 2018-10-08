package entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import enumeradas.StatusAmigo;

@Entity
public class Amigo implements Serializable {
	
	
	@Id
	@JoinColumn(name = "Usuarioemail2")
	@ManyToOne
	private Usuario usuario;
	
	private StatusAmigo status;

	public StatusAmigo getStatus() {
		return status;
	}

	public void setStatus(StatusAmigo status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
