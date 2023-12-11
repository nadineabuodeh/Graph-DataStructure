package com.datastructure;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;



public class App {

	public static AdjacencyMapGraph<String, Void> graph = new AdjacencyMapGraph<>(true);
	public static List<String> plants = new ArrayList<>();

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Enter the name of File:");
		Scanner sc = new Scanner(System.in);
		String nameOfFile = sc.next();
		System.out.println();
        String path = "C:\\Users\\nadin\\OneDrive\\Desktop\\git\\DataS\\graph\\Graph-DataStructure\\graph\\sources\\" + nameOfFile;
		creatGraph(path);
		PredatorsEat();
		System.out.println();
		ApexPredators();
		Producers();
		FlexibleEaters();
		Tastiest();
	}

	public static void creatGraph(String nameFile) throws FileNotFoundException {
		File file = new File(nameFile);
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			String[] array = sc.nextLine().split(",");
			Vertex<String> v = isInsert(array[0]);
			if (v == null)
				v = graph.insertVertex(array[0]);
			for (int i = 1; i < array.length; i++) {
				Vertex<String> u = isInsert(array[i]);
				if (u == null)
					u = graph.insertVertex(array[i]);
				graph.insertEdge(v, u, null);
			}
		}
		sc.close();
	}

	public static Vertex<String> isInsert(String name) {
		for (Vertex<String> vertex : graph.vertices()) {
			if (vertex.getElement().equals(name)) {
				return vertex;
			}
		}
		return null;
	}

	public static void PredatorsEat() {
		System.out.println("Predators and Prey:");
		for (Vertex<String> vertex : graph.vertices()) {
			List<String> list = new ArrayList<>();
			if (vertex == null)
				throw new IllegalArgumentException();
			for (Edge<Void> edge : graph.outgoingEdges(vertex)) {
				Vertex<String> v = graph.opposite(vertex, edge);
				list.add(v.getElement());
			}
			if (list.size() != 0) {
				System.out.print(vertex.getElement() + " eats ");
				for (int i = 0; i < list.size() - 1; i++) {
					System.out.print(list.get(i) + ", ");
				}
				if (list.get(list.size() - 1) != null && list.size() != 1) {
					System.out.print("and ");
				}
				System.out.println(list.get(list.size() - 1));
			} else
				System.out.print("");
		}
	}

	public static void ApexPredators() {
		System.out.print("Apex Predators: ");
		List<String> list = new ArrayList<>();
		for (Vertex<String> vertex : graph.vertices()) {
			List<Edge<Void>> list1 = new ArrayList<>();
			for (Edge<Void> edge : graph.incomingEdges(vertex)) {
				list1.add(edge);
			}
			if (list1.size() == 0)
				list.add(vertex.getElement());
		}
		if (list.size() != 0) {
			for (int i = 0; i < list.size() - 1; i++) {
				System.out.print(list.get(i) + ", ");
			}
			if (list.get(list.size() - 1) != null && list.size() != 1) {
				System.out.print("and ");
			}
			System.out.println(list.get(list.size() - 1));
		} else
			System.out.print("");
	}

	public static void Producers() {
		System.out.print("Producers: ");
		List<String> list = new ArrayList<>();
		for (Vertex<String> vertex : graph.vertices()) {
			List<Edge<Void>> list1 = new ArrayList<>();
			for (Edge<Void> edge : graph.outgoingEdges(vertex)) {
				list1.add(edge);
			}
			if (list1.size() == 0)
				list.add(vertex.getElement());
		}
		if (list.size() != 0) {
			for (int i = 0; i < list.size() - 1; i++) {
				System.out.print(list.get(i) + ", ");
			}
			if (list.get(list.size() - 1) != null && list.size() != 1) {
				System.out.print("and ");
			}
			System.out.println(list.get(list.size() - 1));
		} else
			System.out.print("");
		plants.addAll(list);
	}

	public static void FlexibleEaters() {
		System.out.print("Most Flexible Eaters: ");
		Map<String, Integer> map = new HashMap<>();
		for (Vertex<String> vertex : graph.vertices()) {
			int numOutGoing = 0;
			for (Edge<Void> edge : graph.outgoingEdges(vertex)) {
				numOutGoing++;
			}
			map.put(vertex.getElement(), numOutGoing);
		}
		int max = Integer.MIN_VALUE;
		for (int x : map.values()) {
			if (x > max) {
				max = x;
			}
		}
		List<String> list = new ArrayList<>();
		for (String string : map.keySet()) {
			if (map.get(string) == max)
				list.add(string);
		}
		if (list.size() != 0) {
			for (int i = 0; i < list.size() - 1; i++) {
				System.out.print(list.get(i) + ", ");
			}
			if (list.get(list.size() - 1) != null && list.size() != 1) {
				System.out.print("and ");
			}
			System.out.println(list.get(list.size() - 1));
		} else
			System.out.print("");
	}

	public static void Tastiest() {
		System.out.print("Tastiest: ");
		Map<String, Integer> map = new HashMap<>();
		for (Vertex<String> vertex : graph.vertices()) {
			int numInGoing = 0;
			for (Edge<Void> edge : graph.incomingEdges(vertex)) {
				numInGoing++;
			}
			map.put(vertex.getElement(), numInGoing);
		}
		int max = Integer.MIN_VALUE;
		for (int x : map.values()) {
			if (x > max) {
				max = x;
			}
		}
		List<String> list = new ArrayList<>();
		for (String string : map.keySet()) {
			if (map.get(string) == max)
				list.add(string);
		}
		if (list.size() != 0) {
			for (int i = 0; i < list.size() - 1; i++) {
				System.out.print(list.get(i) + ", ");
			}
			if (list.get(list.size() - 1) != null && list.size() != 1) {
				System.out.print("and ");
			}
			System.out.println(list.get(list.size() - 1));
		} else
			System.out.print("");
	}
}

