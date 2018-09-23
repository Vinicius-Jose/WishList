package controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestScoped
@ManagedBean
public class CabecalhoController {

	public String getTxt() {
		ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();

		return "jhfdshflkjsahdfklsaj";

	}

	public void setTxt(String txt) {
		ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession s = (HttpSession) ex.getSession(true);
		s.setAttribute("txt", txt);
	}

	public void click() {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		Enumeration<String> as = req.getAttributeNames();
		while (as.hasMoreElements()) {
			System.out.println(as.nextElement());

		}
		ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession s = (HttpSession) ex.getSession(true);
		System.out.println(s.getAttribute("txt"));

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
