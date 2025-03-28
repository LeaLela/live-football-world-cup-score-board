package org.scoreboard;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ScoreBoardTest {
    Scoreboard scoreboard = new Scoreboard();

    @Test
    public void testStartMatch(){

        scoreboard.startMatch("Mexico", "Canada");

        List<Match> matches = scoreboard.getSummary();

        assertEquals(1, matches.size());
        //checking expected value of teams and scores and the one added in the list
        assertEquals("Mexico", matches.get(0).getHomeTeam());
        assertEquals("Canada", matches.get(0).getAwayTeam());
        assertEquals(0, matches.get(0).getHomeScore());
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

    @Test
    public void testUpdateScore(){
        scoreboard.startMatch("Mexico","Canada");
        scoreboard.updateScore("Mexico", "Canada", 0, 1);
        Match match = scoreboard.getSummary().get(0);
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

    @Test
    public void testFinishMatch(){
        //starting new match that is added to the list
        scoreboard.startMatch("Mexico","Canada");
        //finding that same match in the list and removing it
        scoreboard.finishMatch("Mexico", "Canada");
        //after the match is finished, checking if the list is empty
        assertTrue(scoreboard.getSummary().isEmpty());
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

    @Test
    public void testGetSummary(){

        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.startMatch("Spain", "Brazil");
        scoreboard.startMatch("Germany", "France");
        scoreboard.startMatch("Uruguay", "Italy");
        scoreboard.startMatch("Argentina", "Australia");

        scoreboard.updateScore("Mexico", "Canada", 0, 5); //total 5, index 2
        scoreboard.updateScore("Spain", "Brazil", 10, 2); //total 12, index 1
        scoreboard.updateScore("Germany", "France", 2, 2); //total 4, index 4
        scoreboard.updateScore("Uruguay", "Italy", 6, 6); //total 12, recently added, index 0
        scoreboard.updateScore("Argentina", "Australia", 3, 1); //total 4, recently added, index 3

        List<Match> summary = scoreboard.getSummary();

        //summary.get().getHomeTeam() takes each Match object by index from the list and checks if hometeam is right
        assertEquals("Uruguay", summary.get(0).getHomeTeam());
        assertEquals("Spain", summary.get(1).getHomeTeam());
        assertEquals("Mexico", summary.get(2).getHomeTeam());
        assertEquals("Argentina", summary.get(3).getHomeTeam());
        assertEquals("Germany", summary.get(4).getHomeTeam());
    }
}
