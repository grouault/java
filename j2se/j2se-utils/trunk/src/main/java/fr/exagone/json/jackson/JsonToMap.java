package fr.exagone.json.jackson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class JsonToMap {

	private static final String FLUX_JSON = "{\"exception\":\"Ressource inexistante\"}";
	private static final String KEY_MAP = "exception";
	
	public static void main(String[] args) {
		JsonToMap jsonToMap = new JsonToMap();
		Map<String, String> cMap = jsonToMap.toMap();
		System.out.println(cMap.get(KEY_MAP));
	}
	
	public Map<String, String> toMap () {
		HashMap<String, String> hMap = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			hMap = mapper.readValue(FLUX_JSON, new TypeReference<Map<String, String>>(){});
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hMap;
	}
	
}
