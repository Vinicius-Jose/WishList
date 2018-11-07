package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import dao.EntretenimentoDAO;
import dao.EntretenimentoDAOImpl;
import dao.ItemFavoritoDAO;
import dao.ItemFavoritoDAOImpl;
import entity.Entretenimento;
import entity.Filme;
import entity.Game;
import entity.ItemFavoritos;
import entity.Serie;
import entity.Usuario;
import excecoes.NotEvaluatedException;

@SessionScoped
@ManagedBean
public class FavoritosBean {

	private List<ItemFavoritos> favoritos = new LinkedList<>();
	private Entretenimento selected;
	private ItemFavoritos item = new ItemFavoritos();
	private List<ItemFavoritos> critica;

	@ManagedProperty(value = "#{usuarioBean.usuarioLogado}")
	private Usuario usuario;

	public FavoritosBean() {
	
	}

	public Entretenimento getSelected() {
		return selected;
	}

	public void setSelected(Entretenimento selected) {
		this.selected = selected;
	}

	public List<ItemFavoritos> getFavoritos() {
		return favoritos;
	}

	public void setFavoritos(List<ItemFavoritos> favoritos) {
		this.favoritos = favoritos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Filme getFilme() {
		return (Filme)selected;
	}
	public Serie getSerie() {
		return (Serie)selected;
	}
	public Game getGame() {
		return (Game)selected;
	}

	public ItemFavoritos getItem() {
		return item;
	}

	public void setItem(ItemFavoritos item) {
		this.item = item;
	}

	public void visualizar() {
		ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
		System.out.println(usuario.getEmail());
		try {
			if (selected instanceof Serie)
				ex.redirect("./infoSerie.xhtml");
			else if (selected instanceof Game) {
				ex.redirect("./infoJogo.xhtml");
			} else if (selected instanceof Filme) {
				ex.redirect("./infoFilme.xhtml");
			}
			EntretenimentoDAO edao = new EntretenimentoDAOImpl();
			try {
				selected = edao.buscarMediaUsuarios(selected);
			} catch (NotEvaluatedException e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void remover() {
		item.setUsuario(usuario);
		ItemFavoritoDAO idao = new ItemFavoritoDAOImpl();
		idao.remover(item);
	}
	
	public void postar(int i){
		
		item.setUsuario(usuario);
		item.setEntretenimento(selected);
		System.out.println(selected.getNomeOriginal());
		ItemFavoritoDAO idao = new ItemFavoritoDAOImpl();
		try{
			idao.adicionar(item);
			if(i == 0)
				item.setNota(0);
			idao.alterar(item);
		}catch(Exception e){
			idao.alterar(item);
		}
		EntretenimentoDAO edao = new EntretenimentoDAOImpl();
		try {
			selected =edao.buscarMediaUsuarios(selected);
		} catch (NotEvaluatedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public List<ItemFavoritos> getCritica() {
		ItemFavoritoDAO idao = new ItemFavoritoDAOImpl();
		critica = idao.buscarCriticas(selected);
		return critica;
	}

	public void setCritica(List<ItemFavoritos> critica) {
		this.critica = critica;
	}

}
