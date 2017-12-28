package com.dougestep.bowling.impl;

import com.dougestep.bowling.GameManager;
import com.dougestep.bowling.PrintManager;
import com.dougestep.bowling.data.Bowler;
import com.dougestep.bowling.data.BowlingFrame;
import com.dougestep.bowling.data.Game;
import com.google.common.base.VerifyException;
import org.junit.Assert;
import org.junit.Test;

public class GameManagerImplTest {
    private static final String NL = System.getProperty("line.separator");

    @Test
    public void testAddFrameNullFrame() {
        try {
            GameManager processor = GameManagerImpl.newGame(new Bowler());
            processor.addFrame(null);
        } catch (VerifyException ve) {
            // expected
            System.out.println(ve.getMessage());
        }
    }

    @Test
    public void testAddFrameToHighScore() {
        try {
            Bowler bowler = new Bowler().setFirstName("Joe").setLastName("Morgan");
            GameManager processor = GameManagerImpl.newGame(bowler);
            processor.addFrame(new BowlingFrame().setFirstBall(8).setSecondBall(9));
        } catch (VerifyException ve) {
            // expected
            System.out.println(ve.getMessage());
        }
    }

    @Test
    public void testAddInvalidFirstBall() {
        try {
            Bowler bowler = new Bowler().setFirstName("Joe").setLastName("Morgan");
            GameManager processor = GameManagerImpl.newGame(bowler);
            processor.addFrame(new BowlingFrame().setFirstBall(11).setSecondBall(0));
        } catch (VerifyException ve) {
            // expected
            System.out.println(ve.getMessage());
        }
    }

    @Test
    public void testAddInvalidSecondBall() {
        try {
            Bowler bowler = new Bowler().setFirstName("Joe").setLastName("Morgan");
            GameManager processor = GameManagerImpl.newGame(bowler);
            processor.addFrame(new BowlingFrame().setFirstBall(0).setSecondBall(11));
        } catch (VerifyException ve) {
            // expected
            System.out.println(ve.getMessage());
        }
    }

    @Test
    public void test123OpenSpareStrike() {
        Bowler bowler = new Bowler().setFirstName("Wilma").setLastName("Flintstone");
        GameManager processor = GameManagerImpl.newGame(bowler);

        BowlingFrame open1 = new BowlingFrame().setFirstBall(8).setSecondBall(1);
        processor.addFrame(open1);
        Assert.assertEquals(9, open1.getScore());
        Assert.assertEquals(9, processor.getGame().getScore());

        BowlingFrame spare2 = new BowlingFrame().setFirstBall(8).setSecondBall(2);
        processor.addFrame(spare2);
        Assert.assertEquals(19, spare2.getScore());
        Assert.assertEquals(19, processor.getGame().getScore());

        BowlingFrame strike3 = BowlingFrame.strike();
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

        PrintManager printManager = PrintManagerImpl.newInstance();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println(NL);
    }

    @Test
    public void test123OpenSpareSpare() {
        Bowler bowler = new Bowler().setFirstName("Fred").setLastName("Flintstone");
        GameManager processor = GameManagerImpl.newGame(bowler);

        BowlingFrame open1 = new BowlingFrame().setFirstBall(8).setSecondBall(1);
        processor.addFrame(open1);
        Assert.assertEquals(9, open1.getScore());
        Assert.assertEquals(9, processor.getGame().getScore());

        BowlingFrame spare2 = new BowlingFrame().setFirstBall(8).setSecondBall(2);
        processor.addFrame(spare2);
        Assert.assertEquals(19, spare2.getScore());
        Assert.assertEquals(19, processor.getGame().getScore());

        BowlingFrame spare3 = new BowlingFrame().setFirstBall(9).setSecondBall(1);
        processor.addFrame(spare3);
        Assert.assertEquals(38, processor.getGame().getScore());

        // 8-1, 8/2, 9/1
        Assert.assertEquals(9, open1.getScore());
        Assert.assertEquals(28, spare2.getScore());
        Assert.assertEquals(38, spare3.getScore());
        Assert.assertFalse(processor.getGame().isComplete());

        PrintManager printManager = PrintManagerImpl.newInstance();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println(NL);
    }

