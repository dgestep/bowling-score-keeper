package com.dougestep.bowling.impl;

import org.junit.Test;

import com.dougestep.bowling.PrintManager;
import com.dougestep.bowling.data.Bowler;
import com.dougestep.bowling.data.BowlingFrame;
import com.dougestep.bowling.data.Game;
import com.google.common.base.VerifyException;

import junit.framework.Assert;

public class GameManagerImplTest {

    @Test
    public void testAddFrameNullFrame() {
        try {
            GameManagerImpl processor = new GameManagerImpl(new Bowler());
            processor.addFrame(null);
        } catch (VerifyException ve) {
            // expected
            System.out.println(ve.getMessage());
        }
    }

    @Test
    public void testAddFrameToHighScore() {
        try {
            GameManagerImpl processor = new GameManagerImpl(new Bowler("Joe", "Morgan"));
            processor.addFrame(new BowlingFrame(8,9));
        } catch (VerifyException ve) {
            // expected
            System.out.println(ve.getMessage());
        }
    }

    @Test
    public void testAddInvalidFirstBall() {
        try {
            GameManagerImpl processor = new GameManagerImpl(new Bowler("Joe", "Morgan"));
            processor.addFrame(new BowlingFrame(11,0));
        } catch (VerifyException ve) {
            // expected
            System.out.println(ve.getMessage());
        }
    }

    @Test
    public void testAddInvalidSecondBall() {
        try {
            GameManagerImpl processor = new GameManagerImpl(new Bowler("Joe", "Morgan"));
            processor.addFrame(new BowlingFrame(0,11));
        } catch (VerifyException ve) {
            // expected
            System.out.println(ve.getMessage());
        }
    }

    @Test
    public void test123OpenSpareStrike() {
        GameManagerImpl processor = new GameManagerImpl(new Bowler("Wilma", "Flintstone"));

        BowlingFrame open1 = new BowlingFrame(8, 1);
        processor.addFrame(open1);
        Assert.assertEquals(9, open1.getScore());
        Assert.assertEquals(9, processor.getGame().getScore());

        BowlingFrame spare2 = new BowlingFrame(8, 2);
        processor.addFrame(spare2);
        Assert.assertEquals(19, spare2.getScore());
        Assert.assertEquals(19, processor.getGame().getScore());

        BowlingFrame strike3 = new BowlingFrame(10, 0);
        processor.addFrame(strike3);

        // 8-1, 8/2, X
        Assert.assertEquals(9, open1.getScore());
        Assert.assertEquals(29, spare2.getScore());
        Assert.assertEquals(39, strike3.getScore());

        Assert.assertEquals(39, processor.getGame().getScore());
        Assert.assertEquals(3, processor.getGame().getNumberOfFrames());
        Assert.assertEquals(open1, processor.getGame().getFrames().get(0));
        Assert.assertEquals(spare2, processor.getGame().getFrames().get(1));
        Assert.assertEquals(strike3, processor.getGame().getFrames().get(2));
        Assert.assertFalse(processor.getGame().isComplete());

        PrintManager printManager = new PrintManagerImpl();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println("\n");
    }

    @Test
    public void test123OpenSpareSpare() {
        GameManagerImpl processor = new GameManagerImpl(new Bowler("Fred", "Flintstone"));

        BowlingFrame open1 = new BowlingFrame(8, 1);
        processor.addFrame(open1);
        Assert.assertEquals(9, open1.getScore());
        Assert.assertEquals(9, processor.getGame().getScore());

        BowlingFrame spare2 = new BowlingFrame(8, 2);
        processor.addFrame(spare2);
        Assert.assertEquals(19, spare2.getScore());
        Assert.assertEquals(19, processor.getGame().getScore());

        BowlingFrame spare3 = new BowlingFrame(9, 1);
        processor.addFrame(spare3);
        Assert.assertEquals(38, processor.getGame().getScore());

        // 8-1, 8/2, 9/1
        Assert.assertEquals(9, open1.getScore());
        Assert.assertEquals(28, spare2.getScore());
        Assert.assertEquals(38, spare3.getScore());
        Assert.assertFalse(processor.getGame().isComplete());

        PrintManager printManager = new PrintManagerImpl();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println("\n");
    }

