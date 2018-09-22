package controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entity.Entretenimento;

@RequestScoped
@ManagedBean
public class BuscaBean {

	private List<Entretenimento> entretenimentos;

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
			ent.setNomeOriginal("Superman IV");
			ent.setDataLancamento(new Date(Calendar.getInstance().getTimeInMillis()));
			ent.setPoster(
					"https://resizing.flixster.com/TuzSqod_m3cBJEHAMpkEKuaTB0Y=/206x305/v1.bTsxMTIwNzg4MztqOzE3OTA0OzEyMDA7MTUwMzsyMDA0");
			entretenimentos.add(ent);
			
		}
	}

}
