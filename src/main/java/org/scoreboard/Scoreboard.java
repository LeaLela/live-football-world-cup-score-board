package org.scoreboard;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {
    private final List<Match> ongoingMatches = new ArrayList<>();

    public void startMatch(String homeTeam, String awayTeam) {
        validateTeams(homeTeam, awayTeam);

        Match match = new Match(homeTeam, awayTeam);
        ongoingMatches.add(match);
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        validateTeams(homeTeam, awayTeam);

        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Scores cannot be negative");
        }

        Match matchToUpdate = findMatch(homeTeam, awayTeam);
        matchToUpdate.updateScore(homeScore, awayScore);
    }

    public void finishMatch(String homeTeam, String awayTeam) {
        validateTeams(homeTeam, awayTeam);

        Match matchToRemove = findMatch(homeTeam, awayTeam);
        ongoingMatches.remove(matchToRemove);
    }

    public List<Match> getSummary() {
        List<Match> summary = new ArrayList<>(ongoingMatches);
        summary.sort((m1, m2) -> {
            int totalGoals1 = m1.getHomeScore() + m1.getAwayScore();
            int totalGoals2 = m2.getHomeScore() + m2.getAwayScore();

            if (totalGoals2 != totalGoals1) {
                return Integer.compare(totalGoals2, totalGoals1);
            }
            return Integer.compare(ongoingMatches.indexOf(m2), ongoingMatches.indexOf(m1));
        });
        return summary;
    }

    // Private helper method to encapsulate
    private void validateTeams(String homeTeam, String awayTeam) {
        if (homeTeam == null || awayTeam == null || homeTeam.isEmpty() || awayTeam.isEmpty()) {
            throw new IllegalArgumentException("Team names cannot be null or empty.");
        }

        if (homeTeam.equals(awayTeam)) {
            throw new IllegalArgumentException("Home team and away team cannot be the same.");
        }
    }

    private Match findMatch(String homeTeam, String awayTeam) {
        for (Match match : ongoingMatches) {
            if (match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam)) {
                return match;
            }
        }
        throw new IllegalArgumentException("Match not found");
    }
}
