package controller;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

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
	private Usuario selected;
	private String txtBuscaUsuario;
	private DefaultStreamedContent imageSelected;
	private int i = 0;
	
	public UsuarioBean() {
		try {
			usuarioLogado = new UsuarioDAOImpl().validarUsuario("rodrigo.cdl1997@gmail.com", "123456");
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
		i=0;
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
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Solicitação Enviada com sucesso");
			FacesContext.getCurrentInstance().addMessage(null,msg);
		} catch (FriendException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO!",e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null,msg);
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

	public DefaultStreamedContent getImageUsuarioLogado() throws IOException {
		return new DefaultStreamedContent(new ByteArrayInputStream(usuarioLogado.getFoto()), "image/jpg");
	}

	public DefaultStreamedContent getImageSelected() throws IOException {
		if(i==usuarios.size())i=0;
		Usuario u = usuarios.get(i);
		i++;
		System.out.println(i);
		imageSelected = new DefaultStreamedContent(new ByteArrayInputStream(u.getFoto()), "image/jpg");
		
		return imageSelected;
	}

	

	public void setImageSelected(DefaultStreamedContent imageSelected) {
		this.imageSelected = imageSelected;
	}

	
	public String getArquivoImagem() throws IOException {
		String endImagem = "/tmp/" + usuarioLogado.getEmail()+".jpg";
		File arq = new File(endImagem);
		RenderedImage img = (RenderedImage) new ImageIcon(usuarioLogado.getFoto()).getImage();
		ImageIO.write(img, "jpg", new File("/tmp/mypng.png"));
		return endImagem;
		
	}

}
