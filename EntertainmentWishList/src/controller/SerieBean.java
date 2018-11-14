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
import dao.SerieDAO;
import dao.SerieDAOImpl;
import entity.Estudio;
import entity.Serie;
import servicos.ServicoEntretenimento;

@SessionScoped
@ManagedBean
public class SerieBean {

	private Serie serie = new Serie();
	private ServicoEntretenimento se = new ServicoEntretenimento();
	private Estudio estudio = new Estudio();
	
	public Estudio getEstudio() {
		return estudio;
	}
	public void setEstudio(Estudio estudio) {
		this.estudio = estudio;
	}
	public Serie getSerie() {
		return serie;
	}
	public void setSerie(Serie serie) {
		this.serie = serie;
	}
	
	public Date getData() {
		Calendar c = Calendar.getInstance();
		
		if(serie.getDataLancamento()!=null) {
			c.setTime(serie.getDataLancamento());
		return c.getTime();
		
		} else {
			return null;
		}
	}
	public void setData(Date dataLancamento) {
		if (dataLancamento != null)
			serie.setDataLancamento(new java.sql.Date(dataLancamento.getTime()));
	}
	
	public Date getDateFinal() {
		Calendar c = Calendar.getInstance();
		
		if(serie.getDataFinal()!=null) {
			c.setTime(serie.getDataFinal());
		return c.getTime();
		
		} else {
			return null;
		}
	}
	public void setDateFinal(Date dataFinal) {
		if (dataFinal != null)
			serie.setDataFinal(new java.sql.Date(dataFinal.getTime()));
	}
	
	public void cadastrar() {
		SerieDAO sdao = new SerieDAOImpl();
		EstudioDAO edao = new EstudioDAOImpl();
		
		try {
			estudio = edao.buscarEspecifico(estudio.getNome());
			serie.setEstudio(estudio);
			sdao.adicionar(serie);
			serie = new Serie();
			estudio = new Estudio();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "info", "Cadastro de seriado realizado com sucesso");
			FacesContext.getCurrentInstance().addMessage(null, message);
			FacesContext.getCurrentInstance().getExternalContext().redirect("./index.xhtml");
		} catch (IOException e) {
			FacesMessage messageError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, messageError);
			e.printStackTrace();
		}
	}
	
	public void buscaAPI() {
		System.out.println(serie.getNomeOriginal());
		System.out.println("buscando...");
		serie = (Serie) se.servicoEntretenimento(serie);
	}
}
