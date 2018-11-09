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
import excecoes.FriendException;
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

	public DefaultStreamedContent imageAmigoSelected(Usuario user) throws IOException {
		DefaultStreamedContent imageSelected = null;
		if (user.getFoto() != null)
			imageSelected = new DefaultStreamedContent(new ByteArrayInputStream(user.getFoto()), "image/jpg");

		return imageSelected;
	}

	public void removerAmigo() {
		Amigo am = new Amigo();
		AmigoPK pk = new AmigoPK();
		pk.setUsuarioEmail(usuarioLogado.getEmail());
		pk.setUsuarioEmail2(selected.getEmail());
		am.setAmigoPk(pk);
		AmigoDAO udao = new AmigoDAOImpl();
		udao.remover(am);
		atualizarUsuario();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Removido com sucesso");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		atualizarUsuario();
	}
	public void recusar(int tipo) {
		Amigo am = new Amigo();
		AmigoPK pk = new AmigoPK();
		if(tipo ==0) {
		pk.setUsuarioEmail2(usuarioLogado.getEmail());
		pk.setUsuarioEmail(selected.getEmail());
		}else {
			pk.setUsuarioEmail(usuarioLogado.getEmail());
			pk.setUsuarioEmail2(selected.getEmail());
		}
		am.setAmigoPk(pk);
		AmigoDAO udao = new AmigoDAOImpl();
		udao.deletarSolicitacao(am);
		atualizarUsuario();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Removido com sucesso");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		atualizarUsuario();
	}

	public void atualizarAmizade() {
		AmigoDAO udao = new AmigoDAOImpl();
		StatusAmigo novo = null;
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
	
	
	public void adicionarAmigo() {
		AmigoDAO adao = new AmigoDAOImpl();
		adao.atualizarAmizade(selected.getEmail(), usuarioLogado.getEmail(), StatusAmigo.ATIVO);
		Amigo amigo = new Amigo();
		AmigoPK pk = new AmigoPK();
		pk.setUsuarioEmail(usuarioLogado.getEmail());
		pk.setUsuarioEmail2(selected.getEmail());
		amigo.setAmigoPk(pk);
		amigo.setStatus(StatusAmigo.ATIVO);
		try {
			adao.adicionarAmigo(amigo);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Amigo adicionado com sucesso");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			atualizarUsuario();
		} catch (FriendException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public StatusAmigo getStatus() {
		return status;
	}

	public void setStatus(StatusAmigo status) {
		this.status = status;
	}

	public List<Usuario> getSolicitacoesEnviadas() {
		UsuarioDAO udao = new UsuarioDAOImpl();
		solicitacoesEnviadas = new ArrayList<Usuario>();
		List<Amigo> enviados = new ArrayList<>();
		AmigoDAO adao = new AmigoDAOImpl();
		enviados = adao.buscarSolicitacaoEnviada(usuarioLogado);
		for (Amigo a : enviados) {
			Usuario user = new Usuario();
			try {
				user = udao.buscarUsuarioEspecifico(a.getAmigoPk().getUsuarioEmail2());
				solicitacoesEnviadas.add(user);
			} catch (UserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	
		return solicitacoesEnviadas;
	}

	public void setSolicitacoesEnviadas(List<Usuario> solicitacoesEnviadas) {
		this.solicitacoesEnviadas = solicitacoesEnviadas;
	}

	public List<Usuario> getSolicitacoesRecebidas() {
		UsuarioDAO udao = new UsuarioDAOImpl();
		solicitacoesRecebidas = new ArrayList<Usuario>();
		List<Amigo> recebido = new ArrayList<>();
		AmigoDAO adao = new AmigoDAOImpl();
		recebido = adao.buscarSolicitacaoRecebida(usuarioLogado);
		for (Amigo a : recebido) {
			Usuario user = new Usuario();
			try {
				user = udao.buscarUsuarioEspecifico(a.getAmigoPk().getUsuarioEmail());
				solicitacoesRecebidas.add(user);
			} catch (UserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
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

}