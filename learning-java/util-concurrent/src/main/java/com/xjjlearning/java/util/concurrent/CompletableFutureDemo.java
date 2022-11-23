package com.xjjlearning.java.util.concurrent;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureDemo {
    public static void main(String[] args) {
//        CompletableFuture<List<String>> ids = ifhIds();
//
//        CompletableFuture<List<String>> result = ids.thenComposeAsync(l -> {
//            Stream<CompletableFuture<String>> zip =
//                    l.stream().map(i -> {
//                        CompletableFuture<String> nameTask = ifhName(i);
//                        CompletableFuture<Integer> statTask = ifhStat(i);
//
//                        return nameTask.thenCombineAsync(statTask, (name, stat) -> "Name " + name + " has stats " + stat);
//                    });
//            List<CompletableFuture<String>> combinationList = zip.collect(Collectors.toList());
//            CompletableFuture<String>[] combinationArray = combinationList.toArray(new CompletableFuture[combinationList.size()]);
//
//            CompletableFuture<Void> allDone = CompletableFuture.allOf(combinationArray);
//            return allDone.thenApply(v -> combinationList.stream()
//                    .map(CompletableFuture::join)
//                    .collect(Collectors.toList()));
//        });

//        List<String> results = result.join();

        String s = "abcabczzzde";
        String p = "*abc???de*";
        int m = s.length(), n = p.length();
        String[] tmp = p.replaceAll("\\?", "*").split("\\*");
        for (String value : tmp) {
            System.out.println(value);
            if (value.length() != 0 && !s.contains(value)) System.out.println(false);
        }


//        assertThat(results).contains(
//                "Name NameJoe has stats 103",
//                "Name NameBart has stats 104",
//                "Name NameHenry has stats 105",
//                "Name NameNicole has stats 106",
//                "Name NameABSLAJNFOAJNFOANFANSF has stats 121");
    }

    private static CompletableFuture<List<String>> ifhIds() {
        return null;
    }
}
