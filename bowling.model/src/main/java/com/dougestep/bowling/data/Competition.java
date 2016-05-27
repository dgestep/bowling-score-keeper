package com.dougestep.bowling.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Represents a competition between individual bowlers (not on teams).
 *
 * @author dougestep
 */
public class Competition implements Serializable {
    private static final long serialVersionUID = 998558534630848716L;
    private UUID uid;
    private List<CompetitionEntity> competitions;
    private List<CompetitionScore> scores;

    /**
     * Creates an instance of this class.
     */
    public Competition() {
    }

    /**
     * Creates an instance of this class.
     * @param uid unique ID for the frame.
     */
    public Competition(final UUID uid) {
        setUid(uid);
    }

    /**
     * Returns a {@link UUID} that uniquely identifying this competition.
     * @return the UUID.
     */
    public UUID getUid() {
        return uid;
    }

    /**
     * Sets the {@link UUID} that uniquely identifying this competition.
     * @param uid the UUID.
     */
    public void setUid(final UUID uid) {
        this.uid = uid;
    }

    /**
     * Returns the {@link CompetitionEntity}'s associated with this competition.
     * @return the competitions.
     */
    public List<CompetitionEntity> getCompetitions() {
        return competitions;
    }

    /**
     * Sets the {@link CompetitionEntity}'s associated with this competition.
     * @param competitions the competitions.
     */
    public void setCompetitions(List<CompetitionEntity> competitions) {
        this.competitions = competitions;
    }

    /**
     * Adds the supplied {@link CompetitionEntity} to the list of competitions associated with this
     * competition.
     * @param competition the competition.
     */
    public void addCompetitions(final CompetitionEntity competition) {
        if (this.competitions == null) {
            this.competitions = new ArrayList<>();
        }
        this.competitions.add(competition);
    }

    /**
     * Returns the competitors and their scores in order of highest scores first.
     * @return the competitors scores.
     */
    public List<CompetitionScore> getCompetitionScores() {
        return scores;
    }

    /**
     * Sets the list of competitors and their scores.
     * @param scores the list.
     */
    public void setCompetitionScores(List<CompetitionScore> scores) {
        this.scores = scores;
    }

    /**
     * Adds the supplied competitor and their score to the list of competitors and their scores.
     * @param score the score entry.
     */
    public void addBowlerScore(final CompetitionScore score) {
        if (this.scores == null) {
            this.scores = new ArrayList<>();
        }
        this.scores.addAll(scores);
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }

        final Competition other = (Competition) obj;
        return Objects.equal(this.uid, other.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uid);
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper str = MoreObjects.toStringHelper(this);
        str.add("competitions", competitions);
        str.add("scores", scores);
        return str.toString();
    }

    /**
     * Represents a bowler and their score for a game.
     * 
     * @author dougestep
     */
    public static class CompetitorScore implements CompetitionScore, Serializable, Comparable<CompetitorScore> {
        private static final long serialVersionUID = -1010464270285890424L;
        private Bowler bowler;
        private int score;

        public CompetitorScore(final Bowler bowler, final int score) {
            this.bowler = bowler;
            this.score = score;
        }

        public Bowler getBowler() {
            return bowler;
        }

        public int getScore() {
            return score;
        }

        @Override
        public boolean equals(final Object obj) {
            if (obj == null) { return false; }
            if (getClass() != obj.getClass()) { return false; }

            final CompetitorScore other = (CompetitorScore) obj;
            return Objects.equal(this.bowler, other.bowler);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(bowler);
        }

        @Override
        public String toString() {
            final MoreObjects.ToStringHelper str = MoreObjects.toStringHelper(this);
            str.add("player", bowler);
            str.add("score", score);
            return str.toString();
        }

        @Override
        public int compareTo(final CompetitorScore obj) {
            int rc = new Integer(score).compareTo(new Integer(obj.score));
            if (rc != 0) { return rc * (-1); }

            rc = bowler.compareTo(obj.bowler);
            return rc;
        }
    }
}
