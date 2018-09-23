package entity;

public class ItemFavoritos {

	private int nota;
	private String critica;
	private Entretenimento entretenimento;
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
