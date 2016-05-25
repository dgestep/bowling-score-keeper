package com.dougestep.bowling.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Represents a bowling game for a particular bowler.
 *
 * @author dougestep
 */
public class Game implements Serializable {
    private static final long serialVersionUID = 751620950527583199L;
    private UUID uid;
    private List<BowlingFrame> frames;
    private int score;
    private boolean complete;
    private Bowler bowler;

    /**
     * Creates an instance of this class.
     */
    public Game() {
    }

    /**
     * Creates an instance of this class.
     * @param uid unique ID for the game.
     */
    public Game(final UUID uid) {
        setUid(uid);
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(final UUID uid) {
        this.uid = uid;
    }

    public List<BowlingFrame> getFrames() {
        return frames;
    }

    public void setFrames(final List<BowlingFrame> frames) {
        this.frames = frames;
    }

    public void addFrame(final BowlingFrame frame) {
        if (frames == null) {
            frames = new ArrayList<>();
        }
        frames.add(frame);
    }

    public void clearFrames() {
        if (frames == null) {
            frames = new ArrayList<>();
        } else {
            frames.clear();
        }
    }

    public int getNumberOfFrames() {
        return frames == null ? 0 : frames.size();
    }

    public int getScore() {
        return score;
    }

    public void setScore(final int score) {
        this.score = score;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(final boolean complete) {
        this.complete = complete;
    }

    public Bowler getBowler() {
        return bowler;
    }

    public void setBowler(final Bowler bowler) {
        this.bowler = bowler;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }

        final Game other = (Game) obj;
        return Objects.equal(this.uid, other.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uid);
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper str = MoreObjects.toStringHelper(this);
        str.add("player", bowler);
        str.add("score", score);
        str.add("complete", complete);
        return str.toString();
    }
}