    @Test
    public void test123OpenSpareOpen() {
        Bowler bowler = new Bowler().setFirstName("Barney").setLastName("Rubble");
        GameManager processor = GameManagerImpl.newGame(bowler);

        BowlingFrame open1 = new BowlingFrame().setFirstBall(8).setSecondBall(1);
        processor.addFrame(open1);
        Assert.assertEquals(9, open1.getScore());
        Assert.assertEquals(9, processor.getGame().getScore());

        BowlingFrame spare2 = new BowlingFrame().setFirstBall(8).setSecondBall(2);
        processor.addFrame(spare2);
        Assert.assertEquals(19, spare2.getScore());
        Assert.assertEquals(19, processor.getGame().getScore());

        BowlingFrame open3 = new BowlingFrame().setFirstBall(9).setSecondBall(0);
        processor.addFrame(open3);
        Assert.assertEquals(37, processor.getGame().getScore());

        Assert.assertEquals(9, open1.getScore());
        Assert.assertEquals(28, spare2.getScore());
        Assert.assertEquals(37, open3.getScore());

        // 8-1, 8/2, 9-0
        Assert.assertEquals(37, processor.getGame().getScore());
        Assert.assertFalse(processor.getGame().isComplete());

        PrintManager printManager = PrintManagerImpl.newInstance();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println(NL);
    }

    @Test
    public void test123StrikeStrikeStrike() {
        Bowler bowler = new Bowler().setFirstName("Betty").setLastName("Rubble");
        GameManager processor = GameManagerImpl.newGame(bowler);

        BowlingFrame strike1 = BowlingFrame.strike();
        processor.addFrame(strike1);
        Assert.assertEquals(10, strike1.getScore());
        Assert.assertEquals(10, processor.getGame().getScore());

        BowlingFrame strike2 = BowlingFrame.strike();
        processor.addFrame(strike2);
        Assert.assertEquals(30, strike2.getScore());
        Assert.assertEquals(30, processor.getGame().getScore());

        BowlingFrame strike3 = BowlingFrame.strike();
        processor.addFrame(strike3);
        Assert.assertEquals(60, processor.getGame().getScore());

        // X, X, X
        Assert.assertEquals(30, strike1.getScore());
        Assert.assertEquals(50, strike2.getScore());
        Assert.assertEquals(60, strike3.getScore());

        Assert.assertEquals(60, processor.getGame().getScore());
        Assert.assertFalse(processor.getGame().isComplete());

        PrintManager printManager = PrintManagerImpl.newInstance();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println(NL);
    }

    @Test
    public void test12345SpareStrikeStrikeSpareOpen() {
        Bowler bowler = new Bowler().setFirstName("Spock").setLastName("Jenkins");
        GameManager processor = GameManagerImpl.newGame(bowler);

        BowlingFrame spare1 = new BowlingFrame().setFirstBall(7).setSecondBall(3);
        processor.addFrame(spare1);
        Assert.assertEquals(10, spare1.getScore());
        Assert.assertEquals(10, processor.getGame().getScore());

        BowlingFrame strike2 = BowlingFrame.strike();
        processor.addFrame(strike2);
        Assert.assertEquals(20, spare1.getScore());
        Assert.assertEquals(30, strike2.getScore());
        Assert.assertEquals(30, processor.getGame().getScore());

        BowlingFrame strike3 = BowlingFrame.strike();
        processor.addFrame(strike3);
        Assert.assertEquals(50, strike3.getScore());
        Assert.assertEquals(50, processor.getGame().getScore());

        BowlingFrame spare4 = new BowlingFrame().setFirstBall(8).setSecondBall(2);
        processor.addFrame(spare4);
        Assert.assertEquals(48, strike2.getScore());
        Assert.assertEquals(68, strike3.getScore());
        Assert.assertEquals(78, spare4.getScore());
        Assert.assertEquals(78, processor.getGame().getScore());

        BowlingFrame open5 = new BowlingFrame().setFirstBall(8).setSecondBall(0);
        processor.addFrame(open5);

        // 7/3, X, X, 8/2, 8-0
        Assert.assertEquals(20, spare1.getScore());
        Assert.assertEquals(48, strike2.getScore());
        Assert.assertEquals(68, strike3.getScore());
        Assert.assertEquals(86, spare4.getScore());
        Assert.assertEquals(94, open5.getScore());

        Assert.assertEquals(94, processor.getGame().getScore());
        Assert.assertFalse(processor.getGame().isComplete());

        PrintManager printManager = PrintManagerImpl.newInstance();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println(NL);
    }

