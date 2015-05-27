package fr.exagone.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadConcurrent {

	private static List<String> words;
	
	static{
		words = new ArrayList<String>();
		words.add("chien");
		words.add("chat");
		words.add("hippopotamme");
		words.add("hip hip hip");
	}
	
	public static void main(String[] args) {
		
		int sum = 0;
		try {
			
			ExecutorService pool = Executors.newFixedThreadPool(3);
		    List<Future<Integer>> set = new ArrayList<Future<Integer>>();
		    
		    for (String word: words) {
		      Callable<Integer> callable = new WordLengthCallable(word);
		      Future<Integer> future = pool.submit(callable);
		      set.add(future);
		    }
		    
		    for (Future<Integer> future : set) {  
		    	System.out.println("future.get = " + future.get());
				sum += future.get();
		    }
		    
	    } catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	   
	    System.out.printf("The sum of lengths is %s%n", sum);
	    System.exit(sum);
	}
		
	
	
}
