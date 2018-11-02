package controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestScoped
@ManagedBean
public class CabecalhoController {
	
	@PostConstruct
	public void init() {
		setTxt(null);
	}

	public String getTxt() {
		ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession ss =  (HttpSession) ex.getSession(true);
		return (String) ss.getAttribute("txt")  ;

	}

	public void setTxt(String txt) {
		ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession s = (HttpSession) ex.getSession(true);
		s.setAttribute("txt", txt);
	}
	
	


	public URL getImage() throws IOException {

		try {
			return new URL(
					"https://upload.wikimedia.org/wikipedia/pt/thumb/3/37/Capa_de_Kamikaze_%28Eminem%29.jpg/220px-Capa_de_Kamikaze_%28Eminem%29.jpg");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	

	
	
	
	

}
