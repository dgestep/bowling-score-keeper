package com.dougestep.bowling;

import com.dougestep.bowling.data.BowlingFrame;
import com.dougestep.bowling.data.Game;

/**
 * Defines a class which manages a bowling game.
 *
 * @author dougestep
 */
public interface GameManager {

    /**
     * Adds the supplied bowling frame to the game.
     *
     * @param frame the bowling frame.
     * @return this instance.
     */
    GameManager addFrame(BowlingFrame frame);

    /**
     * Adds the supplied bowling frames to the game.
     *
     * @param frames the bowling frames.
     * @return this instance.
     */
    GameManager addFrames(BowlingFrame[] frames);

    /**
     * Computes the score for each frame in the game.
     *
     * @return this instance.
     */
    GameManager calculateScore();

    /**
     * Replaces the frame located at the supplied frame number with the supplied frame.
     *
     * @param frameNumber locates the frame to replace.
     * @param frame       the corrected frame.
     * @return this instance.
     */
    GameManager replaceFrame(int frameNumber, BowlingFrame frame);

    /**
     * Deletes the frame located at the supplied frame number.
     *
     * @param frameNumber locates the frame to delete.
     * @return this instance.
     */
    GameManager deleteFrame(int frameNumber);

    /**
     * Returns the frame associated with the supplied frame number.
     *
     * @param frameNumber locates the frame to retrieve.
     * @return the frame or null if not found.
     */
    BowlingFrame retrieveFrame(int frameNumber);

    /**
     * Returns the {@link Game} element associated with this instance.
     *
     * @return the game.
     */
    Game getGame();
}
