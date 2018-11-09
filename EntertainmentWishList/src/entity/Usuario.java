package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import enumeradas.StatusAmigo;

@Entity
public class Usuario implements Serializable{
	
	@Column(length = 200)
	private String primeiroNome;
	
	@Column(length = 200)
	private String segundoNome;
	
	@Column(length = 100)
	private String nickName;

	@Id
	private String email;
	
	private String senha;
	
	@Lob
	@Column(columnDefinition="longblob")
	private byte[] foto;
	
	@Column(length = 1)
	private char permissao = 'c';
	
	
	private boolean statusUsuario= true;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "Usuarioemail")
	private List<Amigo> amigos;
	

	public void adicionarAmigo(Amigo a) {
		amigos.add(a);
	}

	public Amigo remover(Amigo a) {
		Amigo removido = null;
		for(Amigo b : amigos) {
			if(b.getAmigoPk().getUsuarioEmail2().equals(a.getAmigoPk().getUsuarioEmail2())) {
				removido =b;
			}
		}
		amigos.remove(removido);
		return removido;
	}

	public void bloquear(String nome) {
		for (Amigo b : amigos) {
			if (b.getAmigoPk().getUsuarioEmail2().equals(nome)) {
				b.setStatus(StatusAmigo.BLOQUEADO);
			}
		}
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public String getSegundoNome() {
		return segundoNome;
	}

	public void setSegundoNome(String segundoNome) {
		this.segundoNome = segundoNome;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public char getPermissao() {
		return permissao;
	}

	public void setPermissao(char permissao) {
		this.permissao = permissao;
	}

	public boolean isStatusUsuario() {
		return statusUsuario;
	}

	public void setStatusUsuario(boolean statusUsuario) {
		this.statusUsuario = statusUsuario;
	}

	public List<Amigo> getAmigos() {
		return amigos;
	}

	public void setAmigos(List<Amigo> amigos) {
		this.amigos = amigos;
	}

}
