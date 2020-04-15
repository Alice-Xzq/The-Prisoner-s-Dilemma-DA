import java.awt.Color;

public abstract class Prisoner extends LifeForm{

	public Prisoner(int myLifeSpan, Location myLocation, Color myColor, World myWorld) {
		super(myLifeSpan, myLocation, myColor, myWorld);
	}
	
	public Prisoner(Location myLocation, World myWorld) {
		super(myLocation, myWorld);
	}
	
	public abstract int updateScore();

}
