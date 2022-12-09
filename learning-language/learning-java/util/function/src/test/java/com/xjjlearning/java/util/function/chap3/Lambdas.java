package com.xjjlearning.java.util.function.chap3;

import com.xjjlearning.java.util.function.Apple;
import com.xjjlearning.java.util.function.Fruit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Lambdas {
	public static void main(String ...args){

		// Simple example
		Runnable r = () -> System.out.println("Hello!");
		r.run();

		// Filtering with lambdas
		List<Apple> inventory = Arrays.asList(new Apple(80,"green"), new Apple(155, "green"), new Apple(120, "red"));

		// [Apple{color='green', weight=80}, Apple{color='green', weight=155}]	
		List<Apple> greenApples = filter(inventory, (Apple a) -> "green".equals(a.getColor()));
		System.out.println(greenApples);


		Comparator<Apple> c = Comparator.comparingInt(Fruit::getWeight);

		// [Apple{color='green', weight=80}, Apple{color='red', weight=120}, Apple{color='green', weight=155}]
		inventory.sort(c);
		System.out.println(inventory);
	}

	public static List<Apple> filter(List<Apple> inventory, ApplePredicate p){
		List<Apple> result = new ArrayList<>();
		for(Apple apple : inventory){
			if(p.test(apple)){
				result.add(apple);
			}
		}
		return result;
	}

	public static <T> List<T> filter(List<T> inventory, Predicate<T> p) {
		return inventory.stream()
				.filter(p)
				.collect(Collectors.toList());
	}

	interface ApplePredicate{
		public boolean test(Apple a);
	}
}