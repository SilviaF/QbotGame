import java.util.ArrayList;

public class Robot {

	// Constants
	final int JUMPSPEED = -15;
	final int MOVESPEED = 5;
	final int GROUND = 382;

	/* x,y coordinates of our robot character's center */
	private int centerX = 100;
	private int centerY = GROUND;
	/* True when character is in the air, false when grounded */
	private boolean jumped = false;
	/* Rate at which these x,y positions change */
	private int speedX = 0;
	private int speedY = 1;
	/* Variables for not stoping when releasing an arrow if the other is pressed */
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean ducked = false;
	private static Background bg1 = StartingClass.getBg1();
	private static Background bg2 = StartingClass.getBg2();
	/* Declare an ArrayList for bullets*/
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	

	public void update() {
		// Moves character or scrolls background accordingly
		if (speedX < 0) { // Characters with negative speedX will move to the left
			centerX += speedX;
		}
		if (speedX == 0 || speedX < 0) {
			bg1.setSpeedX(0);
			bg2.setSpeedX(0);
		}

		if (centerX <= 200 && speedX > 0) {
			centerX += speedX;
		}

		if (speedX > 0 && centerX > 200) {
			bg1.setSpeedX(-MOVESPEED);
			bg2.setSpeedX(-MOVESPEED);
		}

		// Updates Y position
		centerY += speedY;
		if (centerY + speedY >= GROUND) {
			centerY = GROUND;
		} else {
			centerY += speedY;
		}

		// Handles jumping
		if (jumped == true) {
			speedY += 1;

			if (centerY + speedY >= GROUND) {
				centerY = GROUND;
				speedY = 0;
				jumped = false;
			}
		}

		// Prevents going beyond X coordinate of 0
		if (centerX + speedX <= 60) {
			centerX = 61;
		}
	}

	public void moveRight() {
		if (ducked == false) {
			speedX = MOVESPEED;
		}
	}

	public void moveLeft() {
		if (ducked == false) {
			speedX = -MOVESPEED;
		}
	}

	public void stopRight() {
		setMovingRight(false);
		stop();
	}

	public void stopLeft() {
		setMovingLeft(false);
		stop();
	}

	public void stop() {
		if (isMovingRight() == false && isMovingLeft() == false) {
			speedX = 0;
		}

		if (isMovingRight() == false && isMovingLeft() == true) {
			moveLeft();
		}

		if (isMovingRight() == true && isMovingLeft() == false) {
			moveRight();
		}
	}

	public void jump() {
		if (jumped == false) {
			speedY = JUMPSPEED;
			jumped = true;
		}
	}
	
	public void shoot(){
		Projectile p = new Projectile(centerX + 50, centerY -25);
		projectiles.add(p);
	}
	
	// Getters and setters
	
	public ArrayList getProjectiles(){
		return projectiles;
	}

	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public boolean isJumped() {
		return jumped;
	}

	public void setJumped(boolean jumped) {
		this.jumped = jumped;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public boolean isDucked() {
		return ducked;
	}

	public void setDucked(boolean ducked) {
		this.ducked = ducked;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

}
