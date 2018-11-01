package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import entity.Entretenimento;
import entity.Filme;
import entity.Game;
import entity.Serie;
import enumeradas.Etaria;
import servicos.ServicoEntretenimento;

@RequestScoped
@ManagedBean
public class BuscaBean {

	private List<Entretenimento> entretenimentos;
	private Entretenimento selected;

	public List<Entretenimento> getEntretenimentos() {
		return entretenimentos;
	}

	public void setEntretenimentos(List<Entretenimento> entretenimentos) {
		this.entretenimentos = entretenimentos;
	}

	@PostConstruct
	public void init() {
		entretenimentos = new ArrayList<>();
		Entretenimento s = new Serie();
		s.setNomeOriginal("Revenge");
		s = new ServicoEntretenimento().servicoEntretenimento(s);
		s.setClassificacaoEtaria(Etaria.PG16);
		entretenimentos.add(s);
		
		Entretenimento r = new Filme();
		r.setNomeOriginal("Revenge");
		r = new ServicoEntretenimento().servicoEntretenimento(r);
		r.setClassificacaoEtaria(Etaria.PG16);
		entretenimentos.add(r);
		for (int i = 0; i < 30; i++) {
			entretenimentos.add(r);
			entretenimentos.add(s);
			System.out.println(r.getImagemFundo());
		}

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

}