    @Test
    public void test123OpenSpareOpen() {
        GameManagerImpl processor = new GameManagerImpl(new Bowler("Barney", "Rubble"));

        BowlingFrame open1 = new BowlingFrame(8, 1);
        processor.addFrame(open1);
        Assert.assertEquals(9, open1.getScore());
        Assert.assertEquals(9, processor.getGame().getScore());

        BowlingFrame spare2 = new BowlingFrame(8, 2);
        processor.addFrame(spare2);
        Assert.assertEquals(19, spare2.getScore());
        Assert.assertEquals(19, processor.getGame().getScore());

        BowlingFrame open3 = new BowlingFrame(9, 0);
        processor.addFrame(open3);
        Assert.assertEquals(37, processor.getGame().getScore());

        Assert.assertEquals(9, open1.getScore());
        Assert.assertEquals(28, spare2.getScore());
        Assert.assertEquals(37, open3.getScore());

        // 8-1, 8/2, 9-0
        Assert.assertEquals(37, processor.getGame().getScore());
        Assert.assertFalse(processor.getGame().isComplete());

        PrintManager printManager = new PrintManagerImpl();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println("\n");
    }

    @Test
    public void test123StrikeStrikeStrike() {
        GameManagerImpl processor = new GameManagerImpl(new Bowler("Betty", "Rubble"));

        BowlingFrame strike1 = new BowlingFrame(10);
        processor.addFrame(strike1);
        Assert.assertEquals(10, strike1.getScore());
        Assert.assertEquals(10, processor.getGame().getScore());

        BowlingFrame strike2 = new BowlingFrame(10);
        processor.addFrame(strike2);
        Assert.assertEquals(30, strike2.getScore());
        Assert.assertEquals(30, processor.getGame().getScore());

        BowlingFrame strike3 = new BowlingFrame(10);
        processor.addFrame(strike3);
        Assert.assertEquals(60, processor.getGame().getScore());

        // X, X, X
        Assert.assertEquals(30, strike1.getScore());
        Assert.assertEquals(50, strike2.getScore());
        Assert.assertEquals(60, strike3.getScore());

        Assert.assertEquals(60, processor.getGame().getScore());
        Assert.assertFalse(processor.getGame().isComplete());

        PrintManager printManager = new PrintManagerImpl();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println("\n");
    }

    @Test
    public void test12345SpareStrikeStrikeSpareOpen() {
        GameManagerImpl processor = new GameManagerImpl(new Bowler("Spock", "Jenkins"));

        BowlingFrame spare1 = new BowlingFrame(7, 3);
        processor.addFrame(spare1);
        Assert.assertEquals(10, spare1.getScore());
        Assert.assertEquals(10, processor.getGame().getScore());

        BowlingFrame strike2 = new BowlingFrame(10);
        processor.addFrame(strike2);
        Assert.assertEquals(20, spare1.getScore());
        Assert.assertEquals(30, strike2.getScore());
        Assert.assertEquals(30, processor.getGame().getScore());

        BowlingFrame strike3 = new BowlingFrame(10);
        processor.addFrame(strike3);
        Assert.assertEquals(50, strike3.getScore());
        Assert.assertEquals(50, processor.getGame().getScore());

        BowlingFrame spare4 = new BowlingFrame(8, 2);
        processor.addFrame(spare4);
        Assert.assertEquals(48, strike2.getScore());
        Assert.assertEquals(68, strike3.getScore());
        Assert.assertEquals(78, spare4.getScore());
        Assert.assertEquals(78, processor.getGame().getScore());

        BowlingFrame open5 = new BowlingFrame(8, 0);
        processor.addFrame(open5);

        // 7/3, X, X, 8/2, 8-0
        Assert.assertEquals(20, spare1.getScore());
        Assert.assertEquals(48, strike2.getScore());
        Assert.assertEquals(68, strike3.getScore());
        Assert.assertEquals(86, spare4.getScore());
        Assert.assertEquals(94, open5.getScore());

        Assert.assertEquals(94, processor.getGame().getScore());
        Assert.assertFalse(processor.getGame().isComplete());

        PrintManager printManager = new PrintManagerImpl();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println("\n");
    }

