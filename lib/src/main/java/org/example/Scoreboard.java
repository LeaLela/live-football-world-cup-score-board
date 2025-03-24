package org.example;

//import org.example.Match;
import java.util.ArrayList;
import java.util.List;

public class Scoreboard {
    private final List<Match> ongoingMatches = new ArrayList<>();

    public void startMatch(String homeTeam, String awayTeam){

        if(homeTeam.isEmpty() || awayTeam.isEmpty()){
            throw new IllegalArgumentException("Teams names cannot be empty.");
        }
        if(homeTeam.equals(awayTeam)){
            throw new IllegalArgumentException("Home team and away team cannot be the same.");
        }
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

    //getter za test je li se match dodao u listu
    public List<Match> testOngoingMatches(){
        return ongoingMatches;
    }

}
