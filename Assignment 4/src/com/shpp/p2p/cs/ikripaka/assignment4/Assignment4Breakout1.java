package com.shpp.p2p.cs.ikripaka.assignment4;

import acm.graphics.*;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.Color;
import java.awt.event.MouseEvent;

/**
 * This class create simple game: "BREAKOUT"
 */
public class Assignment4Breakout1 extends WindowProgram {
    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Dimensions of game board (usually the same)
     */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /**
     * Dimensions of the paddle
     */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /**
     * Offset of the paddle up from the bottom
     */
    private static final int PADDLE_Y_OFFSET = 30;

    /**
     * Number of bricks per row
     */
    private static final int NBRICKS_PER_ROW = 10;

    /**
     * Number of rows of bricks
     */
    private static final int NBRICK_ROWS = 10;

    /**
     * Separation between bricks
     */
    private static final int BRICK_SEP = 4;

    /**
     * Width of a brick
     */
    private static final int BRICK_WIDTH =
            (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /**
     * Height of a brick
     */
    private static final int BRICK_HEIGHT = 8;

    /**
     * Radius of the ball in pixels
     */
    private static final int BALL_RADIUS = 10;

    /**
     * Offset of the top brick row from the top
     */
    private static final int BRICK_Y_OFFSET = 70;

    /**
     * Number of turns
     */
    private static final int NTURNS = 3;

    /**
     * Y ball speed
     */
    private static final double BALL_VY = 3.0;

    /**
     *
     */
    private static final int PAUSE_TIME = 10;

    public void run() {
        createTextLabel("Your attempts: " + NTURNS);
        addRacket();
        addBall();
        addMouseListeners();
        gameIsActive = true;

        playGame();
    }

    private double vx, vy = BALL_VY; // ball speed
    private Color[] brickColors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN}; // bricks colors
    private GRoundRect racket = null;
    private GLabel label = null; // label in which you can see useful information
    private GOval ball = null;
    private boolean gameIsActive = true;
    private int brickWhichThePlayerDidNotTouch = NBRICK_ROWS * NBRICKS_PER_ROW;

    /**
     * This function modulate game
     * 1 - build elements of interaction
     * 2 - waiting for the mouse press
     * 3 - ball moves
     * If gameIsActive = false, then output is displayed
     */
    private void playGame() {
        int userTurns;
        for (userTurns = 0; gameIsActive; userTurns++) {
            if (userTurns > 3) {
                break;
            }
            generateRandomVX();
            buildBrickWall();
            ball.setLocation(WIDTH / 2.0, HEIGHT / 2.0);
            pause(20);
            add(label);
            label.setLabel("Your attempts: " + (NTURNS - userTurns));
            centerLabel();
            waitForClick();
            remove(label);
            moveBall();
        }
        if (userTurns >= 3) {
            label.setLabel("Game over. You lost.");
            add(label);
            centerLabel();
        } else {
            label.setLabel("You win with " + userTurns + " attempt(s)");
            add(label);
            centerLabel();
        }
    }

    /**
     * Centers label
     */
    private void centerLabel() {
        label.setLocation((WIDTH - label.getWidth()) / 2.0, label.getAscent());
    }

    /**
     * @return object if at least once brick/racket touch the ball
     */
    private GObject getCollidingObject() {
        GObject collider;
        if (getElementAt(ball.getX(), ball.getY()) != null) { // top left coordinate
            collider = getElementAt(ball.getX(), ball.getY());

        } else if (getElementAt(ball.getX() + BALL_RADIUS, ball.getY()) != null) { // right top coordinate
            collider = getElementAt(ball.getX() + BALL_RADIUS, ball.getY());

        } else if (getElementAt(ball.getX(), ball.getY() + BALL_RADIUS) != null) { // left bottom coordinate
            collider = getElementAt(ball.getX(), ball.getY() + BALL_RADIUS);

        } else if (getElementAt(ball.getX() + BALL_RADIUS, ball.getY() + BALL_RADIUS) != null) { // right bottom coordinate
            collider = getElementAt(ball.getX() + BALL_RADIUS, ball.getY() + BALL_RADIUS);

        } else {
            collider = null;
        }
        return collider;
    }

