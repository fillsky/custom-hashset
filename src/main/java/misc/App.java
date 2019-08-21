package misc;

import hashcollections.CustomHashMap;

import java.util.Map;

public class App {
    public static void main(String[] args) {


        Map<String, String> map = new CustomHashMap<>();

        map.put("Jan", "Kowalski");
        map.put("Danuta", "Nowak");

        System.out.println(map);
    }
}
