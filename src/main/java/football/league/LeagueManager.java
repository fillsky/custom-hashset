package football.league;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeagueManager {

    public static void main(String[] args) throws IOException {

        Team team1 = new Team("Ogórki", 1);
        Team team2 = new Team("Parówki", 2);

        List<Team> teams = new ArrayList<>();

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

        Match match2 = new Match(team1, team2, league);

        FileManager file = new FileManager();
        file.saveTeamsToFile(teams);
        file.readCSV().stream().forEach(x -> System.out.println(Arrays.toString(x)));



    }



}
