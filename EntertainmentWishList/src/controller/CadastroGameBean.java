package controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import dao.GameDAO;
import dao.GameDAOImpl;
import entity.Game;

public class CadastroGameBean {

	private Game game = new Game();
	
	public void cadastrar() {
		GameDAO gdao = new GameDAOImpl();
		
		try {
			gdao.adicionar(game);
			game = new Game();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "info", "Cadastro do jogo realizado com sucesso");
			FacesContext.getCurrentInstance().addMessage(null, message);
			FacesContext.getCurrentInstance().getExternalContext().redirect("./index.xhtml");
		} catch (IOException e) {
			FacesMessage messageError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, messageError);
			e.printStackTrace();
		}
	}
}