    @Test
    public void test12345StrikesAndOpen() {
        Bowler bowler = new Bowler().setFirstName("Scotty").setLastName("Blah");
        GameManager processor = GameManagerImpl.newGame(bowler);

        BowlingFrame strike1 = BowlingFrame.strike();
        processor.addFrame(strike1);
        Assert.assertEquals(10, strike1.getScore());
        Assert.assertEquals(10, processor.getGame().getScore());

        BowlingFrame strike2 = BowlingFrame.strike();
        processor.addFrame(strike2);
        Assert.assertEquals(20, strike1.getScore());
        Assert.assertEquals(30, strike2.getScore());
        Assert.assertEquals(30, processor.getGame().getScore());

        BowlingFrame strike3 = BowlingFrame.strike();
        processor.addFrame(strike3);
        Assert.assertEquals(30, strike1.getScore());
        Assert.assertEquals(50, strike2.getScore());
        Assert.assertEquals(60, strike3.getScore());
        Assert.assertEquals(60, processor.getGame().getScore());

        BowlingFrame strike4 = BowlingFrame.strike();
        processor.addFrame(strike4);
        Assert.assertEquals(30, strike1.getScore());
        Assert.assertEquals(60, strike2.getScore());
        Assert.assertEquals(80, strike3.getScore());
        Assert.assertEquals(90, strike4.getScore());
        Assert.assertEquals(90, processor.getGame().getScore());

        BowlingFrame open5 = new BowlingFrame().setFirstBall(8).setSecondBall(0);
        ;
        processor.addFrame(open5);

        // X, X, X, X, 8-0
        Assert.assertEquals(30, strike1.getScore());
        Assert.assertEquals(60, strike2.getScore());
        Assert.assertEquals(88, strike3.getScore());
        Assert.assertEquals(106, strike4.getScore());
        Assert.assertEquals(114, processor.getGame().getScore());

        Assert.assertEquals(114, processor.getGame().getScore());
        Assert.assertFalse(processor.getGame().isComplete());

        PrintManager printManager = PrintManagerImpl.newInstance();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println(NL);
    }

    @Test
    public void testAllStrikesOpenInFifth() {
        Bowler bowler = new Bowler().setFirstName("Leonard").setLastName("McCoy");
        GameManager processor = GameManagerImpl.newGame(bowler);
        BowlingFrame frame1 = BowlingFrame.strike();
        BowlingFrame frame2 = BowlingFrame.strike();
        BowlingFrame frame3 = BowlingFrame.strike();
        BowlingFrame frame4 = BowlingFrame.strike();
        BowlingFrame frame5 = new BowlingFrame().setFirstBall(8).setSecondBall(0);
        BowlingFrame frame6 = BowlingFrame.strike();
        BowlingFrame frame7 = BowlingFrame.strike();
        BowlingFrame frame8 = BowlingFrame.strike();
        BowlingFrame frame9 = BowlingFrame.strike();
        BowlingFrame frame10 = BowlingFrame.strike();
        BowlingFrame frame11 = BowlingFrame.strike();
        BowlingFrame frame12 = BowlingFrame.strike();

        BowlingFrame[] frames = {frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, frame11, frame12};
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

        PrintManager printManager = PrintManagerImpl.newInstance();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println(NL);
    }

