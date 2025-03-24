package org.example;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class ScoreBoardTest {
    Scoreboard scoreboard = new Scoreboard();

    public void testStartMatch(){

        scoreboard.startMatch("Mexico", "Canada");

        //testiranje je li se match dodao u listu
        List<Match> matches = scoreboard.testOngoingMatches();

        assertEquals(1, matches.size());
        assertEquals("Mexico", matches.get(0).getHomeTeam());
        assertEquals("Canada", matches.get(0).getAwayTeam());
        assertEquals(0, matches.get(0).getHomeScore());
        assertEquals(0, matches.get(0).getAwayScore());
    }

    //Ako je homeTeam prazan - startMatch
    public void testEmptyHomeTeam(){
        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.startMatch("", "Canada");
        });
    }

    //Ako je awayTeam prazan - startMatch
    public void testEmptyAwayTeam(){
        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.startMatch("Mexico", "");
        });
    }

    //Ako su upisani isti timovi - startMatch
    public void testSameTeams(){
        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.startMatch("Mexico", "Mexico");
        });
    }

    //Ako je upisana null vrijednost na home team - startMatch
    public void testNullHomeTeam(){
        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.startMatch(null, "Canada");
        });
    }

    //Ako je upisana null vrijednost na away team - startMatch
    public void testNullAwayTeam(){
        assertThrows(IllegalArgumentException.class, () ->{
            scoreboard.startMatch("Mexico", null);
        });
    }
}
