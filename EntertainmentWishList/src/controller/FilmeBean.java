package controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import dao.FilmeDAO;
import dao.FilmeDAOImpl;
import entity.Filme;

public class FilmeBean {

	private Filme filme = new Filme();
	
	public Filme getFilme() {
		return filme;
	}
	
	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	
	public void cadastrar() {
		FilmeDAO fdao = new FilmeDAOImpl();
		
		try {
			fdao.adicionar(filme);
			filme = new Filme();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "info", "Cadastro de filme realizado com sucesso");
			FacesContext.getCurrentInstance().addMessage(null, message);
			FacesContext.getCurrentInstance().getExternalContext().redirect("./index.xhtml");
		} catch (IOException e) {
			FacesMessage messageError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, messageError);
			e.printStackTrace();
		}
	}
}
