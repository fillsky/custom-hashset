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
import java.util.stream.Collectors;

public class FileManager {

    public void saveGenericFile(Collection<?> collection, String name) {


    }

    public void saveFile(List<Match> matches) throws IOException {

        CSVWriter writer = new CSVWriter(new FileWriter("matches.csv"),
                ';',
                '"',
                '\\',
                "\n");

        writer.writeAll(matches.stream()
                .map(this::matchToArray)
                .collect(Collectors.toList()));

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
                .collect(Collectors.toList())
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

}
