package football.league;

import lombok.extern.log4j.Log4j;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j
/* Using this class instead of writing lambdas in line*/
public class FileUtils {


    private static Map<Integer, Team> teamMap;
    private static Map<Integer, Match> matchMap;

    public static Team readTeam(String [] row){
        //String [] row = (String[]) t;
        int id = Integer.parseInt(row[0]);
        String teamName = row[2];
        List<String> players = Arrays.asList(row[3].split(","));

        Team team = new Team(teamName, id, players);
        team.addPoints(Integer.parseInt(row[1]));
        return team;
    }

    public static Match readMatches (String [] row){

        int id = Integer.parseInt(row[0]);
        int awayId = Integer.parseInt(row[1]);
        int hostId = Integer.parseInt(row[2]);
        int awayScore = Integer.parseInt(row[3]);
        int hostScore = Integer.parseInt(row[4]);

        Team host = teamMap.get(hostId);
        Team away = teamMap.get(awayId);

        if (host == null) {
            log.error(String.format("Host with id %d not found.", hostId));
            throw new RuntimeException("Host not found");
        } if (away == null) {
            log.error(String.format("Away with id %d not found.", awayId));
            throw new RuntimeException("Away not found");
        }

        return new Match(id, host, away, hostScore, awayScore);

    }

    /*public static League readLeague (String[] row){


    }*/
    public static String[] writeLeague(Object object){

        League league = (League) object;

        List<String> teamIds = league.getTeams().stream()
                .map(team -> String.valueOf(team.getId()))
                .collect(Collectors.toList());

        List<String> matchIds = league.getMatches().stream()
                .map(match -> String.valueOf(match.getId()))
                .collect(Collectors.toList());

        return new String[]{
                String.join(",", teamIds),
                String.join(",", matchIds),
                league.getName()
        };
    }


    public static String[] writeTeam(Object object){
        Team team = (Team) object;
        return new String[]{
                String.valueOf(team.getId()),
                String.valueOf(team.getPoints()),
                team.getName(),
                String.join(",", team.getPlayers())
        };
    }

    public static String[] writeMatch (Object object){
        Match match = (Match) object;
        return new String[]{
                String.valueOf(match.getId()),
                String.valueOf(match.getAway().getId()),
                String.valueOf(match.getHost().getId()),
                String.valueOf(match.getAwayScore()),
                String.valueOf(match.getHostScore()),

        };
    }


    public static void setTeamMap(Map<Integer, Team> teamMap) {
        FileUtils.teamMap = teamMap;
    }

    public static void setMatchMap(Map<Integer, Match> matchMap) {
        FileUtils.matchMap = matchMap;
    }
}
