package controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import dao.IndicacaoDAO;
import dao.IndicacaoDAOImpl;
import entity.Indicacao;
import entity.Usuario;

@SessionScoped
@ManagedBean
public class IndicacaoBean {
	
	
	private List<Indicacao> recebidas;
	private List<Indicacao> enviadas;
	
	@ManagedProperty(value = "#{usuarioBean.usuarioLogado}")
	private Usuario usuario;

	




	public List<Indicacao> getRecebidas() {
		IndicacaoDAO idao = new IndicacaoDAOImpl();
		recebidas = idao.buscarIndicacaoRecebida(usuario);
		return recebidas;
	}

	public void setRecebidas(List<Indicacao> recebidas) {
		this.recebidas = recebidas;
	}



	public List<Indicacao> getEnviadas() {
		IndicacaoDAO idao = new IndicacaoDAOImpl();
		enviadas = idao.buscarIndicacaoEnviada(usuario);
		return enviadas;
	}

	public void setEnviadas(List<Indicacao> enviadas) {
		this.enviadas = enviadas;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

}
