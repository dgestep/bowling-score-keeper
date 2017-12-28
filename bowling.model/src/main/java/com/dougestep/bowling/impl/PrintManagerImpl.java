package com.dougestep.bowling.impl;

import java.io.PrintStream;
import java.util.List;

import com.dougestep.bowling.PrintManager;
import com.dougestep.bowling.data.Bowler;
import com.dougestep.bowling.data.BowlingFrame;
import com.dougestep.bowling.data.Game;
import com.google.common.base.Strings;
import com.google.common.base.Verify;

/**
 * Manages the printing of a bowling game.
 *
 * @author dougestep
 */
public class PrintManagerImpl implements PrintManager {
    private static final String COL_DELIM = "|";
    private static final int LINE_LENGTH = 147;
    private static final int NAME_PADDING = 20;
    private static final int MARK_LINE_STRIKE_PADDING = 5;
    private static final int MARK_LINE_PADDING = 2;
    private static final int SCORE_LINE_PADDING = 5;
    private static final int SCORE_LINE_CELL_SPACE = 11;
    private static final int SCORE_LINE_TENTH_FRAME_PADDING = 7;
    private static final int SCORE_LINE_TENTH_FRAME_CELL_SPACE = 17;
    private static final String SPARE = "/";
    private static final String STRIKE = "X";
    private static final String BLANK = " ";
    private static final String ZERO_MARK = "-";
    private static final String NON_NULL_MSG = "expected a non-null reference to %s";
    /**
     * 10 points.
     */
    private static final int MARK_1 = 10;

    @Override
    public void printGames(final Game[] games, final PrintStream out) {
        Verify.verifyNotNull(games, NON_NULL_MSG, "List of GameBeans");
        Verify.verifyNotNull(out, NON_NULL_MSG, "OutputStream");

        printHeader(out);

        for (final Game game : games) {
            final String name = getPlayerName(game);
            doPrintGame(name, game, out);
        }
    }

    @Override
    public void printGame(final Game game, final PrintStream out) {
        Verify.verifyNotNull(game, NON_NULL_MSG, "GameBean");
        Verify.verifyNotNull(out, NON_NULL_MSG, "OutputStream");

        printHeader(out);

        final String name = getPlayerName(game);
        doPrintGame(name, game, out);

        out.print("\nGame: ");
        out.println(game.isComplete() ? "Complete" : "Incomplete");
    }

    /**
     * Returns the name of the bowler associated to the supplied game.
     *
     * @param game the game.
     * @return the name or "" if no name is supplied.
     */
    private String getPlayerName(final Game game) {
        final Bowler player = game.getBowler();
        final StringBuilder name = new StringBuilder();
        if (player != null) {
            if (player.getFirstName() != null) {
                name.append(player.getFirstName());
            }
            if (player.getLastName() != null) {
                if (name.length() > 0) {
                    name.append(' ');
                }
                name.append(player.getLastName());
            }
        }
        return name.toString();
    }

    /**
     * Writes the game sheet to the supplied print stream.
     *
     * @param name the bowler name.
     * @param game the game data.
     * @param out  the print stream.
     */
    private void doPrintGame(final String name, final Game game, final PrintStream out) {
        out.println(Strings.repeat("-", LINE_LENGTH));
        out.print(Strings.padEnd(name, NAME_PADDING, ' '));
        out.print(COL_DELIM);

        final List<BowlingFrame> frames = game.getFrames();
        printMarkLine(out, frames);
        printLineSeparator(out, frames);
        printScoreLine(out, frames);
    }

    /**
     * Prints the bowling frame header line.
     *
     * @param out the {@link PrintStream}.
     */
    private void printHeader(final PrintStream out) {
        out.print(Strings.repeat(BLANK, NAME_PADDING));
        for (int i = 1; i <= MARK_1; i++) {
            out.print(COL_DELIM);
            if (i == MARK_1) {
                out.print(Strings.repeat(BLANK, 7));//NOCHECKSTYLE
                out.print("10");
                out.print(Strings.repeat(BLANK, 8));//NOCHECKSTYLE
            } else {
                out.print(Strings.repeat(BLANK, 5));//NOCHECKSTYLE
                out.print(i);
                out.print(Strings.repeat(BLANK, 5));//NOCHECKSTYLE
            }
        }
        out.print(COL_DELIM);
        out.print("\n");
    }

