package com.xjjlearning.java.util.stream;

import com.xjjlearning.java.util.stream.collector.PrimeNumbersCollector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.partitioningBy;

public class IsPrime {
    // solution 1
    // it's easier for me..., but very poor performance
    public static List<Integer> getPrimeList(int n) {
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            final int num = i;
            boolean b = primes.stream()
                    .noneMatch(prime -> num % prime == 0);
            if (b) {
                primes.add(i);
            }
        }
        return primes;
    }

    // solution 2
    // that's ok
    // partition numbers to prime(true) and non prime(false)
    public static Map<Boolean, List<Integer>> partitionPrime(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(partitioningBy(IsPrime::isPrime));
    }

    // solution 3
    // hard ..
    public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(new PrimeNumbersCollector<>());
    }

    // solution 4
    // That's the same thing as up here
    public static Map<Boolean, List<Integer>> partitionPrimesWithInlineCollector(int n) {
        return Stream.iterate(2, i -> i + 1).limit(n)
                .collect(
                        // supplier
                        () -> new HashMap<Boolean, List<Integer>>() {{
                            put(true, new ArrayList<>());
                            put(false, new ArrayList<>());
                        }},
                        // accumulator
                        (acc, candidate) -> {
                            acc.get(isPrime(acc.get(true), candidate)) // select the List of prime or non-prime
                                    .add(candidate);
                        },
                        // combiner
                        (map1, map2) -> {
                            map1.get(true).addAll(map2.get(true));
                            map1.get(false).addAll(map2.get(false));
                        });
    }

    // isPrime method
    public static Boolean isPrime(int candidate) {
        double candidateRoot = Math.sqrt(candidate);
        return IntStream.rangeClosed(2, (int)candidateRoot)
                .noneMatch(i -> (candidate % i) == 0);
    }


    // Better, Use the primes have got to execute
    public static boolean isPrime(List<Integer> primes, Integer candidate) {
        double candidateRoot = Math.sqrt(candidate);
        return primes.stream() //takeWhile java9
                .takeWhile(i -> i <= candidateRoot).noneMatch(i -> candidate % i == 0);
        // The takeWhile() method takes an assertion and returns a subset of the given Stream
        // until the assertion returns false for the first time.
    }

    /*
    public static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
        int i = 0;
        for (A item : list) {
            if (!p.test(item)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }
*/
}
