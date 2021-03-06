package dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import entity.Amigo;
import entity.AmigoPK;
import entity.Codigo;
import entity.Entretenimento;
import entity.Game;
import entity.ItemFavoritos;
import entity.Plataforma;
import entity.Usuario;
import enumeradas.StatusAmigo;
import excecoes.FriendException;
import excecoes.UserException;
import servicos.ServicoCodigo;

public class DriverTeste {

	public static void main(String[] args)
			throws UserException, FriendException, NoSuchAlgorithmException, UnsupportedEncodingException {
		// Serie a = new Serie();
		// a.setNomeOriginal("Flash");
		// Calendar c = Calendar.getInstance();
		// c.set(2014, 1, 15);
		// a.setDataLancamento(new Date(c.getTimeInMillis()));

		// Game b = new Game();
		// b.setId(2);
		// b.setNomeOriginal("hadouken");
		// b.setDataLancamento(new Date(2555555));
		// b.setClassificacaoEtaria(Etaria.PG18);
		// b.setGenero("fight");
		// GameDAO gd = new GameDAOImpl();
		// gd.adicionar(a);
		// gd.adicionar(b);

		// ServicoEntretenimento so = new ServicoEntretenimento();
		// a = (Serie) so.servicoEntretenimento(a);
		// a.setClassificacaoEtaria(Etaria.PG12);
		// Estudio es = new Estudio();
		// es.setLocalizacao("USA");
		// es.setNome("Universal");
		// EstudioDAO ed = new EstudioDAOImpl();
		// ed.adicionar(es);
		// a.setEstudio(es);
		// SerieDAOImpl fd = new SerieDAOImpl();
		// fd.adicionar(a);
		//
		// ItemFavoritoDAO idao = new ItemFavoritoDAOImpl();
		// Entretenimento e1 = new Entretenimento();
		// e1.setId(14);
		// System.out.println(idao.buscarCriticas(e1).get(0).getCritica());
		//
		// EntretenimentoDAO edao = new EntretenimentoDAOImpl();
		// for(Entretenimento e: edao.maisBuscados()) {
		// System.out.println(e.getNomeOriginal());
		// System.out.println(edao.maisBuscados().size());
		// }
		//
		// UsuarioDAO udao = new UsuarioDAOImpl();
		//// Usuario u = new Usuario();
		//// try {
		//// u = udao.validarUsuario("pedro@hotmail.com", "12345");
		// } catch (UserException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// try {
		// URL url = new
		// URL("https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/Flag_of_Brazil.svg/188px-Flag_of_Brazil.svg.png");
		// BufferedImage imagem = ImageIO.read(url);;
		// ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// ImageIO.write(imagem, "jpg", baos);
		// u.setFoto(baos.toByteArray());
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// udao.alterar(u);

		// List<Game> games = gd.buscarGames("ken");
		// for (Game s : games) {
		// System.out.println(s.getNomeOriginal());
		// }
		// IndicacaoDAO id = new IndicacaoDAOImpl();
		// Usuario user = new Usuario();
		// user.setEmail("pedro");
		// for(Indicacao ind : id.buscarIndicacaoEnviada(user)) {
		// System.out.println(ind.getStatusIndicacao());
		// }
		//
		//
		// ItemFavoritoDAO it = new ItemFavoritoDAOImpl();
		// List<ItemFavoritos> s = it.buscarFavoritos(new Usuario());
		// for(ItemFavoritos i : s) {
		// System.out.println(i.getNota());
		// }
		//
		// Usuario u = udao.buscarUsuarioEspecifico("antonio@outlook.com");
		// AmigoDAOImpl adao = new AmigoDAOImpl();
		// Amigo am = new Amigo();
		// AmigoPK apk = new AmigoPK();
		// apk.setUsuarioEmail2("pedro@outlook.com");
		// am.setAmigoPk(apk);
		// adao.remover(u.getAmigos().get(0));;
		// System.out.println(adao.buscarSolicitacaoRecebida(u).get(0).getAmigoPk().getUsuarioEmail());

		// Amigo am = new Amigo();
		// Usuario user = new Usuario();
		// user.setEmail("pedro@hotmail.com");
		// am.setUsuario(user);
		// u.remover(am);
		// udao.alterar(u);
		// udao.atualizarAmizade("vinijosenog@hotmail.com", "pedro@hotmail.com",
		// StatusAmigo.SOLICITADO);
		//
		// PlataformaDAO pdao = new PlataformaDAOImpl();
		// Plataforma pla = new Plataforma();
		// pla.setEmpresa("MICROSOFT");
		// pla.setNome("XBOX 360");
		// pdao.adicionar(pla);
		//
		// Game g = new Game();
		// g.setId(8);
		// g.setPlataformas(pdao.buscarPlataformas(g));
		// for(Plataforma p : g.getPlataformas()){
		// System.out.println(p.getNome());
		// }

		// MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		// byte messageDigest[] = algorithm.digest("senha".getBytes("UTF-8"));
		//
		// StringBuilder hexString = new StringBuilder();
		// for (byte b : messageDigest) {
		// hexString.append(String.format("%02X", 0xFF & b));
		// }
		// String senha = hexString.toString();
		// System.out.println(senha);
		//
		// System.out.println(algorithm.isEqual(senha.getBytes(),
		// "B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C".getBytes()
		// ));
		//
		//
		//
		Usuario u = new UsuarioDAOImpl().buscarUsuarioEspecifico("pezao@yahoo.com");
		Codigo c = new Codigo();
		c.setUsuario(u.getEmail());
		c.setCodigo(new ServicoCodigo().gerarCodigo());
		new CodigoDAOImpl().adicionarCodigo(c);
		new UsuarioDAOImpl().alterar(u);
	}

}