    /**
     * Prints the line that displays the first/second balls.
     *
     * @param out    the {@link PrintStream}.
     * @param frames the list of {@link BowlingFrame} elements.
     */
    private void printMarkLine(final PrintStream out, final List<BowlingFrame> frames) {
        int frameNumber = 0;
        for (final BowlingFrame frame : frames) {
            frameNumber++;
            if (frameNumber < MARK_1) {
                printMarkLinePreTenthFrame(out, frame);
            } else {
                printMarkLineTenthFrame(out, frame, frameNumber);
            }
        }

        final BowlingFrame blankFrame = new BowlingFrame(-1, -1);
        frameNumber++;
        for (; frameNumber <= MARK_1; frameNumber++) {
            if (frameNumber < MARK_1) {
                printMarkLinePreTenthFrame(out, blankFrame);
            } else {
                out.print(Strings.repeat(BLANK, MARK_LINE_PADDING));
                printPinFall(out, blankFrame.getFirstBall(), false);

                printMarkLineTenthFrame(out, blankFrame, frameNumber);
            }
        }
        out.print("\n");
    }

    /**
     * Prints the line that displays the first/second balls for the first 9 frames.
     *
     * @param out   the {@link PrintStream}.
     * @param frame the {@link BowlingFrame}.
     */
    private void printMarkLinePreTenthFrame(final PrintStream out, final BowlingFrame frame) {
        final int firstBall = frame.getFirstBall();
        if (firstBall == MARK_1) {
            out.print(Strings.repeat(BLANK, MARK_LINE_STRIKE_PADDING));
            out.print(STRIKE);
            out.print(Strings.repeat(BLANK, MARK_LINE_STRIKE_PADDING));
            out.print(COL_DELIM);
        } else {
            out.print(Strings.repeat(BLANK, MARK_LINE_PADDING));
            printPinFall(out, firstBall, frame.isSplit());

            out.print(Strings.repeat(BLANK, MARK_LINE_PADDING));
            if (frame.isSpare()) {
                out.print(SPARE);
                out.print(Strings.repeat(BLANK, MARK_LINE_PADDING));
                out.print(COL_DELIM);
            } else {
                printPinFall(out, frame.getSecondBall(), false);
            }
        }
    }

    /**
     * Prints the line that displays the first/second/third balls for the tenth frame.
     *
     * @param out         the {@link PrintStream}.
     * @param frame       the {@link BowlingFrame}.
     * @param frameNumber the frame number.
     */
    private void printMarkLineTenthFrame(final PrintStream out, final BowlingFrame frame, final int frameNumber) {
        final int firstBall = frame.getFirstBall();
        if (firstBall == MARK_1) {
            out.print(Strings.repeat(BLANK, MARK_LINE_PADDING));
            out.print(STRIKE);
            out.print(Strings.repeat(BLANK, MARK_LINE_PADDING));
            out.print(COL_DELIM);
        } else {
            out.print(Strings.repeat(BLANK, MARK_LINE_PADDING));
            printPinFall(out, firstBall, frame.isSplit());

            final int secondBall = frame.getSecondBall();

            if (frameNumber == MARK_1) {
                out.print(Strings.repeat(BLANK, MARK_LINE_PADDING));
                if (frame.isSpare()) {
                    out.print(SPARE);
                    out.print(Strings.repeat(BLANK, MARK_LINE_PADDING));
                    out.print(COL_DELIM);
                } else {
                    printPinFall(out, secondBall, false);
                }

                if (secondBall >= 0 && frame.isOpenFrame()) {
                    out.print(Strings.repeat(BLANK, MARK_LINE_PADDING + 3)); //NOCHECKSTYLE
                    out.print(COL_DELIM);
                }
            }
            if (frameNumber == MARK_1 + 1) {
                out.print(Strings.repeat(BLANK, MARK_LINE_PADDING));
                if (secondBall >= 0 && frame.isSpare()) {
                    out.print(SPARE);
                    out.print(Strings.repeat(BLANK, MARK_LINE_PADDING));
                    out.print(COL_DELIM);
                } else {
                    printPinFall(out, secondBall, frame.isSplit());
                }
            }
        }
    }

