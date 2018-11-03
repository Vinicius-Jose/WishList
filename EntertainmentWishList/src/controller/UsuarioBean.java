package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import entity.Amigo;
import entity.Usuario;
import enumeradas.StatusAmigo;
import excecoes.FriendException;
import excecoes.UserException;

@SessionScoped
@ManagedBean
public class UsuarioBean {
	private Usuario usuarioLogado = new Usuario();
	private List<Usuario> usuarios = new ArrayList<>();
	private Usuario selected;
	private String txtBuscaUsuario;



	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getSelected() {
		return selected;
	}

	public void setSelected(Usuario selected) {
		this.selected = selected;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public String getTxtBuscaUsuario() {
		return txtBuscaUsuario;
	}

	public void setTxtBuscaUsuario(String txtBuscaUsuario) {
		this.txtBuscaUsuario = txtBuscaUsuario;
	}

	public void buttonBuscaAmigo() {
		UsuarioDAO udao = new UsuarioDAOImpl();
		usuarios.clear();
		usuarios = udao.buscarUsuarios(txtBuscaUsuario);
		txtBuscaUsuario = null;
		ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ex.redirect("./buscaUsuario.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void mostrarNome() {
		if (selected != null) {
			System.out.println(selected.getPrimeiroNome());
			System.out.println(selected.getEmail());
		} else {
			System.out.println("oi");
		}

		ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ex.redirect("./index.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void enviarSolicitacao() {
		Amigo amigo = new Amigo();
		amigo.setUsuario(selected);
		amigo.setStatus(StatusAmigo.SOLICITADO);
		usuarioLogado.getAmigos().add(amigo);
		UsuarioDAO udao = new UsuarioDAOImpl();
		try {
			udao.adicionarAmigo(usuarioLogado, amigo);
		} catch (FriendException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void logar() {
		UsuarioDAO udao = new UsuarioDAOImpl();
		try {
			usuarioLogado = udao.validarUsuario(usuarioLogado.getEmail(), usuarioLogado.getSenha());

			FacesContext.getCurrentInstance().getExternalContext().redirect("./index.xhtml");
		} catch (UserException | IOException e) {
			usuarioLogado.setEmail(null);
			FacesMessage msgError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgError);
			e.printStackTrace();
		}
	}

	public void logout() {
		usuarioLogado = new Usuario();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("./loginUsuario.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
