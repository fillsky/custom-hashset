import hashcollections.StringHashSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class StringHashSetTests {

    StringHashSet set = new StringHashSet();
    @DisplayName("Return false while adding same value to set")
    @Test
    void testAddingToSetSameValue(){
        Assertions.assertTrue(set.add("Marek"));
        Assertions.assertFalse(set.add("Marek"));
    }

    @DisplayName("Checking if Collection is clearing")
    @Test
    void testIfClearWorks(){
        set.add("Ala");
        set.add("Ma");
        set.add("Kota");
        set.add("Alana");
        Assertions.assertFalse(set.isEmpty());
        set.clear();
        Assertions.assertTrue(set.isEmpty());
    }
    @DisplayName("Return true if value removed correctly")
    @Test
    void testIfRemoveWorks(){
        set.add("Ala");
        set.add("Ma");
        set.add("Kota");
        set.add("Alana");

        Assertions.assertTrue(set.remove("Kota"));
        Assertions.assertFalse(set.remove("Tego nie ma w kolecji"));
        Assertions.assertFalse(set.remove(15));
        Assertions.assertFalse(set.remove(null));
    }
    @DisplayName("Return correct size")
    @Test
    void testCorrectSize(){
        set.clear();
        set.add("Ala");
        set.add("Ma");
        set.add("Kota");
        set.add("Alana");
        Assertions.assertEquals(4,set.size());
    }
    @DisplayName("Return true if contains")
    @Test
    void testIfContainWorks(){

        set.add("Ala");
        set.add("Ma");
        set.add("Kota");
        set.add("Alana");

        Assertions.assertTrue(set.contains("Ma"));
        Assertions.assertFalse(set.contains("Nie ma"));
        Assertions.assertFalse(set.contains(5));
        Assertions.assertFalse(set.contains(null));

    }

    @DisplayName("Return true if at least one elemenet is added")
    @Test
    void testIfAddAllWorks(){

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();

        set.clear();

        set.add("Ala");
        set.add("Ma");
        set.add("Kota");

        list1.add("Ala");
        list1.add("Ma");
        list1.add("Kota");

        list2.add("Alan");
        list2.add("Nie");
        list2.add("Ma");

        list3.add("A");
        list3.add("Ja");
        list3.add("Psa");

        Assertions.assertFalse(set.addAll(list1));
        Assertions.assertTrue(set.addAll(list2));
        Assertions.assertTrue(set.addAll(list3));

    }
}