    @Test
    public void test12345StrikesAndOpen() {
        GameManagerImpl processor = new GameManagerImpl(new Bowler("Scotty", "Blah"));

        BowlingFrame strike1 = new BowlingFrame(10);
        processor.addFrame(strike1);
        Assert.assertEquals(10, strike1.getScore());
        Assert.assertEquals(10, processor.getGame().getScore());

        BowlingFrame strike2 = new BowlingFrame(10);
        processor.addFrame(strike2);
        Assert.assertEquals(20, strike1.getScore());
        Assert.assertEquals(30, strike2.getScore());
        Assert.assertEquals(30, processor.getGame().getScore());

        BowlingFrame strike3 = new BowlingFrame(10);
        processor.addFrame(strike3);
        Assert.assertEquals(30, strike1.getScore());
        Assert.assertEquals(50, strike2.getScore());
        Assert.assertEquals(60, strike3.getScore());
        Assert.assertEquals(60, processor.getGame().getScore());

        BowlingFrame strike4 = new BowlingFrame(10);
        processor.addFrame(strike4);
        Assert.assertEquals(30, strike1.getScore());
        Assert.assertEquals(60, strike2.getScore());
        Assert.assertEquals(80, strike3.getScore());
        Assert.assertEquals(90, strike4.getScore());
        Assert.assertEquals(90, processor.getGame().getScore());

        BowlingFrame open5 = new BowlingFrame(8, 0);
        processor.addFrame(open5);

        // X, X, X, X, 8-0
        Assert.assertEquals(30, strike1.getScore());
        Assert.assertEquals(60, strike2.getScore());
        Assert.assertEquals(88, strike3.getScore());
        Assert.assertEquals(106, strike4.getScore());
        Assert.assertEquals(114, processor.getGame().getScore());

        Assert.assertEquals(114, processor.getGame().getScore());
        Assert.assertFalse(processor.getGame().isComplete());

        PrintManager printManager = new PrintManagerImpl();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println("\n");
    }

    @Test
    public void testAllStrikesOpenInFifth() {
        GameManagerImpl processor = new GameManagerImpl(new Bowler("Leonard", "McCoy"));
        BowlingFrame frame1 = new BowlingFrame(10);
        BowlingFrame frame2 = new BowlingFrame(10);
        BowlingFrame frame3 = new BowlingFrame(10);
        BowlingFrame frame4 = new BowlingFrame(10);
        BowlingFrame frame5 = new BowlingFrame(8, 0);
        BowlingFrame frame6 = new BowlingFrame(10);
        BowlingFrame frame7 = new BowlingFrame(10);
        BowlingFrame frame8 = new BowlingFrame(10);
        BowlingFrame frame9 = new BowlingFrame(10);
        BowlingFrame frame10 = new BowlingFrame(10);
        BowlingFrame frame11 = new BowlingFrame(10);
        BowlingFrame frame12 = new BowlingFrame(10);

        BowlingFrame[] frames = { frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, frame11, frame12 };
        processor.addFrames(frames);
        Assert.assertEquals(30, frame1.getScore());
        Assert.assertEquals(60, frame2.getScore());
        Assert.assertEquals(88, frame3.getScore());
        Assert.assertEquals(106, frame4.getScore());
        Assert.assertEquals(114, frame5.getScore());
        Assert.assertEquals(144, frame6.getScore());
        Assert.assertEquals(174, frame7.getScore());
        Assert.assertEquals(204, frame8.getScore());
        Assert.assertEquals(234, frame9.getScore());
        Assert.assertEquals(264, frame10.getScore());

        Assert.assertEquals(264, processor.getGame().getScore());
        Assert.assertTrue(processor.getGame().isComplete());

        PrintManager printManager = new PrintManagerImpl();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println("\n");
    }

