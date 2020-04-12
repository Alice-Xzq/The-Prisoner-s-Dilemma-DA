import java.awt.Color;

import acm.util.RandomGenerator;

public class Cooperator extends Prisoner {

	public Cooperator(Location myLocation, World myWorld) {
		super(myLocation, myWorld);
		myColor = Color.blue;
		myScore = 0;
		myType = 0;
	}

	public Cooperator(Location myLocation, World myWorld, double myScore) {
		super(myLocation, myWorld);
		this.myScore = myScore;
		myColor = Color.blue;
		myType = 0;

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
				return r;
			}
			if (myWorld.getCreatureList().get(index).getMyType() == 1) {
				return s;
			}
		}
		return -1;
	}
	
	public void refill(int myIndex) {
		myWorld.getCreatureList().add(new Defector(myLocation,myWorld));
		myWorld.getCreatureList().remove(myIndex);
	}

	// setters and getters
	public void setScore(double s) {
		myScore = s;
	}

	public double getScore() {
		return myScore;
	}

	public int getType() {
		return myType;
	}

}
