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
import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import entity.Amigo;
import entity.AmigoPK;
import entity.Usuario;
import enumeradas.StatusAmigo;
import excecoes.UserException;

@SessionScoped
@ManagedBean
public class AmigoBean {

	@ManagedProperty(value = "#{usuarioBean.usuarioLogado}")
	private Usuario usuarioLogado;

	private List<Usuario> amigos = new ArrayList<>();

	private List<Usuario> solicitacoesRecebidas = new ArrayList<>();

	private List<Usuario> solicitacoesEnviadas = new ArrayList<>();

	private Usuario selected = new Usuario();

	private StatusAmigo status;

	private int i = -1;

	private int tipo = 0;

	public StatusAmigo getStatus() {
		return status;
	}

	public void setStatus(StatusAmigo status) {
		this.status = status;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public List<Usuario> getSolicitacoesEnviadas() {
		return solicitacoesEnviadas;
	}

	public void setSolicitacoesEnviadas(List<Usuario> solicitacoesEnviadas) {
		this.solicitacoesEnviadas = solicitacoesEnviadas;
	}

	public List<Usuario> getSolicitacoesRecebidas() {
		return solicitacoesRecebidas;
	}

	public void setSolicitacoesRecebidas(List<Usuario> solicitacoesRecebidas) {
		this.solicitacoesRecebidas = solicitacoesRecebidas;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public List<Usuario> getAmigos() {
		UsuarioDAO udao = new UsuarioDAOImpl();
		i = -1;
		amigos = new ArrayList<Usuario>();
		for (Amigo a : usuarioLogado.getAmigos()) {
			if (a.getStatus() != StatusAmigo.SOLICITADO) {
				Usuario user = new Usuario();
				try {
					user = udao.buscarUsuarioEspecifico(a.getAmigoPk().getUsuarioEmail2());
					amigos.add(user);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
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

	public DefaultStreamedContent getImageAmigoSelected() throws IOException {
		List<Usuario> lista = amigos;
		i++;
		if (i >= lista.size())
			i = 0;
		
		if(lista.isEmpty()) {
			return null;
		}
		Usuario u = lista.get(i);

		DefaultStreamedContent imageSelected = new DefaultStreamedContent(new ByteArrayInputStream(u.getFoto()),
				"image/jpg");

		return imageSelected;
	}
	
	public DefaultStreamedContent imageAmigoSelected(Usuario user) throws IOException {
		
		System.out.println("foi na imagem" + user.getEmail());
		DefaultStreamedContent imageSelected = new DefaultStreamedContent(new ByteArrayInputStream(user.getFoto()),
				"image/jpg");

		return imageSelected;
	}

	

	public void removerAmigo() {
		i=-1;
		Amigo am = new Amigo();
		AmigoPK pk = new AmigoPK();
		pk.setUsuarioEmail(usuarioLogado.getEmail());
		pk.setUsuarioEmail2(selected.getEmail());
		System.out.println(selected.getEmail());
		am.setAmigoPk(pk);
		AmigoDAO udao = new AmigoDAOImpl();
		udao.remover(usuarioLogado.remover(am));
		usuarioLogado.remover(am);
		atualizarUsuario();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Removido com sucesso");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		atualizarUsuario();
	}

	public void atualizarAmizade() {
		AmigoDAO udao = new AmigoDAOImpl();
		StatusAmigo novo = null;
		i=-1;
		novo = status;

		udao.atualizarAmizade(usuarioLogado.getEmail(), selected.getEmail(), novo);
		atualizarUsuario();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Dados Atualizados com sucesso");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		atualizarUsuario();
	}

	private void atualizarUsuario() {
		try {
			usuarioLogado = new UsuarioDAOImpl().buscarUsuarioEspecifico(usuarioLogado.getEmail());
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public StatusAmigo buscarStatus(Usuario user) {
		for (Amigo a : usuarioLogado.getAmigos()) {
			if (a.getAmigoPk().getUsuarioEmail2().equals(user.getEmail())) {
				return a.getStatus();
			}
		}
		return null;

	}

}