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
import javax.faces.event.ActionEvent;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Entretenimento;

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
			Entretenimento ent = new Entretenimento();
			ent.setNomeOriginal("Superman IV " + i);
			ent.setDataLancamento(new Date(Calendar.getInstance().getTimeInMillis()));
			ent.setPoster(
					"https://resizing.flixster.com/TuzSqod_m3cBJEHAMpkEKuaTB0Y=/206x305/v1.bTsxMTIwNzg4MztqOzE3OTA0OzEyMDA7MTUwMzsyMDA0");
			entretenimentos.add(ent);

		}
	}

	public Entretenimento getSelected() {
		return selected;
	}

	public void setSelected(Entretenimento selected) {
		this.selected = selected;
	}

	public void mostrarNome() {
		if (selected != null) {
			System.out.println(selected.getNomeOriginal());
			System.out.println(selected.getPoster());
		} else {
			System.out.println("oi");
		}

		ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ex.redirect("./index.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
