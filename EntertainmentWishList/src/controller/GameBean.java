package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.EstudioDAO;
import dao.EstudioDAOImpl;
import dao.GameDAO;
import dao.GameDAOImpl;
import dao.PlataformaDAO;
import dao.PlataformaDAOImpl;
import entity.Estudio;
import entity.Game;
import entity.Plataforma;
import servicos.ServicoEntretenimento;

@SessionScoped
@ManagedBean
public class GameBean {

	private Game game = new Game();
	private ServicoEntretenimento se = new ServicoEntretenimento();
	private List<Plataforma> plataformasBanco;
	private List<String> plataformaSelected;
	private Estudio estudio = new Estudio();
	
	
	
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	
	public Date getData() {
		Calendar c = Calendar.getInstance();
		
		if(game.getDataLancamento()!=null) {
			c.setTime(game.getDataLancamento());
		return c.getTime();
		
		} else {
			return null;
		}
	}
	public void setData(Date data) {
		if (data != null)
			game.setDataLancamento(new java.sql.Date(data.getTime()));
	}
	
	public List<Plataforma> getPlataformasBanco() {
		PlataformaDAO pdao = new PlataformaDAOImpl();
		setPlataformasBanco(pdao.buscarPlataformas());
		return plataformasBanco;
	}

	public void setPlataformasBanco(List<Plataforma> plataformasBanco) {
		this.plataformasBanco = plataformasBanco;
	}



	public List<String> getPlataformaSelected() {
		return plataformaSelected;
	}

	public void setPlataformaSelected(List<String> plataformaSelected) {
		this.plataformaSelected = plataformaSelected;
	}

	public void cadastrar() {
		GameDAO gdao = new GameDAOImpl();
		PlataformaDAO pdao = new PlataformaDAOImpl();
		List<Plataforma> pl = new ArrayList<Plataforma>();
		for(String p : plataformaSelected) {
			for(Plataforma pla : plataformasBanco) {
				if(p.equals(pla.getNome())) {
					pl.add(pla);
				}
			}
			
		}
		try {
			EstudioDAO edao = new EstudioDAOImpl();
			estudio = edao.buscarEspecifico(estudio.getNome());
			game.setEstudio(estudio);
			game.setPlataformas(pl);
			gdao.adicionar(game);
			game = new Game();
			estudio = new Estudio();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "info", "Cadastro do jogo realizado com sucesso");
			FacesContext.getCurrentInstance().addMessage(null, message);
			FacesContext.getCurrentInstance().getExternalContext().redirect("./index.xhtml");
		} catch (IOException e) {
			FacesMessage messageError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, messageError);
			e.printStackTrace();
		}
	}
	
	public void buscaAPI() {
		System.out.println(game.getNomeOriginal());
		System.out.println("buscando...");
		game = (Game) se.servicoEntretenimento(game);
	}

	public Estudio getEstudio() {
		return estudio;
	}

	public void setEstudio(Estudio estudio) {
		this.estudio = estudio;
	}
}