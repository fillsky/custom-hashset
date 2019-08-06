import hashset.StringHashSet;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) {
/*
        Complexity.start();
        experimentWithArrayList(100_000);
        Complexity.print("List");
        System.out.println();
        Complexity.start();
        experimentWithArraySet(100_00);
        Complexity.print("Set");*/
        Set<String> set = new StringHashSet();
        set.add("Ala");
        set.add("Alan");
        set.add("Jakub");
        set.add("Marek");
        set.add("Marek");

        System.out.println(set);

        set.clear();
        System.out.println(set);

    }

    public static void experimentWithArrayList(int size) {
        List<Integer> numbers = IntStream.range(0, size)
                .boxed()    // changing primitives to integer
                .collect(Collectors.toList());

        for (int i = size / 2; i < 1.5 * size; i++) {
            boolean contains = numbers.contains(i);

        }
    }

    public static void experimentWithArraySet(int size) {
        Set<Integer> numbers = IntStream.range(0, size)
                .boxed()    // changing primitives to integer
                .collect(Collectors.toSet());

        for (int i = size / 2; i < 1.5 * size; i++) {
            boolean contains = numbers.contains(i);

        }
    }
}
