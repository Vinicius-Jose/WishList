package entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;

@Embeddable
public class AmigoPK implements Serializable{
	
	@JoinColumn(table="Usuario", referencedColumnName="email")
	private String usuarioEmail;
	
	@JoinColumn(table="Usuario", referencedColumnName="email")
	private String usuarioEmail2;
	
	
	
	public String getUsuarioEmail2() {
		return usuarioEmail2;
	}

	public void setUsuarioEmail2(String usuarioEmail2) {
		this.usuarioEmail2 = usuarioEmail2;
	}

	public String getUsuarioEmail() {
		return usuarioEmail;
	}

	public void setUsuarioEmail(String usuarioEmail) {
		this.usuarioEmail = usuarioEmail;
	}

	@Override
	public boolean equals(Object obj) {
		
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
}
