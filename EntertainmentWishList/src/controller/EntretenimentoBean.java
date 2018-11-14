package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import dao.EntretenimentoDAO;
import dao.EntretenimentoDAOImpl;
import dao.FilmeDAO;
import dao.FilmeDAOImpl;
import dao.GameDAO;
import dao.GameDAOImpl;
import dao.SerieDAO;
import dao.SerieDAOImpl;
import entity.Entretenimento;

@SessionScoped
@ManagedBean
public class EntretenimentoBean {

	private List<Entretenimento> entretenimentos = new LinkedList<>();
	private String txtPesquisa;
	private List<String> nomes;
	private List<Entretenimento> maisBuscados;

	public EntretenimentoBean() {
		EntretenimentoDAO edao = new EntretenimentoDAOImpl();
		setMaisBuscados(edao.maisBuscados());

	}
	
	
	private List<String> getNomes(){
		nomes = new ArrayList<String>();
		EntretenimentoDAO edao = new EntretenimentoDAOImpl();
		nomes.addAll(edao.buscarNomes());
		FilmeDAO fdao = new FilmeDAOImpl();
		nomes.addAll(fdao.buscarNomesFilmes());
		SerieDAO sdao = new SerieDAOImpl();
		nomes.addAll(sdao.buscarNomesSeries());
		return nomes;
	}

	public List<Entretenimento> getMaisBuscados() {
		return maisBuscados;
	}

	public void setMaisBuscados(List<Entretenimento> maisBuscados) {
		this.maisBuscados = maisBuscados;
	}

	public List<Entretenimento> getEntretenimentos() {
		return entretenimentos;
	}

	public void setEntretenimentos(List<Entretenimento> entretenimentos) {
		this.entretenimentos = entretenimentos;
	}

	public String getTxtPesquisa() {
		return txtPesquisa;
	}

	public void setTxtPesquisa(String txtPesquisa) {
		this.txtPesquisa = txtPesquisa;
	}

	public void buttonBusca(ActionEvent actionEvent) {
		entretenimentos.clear();
		FilmeDAO fdao = new FilmeDAOImpl();
		SerieDAO sdao = new SerieDAOImpl();
		GameDAO gdao = new GameDAOImpl();
		entretenimentos.addAll(fdao.buscarFilmes(txtPesquisa));
		entretenimentos.addAll(sdao.buscarSeries(txtPesquisa));
		entretenimentos.addAll(gdao.buscarGames(txtPesquisa));
		try {
			txtPesquisa = null;
			FacesContext.getCurrentInstance().getExternalContext().redirect("./busca2.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<String> completeText(String query) {
		List<String> results = new ArrayList<>();

		for (String s : getNomes()) {
			if (s.toLowerCase().contains(query.toLowerCase())) {
				results.add(s);
			}
		}
		return results;
	}

}
