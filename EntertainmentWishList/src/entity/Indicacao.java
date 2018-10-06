package entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import enumeradas.StatusIndicacao;

@Entity
public class Indicacao implements Serializable {

	private StatusIndicacao statusIndicacao;

	@Id
	@ManyToOne
	@JoinColumn(name = "Usuarioemail")
	private Usuario usuarioIndicador;

	@Id
	@ManyToOne
	@JoinColumn(name = "Usuarioemail2")
	private Usuario usuarioRecebido;

	@Id
	@ManyToOne
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