    @Test
    public void testAllStrikesOpenInTenth() {
        GameManagerImpl processor = new GameManagerImpl(new Bowler("Bam Bam", "Rubble"));
        BowlingFrame frame1 = new BowlingFrame(10);
        BowlingFrame frame2 = new BowlingFrame(10);
        BowlingFrame frame3 = new BowlingFrame(10);
        BowlingFrame frame4 = new BowlingFrame(10);
        BowlingFrame frame5 = new BowlingFrame(10);
        BowlingFrame frame6 = new BowlingFrame(10);
        BowlingFrame frame7 = new BowlingFrame(10);
        BowlingFrame frame8 = new BowlingFrame(10);
        BowlingFrame frame9 = new BowlingFrame(10);
        BowlingFrame frame10 = new BowlingFrame(9, 0);

        BowlingFrame[] frames = { frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10 };
        processor.addFrames(frames);
        Assert.assertEquals(30, frame1.getScore());
        Assert.assertEquals(60, frame2.getScore());
        Assert.assertEquals(90, frame3.getScore());
        Assert.assertEquals(120, frame4.getScore());
        Assert.assertEquals(150, frame5.getScore());
        Assert.assertEquals(180, frame6.getScore());
        Assert.assertEquals(210, frame7.getScore());
        Assert.assertEquals(239, frame8.getScore());
        Assert.assertEquals(258, frame9.getScore());
        Assert.assertEquals(267, frame10.getScore());

        Assert.assertEquals(267, processor.getGame().getScore());
        Assert.assertTrue(processor.getGame().isComplete());

        PrintManager printManager = new PrintManagerImpl();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println("\n");
    }

    @Test
    public void testAllStrikesStrikeSpareInTenth() {
        GameManagerImpl processor = new GameManagerImpl(new Bowler("Pebbles", "Flintstone"));
        BowlingFrame frame1 = new BowlingFrame(10);
        BowlingFrame frame2 = new BowlingFrame(10);
        BowlingFrame frame3 = new BowlingFrame(10);
        BowlingFrame frame4 = new BowlingFrame(10);
        BowlingFrame frame5 = new BowlingFrame(10);
        BowlingFrame frame6 = new BowlingFrame(10);
        BowlingFrame frame7 = new BowlingFrame(10);
        BowlingFrame frame8 = new BowlingFrame(10);
        BowlingFrame frame9 = new BowlingFrame(10);
        BowlingFrame frame10 = new BowlingFrame(10);
        BowlingFrame frame11 = new BowlingFrame(8, 2, true);

        BowlingFrame[] frames = { frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, frame11 };
        processor.addFrames(frames);
        Assert.assertEquals(30, frame1.getScore());
        Assert.assertEquals(60, frame2.getScore());
        Assert.assertEquals(90, frame3.getScore());
        Assert.assertEquals(120, frame4.getScore());
        Assert.assertEquals(150, frame5.getScore());
        Assert.assertEquals(180, frame6.getScore());
        Assert.assertEquals(210, frame7.getScore());
        Assert.assertEquals(240, frame8.getScore());
        Assert.assertEquals(268, frame9.getScore());
        Assert.assertEquals(288, frame10.getScore());

        Assert.assertEquals(288, processor.getGame().getScore());
        Assert.assertTrue(processor.getGame().isComplete());

        PrintManager printManager = new PrintManagerImpl();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println("\n");
    }

