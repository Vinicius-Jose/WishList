package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Entretenimento;
import entity.Usuario;

@RequestScoped
@ManagedBean
public class BuscaUser {

	private List<Usuario> usuarios;
	private Usuario selected;

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@PostConstruct
	public void init() {
		usuarios = new ArrayList<>();
		for (int i = 0; i < 30; i++) {
			Usuario user = new Usuario();
			user.setPrimeiroNome("Usuario " + i);
			user.setNickName("ÉTOIZ2018");
			user.setEmail("aaaaaa@hotmail.com");
			usuarios.add(user);
		}
	}

	public Usuario getSelected() {
		return selected;
	}

	public void setSelected(Usuario selected) {
		this.selected = selected;
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


}
