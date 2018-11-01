package controller;

import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
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
import entity.Filme;
import entity.Game;
import entity.Serie;


@SessionScoped
@ManagedBean
public class BuscaBean {

	private List<Entretenimento> entretenimentos = new LinkedList<>();
	private Entretenimento selected;
	private String txtPesquisa;
	private List<String> nomes;


	public BuscaBean() {
		nomes = new ArrayList<String>();
		EntretenimentoDAO edao = new EntretenimentoDAOImpl();
		nomes.addAll(edao.buscarNomes());
		FilmeDAO fdao = new FilmeDAOImpl();
		nomes.addAll(fdao.buscarNomesFilmes());
		SerieDAO sdao = new SerieDAOImpl();
		nomes.addAll(sdao.buscarNomesSeries());

	}

	public List<Entretenimento> getEntretenimentos() {
		return entretenimentos;
	}

	public void setEntretenimentos(List<Entretenimento> entretenimentos) {
		this.entretenimentos = entretenimentos;
	}

	public Entretenimento getSelected() {
		return selected;
	}

	public void setSelected(Entretenimento selected) {
		this.selected = selected;
	}

	public void mostrarNome() {
		System.out.println(selected.getNomeOriginal());
		System.out.println(selected.getPoster());
		ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();

		try {
			if (selected instanceof Serie)
				ex.redirect("./infoSerie.xhtml");
			else if (selected instanceof Game) {
				ex.redirect("./infoJogo.xhtml");
			} else if (selected instanceof Filme) {
				ex.redirect("./infoFilme.xhtml");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

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

	public String getTxtPesquisa() {
		return txtPesquisa;
	}

	public void setTxtPesquisa(String txtPesquisa) {
		this.txtPesquisa = txtPesquisa;
	}

	public List<String> completeText(String query) {
		List<String> results = new ArrayList<>();
		Collator coll = Collator.getInstance();
		for(String s : nomes) {
			if(s.toLowerCase().contains(query.toLowerCase())) {
				results.add(s);
			}
		}
		return results;
	}

}
