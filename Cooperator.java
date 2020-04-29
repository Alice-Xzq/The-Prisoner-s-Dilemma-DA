import java.awt.Color;

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
	
	public int clusteringOneNeighbor(int neighbor) {
		int index = myWorld.findNeighborIndex(myLocation, neighbor);
		if (index != -1) {
			if (myWorld.getCreatureList().get(index).getMyType() == 0) {
				return 1;
			}
			if (myWorld.getCreatureList().get(index).getMyType() == 1) {
				return 0;
			}
		}
		return -1;
	}
	
	
	public void refill(int myIndex) {
		myWorld.getCreatureList().remove(myIndex);
		//myWorld.getCreatureList().add(myIndex,new Defector(myLocation,myWorld));
		myWorld.getCreatureList().add(myIndex,new Defector(myLocation,myWorld,myScore));
	}

}
