package entity;

import enumeradas.StatusAmigo;

public class Amigo {
	private StatusAmigo status;
	private Usuario usuario;

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
