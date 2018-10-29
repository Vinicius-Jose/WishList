package servicos;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import entity.Entretenimento;
import entity.Filme;
import entity.Game;
import entity.Serie;

public class ServicoOMDB {

	public Entretenimento servicoEntretenimento(Entretenimento e) {
		Client cliente = new Client();
		String uri = criaURI(e);
		WebResource resource = cliente.resource(uri);
		GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
		builder.registerTypeAdapter(Integer.class, new MetacriticDeserializer());
		builder.registerTypeAdapter(Date.class, new DateDeserializer());
		
		Gson gson = builder.create();
		
		String json = resource.get(String.class);
		Entretenimento novo = null;
		
		if (e instanceof Game) {
			novo = gson.fromJson(json, Game.class);
		} else if (e instanceof Filme) {
			novo = gson.fromJson(json, Filme.class);
		} else if (e instanceof Serie) {
			novo = gson.fromJson(json, Serie.class);
		}
		novo.setMetacritic(gson.fromJson(json, Integer.class));
		novo.setDataLancamento(gson.fromJson(json, Date.class));
		if (novo.getNomeOriginal() != null) {
			return novo;
		}

		return e;
	}

	private String criaURI(Entretenimento e) {
		String uri = null;
		try {
			uri = "http://www.omdbapi.com/?t=" + URLEncoder.encode(e.getNomeOriginal(), "UTF-8");
			if (e instanceof Filme) {
				uri+="&type=movie&";
			} else if (e instanceof Serie) {
				uri+="&type=series&";
			}
			uri+="plot=full&apikey=7fd1758d";
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return uri;
	}
}

//outra api = https://moviegraph.io/#usage
