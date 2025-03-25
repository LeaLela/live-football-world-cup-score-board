package org.example;

//import org.example.Match;
import java.util.ArrayList;
import java.util.List;

public class Scoreboard {
    private final List<Match> ongoingMatches = new ArrayList<>();

    public void startMatch(String homeTeam, String awayTeam){

        //test empty value
        if(homeTeam.isEmpty() || awayTeam.isEmpty()){
            throw new IllegalArgumentException("Teams names cannot be empty.");
        }

        //test same names of teams
        if(homeTeam.equals(awayTeam)){
            throw new IllegalArgumentException("Home team and away team cannot be the same.");
        }

        //test null value
        if(homeTeam == null || awayTeam == null){
            throw new IllegalArgumentException("Teams names cannot be the same");
        }

        Match match = new Match(homeTeam, awayTeam);
        ongoingMatches.add(match);
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore){

    }

    public void finishMatch(String homeTeam, String awayTeam){

    }

    //getter for test to see if match was added in the list
    public List<Match> testOngoingMatches(){
        return ongoingMatches;
    }

}
