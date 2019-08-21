package football.league;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LeagueManager {

    public static void main(String[] args) throws IOException {

        Team team1 = new Team("Ogórki", 1);
        Team team2 = new Team("Parówki", 2);

        List<Team> teams = new ArrayList<>();

        teams.add(team1);
        teams.add(team2);

        League league = new League("Ostatnia", teams);

        Match match1 = new Match(1, team1, team2, league);
        match1.addPoint(team1);
        match1.addPoint(team1);
        match1.addPoint(team2);
        match1.addPoint(team2);
        match1.addPoint(team2);
        match1.addPoint(team1);

        System.out.println(match1.getScore());

        match1.matchFinished();
        league.showMatches();
        league.showTeams();
        league.showTeamMatches(team1);

        Match match2 = new Match(2,team1, team2, league);

        match2.addPoint(team1);
        match2.addPoint(team1);
        match2.addPoint(team1);
        match2.addPoint(team2);
        match2.addPoint(team2);
        match2.addPoint(team2);
        match2.addPoint(team1);

        List<League> leagues = new ArrayList<>();
        leagues.add(league);

        FileManager file = new FileManager();
        /*file.saveTeamsToFile(teams);
        file.readCSV().stream().forEach(x -> System.out.println(Arrays.toString(x)));*/

        file.saveGenericFile("teams_g", teams, FileUtils::writeTeam);
        file.saveGenericFile("matches_g", league.getMatches(), FileUtils::writeMatch);
        file.saveGenericFile("leagues_g", leagues, FileUtils::writeLeague);

        List<Team> teams2;

        teams2 = file.readGenericFile("teams_g", FileUtils::readTeam);


        System.out.println(" ---- Team Po wczytaniu ---");
        teams2.stream().forEach(System.out::println);


        FileUtils.setTeamMap(teams2.stream()
                .collect(Collectors.toMap(Team::getId, Function.identity())));

        List<Match> restoredMatches = file.readGenericFile("matches_g", FileUtils::readMatches);


        System.out.println("---- odzyskane mecze --- ");
        restoredMatches.forEach(System.out::println);

        FileUtils.setMatchMap(league.getMatches().stream()
                .collect(Collectors.toMap(Match::getId, Function.identity())));

    }


}
