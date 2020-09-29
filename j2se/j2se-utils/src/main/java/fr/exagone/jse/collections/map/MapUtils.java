package fr.exagone.jse.collections.map;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {

	
	private static Map<String, String> mapCategories = new HashMap<String, String>();
	static {
		mapCategories.put("thematiques", "astronomie#agronomie#ecologie");
		mapCategories.put("sports", "tennis#football#basket");
	}
	
	public static void main(String[] args) {
		Map<String,String> cloneMap = cloneMap();
		cloneMap.put("medecine", "general#sport#chirurgie");
		iterMap(mapCategories);
		System.out.println("--------------------");
		iterMap(cloneMap);
		System.out.println("--------------------");
		iterMap(mapCategories);

	}
	
	private static void iterMap( final Map<String, String> mapParams) {
		 for (Map.Entry<String, String> entry : mapParams.entrySet()) {
		     System.out.println(entry.getKey() + " ==> " + entry.getValue());
		 }
	}
	
	private static Map<String, String> cloneMap() {
		return  (HashMap<String, String>)((HashMap<String, String>)mapCategories).clone();
	}
	
}
