package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import entity.Entretenimento;
import entity.Filme;
import entity.Game;
import entity.ItemFavoritos;
import entity.Serie;
import entity.Usuario;

@SessionScoped
@ManagedBean
public class FavoritosBean {

	private List<ItemFavoritos> favoritos = new LinkedList<>();
	private Entretenimento selected;
	

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
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
