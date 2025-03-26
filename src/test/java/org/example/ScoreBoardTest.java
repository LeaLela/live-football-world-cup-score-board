package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreBoardTest {
    Scoreboard scoreboard = new Scoreboard();

    //all tests for StartMatch
    @Test
    public void testStartMatch(){

        scoreboard.startMatch("Mexico", "Canada");

        //test to see if the match was added in the list
        List<Match> matches = scoreboard.testOngoingMatches();

        assertEquals(1, matches.size());

        //check expected value of homeTeam and the one added in the list
        assertEquals("Mexico", matches.get(0).getHomeTeam());

        //check expected value of awayTeam and the one added in the list
        assertEquals("Canada", matches.get(0).getAwayTeam());

        //check expected value of homeScore and the one added in the list
        assertEquals(0, matches.get(0).getHomeScore());

        //check expected value of awayScore and the one added in the list
        assertEquals(0, matches.get(0).getAwayScore());
    }

    //test if homeTeam is empty - startMatch
    @Test
    public void testEmptyHomeTeam(){
        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.startMatch("", "Canada");
        });
    }

    //test if awayTeam is empty - startMatch
    @Test
    public void testEmptyAwayTeam(){
        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.startMatch("Mexico", "");
        });
    }

    //test if both teams have same name - startMatch
    @Test
    public void testStartMatchSameTeams(){
        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.startMatch("Mexico", "Mexico");
        });
    }

    //test if home team is null value - startMatch
    @Test
    public void testNullHomeTeam(){
        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.startMatch(null, "Canada");
        });
    }

    //test if away team is null value - startMatch
    @Test
    public void testNullAwayTeam(){
        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.startMatch("Mexico", null);
        });
    }


    //all tests for UpdateScore
    @Test
    public void testUpdateScore(){
        scoreboard.startMatch("Mexico","Canada");
        scoreboard.updateScore("Mexico", "Canada", 0, 1);
        Match match = scoreboard.testOngoingMatches().get(0);
        assertEquals(0, match.getHomeScore());
        assertEquals(1, match.getAwayScore());
    }

    //test for non-existing match - updateScore
    @Test
    public void testUpdateScoreNonExistingMatch(){
        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.updateScore("Spain","Mexico", 3,0);
        });
    }


    //test for negative homeScore - updateScore
    @Test
    public void testUpdateScoreNegativeHomeScore(){
        scoreboard.startMatch("Mexico", "Canada");
        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.updateScore("Mexico", "Canada", -1, 0);
        });
    }

    //test for negative awayScore - updateScore
    @Test
    public void testUpdateScoreNegativeAwayScore(){
        scoreboard.startMatch("Mexico", "Canada");
        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.updateScore("Mexico", "Canada", 0, -1);
        });
    }

    //test for null team names - updateScore
    @Test
    public void testUpdateScoreNullTeamNames(){
        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.updateScore(null, "Canada", 0, 1);
        });

        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.updateScore("Mexico", null, 0, 1);
        });
    }

    //test for empty team names - updateScore
    @Test
    public void testUpdateScoreEmptyTeamNames(){
        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.updateScore(" ", "Canada",0,1);
        });

        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.updateScore("Mexico", " ",0,1);
        });
    }


    //all tests for finishMatch
    @Test
    public void testFinishMatch(){
        //starting new match that is added to the list ongoingMatches
        scoreboard.startMatch("Mexico","Canada");
        //finding that same match in the list and removing it
        scoreboard.finishMatch("Mexico", "Canada");
        //after the match is finished, checking if the list is empty
        assertTrue(scoreboard.testOngoingMatches().isEmpty());
    }

    //test for non-existing match - finishMatch
    @Test
    public void testFinishMatchNotFound(){
        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.finishMatch("Spain", "Mexico");
        });
    }

    //test to check null team names - finishMatch
    @Test
    public void testFinishMatchNullTeamNames(){
        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.finishMatch(null,"Canada");
        });
        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.finishMatch("Mexico",null);
        });
    }

    //test to check empty team names - finishMatch
    @Test
    public void testFinishMatchEmptyTeamNames(){
        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.finishMatch(" ","Canada");
        });
        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.finishMatch("Mexico"," ");
        });
    }
}
