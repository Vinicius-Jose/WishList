package dao;

import java.sql.Date;
import java.util.Calendar;

import entity.Entretenimento;
import entity.Estudio;
import entity.Serie;
import enumeradas.Etaria;
import servicos.ServicoEntretenimento;

public class DriverTeste {

	public static void main(String[] args) {
//		 Serie a = new Serie();
//		 a.setNomeOriginal("Flash");
//		 Calendar c = Calendar.getInstance();
//		 c.set(2014, 1, 15);
//		 a.setDataLancamento(new Date(c.getTimeInMillis()));
		
		// Game b = new Game();
		// b.setId(2);
		// b.setNomeOriginal("hadouken");
		// b.setDataLancamento(new Date(2555555));
		// b.setClassificacaoEtaria(Etaria.PG18);
		// b.setGenero("fight");
		// GameDAO gd = new GameDAOImpl();
		// gd.adicionar(a);
		// gd.adicionar(b);

//		 ServicoEntretenimento so = new ServicoEntretenimento();
//		 a = (Serie) so.servicoEntretenimento(a);
//		 a.setClassificacaoEtaria(Etaria.PG12);
//		 Estudio es = new Estudio();
//		 es.setLocalizacao("USA");
//		 es.setNome("Universal");
//		 EstudioDAO ed = new EstudioDAOImpl();
//		 ed.adicionar(es);
//		 a.setEstudio(es);
//		 SerieDAOImpl fd = new SerieDAOImpl();
//		 fd.adicionar(a);
		//
		
		EntretenimentoDAO edao = new EntretenimentoDAOImpl();
		for(Entretenimento e: edao.maisBuscados()) {
			System.out.println(e.getNomeOriginal());
			System.out.println(edao.maisBuscados().size());
		}

//		UsuarioDAO udao = new UsuarioDAOImpl();
//		Usuario u = new Usuario();
//		try {
//			u = udao.validarUsuario("pedro@hotmail.com", "12345");
//		} catch (UserException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		try {
//			URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/Flag_of_Brazil.svg/188px-Flag_of_Brazil.svg.png");
//			BufferedImage imagem = ImageIO.read(url);;
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			ImageIO.write(imagem, "jpg", baos);
//			u.setFoto(baos.toByteArray());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		udao.alterar(u);

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
	}

}
