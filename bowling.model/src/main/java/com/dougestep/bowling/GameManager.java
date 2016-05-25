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
     * @param frame the bowling frame.
     */
    void addFrame(BowlingFrame frame);

    /**
     * Adds the supplied bowling frames to the game.
     * @param frames the bowling frames.
     */
    void addFrames(BowlingFrame[] frames);
    
    /**
     * Computes the score for each frame in the game.
     */
    void calculateScore();

    /**
     * Replaces the frame located at the supplied frame number with the supplied frame.
     * @param frameNumber locates the frame to replace.
     * @param frame the corrected frame.
     */
    void replaceFrame(int frameNumber, BowlingFrame frame);

    /**
     * Deletes the frame located at the supplied frame number.
     * @param frameNumber locates the frame to delete.
     */
    void deleteFrame(int frameNumber);

    /**
     * Returns the frame associated with the supplied frame number.
     * @param frameNumber locates the frame to retrieve.
     * @return the frame or null if not found.
     */
    BowlingFrame retrieveFrame(int frameNumber);

    /**
     * Returns the {@link Game} element associated wit this instance.
     * @return the game.
     */
    Game getGame();
}
