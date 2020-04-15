import java.awt.Color;

public class Cooperator extends Prisoner {

	//instance variable
	int score;
	
	public Cooperator(Location myLocation, World myWorld) {
		super(myLocation, myWorld);
		myColor = Color.blue;
		score = 0;
	}
	
	public Cooperator(Location myLocation, World myWorld, int score) {
		super(myLocation, myWorld);
		myColor = Color.blue;
		this.score = score;
	}
	
	//setters and getters
	public void setScore(int s) {
		score = s;
	}
	
	public int getScore() {
		return score;
	}

	@Override
	public void updateStrat() {

	}

	@Override
	public int updateScore() {
		return score;
	}

}