    @Test
    public void testAllStrikesOpenInTenth() {
        Bowler bowler = new Bowler().setFirstName("Bam Bam").setLastName("Rubble");
        GameManager processor = GameManagerImpl.newGame(bowler);
        BowlingFrame frame1 = BowlingFrame.strike();
        BowlingFrame frame2 = BowlingFrame.strike();
        BowlingFrame frame3 = BowlingFrame.strike();
        BowlingFrame frame4 = BowlingFrame.strike();
        BowlingFrame frame5 = BowlingFrame.strike();
        BowlingFrame frame6 = BowlingFrame.strike();
        BowlingFrame frame7 = BowlingFrame.strike();
        BowlingFrame frame8 = BowlingFrame.strike();
        BowlingFrame frame9 = BowlingFrame.strike();
        BowlingFrame frame10 = new BowlingFrame().setFirstBall(9).setSecondBall(0);

        BowlingFrame[] frames = {frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10};
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

        PrintManager printManager = PrintManagerImpl.newInstance();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println(NL);
    }

    @Test
    public void testAllStrikesStrikeSpareInTenth() {
        Bowler bowler = new Bowler().setFirstName("Pebbles").setLastName("Flintstone");
        GameManager processor = GameManagerImpl.newGame(bowler);
        BowlingFrame frame1 = BowlingFrame.strike();
        BowlingFrame frame2 = BowlingFrame.strike();
        BowlingFrame frame3 = BowlingFrame.strike();
        BowlingFrame frame4 = BowlingFrame.strike();
        BowlingFrame frame5 = BowlingFrame.strike();
        BowlingFrame frame6 = BowlingFrame.strike();
        BowlingFrame frame7 = BowlingFrame.strike();
        BowlingFrame frame8 = BowlingFrame.strike();
        BowlingFrame frame9 = BowlingFrame.strike();
        BowlingFrame frame10 = BowlingFrame.strike();
        BowlingFrame frame11 = new BowlingFrame().setFirstBall(8).setSecondBall(2).setSplit(true);

        BowlingFrame[] frames = {frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, frame11};
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

        PrintManager printManager = PrintManagerImpl.newInstance();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println(NL);
    }

    @Test
    public void testAllStrikesSpareStrikeInTenth() {
        Bowler bowler = new Bowler().setFirstName("Robert").setLastName("Barone");
        GameManager processor = GameManagerImpl.newGame(bowler);
        BowlingFrame frame1 = BowlingFrame.strike();
        BowlingFrame frame2 = BowlingFrame.strike();
        BowlingFrame frame3 = BowlingFrame.strike();
        BowlingFrame frame4 = BowlingFrame.strike();
        BowlingFrame frame5 = BowlingFrame.strike();
        BowlingFrame frame6 = BowlingFrame.strike();
        BowlingFrame frame7 = BowlingFrame.strike();
        BowlingFrame frame8 = BowlingFrame.strike();
        BowlingFrame frame9 = BowlingFrame.strike();
        BowlingFrame frame10 = new BowlingFrame().setFirstBall(9).setSecondBall(1);
        BowlingFrame frame11 = BowlingFrame.strike();

        BowlingFrame[] frames = {frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, frame11};
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

        PrintManager printManager = PrintManagerImpl.newInstance();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println(NL);
    }

    @Test
    public void testAllStrikes() {
        Bowler bowler = new Bowler().setFirstName("Raymond").setLastName("Barone");
        GameManager processor = GameManagerImpl.newGame(bowler);
        BowlingFrame frame1 = BowlingFrame.strike();
        BowlingFrame frame2 = BowlingFrame.strike();
        BowlingFrame frame3 = BowlingFrame.strike();
        BowlingFrame frame4 = BowlingFrame.strike();
        BowlingFrame frame5 = BowlingFrame.strike();
        BowlingFrame frame6 = BowlingFrame.strike();
        BowlingFrame frame7 = BowlingFrame.strike();
        BowlingFrame frame8 = BowlingFrame.strike();
        BowlingFrame frame9 = BowlingFrame.strike();
        BowlingFrame frame10 = BowlingFrame.strike();
        BowlingFrame frame11 = BowlingFrame.strike();
        BowlingFrame frame12 = BowlingFrame.strike();

        BowlingFrame[] frames = {frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, frame11, frame12};
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

        PrintManager printManager = PrintManagerImpl.newInstance();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println(NL);
    }

