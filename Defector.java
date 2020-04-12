import java.awt.Color;

public class Defector extends Prisoner {

	// instance variable

	public Defector(Location myLocation, World myWorld) {
		super(myLocation, myWorld);
		myColor = Color.red;
		myScore = 0;
		myType = 1;
	}

	public Defector(Location myLocation, World myWorld, double myScore) {
		super(myLocation, myWorld);
		myColor = Color.red;
		this.myScore = myScore;
		this.myType = 1;
	}
	
	public void interact() {
		double rawScore = 0;
		int numberOfNeighbors = 0;
		for (int i = 0; i < 4; i++) {
			double scoreOfOneNeighbor = interactOneNeighbor(i);
			if (scoreOfOneNeighbor != -1) {
				rawScore += scoreOfOneNeighbor;
				numberOfNeighbors++;
			}
		}
		myScore += rawScore / numberOfNeighbors;
	}

	public int interactOneNeighbor(int neighbor) {
		int index = myWorld.findNeighborIndex(myLocation, neighbor);
		if (index != -1) {
			if (myWorld.getCreatureList().get(index).getMyType() == 0) {
				return t;
			}
			if (myWorld.getCreatureList().get(index).getMyType() == 1) {
				return p;
			}
		}
		return -1;
	}
	
	public void refill(int myIndex) {
		myWorld.getCreatureList().add(new Cooperator(myLocation,myWorld));
		myWorld.getCreatureList().remove(myIndex);
	}
	
	//setters and getters
	public void setScore(double s) {
		myScore = s;
	}
	
	public double getScore() {
		return myScore;
	}

}
