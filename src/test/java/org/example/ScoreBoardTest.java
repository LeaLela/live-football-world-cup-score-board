package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreBoardTest {
    @Test
    void testSomething(){
        assertTrue(true);
    }
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
    public void testSameTeams(){
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
    void testUpdateScore(){
        scoreboard.startMatch("Mexico","Canada");
        scoreboard.updateScore("Mexico", "Canada", 0, 1);
        Match match = scoreboard.testOngoingMatches().get(0);
        assertEquals(0, match.getHomeScore());
        assertEquals(1, match.getAwayScore());
    }

    //test for non-existing match - updateScore
    @Test
    void testUpdateScoreNonExistingMatch(){
        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.updateScore("Spain","Mexico", 3,0);
        });
    }

    //test for negative homeScore - updateScore
    @Test
    void testUpdateScoreNegativeHomeScore(){
        scoreboard.startMatch("Mexico", "Canada");
        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.updateScore("Mexico", "Canada", -1, 0);
        });
    }

    //test for negative awayScore - updateScore
    @Test
    void testUpdateScoreNegativeAwayScore(){
        scoreboard.startMatch("Mexico", "Canada");
        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.updateScore("Mexico", "Canada", 0, -1);
        });
    }

}
