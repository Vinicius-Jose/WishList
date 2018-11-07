package controller;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import entity.Usuario;

public class InterceptadorLogin implements PhaseListener{

	@Override
	public void afterPhase(PhaseEvent e) {
		 FacesContext ctx = FacesContext.getCurrentInstance();
		 UIViewRoot ui = ctx.getViewRoot();
		 String pagina = ui.getViewId();
		 Application app = ctx.getApplication();
		 if(!pagina.equals("/loginUsuario.xhtml") && !pagina.equals("/recuperarSenha.xhtml") && !pagina.equals("/cadastroUsuario.xhtml")){
			 UsuarioBean user = app.evaluateExpressionGet(ctx, "#{usuarioBean}", UsuarioBean.class);
			 if(user.getUsuarioLogado().getEmail() == null){
				 NavigationHandler nav = app.getNavigationHandler();
				 nav.handleNavigation(ctx, "", "loginUsuario?faces-redirect=true");
			 }
		 }
		
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