    @Test
    public void testAllStrikesSpareStrikeInTenth() {
        GameManagerImpl processor = new GameManagerImpl(new Bowler("Robert", "Barone"));
        BowlingFrame frame1 = new BowlingFrame(10);
        BowlingFrame frame2 = new BowlingFrame(10);
        BowlingFrame frame3 = new BowlingFrame(10);
        BowlingFrame frame4 = new BowlingFrame(10);
        BowlingFrame frame5 = new BowlingFrame(10);
        BowlingFrame frame6 = new BowlingFrame(10);
        BowlingFrame frame7 = new BowlingFrame(10);
        BowlingFrame frame8 = new BowlingFrame(10);
        BowlingFrame frame9 = new BowlingFrame(10);
        BowlingFrame frame10 = new BowlingFrame(9, 1);
        BowlingFrame frame11 = new BowlingFrame(10);

        BowlingFrame[] frames = { frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, frame11 };
        processor.addFrames(frames);
        Assert.assertEquals(30, frame1.getScore());
        Assert.assertEquals(60, frame2.getScore());
        Assert.assertEquals(90, frame3.getScore());
        Assert.assertEquals(120, frame4.getScore());
        Assert.assertEquals(150, frame5.getScore());
        Assert.assertEquals(180, frame6.getScore());
        Assert.assertEquals(210, frame7.getScore());
        Assert.assertEquals(239, frame8.getScore());
        Assert.assertEquals(259, frame9.getScore());
        Assert.assertEquals(279, frame10.getScore());

        Assert.assertEquals(279, processor.getGame().getScore());
        Assert.assertTrue(processor.getGame().isComplete());

        PrintManager printManager = new PrintManagerImpl();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println("\n");
    }

    @Test
    public void testAllStrikes() {
        GameManagerImpl processor = new GameManagerImpl(new Bowler("Raymond", "Barone"));
        BowlingFrame frame1 = new BowlingFrame(10);
        BowlingFrame frame2 = new BowlingFrame(10);
        BowlingFrame frame3 = new BowlingFrame(10);
        BowlingFrame frame4 = new BowlingFrame(10);
        BowlingFrame frame5 = new BowlingFrame(10);
        BowlingFrame frame6 = new BowlingFrame(10);
        BowlingFrame frame7 = new BowlingFrame(10);
        BowlingFrame frame8 = new BowlingFrame(10);
        BowlingFrame frame9 = new BowlingFrame(10);
        BowlingFrame frame10 = new BowlingFrame(10);
        BowlingFrame frame11 = new BowlingFrame(10);
        BowlingFrame frame12 = new BowlingFrame(10);

        BowlingFrame[] frames = { frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, frame11, frame12 };
        processor.addFrames(frames);
        Assert.assertEquals(30, frame1.getScore());
        Assert.assertEquals(60, frame2.getScore());
        Assert.assertEquals(90, frame3.getScore());
        Assert.assertEquals(120, frame4.getScore());
        Assert.assertEquals(150, frame5.getScore());
        Assert.assertEquals(180, frame6.getScore());
        Assert.assertEquals(210, frame7.getScore());
        Assert.assertEquals(240, frame8.getScore());
        Assert.assertEquals(270, frame9.getScore());
        Assert.assertEquals(300, frame10.getScore());

        Assert.assertEquals(300, processor.getGame().getScore());
        Assert.assertTrue(processor.getGame().isComplete());

        PrintManager printManager = new PrintManagerImpl();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println("\n");
    }

    @Test
    public void testAllStrikeNineSpare() {
        GameManagerImpl processor = new GameManagerImpl(new Bowler("James", "Kirk"));
        BowlingFrame frame1 = new BowlingFrame(10);
        BowlingFrame frame2 = new BowlingFrame(9, 1);
        BowlingFrame frame3 = new BowlingFrame(10);
        BowlingFrame frame4 = new BowlingFrame(9, 1);
        BowlingFrame frame5 = new BowlingFrame(10);
        BowlingFrame frame6 = new BowlingFrame(9, 1);
        BowlingFrame frame7 = new BowlingFrame(10);
        BowlingFrame frame8 = new BowlingFrame(9, 1);
        BowlingFrame frame9 = new BowlingFrame(10);
        BowlingFrame frame10 = new BowlingFrame(9, 1);
        BowlingFrame frame11 = new BowlingFrame(10);

        BowlingFrame[] frames = { frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, frame11 };
        processor.addFrames(frames);
        Assert.assertEquals(20, frame1.getScore());
        Assert.assertEquals(40, frame2.getScore());
        Assert.assertEquals(60, frame3.getScore());
        Assert.assertEquals(80, frame4.getScore());
        Assert.assertEquals(100, frame5.getScore());
        Assert.assertEquals(120, frame6.getScore());
        Assert.assertEquals(140, frame7.getScore());
        Assert.assertEquals(160, frame8.getScore());
        Assert.assertEquals(180, frame9.getScore());
        Assert.assertEquals(200, frame10.getScore());

        Assert.assertEquals(200, processor.getGame().getScore());
        Assert.assertTrue(processor.getGame().isComplete());

        PrintManager printManager = new PrintManagerImpl();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println("\n");
    }

