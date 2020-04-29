import java.awt.Color;

public abstract class Prisoner extends LifeForm{
	
	public int t=10;
	public int r=8;
	public int p=2;
	public int s=0;

	public Prisoner(int myLifeSpan, Location myLocation, Color myColor, World myWorld, int myType, double myScore) {
		super(myLifeSpan, myLocation, myColor, myWorld, myType, myScore);
	}
	
	public Prisoner(Location myLocation, World myWorld) {
		super(myLocation, myWorld);
	}
	
	public abstract void refill(int myIndex);
	public abstract int interactOneNeighbor(int neighbor);
	public abstract int clusteringOneNeighbor(int neighbor);

}
