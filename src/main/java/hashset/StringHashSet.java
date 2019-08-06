package hashset;


import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringHashSet implements Set<String> {
    private List<List<String>> buckets;
    private int currentSize;

    public StringHashSet() {
        currentSize = 0;
        buckets = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            buckets.add(new ArrayList<>());

        }
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        if (currentSize == 0) return true;
        return false;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) return false;

        if (!(o instanceof String)) {
            return false;
        }
        int hash = o.hashCode();
        int index = Math.abs(hash % this.buckets.size());
        return this.buckets.get(index).contains(o);
    }

    @Override
    public Iterator<String> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super String> action) {

    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(String s) {
        int hash = s.hashCode();
        int index = Math.abs(hash % this.buckets.size());
        for (String bucket : this.buckets.get(index)) {
            if (bucket.hashCode() == hash) {
                return false;
            }
        }
        this.buckets.get(index).add(s);
        currentSize++;
        return true;
    }

    @Override
    public boolean remove(Object o) {

        if (o == null) return false;

        if (!(o instanceof String)) {
            return false;
        }

        int hash = o.hashCode();
        int index = Math.abs(hash % this.buckets.size());

        if (this.buckets.get(index).remove(o)) {
            currentSize--;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        boolean changed = false;

        for (String s : c) {
            if (this.add(s)) {
                changed = true;
            }
        }

        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeIf(Predicate<? super String> filter) {

        return false;
    }

    @Override
    public void clear() {
       /* for (int i = 0; i < this.buckets.size(); i++) {
            this.buckets.get(i).clear();
        }*/
        for (List<String> bucket : this.buckets) {
            bucket.clear();
        }
        currentSize = 0;
    }

    @Override
    public Spliterator<String> spliterator() {
        return null;
    }

    @Override
    public Stream<String> stream() {
        return null;
    }

    @Override
    public Stream<String> parallelStream() {
        return null;
    }

    @Override
    public String toString() {
        return "{" + this.buckets.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.joining(", ")) + "}";
    }
}