    @Test
    public void testAllGutterBalls() {
        GameManagerImpl processor = new GameManagerImpl(new Bowler("Marie", "Barone"));
        BowlingFrame frame1 = new BowlingFrame();
        BowlingFrame frame2 = new BowlingFrame();
        BowlingFrame frame3 = new BowlingFrame();
        BowlingFrame frame4 = new BowlingFrame();
        BowlingFrame frame5 = new BowlingFrame();
        BowlingFrame frame6 = new BowlingFrame();
        BowlingFrame frame7 = new BowlingFrame();
        BowlingFrame frame8 = new BowlingFrame();
        BowlingFrame frame9 = new BowlingFrame();
        BowlingFrame frame10 = new BowlingFrame();

        BowlingFrame[] frames = { frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10 };
        processor.addFrames(frames);
        Assert.assertEquals(0, frame1.getScore());
        Assert.assertEquals(0, frame2.getScore());
        Assert.assertEquals(0, frame3.getScore());
        Assert.assertEquals(0, frame4.getScore());
        Assert.assertEquals(0, frame5.getScore());
        Assert.assertEquals(0, frame6.getScore());
        Assert.assertEquals(0, frame7.getScore());
        Assert.assertEquals(0, frame8.getScore());
        Assert.assertEquals(0, frame9.getScore());
        Assert.assertEquals(0, frame10.getScore());

        Assert.assertEquals(0, processor.getGame().getScore());
        Assert.assertTrue(processor.getGame().isComplete());

        PrintManager printManager = new PrintManagerImpl();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println("\n");
    }

    @Test
    public void testAllGutterBallsStrikeOutInTenth() {
        GameManagerImpl processor = new GameManagerImpl(new Bowler("Frank", "Barone"));
        BowlingFrame frame1 = new BowlingFrame();
        BowlingFrame frame2 = new BowlingFrame();
        BowlingFrame frame3 = new BowlingFrame();
        BowlingFrame frame4 = new BowlingFrame();
        BowlingFrame frame5 = new BowlingFrame();
        BowlingFrame frame6 = new BowlingFrame();
        BowlingFrame frame7 = new BowlingFrame();
        BowlingFrame frame8 = new BowlingFrame();
        BowlingFrame frame9 = new BowlingFrame();
        BowlingFrame frame10 = new BowlingFrame(10);
        BowlingFrame frame11 = new BowlingFrame(10);
        BowlingFrame frame12 = new BowlingFrame(10);

        BowlingFrame[] frames = { frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, frame11, frame12 };
        processor.addFrames(frames);
        Assert.assertEquals(0, frame1.getScore());
        Assert.assertEquals(0, frame2.getScore());
        Assert.assertEquals(0, frame3.getScore());
        Assert.assertEquals(0, frame4.getScore());
        Assert.assertEquals(0, frame5.getScore());
        Assert.assertEquals(0, frame6.getScore());
        Assert.assertEquals(0, frame7.getScore());
        Assert.assertEquals(0, frame8.getScore());
        Assert.assertEquals(0, frame9.getScore());
        Assert.assertEquals(30, frame10.getScore());

        Assert.assertEquals(30, processor.getGame().getScore());
        Assert.assertTrue(processor.getGame().isComplete());

        PrintManager printManager = new PrintManagerImpl();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println("\n");
    }

