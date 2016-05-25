package com.dougestep.bowling.data;

import java.io.Serializable;
import java.util.UUID;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Represents a bowling frame.
 *
 * @author dougestep
 */
public class BowlingFrame implements Serializable {
    private static final long serialVersionUID = 7993320853342434486L;
    private UUID uid;
    private int firstBall;
    private int secondBall;
    private int score;
    private boolean split;

    /**
     * Creates an instance of this class.
     */
    public BowlingFrame() {
    }

    /**
     * Creates an instance of this class.
     * @param uid unique ID for the frame.
     */
    public BowlingFrame(final UUID uid) {
        setUid(uid);
    }

    /**
     * Creates an instance of this class.
     * @param firstBall the number of pins knocked down on the first ball.
     */
    public BowlingFrame(final int firstBall) {
        this();
        setFirstBall(firstBall);
    }

    /**
     * Creates an instance of this class.
     * @param firstBall the number of pins knocked down on the first ball.
     * @param secondBall the number of pins knocked down on the second ball.
     */
    public BowlingFrame(final int firstBall, final int secondBall) {
        this(firstBall);
        setSecondBall(secondBall);
    }

    /**
     * Creates an instance of this class.
     * @param firstBall the number of pins knocked down on the first ball.
     * @param secondBall the number of pins knocked down on the second ball.
     * @param split supply true to indicate the bowler through a split.
     */
    public BowlingFrame(final int firstBall, final int secondBall, final boolean split) {
        this(firstBall);
        setSecondBall(secondBall);
        setSplit(split);
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(final UUID uid) {
        this.uid = uid;
    }

    public int getFirstBall() {
        return firstBall;
    }

    public void setFirstBall(final int firstBall) {
        this.firstBall = firstBall;
    }

    public int getSecondBall() {
        return secondBall;
    }

    public void setSecondBall(final int secondBall) {
        this.secondBall = secondBall;
    }

    public int getScore() {
        return score;
    }

    public void setScore(final int score) {
        this.score = score;
    }

    public boolean isStrike() {
        return firstBall == 10;
    }

    public boolean isSpare() {
        return getFrameTotal() == 10 && firstBall != 10;
    }

    public boolean isOpenFrame() {
        return !isSpare() && !isStrike();
    }

    public int getFrameTotal() {
        return firstBall + secondBall;
    }

    public boolean isSplit() {
        return split;
    }

    public void setSplit(final boolean split) {
        this.split = split;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }

        final BowlingFrame other = (BowlingFrame) obj;
        return Objects.equal(this.uid, other.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uid);
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper str = MoreObjects.toStringHelper(this);
        str.add("firstBall", firstBall);
        str.add("secondBall", secondBall);
        str.add("score", score);
        str.add("strike", isStrike());
        str.add("spare", isSpare());
        str.add("split", isSplit());
        str.add("openFrame", isOpenFrame());
        str.add("frameTotal", getFrameTotal());
        return str.toString();
    }
}
