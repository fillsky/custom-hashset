package football.league;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class LeagueManager {

    public static void main(String[] args) throws IOException {

        Team team1 = new Team("Ogórki", 1);
        Team team2 = new Team("Parówki", 2);
        Team team3 = new Team("Marchewki", 4);

        List<Team> teams = new ArrayList<>();

        teams.add(team1);
        teams.add(team2);
        teams.add(team3);

        League league1 = new League("Ostatnia", teams);

        Match match1 = new Match(1, team1, team2, league1);
        match1.addPoint(team1);
        match1.addPoint(team1);
        match1.addPoint(team2);
        match1.addPoint(team2);
        match1.addPoint(team2);


        match1.matchFinished();

        league1.showMatches();
        league1.showTeams();
        league1.showTeamMatches(team1);

        Match match2 = new Match(3, team3, team1, league1);

        match2.addPoint(team1);
        match2.addPoint(team3);
        match2.addPoint(team3);
        match2.addPoint(team3);
        match2.matchFinished();
        League league2 = new League("Parówkowa", teams);

        Match match3 = new Match(4,team1, team2, league2);

        match3.addPoint(team1);
        match3.addPoint(team1);
        match3.addPoint(team1);
        match3.addPoint(team2);
        match3.addPoint(team2);
        match3.addPoint(team2);
        match3.addPoint(team1);
        match3.matchFinished();

        List<League> leagues = new ArrayList<>();
        leagues.add(league1);
        leagues.add(league2);

        FileManager file = new FileManager();
        /*file.saveTeamsToFile(teams);
        file.readCSV().stream().forEach(x -> System.out.println(Arrays.toString(x)));*/

        file.saveGenericFile("teams_g", teams, FileUtils::writeTeam);
        file.saveGenericFile("matches_g", league1.getMatches(), FileUtils::writeMatch);
        file.saveGenericFile("leagues_g", leagues, FileUtils::writeLeague);

        List<Team> teams2;

        teams2 = file.readGenericFile("teams_g", FileUtils::readTeam);


        System.out.println(" ---- Team Po wczytaniu ---");
        teams2.forEach(System.out::println);


        FileUtils.setTeamMap(teams2.stream()
                .collect(toMap(Team::getId, Function.identity())));

        List<Match> restoredMatches = file.readGenericFile("matches_g", FileUtils::readMatches);


        System.out.println("---- odzyskane mecze --- ");
        restoredMatches.forEach(System.out::println);

        List<League> leagueList = new ArrayList<>();
        leagueList.add(league1);
        leagueList.add(league2);

        Map<Integer, Match> matchMap = new HashMap<>(); //wrzucanie meczy każdej ligi do mapy.
        leagueList.forEach(league ->
            matchMap.putAll(league.getMatches() // tu zwracana jest cala mapa a nie Integer, Match dlatego putAll a nie put.
                    .stream()
                    .collect(toMap(Match::getId, Function.identity())))

        );

        FileUtils.setMatchMap(matchMap);


        List<League> restoredLeagues = file.readGenericFile("leagues_g", FileUtils::readLeague);
        System.out.println(" --- odzyskane ligi --- ");
        restoredLeagues.forEach(System.out::println);

    }


}
