package controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;

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
	private Usuario selected=new Usuario();
	private String txtBuscaUsuario;
	private int i = 0;


	public UsuarioBean() {
//		try {
//			usuarioLogado = new UsuarioDAOImpl().validarUsuario("rodrigo.cdl1997@gmail.com", "123456");
//		} catch (UserException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public void buttonBuscaAmigo() {
		UsuarioDAO udao = new UsuarioDAOImpl();
		usuarios.clear();
		i = 0;
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

	public void enviarSolicitacao() {
		Amigo amigo = new Amigo();
		amigo.setUsuario(selected);
		amigo.setStatus(StatusAmigo.SOLICITADO);
		usuarioLogado.getAmigos().add(amigo);
		UsuarioDAO udao = new UsuarioDAOImpl();
		try {
			udao.adicionarAmigo(usuarioLogado, amigo);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Solicitação Enviada com sucesso");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (FriendException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
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

	

	public DefaultStreamedContent getImageSelected() throws IOException {
		if (i == usuarios.size())
			i = 0;
		Usuario u = usuarios.get(i);
		i++;
		DefaultStreamedContent imageSelected = new DefaultStreamedContent(new ByteArrayInputStream(u.getFoto()),
				"image/jpg");

		return imageSelected;
	}

	public void recuperar() {

	}

	public boolean getPermissao() {
		if (usuarioLogado.getPermissao() == 'c')
			return false;
		else
			return true;
	}
	
	public void atualizarDados() {
		UsuarioDAO udao = new UsuarioDAOImpl();
		udao.alterar(usuarioLogado);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Dados Atualizados com sucesso");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void cadastrar() {
		UsuarioDAO udao = new UsuarioDAOImpl();
		try {
			udao.adicionar(usuarioLogado);
			usuarioLogado = new Usuario();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Cadastro realizado com sucesso");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			FacesContext.getCurrentInstance().getExternalContext().redirect("./loginUsuario.xhtml");
		} catch (UserException e1) {
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e1.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgErro);
			e1.printStackTrace();
		} catch (IOException e) {
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
	
	
}
