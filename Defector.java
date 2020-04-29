import java.awt.Color;

public class Defector extends Prisoner {

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
	
	public int clusteringOneNeighbor(int neighbor) {
		int index = myWorld.findNeighborIndex(myLocation, neighbor);
		if (index != -1) {
			if (myWorld.getCreatureList().get(index).getMyType() == 0) {
				return 0;
			}
			if (myWorld.getCreatureList().get(index).getMyType() == 1) {
				return 1;
			}
		}
		return -1;
	}
	
	public void refill(int myIndex) {
		myWorld.getCreatureList().remove(myIndex);
		//myWorld.getCreatureList().add(myIndex,new Cooperator(myLocation,myWorld));
		myWorld.getCreatureList().add(myIndex,new Cooperator(myLocation,myWorld,myScore));
		
	}

}
