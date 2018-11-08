package entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import enumeradas.StatusAmigo;

@Entity
public class Amigo implements Serializable {
	
	
	@EmbeddedId
	private AmigoPK amigoPk;
	

	private StatusAmigo status;

	

	public AmigoPK getAmigoPk() {
		return amigoPk;
	}

	public void setAmigoPk(AmigoPK amigoPk) {
		this.amigoPk = amigoPk;
	}
	public StatusAmigo getStatus() {
		return status;
	}

	public void setStatus(StatusAmigo status) {
		this.status = status;
	}



}
