package controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;

import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import entity.Amigo;
import entity.Usuario;
import enumeradas.StatusAmigo;
import excecoes.FriendException;
import excecoes.UserException;
import servicos.ServicoSenha;

@SessionScoped
@ManagedBean
public class UsuarioBean {
	private Usuario usuarioLogado = new Usuario();
	private String option;

	private List<Usuario> usuarios = new ArrayList<>();
	private Usuario selected = new Usuario();
	private String txtBuscaUsuario;

	private int i = 0;


	public UsuarioBean() {
		try {
			usuarioLogado = new UsuarioDAOImpl().validarUsuario("vinijosenog@hotmail.com", "12345");
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getSelected() {
		return selected;
	}

	public void setSelected(Usuario selected) {
		this.selected = selected;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public String getTxtBuscaUsuario() {
		return txtBuscaUsuario;
	}

	public void setTxtBuscaUsuario(String txtBuscaUsuario) {
		this.txtBuscaUsuario = txtBuscaUsuario;
	}

	public String getOption() {
		if (selected != null) {
			if (!selected.isStatusUsuario())
				return "BLOQUEADO";
			else
				return "ATIVO";

		}
		return null;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public void buttonBuscaAmigo() {
		UsuarioDAO udao = new UsuarioDAOImpl();
		usuarios.clear();
		i = 0;
		usuarios = udao.buscarUsuarios(txtBuscaUsuario);
		txtBuscaUsuario = null;
		ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ex.redirect("./buscaUsuario.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void enviarSolicitacao() {
		Amigo amigo = new Amigo();
		amigo.setUsuario(selected);
		amigo.setStatus(StatusAmigo.SOLICITADO);
		usuarioLogado.getAmigos().add(amigo);
		UsuarioDAO udao = new UsuarioDAOImpl();
		try {
			udao.adicionarAmigo(usuarioLogado, amigo);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Solicitação Enviada com sucesso");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (FriendException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void logar() {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Validando dados no servidor...");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		UsuarioDAO udao = new UsuarioDAOImpl();
		try {
			usuarioLogado = udao.validarUsuario(usuarioLogado.getEmail(), usuarioLogado.getSenha());
			if (usuarioLogado.isStatusUsuario())
				FacesContext.getCurrentInstance().getExternalContext().redirect("./index.xhtml");
			else {
				FacesMessage msgError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO",
						"Este Login está bloqueado entre em contato com wishList@gmail.com para mais informações");
				FacesContext.getCurrentInstance().addMessage(null, msgError);
				usuarioLogado = new Usuario();
			}
		} catch (UserException | IOException e) {
			usuarioLogado.setEmail(null);
			FacesMessage msgError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgError);
			e.printStackTrace();
		}
	}

	public void logout() {
		usuarioLogado = new Usuario();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("./loginUsuario.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DefaultStreamedContent getImageSelected() throws IOException {
		if (i == usuarios.size())
			i = 0;
		Usuario u = usuarios.get(i);
		i++;
		DefaultStreamedContent imageSelected = new DefaultStreamedContent(new ByteArrayInputStream(u.getFoto()),
				"image/jpg");

		return imageSelected;
	}

	public DefaultStreamedContent getImageAmigoSelected() throws IOException {
		if (i == usuarioLogado.getAmigos().size())
			i = 0;
		Usuario u = usuarioLogado.getAmigos().get(i).getUsuario();
		i++;
		DefaultStreamedContent imageSelected = new DefaultStreamedContent(new ByteArrayInputStream(u.getFoto()),
				"image/jpg");

		return imageSelected;
	}

	public void recuperar() {
		UsuarioDAO udao = new UsuarioDAOImpl();
		try {
			usuarioLogado = udao.buscarUsuarioEspecifico(usuarioLogado.getEmail());
			ServicoSenha senha = new ServicoSenha();
			usuarioLogado.setSenha(senha.gerarSenha());
			udao.alterar(usuarioLogado);
			usuarioLogado = new Usuario();
			FacesContext.getCurrentInstance().getExternalContext().redirect("./loginUsuario.xhtml");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info",
					"Uma nova senha foi enviada para o seu email");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (UserException | IOException e1) {
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e1.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgErro);
			e1.printStackTrace();
		}

	}

	public boolean getPermissao() {
		if (usuarioLogado.getPermissao() == 'c')
			return false;
		else
			return true;
	}

	public void atualizarDados() {
		UsuarioDAO udao = new UsuarioDAOImpl();
		udao.alterar(usuarioLogado);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Dados Atualizados com sucesso");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void cadastrar() {
		UsuarioDAO udao = new UsuarioDAOImpl();
		try {
			udao.adicionar(usuarioLogado);
			usuarioLogado = new Usuario();
			FacesContext.getCurrentInstance().getExternalContext().redirect("./loginUsuario.xhtml");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Cadastro realizado com sucesso");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (UserException e1) {
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e1.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgErro);
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void buscarUsuarioEmail() {
		UsuarioDAO udao = new UsuarioDAOImpl();
		try {
			selected = udao.buscarUsuarioEspecifico(txtBuscaUsuario);
		} catch (UserException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void atualizarUsuario() {
		UsuarioDAO udao = new UsuarioDAOImpl();
		System.out.println(selected.getEmail());
		if (option.equals("BLOQUEADO"))
			selected.setStatusUsuario(false);
		else
			selected.setStatusUsuario(true);
		udao.alterar(selected);
		selected = new Usuario();
		txtBuscaUsuario = null;
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Dados Atualizados com sucesso");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void removerAmigo() {
		Amigo am = new Amigo();
		am.setUsuario(selected);
		usuarioLogado.remover(am);
		UsuarioDAO udao = new UsuarioDAOImpl();
		udao.alterar(usuarioLogado);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Removido com sucesso");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void atualizarAmizade() {
		UsuarioDAO udao = new UsuarioDAOImpl();
		StatusAmigo novo = null;
		for (Amigo a : usuarioLogado.getAmigos()) {
			if (a.getUsuario().getEmail().equals(selected.getEmail())) {
				novo = a.getStatus();
			}
		}
		udao.atualizarAmizade(usuarioLogado.getEmail(), selected.getEmail(), novo);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Dados Atualizados com sucesso");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	
}
