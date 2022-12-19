package com.xjjlearning.springframework.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Hashtable;

@SpringBootApplication
public class SpringBootDemoApplication {

    public static void main(String[] args) throws InterruptedException {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(null, "1");
        hashMap.put("1", null);
        hashMap.put(null, null);
        System.out.println(hashMap.get(null));
        System.out.println(hashMap.get("1"));


        Hashtable<String, String> stringStringHashtable = new Hashtable<>();
        /**
         * error
         */
        // stringStringHashtable.put(null, null);
        // stringStringHashtable.put(null, "1");
        // stringStringHashtable.put("1", null);
        // System.out.println(stringStringHashtable.get(null));
        // System.out.println(stringStringHashtable.get("1"));

        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

}
