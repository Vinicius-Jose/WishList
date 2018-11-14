package controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.EstudioDAO;
import dao.EstudioDAOImpl;
import dao.FilmeDAO;
import dao.FilmeDAOImpl;
import entity.Estudio;
import entity.Filme;
import servicos.ServicoEntretenimento;

@SessionScoped
@ManagedBean
public class FilmeBean {

	private Filme filme = new Filme();
	private ServicoEntretenimento se = new ServicoEntretenimento();
	private Estudio estudio = new Estudio();

	public Estudio getEstudio() {
		return estudio;
	}
	public void setEstudio(Estudio estudio) {
		this.estudio = estudio;
	}
	public Filme getFilme() {
		return filme;
	}
	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public Date getData() {
		Calendar c = Calendar.getInstance();
		
		if(filme.getDataLancamento()!=null) {
			c.setTime(filme.getDataLancamento());
		return c.getTime();
		
		} else {
			return null;
		}
	}
	public void setData(Date data) {
		if (data != null)
			filme.setDataLancamento(new java.sql.Date(data.getTime()));
	}

	public void cadastrar() {
		FilmeDAO fdao = new FilmeDAOImpl();
		EstudioDAO edao = new EstudioDAOImpl();
		try {
			estudio = edao.buscarEspecifico(estudio.getNome());
			filme.setEstudio(estudio);
			fdao.adicionar(filme);
			filme = new Filme();
			estudio = new Estudio();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "info",
					"Cadastro de filme realizado com sucesso");
			FacesContext.getCurrentInstance().addMessage(null, message);
			FacesContext.getCurrentInstance().getExternalContext().redirect("./index.xhtml");
		} catch (IOException e) {
			FacesMessage messageError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, messageError);
			e.printStackTrace();
		}
	}

	public void buscaAPI() {
		System.out.println(filme.getNomeOriginal());
		System.out.println("buscando...");
		filme = (Filme) se.servicoEntretenimento(filme);
	}
}