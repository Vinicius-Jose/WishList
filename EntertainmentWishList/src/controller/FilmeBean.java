package controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.FilmeDAO;
import dao.FilmeDAOImpl;
import entity.Filme;
import servicos.ServicoEntretenimento;

@SessionScoped
@ManagedBean
public class FilmeBean {

	private Filme filme = new Filme();
	private ServicoEntretenimento se = new ServicoEntretenimento();
	
	private int metaCritic;
	private int rottenTomatoes;
	private double imdb;
	
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
	
	public int getMetaCritic() {
		return metaCritic;
	}
	public void setMetaCritic(int metaCritic) {
        this.metaCritic = metaCritic;
	}
	
	public int getRottenTomatoes() {
		return rottenTomatoes;
	}
	public void setRottenTomatoes(int rottenTomatoes) {
        this.rottenTomatoes = rottenTomatoes;
	}
	
	public double getImdb() {
		return imdb;
	}
	public void setImdb(double imdb) {
        this.imdb = imdb;
	}
	
	public void buscaAPI() {
		
		filme = (Filme) se.servicoEntretenimento(filme);
		
		filme.getPoster();
		filme.getImagemFundo();
		filme.getNomePortugues();
		filme.getClassificacaoEtaria();
		filme.getDataLancamento();
		filme.getEstudio();
		filme.getDiretor();
		filme.getSinopse();
		
		System.out.println(filme.getNomeOriginal());
	}
}
