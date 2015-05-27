package fr.exagone.thread;

import java.util.concurrent.Callable;

public class WordLengthCallable implements Callable<Integer> {

	private String word;
	
	public WordLengthCallable(final String word) {
		this.word = word;
	}
	
	public Integer call() throws Exception {
		return Integer.valueOf( this.word.length() );
	}

}
