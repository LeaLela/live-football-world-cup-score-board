package org.list;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {
    private final List<Match> ongoingMatches = new ArrayList<>();

    public void startMatch(String homeTeam, String awayTeam){

        //test null value
        if(homeTeam == null || awayTeam == null){
            throw new IllegalArgumentException("Teams names cannot be null");
        }

        //test empty value
        if(homeTeam.isEmpty() || awayTeam.isEmpty()){
            throw new IllegalArgumentException("Teams names cannot be empty.");
        }

        //test same names of teams
        if(homeTeam.equals(awayTeam)){
            throw new IllegalArgumentException("Home team and away team cannot be the same.");
        }

        Match match = new Match(homeTeam, awayTeam);
        ongoingMatches.add(match);
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore){

        //checking if values are null or blank
        if (homeTeam == null || awayTeam == null || homeTeam.isEmpty() || awayTeam.isEmpty()){
            throw new IllegalArgumentException("Team names cannot be null or empty.");
        }

        //checking if scores are negative
        if (homeScore < 0 || awayScore < 0){
            throw new IllegalArgumentException("Scores cannot be negative");
        }

        //assuming we may not find a match that matches conditions; if we don't find it MatchToUpdate stays null
        Match matchToUpdate = null;
        for (int i = 0; i < ongoingMatches.size(); i++){
            Match match = ongoingMatches.get(i);
            if (match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam)){
                matchToUpdate = match;
                break;
            }
        }

        //checking if MatchToUpdate is still null ---> if it is, we have not found a match
        if(matchToUpdate == null){
            throw new IllegalArgumentException("Match not found");
        }

        matchToUpdate.updateScore(homeScore, awayScore);

    }

    public void finishMatch(String homeTeam, String awayTeam){
        if (homeTeam == null || awayTeam == null || homeTeam.isEmpty() || awayTeam.isEmpty()){
            throw new IllegalArgumentException("Team names cannot be null or empty");
        }

        Match matchToRemove = null;
        for(int i = 0; i < ongoingMatches.size(); i++){
            Match match = ongoingMatches.get(i);
            if(match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam)){
                matchToRemove = match;
                break;
            }
        }

        if (matchToRemove == null){
            throw new IllegalArgumentException("Match not found.");
        }

        ongoingMatches.remove(matchToRemove);

    }

    public List<Match> getSummary(){
        //copy of ongoingMatches list so that original list stays the same
        List<Match> summary = new ArrayList<>(ongoingMatches);

        //sorting out - first by total goals then by the order that matches were added in the list
        summary.sort((m1,m2) -> {
            int totalGoals1 = m1.getHomeScore() + m1.getAwayScore();
            int totalGoals2 = m2.getHomeScore() + m2.getAwayScore();

            //sorting by total goals in descending order
            if (totalGoals2 != totalGoals1){
                return Integer.compare(totalGoals2, totalGoals1);
            }

            //sorting by index of ongoingMatches in case total goals are the same(most recently added goes on top)
            return Integer.compare(ongoingMatches.indexOf(m2), ongoingMatches.indexOf(m1));
        });
        return summary;
    }

    //getter for test to see if match was added in the list
    public List<Match> testOngoingMatches(){
        return ongoingMatches;
    }

}
