package football.league;

import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

/**
 * klasa prezentująca ligę. Przechowuje listę drużyn i listę meczów.
 * Umie zwrócić listę drużyn posortowaną po zdobytych punktach.
 */
@ToString
public class League {

    private String name;
    private Collection<Team> teams;
    private Collection<Match> matches = new HashSet<>();

    public League(String name, Collection<Team> teams) {
        this.name = name;
        this.teams = teams;
    }

    public League(String name, Collection<Team> teams, Collection<Match> matches) {
        this.name = name;
        this.teams = teams;
        this.matches = matches;
    }

    public boolean addMatch(Match match) {
        return matches.add(match);
    }

    public void showMatches() {
        System.out.println(matches);
    }

    public List<Team> showTeamMatches(Team team) {
       /* if (teams.contains(team)){
            matches.stream().filter(match -> match.getHost() == team || match.getAway() == team).
            forEach(System.out::println);
        }*/

        Map<Team, List<Match>> teamMatchMap = new HashMap<>();

        if (teams.contains(team)) {
            matches.stream().filter(match -> match.hasTeam(team))
                    .forEach(System.out::println);
        }

        for (Team t : teams) {
            List<Match> matches = this.matches.stream().
                    filter(match -> match.hasTeam(t))
                    .collect(Collectors.toList());
            teamMatchMap.put(team, matches);
        }

        Map<Team, Integer> scores = new HashMap<>();
        for (Team t : teams) {
            int sum = teamMatchMap.get(team).stream()
                    .mapToInt(match -> match.getScore(team))
                    .sum();
            scores.put(team, sum);
        }

          return scores.entrySet().stream()
                    .sorted(Comparator.comparing(Map.Entry<Team, Integer>::getValue).reversed())
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

    }

    public String getName() {
        return name;
    }

    public void showTeams() {
        System.out.println(teams);
    }

    public Collection<Team> getTeams() {
        return teams;
    }

    public Collection<Match> getMatches() {
        return matches;
    }
}
