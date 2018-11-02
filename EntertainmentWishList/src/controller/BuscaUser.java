package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import entity.Amigo;
import entity.Usuario;
import enumeradas.StatusAmigo;
import excecoes.UserException;

@SessionScoped
@ManagedBean
public class BuscaUser {
	private Usuario usuarioLogado;
	private List<Usuario> usuarios = new ArrayList<>();
	private Usuario selected;
	private String txtBuscaUsuario;
	
	
	public BuscaUser() {
		UsuarioDAO udao = new UsuarioDAOImpl();
		try {
			usuarioLogado = udao.validarUsuario("joana@yahoo.com.br", "12345");
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
		txtBuscaUsuario=null;
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
	
	
	
	public void enviarSolicitação() {
		Amigo amigo = new Amigo();
		amigo.setUsuario(selected);
		amigo.setStatus(StatusAmigo.SOLICITADO);
		usuarioLogado.getAmigos().add(amigo);
		UsuarioDAO udao = new UsuarioDAOImpl();
		udao.alterar(usuarioLogado);
	}

}
