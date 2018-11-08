package controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;

import dao.AmigoDAO;
import dao.AmigoDAOImpl;
import entity.Amigo;
import entity.AmigoPK;
import entity.Usuario;

@SessionScoped
@ManagedBean
public class AmigoBean {

	@ManagedProperty(value = "#{usuarioBean.usuarioLogado}")
	private Usuario usuarioLogado;

	private List<Usuario> amigos = new ArrayList<>();

	private Usuario selected = new Usuario();
	private int i = 0;

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public List<Usuario> getAmigos() {
		return amigos;
	}

	public void setAmigos(List<Usuario> amigos) {
		this.amigos = amigos;
	}

	public Usuario getSelected() {
		return selected;
	}

	public void setSelected(Usuario selected) {
		this.selected = selected;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public AmigoBean() {

	}

	public DefaultStreamedContent getImageAmigoSelected() throws IOException {
		if (i == usuarioLogado.getAmigos().size())
			i = 0;
		Usuario u = amigos.get(i);
		i++;
		DefaultStreamedContent imageSelected = new DefaultStreamedContent(new ByteArrayInputStream(u.getFoto()),
				"image/jpg");

		return imageSelected;
	}
	
	
	public void removerAmigo() {
		Amigo am = new Amigo();
		AmigoPK pk = new AmigoPK();
		pk.setUsuarioEmail(usuarioLogado.getEmail());
		pk.setUsuarioEmail2(selected.getEmail());
		AmigoDAO udao = new AmigoDAOImpl();
		udao.remover(usuarioLogado.remover(am));
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Removido com sucesso");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}