    @Test
    public void testAllStrikeFirstFrameAllGutterBallsStrikeOutInTenth() {
        GameManagerImpl processor = new GameManagerImpl(new Bowler("Alley", "Barone"));
        BowlingFrame frame1 = new BowlingFrame(10);
        BowlingFrame frame2 = new BowlingFrame();
        BowlingFrame frame3 = new BowlingFrame();
        BowlingFrame frame4 = new BowlingFrame();
        BowlingFrame frame5 = new BowlingFrame();
        BowlingFrame frame6 = new BowlingFrame();
        BowlingFrame frame7 = new BowlingFrame();
        BowlingFrame frame8 = new BowlingFrame();
        BowlingFrame frame9 = new BowlingFrame();
        BowlingFrame frame10 = new BowlingFrame(10);
        BowlingFrame frame11 = new BowlingFrame(10);
        BowlingFrame frame12 = new BowlingFrame(10);

        BowlingFrame[] frames = { frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, frame11, frame12 };
        processor.addFrames(frames);
        Assert.assertEquals(10, frame1.getScore());
        Assert.assertEquals(10, frame2.getScore());
        Assert.assertEquals(10, frame3.getScore());
        Assert.assertEquals(10, frame4.getScore());
        Assert.assertEquals(10, frame5.getScore());
        Assert.assertEquals(10, frame6.getScore());
        Assert.assertEquals(10, frame7.getScore());
        Assert.assertEquals(10, frame8.getScore());
        Assert.assertEquals(10, frame9.getScore());
        Assert.assertEquals(40, frame10.getScore());

        Assert.assertEquals(40, processor.getGame().getScore());
        Assert.assertTrue(processor.getGame().isComplete());

        PrintManager printManager = new PrintManagerImpl();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println("\n");
    }

    @Test
    public void testAllStrikeNineSpareInFifth() {
        GameManagerImpl processor = new GameManagerImpl(new Bowler("Sam", "Estep"));
        BowlingFrame frame1 = new BowlingFrame(10);
        BowlingFrame frame2 = new BowlingFrame(10);
        BowlingFrame frame3 = new BowlingFrame(10);
        BowlingFrame frame4 = new BowlingFrame(10);
        BowlingFrame frame5 = new BowlingFrame(9, 1);
        BowlingFrame frame6 = new BowlingFrame(10);
        BowlingFrame frame7 = new BowlingFrame(10);
        BowlingFrame frame8 = new BowlingFrame(10);
        BowlingFrame frame9 = new BowlingFrame(10);
        BowlingFrame frame10 = new BowlingFrame(10);
        BowlingFrame frame11 = new BowlingFrame(10);
        BowlingFrame frame12 = new BowlingFrame(10);

        BowlingFrame[] frames = { frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, frame11, frame12 };
        processor.addFrames(frames);
        Assert.assertEquals(30, frame1.getScore());
        Assert.assertEquals(60, frame2.getScore());
        Assert.assertEquals(89, frame3.getScore());
        Assert.assertEquals(109, frame4.getScore());
        Assert.assertEquals(129, frame5.getScore());
        Assert.assertEquals(159, frame6.getScore());
        Assert.assertEquals(189, frame7.getScore());
        Assert.assertEquals(219, frame8.getScore());
        Assert.assertEquals(249, frame9.getScore());
        Assert.assertEquals(279, frame10.getScore());

        Assert.assertEquals(279, processor.getGame().getScore());
        Assert.assertTrue(processor.getGame().isComplete());

        PrintManager printManager = new PrintManagerImpl();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println("\n");
    }

