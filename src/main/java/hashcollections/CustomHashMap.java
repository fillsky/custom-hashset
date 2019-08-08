package hashcollections;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class CustomHashMap <K, V> implements Map<K, V> {

    private HashSet<EntryImpl<K, V>> storage;

    public CustomHashMap() {
        this.storage = new HashSet<>();
    }


    @Override
    public String toString() {
        return  this.storage.toString();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return getCurrentValue((K) key);
    }

    @Override
    public V put(K key, V value) {

        EntryImpl<K, V> entry = new EntryImpl<>(key, value);
        if (storage.contains(entry)) {
            storage.remove(entry);
        }
        storage.add(entry);
        return value;

    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return storage.stream()
                .map(EntryImpl::getKey)
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<V> values() {
        return storage.stream()
                .map(EntryImpl::getValue)
                .collect(Collectors.toList());
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new HashSet<>(storage);
    }

    private V getCurrentValue(K key) {

        //Refleksja jest tutaj niewykorzystywana
        try {
            Field field = storage.getClass().getDeclaredField("map");
            field.setAccessible(true);
            HashMap<K, V> map = (HashMap<K, V>) field.get(storage);
            return storage.stream()
                    .filter(entry -> entry.getKey().equals(key))
                    .findFirst()
                    .map(EntryImpl::getValue)
                    .orElse(null);

        } catch (NoSuchFieldException | IllegalAccessException | ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static class EntryImpl<K, V> implements Entry<K, V>{

        private K key;
        private V value;

        public EntryImpl(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {

            return this.value = value;
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return Objects.equals(obj, key);
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }
}
