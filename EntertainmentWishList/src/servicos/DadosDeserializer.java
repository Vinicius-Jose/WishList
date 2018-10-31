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

public class DadosDeserializer implements JsonDeserializer<HashMap<String, String>> {

	@Override
	public HashMap<String, String> deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		JsonObject jsonObject = element.getAsJsonObject();
		JsonElement name = jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().get("backdrop_path");
		HashMap<String, String> dados = new HashMap<>();
		dados.put("backgroung", name.getAsString() );
		name = jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().get("overview");
		dados.put("sinopse", name.getAsString() );	
		
		return dados;
	}

}