    /**
     * Prints the number of pins knocked down.
     *
     * @param out     the {@link PrintStream}.
     * @param pinFall the number of pins knocked down.
     * @param split   supply true to indicate the bowler has a split.
     */
    private void printPinFall(final PrintStream out, final int pinFall, final boolean split) {
        if (pinFall < 0) {
            out.print(BLANK);
        } else if (pinFall == 0) {
            out.print(ZERO_MARK);
        } else {
            if (split) {
                out.print("S");
            }
            out.print(pinFall);
        }
        int padding = MARK_LINE_PADDING;
        if (split) {
            padding--;
        }
        out.print(Strings.repeat(BLANK, padding));
        out.print(COL_DELIM);
    }

    /**
     * Prints the separation line.
     *
     * @param out    the {@link PrintStream}.
     * @param frames the list of {@link BowlingFrame} elements.
     */
    private void printLineSeparator(final PrintStream out, final List<BowlingFrame> frames) {
        out.print(Strings.repeat(BLANK, NAME_PADDING));
        out.print(COL_DELIM);

        int frameNumber = 0;
        for (int i = 0; i < frames.size(); i++) {
            frameNumber++;

            final int cellSpace;
            if (frameNumber > MARK_1) {
                continue;
            } else if (frameNumber == MARK_1) {
                cellSpace = SCORE_LINE_TENTH_FRAME_CELL_SPACE;
            } else {
                cellSpace = SCORE_LINE_CELL_SPACE;
            }

            out.print(Strings.repeat("-", cellSpace));
            out.print(COL_DELIM);
        }
        frameNumber++;
        for (; frameNumber <= MARK_1; frameNumber++) {
            final int cellSpace;
            if (frameNumber == MARK_1) {
                cellSpace = SCORE_LINE_TENTH_FRAME_CELL_SPACE;
            } else {
                cellSpace = SCORE_LINE_CELL_SPACE;
            }
            out.print(Strings.repeat("-", cellSpace));
            out.print(COL_DELIM);
        }
        out.print("\n");
    }

    /**
     * Prints the line that displays the scores for the frames.
     *
     * @param out    the {@link PrintStream}.
     * @param frames the list of {@link BowlingFrame} elements.
     */
    private void printScoreLine(final PrintStream out, final List<BowlingFrame> frames) {
        out.print(Strings.repeat(BLANK, NAME_PADDING));
        out.print(COL_DELIM);

        int frameNumber = 0;
        for (final BowlingFrame frame : frames) {
            frameNumber++;

            final int padding, cellSpace;
            if (frameNumber > MARK_1) {
                continue;
            } else if (frameNumber == MARK_1) {
                cellSpace = SCORE_LINE_TENTH_FRAME_CELL_SPACE;
                padding = SCORE_LINE_TENTH_FRAME_PADDING;
            } else {
                cellSpace = SCORE_LINE_CELL_SPACE;
                padding = SCORE_LINE_PADDING;
            }

            out.print(Strings.repeat(BLANK, padding));
            final String score = String.valueOf(frame.getScore());
            out.print(score);
            final int len = cellSpace - (padding + score.length());
            out.print(Strings.repeat(BLANK, len));
            out.print(COL_DELIM);
        }
        frameNumber++;
        for (; frameNumber <= MARK_1; frameNumber++) {
            final int cellSpace;
            if (frameNumber == MARK_1) {
                cellSpace = SCORE_LINE_TENTH_FRAME_CELL_SPACE;
            } else {
                cellSpace = SCORE_LINE_CELL_SPACE;
            }
            out.print(Strings.repeat(" ", cellSpace));
            out.print(COL_DELIM);
        }
        out.print("\n");
    }
}
