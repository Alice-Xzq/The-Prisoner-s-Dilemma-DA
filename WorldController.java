import java.awt.Color;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

public class WorldController extends GraphicsProgram {
	
	private World theWorld;
	private GCanvas theWorldCanvas;
	public static final int APPLICATION_WIDTH = 200;
	public static final int APPLICATION_HEIGHT = 200;
	
	public void run(){	
		setUpWorld();
		runWorld();
	}
	
	public void init(){
	    resize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}
	
	public void setUpWorld(){
		theWorld = new World(20,20);
		initializePrisoners(theWorld, 0.5);
		theWorldCanvas = this.getGCanvas();
	}
	
	public void initializePrisoners(World world, double p) {
		for(int row = 0; row < 20; row++) {
			for(int col = 0; col < 20; col++) {
				RandomGenerator rgen = new RandomGenerator();
				if(rgen.nextBoolean(p)) {
					world.getCreatureList().add( new Cooperator( new Location(row,col), theWorld ));
				}else {
					world.getCreatureList().add( new Defector( new Location(row,col), theWorld ));
				}
			}
		}
	}
	
	public void runWorld(){
		drawWorld();
		for(int i=0; i<3;i++){
			theWorld.letTimePass();
			pause(500);
			drawWorld();
		}
	}	
	
	public void drawWorld(){
		drawBlankWorld();
		drawCreatures();
	}
	
	public void drawBlankWorld(){
		for(int row = 0 ; row<theWorld.getWidth(); row++)
			for(int col=0; col<theWorld.getHeight(); col++){
				GRect r = new GRect(row*10, col*10, 10, 10);
				r.setFillColor(Color.WHITE);
				r.setFilled(true);
				theWorldCanvas.add(r);
			}
	}
	
	public void drawCreatures(){
		for(LifeForm x: theWorld.getCreatureList()){
			GRect r = new GRect (x.getMyLocation().getX()*10, x.getMyLocation().getY()*10,10,10);
			r.setFillColor(x.getMyColor());
			r.setFilled(true);
			theWorldCanvas.add(r);
		}
	}
}
