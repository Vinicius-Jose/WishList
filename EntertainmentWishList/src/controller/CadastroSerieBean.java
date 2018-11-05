package controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import dao.SerieDAO;
import dao.SerieDAOImpl;
import entity.Serie;

public class CadastroSerieBean {

	private Serie serie = new Serie();
	
	public void cadastrar() {
		SerieDAO sdao = new SerieDAOImpl();
		
		try {
			sdao.adicionar(serie);
			serie = new Serie();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "info", "Cadastro de seriado realizado com sucesso");
			FacesContext.getCurrentInstance().addMessage(null, message);
			FacesContext.getCurrentInstance().getExternalContext().redirect("./index.xhtml");
		} catch (IOException e) {
			FacesMessage messageError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, messageError);
			e.printStackTrace();
		}
	}
}
