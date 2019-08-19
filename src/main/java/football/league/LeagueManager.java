package football.league;

import com.opencsv.CSVWriter;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class LeagueManager {

    public static void main(String[] args) {

        Team team1 = new Team("Ogórki");
        Team team2 = new Team("Parówki");

        HashSet<Team> teams = new HashSet<>();

        teams.add(team1);
        teams.add(team2);

        League league = new League("Ostatnia", teams);

        Match match = new Match(team1, team2, league);
        match.addPoint(team1);
        match.addPoint(team1);
        match.addPoint(team2);
        match.addPoint(team2);
        match.addPoint(team1);

        System.out.println(match.getScore());

        match.matchFinished();
        league.showMatches();
        league.showTeams();
        league.showTeamMatches(team1);


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

    public List<String[]> readCSV() throws FileNotFoundException {
       /*CSVReaderBuilder reader = new CSVReaderBuilder().withCSVParser(new CSVParserBuilder().withEscapeChar('\\')
        .withQuoteChar('"')
        .withSeparator(';')).withKeepCarriageReturn(false);
*/
       return Collections.emptyList();
    }

    private String[] matchToArray(Match match) {
        return new String[]{
                match.getHost().getName(),
                match.getAway().getName(),
                match.getScore()};
    }
}