    @Test
    public void testAllStrikeNineSpare() {
        Bowler bowler = new Bowler().setFirstName("James").setLastName("Kirk");
        GameManager processor = GameManagerImpl.newGame(bowler);
        BowlingFrame frame1 = BowlingFrame.strike();
        BowlingFrame frame2 = new BowlingFrame().setFirstBall(9).setSecondBall(1);
        BowlingFrame frame3 = BowlingFrame.strike();
        BowlingFrame frame4 = new BowlingFrame().setFirstBall(9).setSecondBall(1);
        BowlingFrame frame5 = BowlingFrame.strike();
        BowlingFrame frame6 = new BowlingFrame().setFirstBall(9).setSecondBall(1);
        BowlingFrame frame7 = BowlingFrame.strike();
        BowlingFrame frame8 = new BowlingFrame().setFirstBall(9).setSecondBall(1);
        BowlingFrame frame9 = BowlingFrame.strike();
        BowlingFrame frame10 = new BowlingFrame().setFirstBall(9).setSecondBall(1);
        BowlingFrame frame11 = BowlingFrame.strike();

        BowlingFrame[] frames = {frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, frame11};
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

        PrintManager printManager = PrintManagerImpl.newInstance();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println(NL);
    }

    @Test
    public void testAllGutterBalls() {
        Bowler bowler = new Bowler().setFirstName("Marie").setLastName("Barone");
        GameManager processor = GameManagerImpl.newGame(bowler);
        BowlingFrame frame1 = BowlingFrame.gutterFrame();
        BowlingFrame frame2 = BowlingFrame.gutterFrame();
        BowlingFrame frame3 = BowlingFrame.gutterFrame();
        BowlingFrame frame4 = BowlingFrame.gutterFrame();
        BowlingFrame frame5 = BowlingFrame.gutterFrame();
        BowlingFrame frame6 = BowlingFrame.gutterFrame();
        BowlingFrame frame7 = BowlingFrame.gutterFrame();
        BowlingFrame frame8 = BowlingFrame.gutterFrame();
        BowlingFrame frame9 = BowlingFrame.gutterFrame();
        BowlingFrame frame10 = BowlingFrame.gutterFrame();

        BowlingFrame[] frames = {frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10};
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

        PrintManager printManager = PrintManagerImpl.newInstance();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println(NL);
    }

    @Test
    public void testAllGutterBallsStrikeOutInTenth() {
        Bowler bowler = new Bowler().setFirstName("Frank").setLastName("Barone");
        GameManager processor = GameManagerImpl.newGame(bowler);
        BowlingFrame frame1 = BowlingFrame.gutterFrame();
        BowlingFrame frame2 = BowlingFrame.gutterFrame();
        BowlingFrame frame3 = BowlingFrame.gutterFrame();
        BowlingFrame frame4 = BowlingFrame.gutterFrame();
        BowlingFrame frame5 = BowlingFrame.gutterFrame();
        BowlingFrame frame6 = BowlingFrame.gutterFrame();
        BowlingFrame frame7 = BowlingFrame.gutterFrame();
        BowlingFrame frame8 = BowlingFrame.gutterFrame();
        BowlingFrame frame9 = BowlingFrame.gutterFrame();
        BowlingFrame frame10 = BowlingFrame.strike();
        BowlingFrame frame11 = BowlingFrame.strike();
        BowlingFrame frame12 = BowlingFrame.strike();

        BowlingFrame[] frames = {frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, frame11, frame12};
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

        PrintManager printManager = PrintManagerImpl.newInstance();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println(NL);
    }

