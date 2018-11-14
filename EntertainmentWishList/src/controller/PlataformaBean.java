package controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.PlataformaDAO;
import dao.PlataformaDAOImpl;
import entity.Plataforma;

@SessionScoped
@ManagedBean
public class PlataformaBean {

	private Plataforma plataforma = new Plataforma();
	
	public Plataforma getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}
	
	public void cadastrar() {
		PlataformaDAO pdao = new PlataformaDAOImpl();
		try {
			pdao.adicionarLista(plataforma);
			plataforma = new Plataforma();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "info", 
					"Cadastro da plataforma realizado com sucesso");
			FacesContext.getCurrentInstance().addMessage(null, message);
			FacesContext.getCurrentInstance().getExternalContext().redirect("./index.xhtml");
		} catch (IOException e) {
			FacesMessage messageError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, messageError);
			e.printStackTrace();
		}
	}
}