    /**
     * Creates text label with text
     *
     * @param labelText - interface text
     */
    private void createTextLabel(String labelText) {
        label = new GLabel(labelText);
        label.setFont("Georgia-20");
        label.setLocation(label.getAscent(), label.getAscent());
    }

    /**
     * Moves ball. The ball can lean of the racket / brick / walls.
     */
    private void moveBall() {
        GObject object;
        while (gameIsActive) {
            if (!(ball.getY() + BALL_RADIUS > HEIGHT - PADDLE_Y_OFFSET)) {
                ball.move(vx, vy);
                object = getCollidingObject();

                if (ball.getX() < 0) {
                    vx = -vx;
                } else if (ball.getY() < 0) {
                    vy = -vy;
                } else if (ball.getX() + BALL_RADIUS > getWidth()) {
                    vx = -vx;
                } else if (object != null) {
                    vy = -vy;
                    if (object != racket) {
                        remove(object);
                        brickWhichThePlayerDidNotTouch--;
                    }
                    ball.move(vx, vy);
                    ball.move(vx, vy);
                }
                pause(PAUSE_TIME);
                if (brickWhichThePlayerDidNotTouch == 0) {
                    gameIsActive = false;
                    break;
                }

            } else {
                gameIsActive = true;
                break;
            }
        }
    }

    /**
     * Generates random X speed
     */
    private void generateRandomVX() {
        RandomGenerator rgen = RandomGenerator.getInstance();
        vx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5))
            vx = -vx;
    }

    /**
     * Paint brick wall
     */
    private void buildBrickWall() {
        paintBrickRows();
    }

    /**
     * Paints 1 custom brick
     */
    private void addOneRectangle(double x, double y, Color color) {
        GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        brick.setFilled(true);
        brick.setColor(color);
        add(brick);
    }

    /**
     * Paints brick wall rows
     */
    private void paintBrickRows() {
        double rectX, rectY;
        Color color = brickColors[0];
        for (int i = 0; i < NBRICK_ROWS; i++) {

            if (!(i > brickColors.length * 2)) { // if (lines > bricksColor.length)
                if (i % 2 == 0) {
                    color = brickColors[i / 2];
                }
            } else {
                color = brickColors[(i - i * (i / brickColors.length)) / 2];  // if (lines < bricksColor.length)
            }
            rectY = BRICK_Y_OFFSET + BRICK_HEIGHT * i + BRICK_SEP * i;

            for (int j = 0; j < NBRICKS_PER_ROW; j++) { // Paints 1 ROW
                rectX = j * BRICK_WIDTH + j * BRICK_SEP;

                addOneRectangle(rectX, rectY, color);
            }
        }

    }

    /**
     * Adds the ball
     */
    private void addBall() {
        ball = new GOval(WIDTH / 2.0, HEIGHT / 2.0, BALL_RADIUS, BALL_RADIUS);
        ball.setFilled(true);
        ball.setColor(Color.BLACK);
        add(ball);
    }

    /**
     * Adds the racket
     */
    private void addRacket() {
        racket = new GRoundRect(PADDLE_WIDTH, PADDLE_HEIGHT);
        racket.setFilled(true);
        racket.setColor(Color.BLACK);
        racket.setLocation((WIDTH - PADDLE_WIDTH) / 2.0, getHeight() - PADDLE_Y_OFFSET);
        add(racket);
    }

    /**
     * If mouse moved -> move racket
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        double newX = e.getX() - PADDLE_WIDTH / 2.0;
        if (newX < 0) {
            racket.setLocation(0, getHeight() - PADDLE_Y_OFFSET);
        } else {
            if (newX > WIDTH - PADDLE_WIDTH) {
                racket.setLocation(WIDTH - PADDLE_WIDTH, getHeight() - PADDLE_Y_OFFSET);
            } else
                racket.setLocation(newX, getHeight() - PADDLE_Y_OFFSET);
        }
    }
}