    @Test
    public void testPrintMultipleGames() {
        GameManagerImpl game1 = new GameManagerImpl(new Bowler("Christene", "Kuhn"));
        game1.addFrames(new BowlingFrame[] { new BowlingFrame(0, 2), new BowlingFrame(8, 1), new BowlingFrame(7, 2), new BowlingFrame(10), new BowlingFrame(5, 2), new BowlingFrame(9, 0), new BowlingFrame(6, 3), new BowlingFrame(7, 3), new BowlingFrame(9, 0), new BowlingFrame(8, 1) });

        GameManagerImpl game2 = new GameManagerImpl(new Bowler("Chris", "Kuhn"));
        game2.addFrames(new BowlingFrame[] { new BowlingFrame(10), new BowlingFrame(10), new BowlingFrame(7, 2), new BowlingFrame(8, 0), new BowlingFrame(9, 1), new BowlingFrame(9, 1), new BowlingFrame(6, 3), new BowlingFrame(7, 3), new BowlingFrame(9, 0), new BowlingFrame(8, 2, true), new BowlingFrame(10) });

        GameManagerImpl game3 = new GameManagerImpl(new Bowler("Gwen", "Estep"));
        game3.addFrames(new BowlingFrame[] { new BowlingFrame(8, 1), new BowlingFrame(9, 0), new BowlingFrame(10), new BowlingFrame(6, 2), new BowlingFrame(7, 2), new BowlingFrame(7, 3), new BowlingFrame(3, 5), new BowlingFrame(0, 7), new BowlingFrame(9, 1), new BowlingFrame(10), new BowlingFrame(10), new BowlingFrame(6) });

        GameManagerImpl game4 = new GameManagerImpl(new Bowler("Doug", "Estep"));
        game4.addFrames(new BowlingFrame[] { new BowlingFrame(10), new BowlingFrame(10), new BowlingFrame(10), new BowlingFrame(9, 1), new BowlingFrame(9, 1), new BowlingFrame(8, 1, true), new BowlingFrame(8, 2), new BowlingFrame(9, 1), new BowlingFrame(9, 1), new BowlingFrame(10), new BowlingFrame(10), new BowlingFrame(9) });

        PrintManager printManager = new PrintManagerImpl();
        Game[] games = { game1.getGame(), game2.getGame(), game3.getGame(), game4.getGame() };
        printManager.printGames(games, System.out);
        System.out.println("\n");
    }

    @Test
    public void test12OpenSpareCorrectSpareToOpen() {
        GameManagerImpl processor = new GameManagerImpl(new Bowler("Natalie", "Estep"));

        BowlingFrame open1 = new BowlingFrame(8, 1, true);
        processor.addFrame(open1);
        Assert.assertEquals(9, open1.getScore());
        Assert.assertEquals(9, processor.getGame().getScore());

        BowlingFrame spare2 = new BowlingFrame(8, 2);
        processor.addFrame(spare2);
        Assert.assertEquals(19, spare2.getScore());
        Assert.assertEquals(19, processor.getGame().getScore());

        BowlingFrame correctedSpare = new BowlingFrame(8, 1);
        processor.replaceFrame(2, correctedSpare);

        Assert.assertEquals(18, processor.getGame().getScore());
        Assert.assertEquals(2, processor.getGame().getNumberOfFrames());

        Assert.assertEquals(open1, processor.getGame().getFrames().get(0));
        Assert.assertEquals(correctedSpare, processor.getGame().getFrames().get(1));

        PrintManager printManager = new PrintManagerImpl();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println("\n");
    }

    @Test
    public void test12OpenSpareRemoveSpare() {
        GameManagerImpl processor = new GameManagerImpl(new Bowler("Gwen", "Estep"));

        BowlingFrame open1 = new BowlingFrame(8, 1);
        processor.addFrame(open1);
        Assert.assertEquals(9, open1.getScore());
        Assert.assertEquals(9, processor.getGame().getScore());

        BowlingFrame spare2 = new BowlingFrame(8, 2);
        processor.addFrame(spare2);
        Assert.assertEquals(19, spare2.getScore());
        Assert.assertEquals(19, processor.getGame().getScore());

        processor.deleteFrame(2);
        Assert.assertNull(processor.retrieveFrame(2));

        Assert.assertEquals(9, processor.getGame().getScore());
        Assert.assertEquals(1, processor.getGame().getNumberOfFrames());

        Assert.assertEquals(open1, processor.getGame().getFrames().get(0));

        PrintManager printManager = new PrintManagerImpl();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println("\n");
    }
}
