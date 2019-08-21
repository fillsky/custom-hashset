package football.league;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class FileManager {



    public <T> List<T> readGenericFile(String name, IReaderCSV<T> reader) throws IOException {

        CSVParserBuilder parserBuilder = new CSVParserBuilder() // parser builder do parametrów
                .withEscapeChar('\\')
                .withIgnoreLeadingWhiteSpace(true)
                .withQuoteChar('"')
                .withSeparator(';');

        CSVReaderBuilder readerBuilder = new CSVReaderBuilder(
                new FileReader(name + ".csv"))
                .withCSVParser(parserBuilder.build());

        return readerBuilder.build().readAll()
                .stream()
                .map(reader::fromArray)
                .collect(toList());

    }

    public void saveGenericFile(String name, Collection<?> collection, IWriterCSV iWriterCSV) throws IOException {


        CSVWriter writer = new CSVWriter(new FileWriter(name + ".csv"),
                ';',
                '"',
                '\\',
                "\n");

        writer.writeAll(collection.stream()
                .map(iWriterCSV::toArrays)
                .collect(toList()));

        writer.close();

    }

    public List<Team> readTeamsFromFile() throws IOException {

        CSVParserBuilder parserBuilder = new CSVParserBuilder() // parser builder do parametrów
                .withEscapeChar('\\')
                .withIgnoreLeadingWhiteSpace(true)
                .withQuoteChar('"')
                .withSeparator(';');

        CSVReaderBuilder readerBuilder = new CSVReaderBuilder(
                new FileReader("teams.csv"))
                .withCSVParser(parserBuilder.build());

        return readerBuilder.build().readAll().stream()
                .map(this::arrayToTeam)
                .collect(toList());
    }

    public void saveFile(List<Match> matches) throws IOException {

        CSVWriter writer = new CSVWriter(new FileWriter("matches.csv"),
                ';',
                '"',
                '\\',
                "\n");

        writer.writeAll(matches.stream()
                .map(this::matchToArray)
                .collect(toList()));

        writer.close();
    }

    public void saveTeamsToFile(List<Team> teams) throws IOException {
        CSVWriter writer = new CSVWriter(
                new FileWriter("teams.csv"),
                ';',
                '"',
                '\\',
                "\n");

        writer.writeAll(teams.stream()
                .map(this::teamToArray)
                .peek(team -> System.out.println(Arrays.toString(team)))
                .peek(System.out::println)
                .collect(toList())
        );

        writer.close();

    }

    public List<String[]> readCSV() throws IOException {

        CSVParserBuilder parserBuilder = new CSVParserBuilder()
                .withEscapeChar('\\')
                .withIgnoreLeadingWhiteSpace(true)
                .withQuoteChar('"')
                .withSeparator(';');

        CSVReaderBuilder reader = new CSVReaderBuilder(new FileReader("teams.csv")).withCSVParser(parserBuilder.build());
        return reader.build().readAll();
    }

    private String[] matchToArray(Match match) {
        return new String[]{
                match.getHost().getName(),
                match.getAway().getName(),
                match.getScore()};
    }

    private String[] teamToArray(Team team) {
        return new String[]{
                String.valueOf(team.getId()),
                team.getName(),
                String.join(",", team.getPlayers())
        };
    }

    private Team arrayToTeam(String[] row) {
        int id = Integer.parseInt(row[0]);
        String teamName = row[1];
        List<String> players = Arrays.asList(row[2].split(","));
        return new Team(teamName, id, players);

    }


}
