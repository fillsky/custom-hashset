package football.league;

import java.util.HashSet;

/**
 * Przechowuje listę zawodników. - unikalna bez powtórzeń;
 * Ma nazwę.
 */
public class Team {

    private HashSet<String> players = new HashSet();
    private String name;
    private int points;
    private final int id;

    public Team(String name, int id) {
        this.name = name;
        this.id = id;

        players.add("Leluś Zielony");
        players.add("Leluś Czerwony");
        players.add("Leluś Błękitny");
        players.add("Leluś Czarny");

        points = 0;
    }



    public Team(String name, int id, HashSet<String> players) {
        this.name = name;
        this.id = id;
        this.players = players;
        points = 0;
    }


    public void addPlayer(String name) {

        players.add(name);
    }


    public void addPoints(int points) {
        this.points = +points;
    }

    @Override
    public String toString() {
        return "\n" + name +
                ", goals: " + points +
                " squad: \n\t" + players.toString();
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getId() {
        return id;
    }

    public HashSet<String> getPlayers() {
        return players;
    }
}