    @Test
    public void testAllStrikeFirstFrameAllGutterBallsStrikeOutInTenth() {
        Bowler bowler = new Bowler().setFirstName("Alley").setLastName("Barone");
        GameManager processor = GameManagerImpl.newGame(bowler);
        BowlingFrame frame1 = BowlingFrame.strike();
        BowlingFrame frame2 = BowlingFrame.gutterFrame();
        BowlingFrame frame3 = BowlingFrame.gutterFrame();
        BowlingFrame frame4 = BowlingFrame.gutterFrame();
        BowlingFrame frame5 = BowlingFrame.gutterFrame();
        BowlingFrame frame6 = BowlingFrame.gutterFrame();
        BowlingFrame frame7 = BowlingFrame.gutterFrame();
        BowlingFrame frame8 = BowlingFrame.gutterFrame();
        BowlingFrame frame9 = BowlingFrame.gutterFrame();
        BowlingFrame frame10 = BowlingFrame.strike();
        BowlingFrame frame11 = BowlingFrame.strike();
        BowlingFrame frame12 = BowlingFrame.strike();

        BowlingFrame[] frames = {frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, frame11, frame12};
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

        PrintManager printManager = PrintManagerImpl.newInstance();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println(NL);
    }

    @Test
    public void testAllStrikeNineSpareInFifth() {
        Bowler bowler = new Bowler().setFirstName("Sam").setLastName("Estep");
        GameManager processor = GameManagerImpl.newGame(bowler);
        BowlingFrame frame1 = BowlingFrame.strike();
        BowlingFrame frame2 = BowlingFrame.strike();
        BowlingFrame frame3 = BowlingFrame.strike();
        BowlingFrame frame4 = BowlingFrame.strike();
        BowlingFrame frame5 = new BowlingFrame().setFirstBall(9).setSecondBall(1);
        BowlingFrame frame6 = BowlingFrame.strike();
        BowlingFrame frame7 = BowlingFrame.strike();
        BowlingFrame frame8 = BowlingFrame.strike();
        BowlingFrame frame9 = BowlingFrame.strike();
        BowlingFrame frame10 = BowlingFrame.strike();
        BowlingFrame frame11 = BowlingFrame.strike();
        BowlingFrame frame12 = BowlingFrame.strike();

        BowlingFrame[] frames = {frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, frame11, frame12};
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

        PrintManager printManager = PrintManagerImpl.newInstance();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println(NL);
    }

    @Test
    public void testPrintMultipleGames() {
        Bowler christene = new Bowler().setFirstName("Christene").setLastName("Kuhn");
        Game game1 = GameManagerImpl.newGame(christene)
                .addFrame(new BowlingFrame(0, 2))
                .addFrame(new BowlingFrame(8, 1))
                .addFrame(new BowlingFrame(7, 2))
                .addFrame(BowlingFrame.strike())
                .addFrame(new BowlingFrame(5, 2))
                .addFrame(new BowlingFrame(9, 0))
                .addFrame(new BowlingFrame(6, 3))
                .addFrame(new BowlingFrame(7, 3))
                .addFrame(new BowlingFrame(9, 0))
                .addFrame(new BowlingFrame(8, 1))
                .getGame();

        Bowler chris = new Bowler().setFirstName("Chris").setLastName("Kuhn");
        Game game2 = GameManagerImpl.newGame(chris)
                .addFrame(BowlingFrame.strike())
                .addFrame(BowlingFrame.strike())
                .addFrame(new BowlingFrame(7, 2))
                .addFrame(new BowlingFrame(8, 0))
                .addFrame(new BowlingFrame(9, 1))
                .addFrame(new BowlingFrame(9, 1))
                .addFrame(new BowlingFrame(6, 3))
                .addFrame(new BowlingFrame(7, 3))
                .addFrame(new BowlingFrame(9, 0))
                .addFrame(new BowlingFrame(8, 2).setSplit(true))
                .addFrame(BowlingFrame.strike())
                .getGame();

        Bowler gwen = new Bowler().setFirstName("Gwen").setLastName("Estep");
        Game game3 = GameManagerImpl.newGame(gwen)
                .addFrame(new BowlingFrame(8, 1))
                .addFrame(new BowlingFrame(9, 0))
                .addFrame(BowlingFrame.strike())
                .addFrame(new BowlingFrame(6, 2))
                .addFrame(new BowlingFrame(7, 2))
                .addFrame(new BowlingFrame(7, 3))
                .addFrame(new BowlingFrame(3, 5))
                .addFrame(new BowlingFrame(0, 7))
                .addFrame(new BowlingFrame(9, 1))
                .addFrame(BowlingFrame.strike())
                .addFrame(BowlingFrame.strike())
                .addFrame(BowlingFrame.oneBall(6))
                .getGame();

        Bowler doug = new Bowler().setFirstName("Doug").setLastName("Estep");
        Game game4 = GameManagerImpl.newGame(doug)
                .addFrame(BowlingFrame.strike())
                .addFrame(BowlingFrame.strike())
                .addFrame(BowlingFrame.strike())
                .addFrame(new BowlingFrame(9, 1))
                .addFrame(new BowlingFrame(9, 1))
                .addFrame(new BowlingFrame(8, 1).setSplit(true))
                .addFrame(new BowlingFrame(8, 2))
                .addFrame(new BowlingFrame(9, 1))
                .addFrame(new BowlingFrame(9, 1))
                .addFrame(BowlingFrame.strike())
                .addFrame(BowlingFrame.strike())
                .addFrame(BowlingFrame.oneBall(9))
                .getGame();

        PrintManager printManager = PrintManagerImpl.newInstance();
        Game[] games = {game1, game2, game3, game4};
        printManager.printGames(games, System.out);
        System.out.println(NL);
    }

