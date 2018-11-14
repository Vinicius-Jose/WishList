package controller;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import dao.EntretenimentoDAO;
import dao.EntretenimentoDAOImpl;
import dao.IndicacaoDAO;
import dao.IndicacaoDAOImpl;
import dao.ItemFavoritoDAO;
import dao.ItemFavoritoDAOImpl;
import entity.Entretenimento;
import entity.Indicacao;
import entity.ItemFavoritos;
import entity.Usuario;
import enumeradas.StatusIndicacao;
import excecoes.NotEvaluatedException;

@SessionScoped
@ManagedBean
public class IndicacaoBean {

	private List<Indicacao> recebidas;
	private List<Indicacao> enviadas;
	private Indicacao indicacao = new Indicacao();
	private Entretenimento selected;

	@ManagedProperty(value = "#{usuarioBean.usuarioLogado}")
	private Usuario usuario;

	private Usuario enviado;

	public Indicacao getIndicacao() {
		return indicacao;
	}

	public void setIndicacao(Indicacao indicacao) {
		this.indicacao = indicacao;
	}

	public void remover() {
		IndicacaoDAO idao = new IndicacaoDAOImpl();
		idao.remover(indicacao);
		recebidas = getRecebidas();
	}

	public void cancelarEnviadas() {
		IndicacaoDAO idao = new IndicacaoDAOImpl();
		idao.remover(indicacao);
		enviadas = getEnviadas();
	}

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

	public void recomendar() {
		ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();

		try {
			ex.redirect("./amigosRecomendacao.xhtml");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void indicar() {
		IndicacaoDAO idao = new IndicacaoDAOImpl();
		indicacao.setEntretenimento(selected);
		indicacao.setStatusIndicacao(StatusIndicacao.ENVIADO);
		indicacao.setUsuarioIndicador(usuario);
		indicacao.setUsuarioRecebido(enviado);
		idao.adicionar(indicacao);

	}

	public Usuario getEnviado() {
		return enviado;
	}

	public void setEnviado(Usuario enviado) {
		this.enviado = enviado;
	}

	public void postar() {

	}

	public Entretenimento getSelected() {
		return selected;
	}

	public void setSelected(Entretenimento selected) {
		this.selected = selected;
	}

	public void adicionar() {
		ItemFavoritos item = new ItemFavoritos();
		item.setUsuario(usuario);
		item.setEntretenimento(indicacao.getEntretenimento());
		ItemFavoritoDAO idao = new ItemFavoritoDAOImpl();
		idao.adicionar(item);
		remover();
	}

}
