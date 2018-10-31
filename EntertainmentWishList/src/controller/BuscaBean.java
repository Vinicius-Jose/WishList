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
		for (int i = 0; i < 30; i++) {
			// Entretenimento ent = new Serie();
			// ent.setNomeOriginal("Superman IV " + i);
			// ent.setDataLancamento(new
			// Date(Calendar.getInstance().getTimeInMillis()));
			// ent.setPoster(
			// "https://resizing.flixster.com/TuzSqod_m3cBJEHAMpkEKuaTB0Y=/206x305/v1.bTsxMTIwNzg4MztqOzE3OTA0OzEyMDA7MTUwMzsyMDA0");
			// ent.setClassificacaoEtaria(Etaria.PG10);
			// ent.setSinopse("EUA e União Soviética entram em uma corrida
			// armamentista que pode destruir a Terra, e Super-Homem decide
			// intervir. Enquanto isso, Lex Luthor, seu arqui-inimigo, escapa da
			// prisão e clona o Homem de Aço com material radioativo.");
			// entretenimentos.add(ent);
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
