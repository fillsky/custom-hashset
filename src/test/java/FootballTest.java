import football.league.League;
import football.league.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

public class FootballTest {
    Team team1 = new Team("Ogórki");
    Team team2 = new Team("Parówki");

    HashSet<Team> teams = new HashSet<>();

    League league;

    //Match match = new Match(team1, team2, league);

    @DisplayName("Constructor working properly")
    @Test
    void testLeagueConstructor() {
        teams.add(team1);
        teams.add(team2);
        League league = new League("Najlepsza", teams);


        Assertions.assertFalse(league.getName().isEmpty());
        Assertions.assertFalse(league.getTeams().isEmpty());

    }
}
