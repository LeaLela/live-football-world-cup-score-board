package org.example;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class ScoreBoardTest {
    Scoreboard scoreboard = new Scoreboard();

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
    public void testEmptyHomeTeam(){
        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.startMatch("", "Canada");
        });
    }

    //test if awayTeam is empty - startMatch
    public void testEmptyAwayTeam(){
        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.startMatch("Mexico", "");
        });
    }

    //test if both teams have same name - startMatch
    public void testSameTeams(){
        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.startMatch("Mexico", "Mexico");
        });
    }

    //test if home team is null value - startMatch
    public void testNullHomeTeam(){
        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.startMatch(null, "Canada");
        });
    }

    //test if away team is null value - startMatch
    public void testNullAwayTeam(){
        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.startMatch("Mexico", null);
        });
    }
}
