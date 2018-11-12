package controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.EstudioDAO;
import dao.EstudioDAOImpl;
import entity.Estudio;

@SessionScoped
@ManagedBean
public class EstudioBean {

	private Estudio estudio = new Estudio();
	
	public Estudio getEstudio() {
		return estudio;
	}
	public void setEstudio(Estudio estudio) {
		this.estudio = estudio;
	}
	
	public void cadastrar() {
		EstudioDAO edao = new EstudioDAOImpl();
		try {
			edao.adicionar(estudio);
			estudio = new Estudio();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "info", 
					"Cadastro do estudio realizado com sucesso");
			FacesContext.getCurrentInstance().addMessage(null, message);
			FacesContext.getCurrentInstance().getExternalContext().redirect("./index.xhtml");
		} catch (IOException e) {
			FacesMessage messageError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, messageError);
			e.printStackTrace();
		}
	}
}
