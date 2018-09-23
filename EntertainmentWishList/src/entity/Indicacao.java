package entity;

import enumeradas.StatusIndicacao;

public class Indicacao {

	private StatusIndicacao statusIndicacao;
	private Usuario usuarioIndicador;
	private Usuario usuarioRecebido;
	private Entretenimento entretenimento;
	
	

	public StatusIndicacao getStatusIndicacao() {
		return statusIndicacao;
	}



	public void setStatusIndicacao(StatusIndicacao statusIndicacao) {
		this.statusIndicacao = statusIndicacao;
	}



	public Usuario getUsuarioIndicador() {
		return usuarioIndicador;
	}



	public void setUsuarioIndicador(Usuario usuarioIndicador) {
		this.usuarioIndicador = usuarioIndicador;
	}



	public Usuario getUsuarioRecebido() {
		return usuarioRecebido;
	}



	public void setUsuarioRecebido(Usuario usuarioRecebido) {
		this.usuarioRecebido = usuarioRecebido;
	}



	public Entretenimento getEntretenimento() {
		return entretenimento;
	}



	public void setEntretenimento(Entretenimento entretenimento) {
		this.entretenimento = entretenimento;
	}



	public void bloquearIndicacao() {
		this.statusIndicacao = StatusIndicacao.BLOQUEADO;
	}

}
