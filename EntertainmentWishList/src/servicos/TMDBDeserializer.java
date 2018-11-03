package servicos;

import java.lang.reflect.Type;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class TMDBDeserializer implements JsonDeserializer<HashMap<String, String>> {

	@Override
	public HashMap<String, String> deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2)
			throws JsonParseException {
		HashMap<String, String> dadosTMDB =new HashMap();;
		JsonObject jsonObject = element.getAsJsonObject();
		JsonArray jsonArray = jsonObject.getAsJsonArray("results");
		if (jsonArray.size() > 0) {
			JsonElement name = jsonArray.get(0).getAsJsonObject().get("backdrop_path");
			
			dadosTMDB.put("background", "https://image.tmdb.org/t/p/w300" +name.getAsString());
			name = jsonArray.get(0).getAsJsonObject().get("overview");
			dadosTMDB.put("sinopse", name.getAsString());
			name = jsonArray.get(0).getAsJsonObject().get("title");
			if(name==null) {
				name = jsonArray.get(0).getAsJsonObject().get("name");
			}
			dadosTMDB.put("nomePt", name.getAsString());
		}
		return dadosTMDB;
	}

}
