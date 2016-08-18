package sec;

import java.awt.Point;
import java.awt.PointerInfo;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

class Model extends Observable {
    public final int BALL_SIZE = 20;
    private int xPosition = 0;
    private int yPosition = 0;
    private int xLimit, yLimit;
    private int xMaxSpeed = 5;
    private int score = 0;
    ArrayList<Point> hits = new ArrayList<Point>();
    public int getScore() {
		return score;
	}

	public void addScore(int score) {
		this.score += score;
	}

	Random rand = new Random();
    public int getyLimit() {
		return yLimit;
	}

	public int getxLimit() {
		return xLimit;
	}

	private int xDelta = 6;
    private int yDelta = 4;
    private long gravityTime;

    public void setLimits(int xLimit, int yLimit) {
        this.xLimit = xLimit - BALL_SIZE;
        this.yLimit = yLimit - BALL_SIZE;
    }

    public int getX() {
        return xPosition;
    }

    public int getY() {
        return yPosition;
    }
    
    public void hit(){
    	xDelta -= 20;
    	score += 1;
    	System.out.println("Hit");
    }
    public void resetSpeed(){
    	xDelta = 6;
    	yDelta = 4;
    }
    public void speedUp(int amount){
    	
    	yDelta += amount;
    }
    private int getGravityChange(){
    	long math = ((System.currentTimeMillis()-gravityTime)/1000)-2;
    	return (int) math;
    }
    public void bounce(){
    	gravityTime = System.currentTimeMillis();
    	
    }

    public void makeOneStep() {
        // Do the work
        xPosition += xDelta;
        if (xPosition < 0 || xPosition >= xLimit) {
        	bounce();
        	if (xDelta < 0) {
				xDelta += 1;
			} else {
				xDelta -= 1;
			}
            xDelta = -xDelta;
            xPosition += xDelta;
            if (rand.nextInt(25) == 12) {
            	if (rand.nextBoolean()) {
					xDelta += -rand.nextInt(5);
				} else {
					xDelta += rand.nextInt(5);
				}
			}
        }
        yPosition += yDelta;
        if (yPosition < 0 || yPosition >= yLimit) {
        	if (yPosition <0) {
				yDelta = -yDelta/2;
			} else {
				yDelta = yDelta-8;
			}
        	bounce();
            yDelta = -yDelta;
            yPosition += yDelta;
            if (yPosition < 0) {
				yPosition = 1;
			}
            
        }
        if (yDelta < 0) {
			yDelta += getGravityChange()/10;
		} else {
			yDelta -= getGravityChange();
		}
        // Notify observers
        setChanged();
        notifyObservers();
    }
}