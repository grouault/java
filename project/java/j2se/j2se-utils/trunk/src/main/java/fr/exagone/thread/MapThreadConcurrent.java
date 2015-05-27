package fr.exagone.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MapThreadConcurrent {

	private static List<String> words;
	
	static{
		words = new ArrayList<String>();
		words.add("chien");
		words.add("chat");
		words.add("hippopotamme");
	}
	
	public static void main(String[] args) {
		
		int sum = 0;

		ExecutorService pool = Executors.newFixedThreadPool(3);
	    Set<Future<Integer>> set = new HashSet<Future<Integer>>();
	    Map<String, Future<Integer>> map = new HashMap<String, Future<Integer>>(); 
	    
	    for (String word: words) {
	      Callable<Integer> callable = new WordLengthCallable(word);
	      Future<Integer> future = pool.submit(callable);
	      map.put(word, future);
	    }
	  
		for (Map.Entry<String, Future<Integer>> entry : map.entrySet()){
		    System.out.println(entry.getKey() + "/" + entry.getValue());
		}
	   
	    System.out.printf("The sum of lengths is %s%n", sum);
	    System.exit(sum);
	}
		
	
}
