package controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
//Teste, n�o necess�rio na execu��o
@SessionScoped
@ManagedBean
public class CaptchaBean {

	public void validar() {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", "Correct");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}