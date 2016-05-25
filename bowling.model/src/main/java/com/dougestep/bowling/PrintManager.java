package com.dougestep.bowling;

import java.io.PrintStream;

import com.dougestep.bowling.data.Game;

/**
 * Defines a class which manages printing a bowling game.
 *
 * @author dougestep
 */
public interface PrintManager {

    /**
     * Prints all supplied games to the supplied output stream.
     * @param games the games.
     * @param out the output stream to print the games to.
     */
    void printGames(Game[] games, PrintStream out);

    /**
     * Prints the supplied game to the supplied output stream.
     * @param game the game.
     * @param out the output stream to print the game to.
     */
    void printGame(Game game, PrintStream out);
}
