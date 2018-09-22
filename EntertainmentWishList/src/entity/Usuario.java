package entity;

import java.util.Set;

import enumeradas.StatusAmigo;

public class Usuario {

	private String primeiroNome;
	private String segundoNome;
	private String nickName;
	private String email;
	private String senha;
	private Byte[] foto;
	private char permissao;
	private boolean statusUsuario;
	private Set<Amigo> amigos;
	private Usuario usuario;

	public void adicionarAmigo(Amigo a) {
		amigos.add(a);
	}

	public void remover(Amigo a) {
		amigos.remove(a);
	}

	public void bloquear(String nome) {
		for (Amigo b : amigos) {
			if (b.getUsuario().getPrimeiroNome().equals(nome)) {
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

	public Byte[] getFoto() {
		return foto;
	}

	public void setFoto(Byte[] foto) {
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

	public Set<Amigo> getAmigos() {
		return amigos;
	}

	public void setAmigos(Set<Amigo> amigos) {
		this.amigos = amigos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
