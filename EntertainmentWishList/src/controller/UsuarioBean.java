package controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;

import dao.AmigoDAO;
import dao.AmigoDAOImpl;
import dao.CodigoDAO;
import dao.CodigoDAOImpl;
import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import entity.Amigo;
import entity.AmigoPK;
import entity.Codigo;
import entity.Usuario;
import enumeradas.StatusAmigo;
import excecoes.CodeNotFoundException;
import excecoes.FriendException;
import excecoes.UserException;
import servicos.ServicoEmail;
import servicos.ServicoCodigo;

@SessionScoped
@ManagedBean
public class UsuarioBean {
	private Usuario usuarioLogado = new Usuario();
	private String option;
	private Codigo codigo = new Codigo();

	private List<Usuario> usuarios = new ArrayList<>();
	private Usuario selected = new Usuario();
	private String txtBuscaUsuario;
	private String senha;
	private int i = 0;

	public UsuarioBean() {
		// try {
		// usuarioLogado = new
		// UsuarioDAOImpl().buscarUsuarioEspecifico("vinijosenog@hotmail.com");
		// } catch (UserException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	public Codigo getCodigo() {
		return codigo;
	}

	public void setCodigo(Codigo codigo) {
		this.codigo = codigo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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
		AmigoPK pk = new AmigoPK();
		pk.setUsuarioEmail(usuarioLogado.getEmail());
		pk.setUsuarioEmail2(selected.getEmail());
		amigo.setAmigoPk(pk);
		;
		amigo.setStatus(StatusAmigo.SOLICITADO);
		AmigoDAO udao = new AmigoDAOImpl();
		try {
			udao.adicionarAmigo(amigo);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Solicitação Enviada com sucesso");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (FriendException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void logar() {
		criptografar();
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

	public void recuperar() {
		UsuarioDAO udao = new UsuarioDAOImpl();
		System.out.println("recuperando" + " " + codigo.getCodigo() + " " + codigo.getUsuario());
		try {
			CodigoDAO cdao = new CodigoDAOImpl();
			if (cdao.buscarCodigo(codigo)) {
				usuarioLogado = udao.buscarUsuarioEspecifico(codigo.getUsuario());
				usuarioLogado.setSenha(senha);
				criptografar();
				udao.alterar(usuarioLogado);
				usuarioLogado = new Usuario();
			}
			;

			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info",
					"Sua nova Senha foi salva no sistema");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (UserException | CodeNotFoundException e1) {
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
		criptografar();
		udao.alterar(usuarioLogado);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Dados Atualizados com sucesso");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void cadastrar() {
		UsuarioDAO udao = new UsuarioDAOImpl();
		try {
			criptografar();
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

	private void criptografar() {
		MessageDigest algorithm;
		try {
			algorithm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algorithm.digest(usuarioLogado.getSenha().getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}
			usuarioLogado.setSenha(hexString.toString());
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
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

	public void enviarCodigo() {
		Codigo code = new Codigo();
		UsuarioDAO udao = new UsuarioDAOImpl();
		CodigoDAO cdao = new CodigoDAOImpl();
		ServicoCodigo scode = new ServicoCodigo();
		ServicoEmail semail = new ServicoEmail();
		try {
			usuarioLogado = udao.buscarUsuarioEspecifico(usuarioLogado.getEmail());
			code.setUsuario(usuarioLogado.getEmail());
			code.setCodigo(scode.gerarCodigo());
			cdao.adicionarCodigo(code);
			semail.servicoEmail(code);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!",
					"Um código foi gerado e enviado para o seu email");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			usuarioLogado = new Usuario();
			codigo = new Codigo();
		} catch (UserException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

}
