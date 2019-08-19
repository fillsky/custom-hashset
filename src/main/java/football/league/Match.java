package football.league;

/**
 * Reperezentuje konkretny, zakonczony pojedynek.
 * Ma gospodarze (host), gość (away) i wynik (1-3-0)
 */

public class Match {

    private Team host;


    private Team away;
    private int hostScore;
    private int awayScore;

    public Match(Team host, Team away, League league) {
        this.host = host;
        this.away = away;
        hostScore = 0;
        awayScore = 0;
        league.addMatch(this);
    }


    public void addPoint(Team team) {
        if (team == this.host) {
            hostScore++;
            System.out.println("Goal for host team!");
        } else if (team == this.away) {

            awayScore++;
            System.out.println("Goal for away team!");
        } else {
            System.out.println("No such team in this match!");
        }


    }

    public String getScore() {
        return hostScore + " : " + awayScore;
    }

    public int getScore(Team team) {

        if (!(this.hasTeam(team))) {
            throw new RuntimeException();
        }
        if (team == host) {
            return hostScore;
        } else {
            return awayScore;
        }

    }

    public void matchFinished() {
        System.out.println("Match finished with score " + getScore());
        host.addPoints(hostScore);
        away.addPoints(awayScore);
        if (hostScore > awayScore) {
            System.out.println("Host won!");
        }

    }

    public Team getHost() {
        return host;
    }

    public Team getAway() {
        return away;
    }

    public boolean hasTeam(Team team) {
        return host.equals(team) || away.equals(team);
    }

    @Override
    public String toString() {
        return getScore();
    }
}
