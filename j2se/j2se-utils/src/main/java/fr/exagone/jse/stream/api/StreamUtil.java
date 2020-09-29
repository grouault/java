package fr.exagone.jse.stream.api;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamUtil {

	public static String[] ARR_ABC = new String[]{"a", "b", "c"};
	
	public static List<String> animals = new ArrayList<String>();
	static {
		animals.add("chat");
		animals.add("chien");
		animals.add("cheval");
		animals.add("lapin");
		animals.add("vache");
		animals.add("cheval");
		animals.add("mouton");
	}
	
	public static List<Detail> details = new ArrayList<>();
	static {
		Detail detail1 = new Detail();
		detail1.getParts().add("part-1");
		detail1.getParts().add("part-2");
		details.add(detail1);
		Detail detail2 = new Detail();
		detail2.getParts().add("part-3");
		detail2.getParts().add("part-4");
		details.add(detail2);
	}	
	
	public static void main(String[] args) {
		// StreamUtil.create();
		// StreamUtil.multiThreading();
		// StreamUtil.streamOperations();
		// StreamUtil.streamIterating();
		StreamUtil.streamFiltering();
		// StreamUtil.streamMapping();
		// StreamUtil.streamMatching();
		// StreamUtil.streamReducing();
		// StreamUtil.streamCollecting();
	}
	
	public static void create() {
		System.out.println("### CREATE");
		//
		// from scratch
		Stream<String> stream2 = Stream.of("a", "b", "c");
		System.out.println("stream2 = " + stream2);
		//
		// tableau
		Stream<String> stream = Arrays.stream(ARR_ABC);
		System.out.println("Stream ARR_ABC = " + stream);
		//
		// collection
		Stream<String> streamAnimals = animals.stream();
		System.out.println("streamAnimals = " + streamAnimals);
		animals.forEach(a -> System.out.println(a));
	}
	
	public static void multiThreading() {
		System.out.println("### MUTLI-THREADING");
		animals.parallelStream().forEach(a -> System.out.println("thread " + a));
	}
	
	public static void streamOperations() {
		System.out.println("### STREAM-OPERATION");
		System.out.println("animals - distinct elements= " + animals.stream().distinct().count());
	}
	
	public static void streamIterating() {
		System.out.println("### STREAM-ITERATING");
		// check si a est contenu dans au moins un élément.
		// iteration implicit
		System.out.println("[Match] - contains 'a'= " 
				+ animals.stream().anyMatch(a -> a.contains("a")));
		// different de : check si un element contient a
		// iteration explicit
		animals.stream().forEach( a -> {
			if (a.contains("a")) {
				System.out.println(a + " contient 'a'");	
			} else {
				System.out.println(a + " ne contient pas 'a'");
			}
		});
	}
	
	
	public static void streamFiltering() {
		System.out.println("### STREAM FILTERING - 1");
		System.out.println("Animaux dont le nom contient 'v':");
		animals.stream().filter(a -> a.contains("v")).forEach(a->System.out.println(a));
		System.out.println("### STREAM FILTERING - 2");
		Stream<String> streamFilter = animals.stream().filter(element -> element.contains("m"));
		streamFilter.forEach(elt -> System.out.println(elt));
		
	}

	
	public static void streamMapping() {
		System.out.println("### STREAM MAPPING");
		System.out.println("conversion");
		List<String> uris = new ArrayList<>();
		uris.add("C:\\My.txt");
		Stream<Path> stream = uris.stream().map(uri -> Paths.get(uri));
		stream.forEach(p -> System.out.println("Path fileName = " + p.getFileName()));
		System.out.println("flat-map");
		details.stream().flatMap(d -> d.getParts().stream()).forEach(p -> System.out.println(p));
	}
	
	public static void streamMatching() {
		System.out.println("### STREAM MATCHING");
		System.out.println("stream contains h " + animals.stream().anyMatch(a -> a.contains("h")));
		System.out.println("all elements of stream contains h " + animals.stream().allMatch(a -> a.contains("h")));
		System.out.println("none elements of stream contains h " + animals.stream().noneMatch(a -> a.contains("h")));
	}
	
	public static void streamReducing(){
		System.out.println("### STREAM REDUCING");
		List<Integer> integers = Arrays.asList(1, 1, 1);
		Integer reduced = integers.stream().reduce(23, (a, b) -> a + b);
		System.out.println("reduced = " + reduced);
	}

	public static void streamCollecting(){
		System.out.println("### STREAM REDUCING");
		List<String> resultList 
		  = animals.stream().map(element -> element.toUpperCase()).collect(Collectors.toList());
		resultList.stream().forEach(e -> System.out.println(e));
	}
}


