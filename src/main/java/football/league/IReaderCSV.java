package football.league;

@FunctionalInterface
public interface IReaderCSV<T> {

    T fromArray(String[] row);
}