    @Test
    public void test12OpenSpareCorrectSpareToOpen() {
        Bowler bowler = new Bowler().setFirstName("Natalie").setLastName("Estep");
        GameManager processor = GameManagerImpl.newGame(bowler);

        BowlingFrame open1 = new BowlingFrame(8, 1, true);
        processor.addFrame(open1);
        Assert.assertEquals(9, open1.getScore());
        Assert.assertEquals(9, processor.getGame().getScore());

        BowlingFrame spare2 = new BowlingFrame().setFirstBall(8).setSecondBall(2);
        processor.addFrame(spare2);
        Assert.assertEquals(19, spare2.getScore());
        Assert.assertEquals(19, processor.getGame().getScore());

        BowlingFrame correctedSpare = new BowlingFrame().setFirstBall(8).setSecondBall(1);
        processor.replaceFrame(2, correctedSpare);

        Assert.assertEquals(18, processor.getGame().getScore());
        Assert.assertEquals(2, processor.getGame().getNumberOfFrames());

        Assert.assertEquals(open1, processor.getGame().getFrames().get(0));
        Assert.assertEquals(correctedSpare, processor.getGame().getFrames().get(1));

        PrintManager printManager = PrintManagerImpl.newInstance();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println(NL);
    }

    @Test
    public void test12OpenSpareRemoveSpare() {
        Bowler bowler = new Bowler().setFirstName("Gwen").setLastName("Estep");
        GameManager processor = GameManagerImpl.newGame(bowler);

        BowlingFrame open1 = new BowlingFrame().setFirstBall(8).setSecondBall(1);
        processor.addFrame(open1);
        Assert.assertEquals(9, open1.getScore());
        Assert.assertEquals(9, processor.getGame().getScore());

        BowlingFrame spare2 = new BowlingFrame().setFirstBall(8).setSecondBall(2);
        processor.addFrame(spare2);
        Assert.assertEquals(19, spare2.getScore());
        Assert.assertEquals(19, processor.getGame().getScore());

        processor.deleteFrame(2);
        Assert.assertNull(processor.retrieveFrame(2));

        Assert.assertEquals(9, processor.getGame().getScore());
        Assert.assertEquals(1, processor.getGame().getNumberOfFrames());

        Assert.assertEquals(open1, processor.getGame().getFrames().get(0));

        PrintManager printManager = PrintManagerImpl.newInstance();
        printManager.printGame(processor.getGame(), System.out);
        System.out.println(NL);
    }
}
