package fr.exagone.jse.collections.list;

import java.util.ArrayList;
import java.util.List;

public class Cast {
	
	public static void main(String[] args) {
		
		List<Long> currentList = new ArrayList<Long>();
		currentList.add(18406L);
		currentList.add(18407L);
		
		Integer[] tabInt = {18406,18409};
		
		// Le test contains ==> doit porter sur des listes.
		for (Integer intVal : tabInt) {
			if (!currentList.isEmpty() && !currentList.contains(new Long(intVal))) {
				System.out.println(intVal);
			}
		}
		
	}
	
}
