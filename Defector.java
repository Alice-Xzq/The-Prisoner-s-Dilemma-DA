import java.awt.Color;

public class Defector extends Prisoner {

	// instance variable
	int score;

	public Defector(Location myLocation, World myWorld) {
		super(myLocation, myWorld);
		myColor = Color.red;
		score = 0;
	}

	public Defector(Location myLocation, World myWorld, int score) {
		super(myLocation, myWorld);
		myColor = Color.red;
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
