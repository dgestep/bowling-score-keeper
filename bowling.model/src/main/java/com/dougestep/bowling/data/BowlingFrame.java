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
    /**
     * 10 points.
     */
    private static final int MARK_1 = 10;
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
     *
     * @param uid unique ID for the frame.
     */
    public BowlingFrame(final UUID uid) {
        setUid(uid);
    }

    /**
     * Creates an instance of this class.
     *
     * @param firstBall the number of pins knocked down on the first ball.
     */
    public BowlingFrame(final int firstBall) {
        this();
        setFirstBall(firstBall);
    }

    /**
     * Creates an instance of this class.
     *
     * @param firstBall  the number of pins knocked down on the first ball.
     * @param secondBall the number of pins knocked down on the second ball.
     */
    public BowlingFrame(final int firstBall, final int secondBall) {
        this(firstBall);
        setSecondBall(secondBall);
    }

    /**
     * Creates an instance of this class.
     *
     * @param firstBall  the number of pins knocked down on the first ball.
     * @param secondBall the number of pins knocked down on the second ball.
     * @param split      supply true to indicate the bowler threw a split.
     */
    public BowlingFrame(final int firstBall, final int secondBall, final boolean split) {
        this(firstBall);
        setSecondBall(secondBall);
        setSplit(split);
    }

    /**
     * Returns a {@link UUID} that uniquely identifying this frame.
     *
     * @return the UUID.
     */
    public UUID getUid() {
        return uid;
    }

    /**
     * Sets the {@link UUID} that uniquely identifying this frame.
     *
     * @param uid the UUID.
     */
    public void setUid(final UUID uid) {
        this.uid = uid;
    }

    /**
     * Returns the number of pins knocked down on the first ball.
     *
     * @return the first ball.
     */
    public int getFirstBall() {
        return firstBall;
    }

    /**
     * Sets the number of pins knocked down on the first ball. Set to 10 to indicate a strike.
     *
     * @param firstBall the first ball.
     */
    public void setFirstBall(final int firstBall) {
        this.firstBall = firstBall;
    }

    /**
     * Returns the number of pins knocked down on the second ball.
     *
     * @return the first ball.
     */
    public int getSecondBall() {
        return secondBall;
    }

    /**
     * Sets the number of pins knocked down on the second ball.
     *
     * @param secondBall the second ball.
     */
    public void setSecondBall(final int secondBall) {
        this.secondBall = secondBall;
    }

    /**
     * Returns the score for this frame.
     *
     * @return the score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the score for this frame.
     *
     * @param score the score.
     */
    public void setScore(final int score) {
        this.score = score;
    }

    /**
     * Returns true if the bowler scored a strike for this frame (first ball = 10).
     *
     * @return true if a strike.
     */
    public boolean isStrike() {
        return firstBall == MARK_1;
    }

    /**
     * Returns true if the bowler scored a spare for this frame (first ball != 10 and ball1 + ball2 = 10).
     *
     * @return true if a strike.
     */
    public boolean isSpare() {
        return getFrameTotal() == MARK_1 && firstBall != MARK_1;
    }

    /**
     * Returns true if the bowler didn't get a strike or a spare in this frame.
     *
     * @return true if a spare.
     */
    public boolean isOpenFrame() {
        return !isSpare() && !isStrike();
    }

    /**
     * Returns the sum of the first and second balls for this frame.
     *
     * @return the frame total.
     */
    public int getFrameTotal() {
        return firstBall + secondBall;
    }

    /**
     * Returns true if the bowler threw a split.
     *
     * @return true if a split.
     */
    public boolean isSplit() {
        return split;
    }

    /**
     * Set to true if the bowler threw a split.
     *
     * @param split true if a split.
     */
    public void setSplit(final boolean split) {
        this.split = split;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

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
