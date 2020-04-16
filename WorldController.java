import java.awt.Color;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

public class WorldController extends GraphicsProgram {
	
	private World theWorld;
	private GCanvas theWorldCanvas;
	public static final int APPLICATION_WIDTH = 200;
	public static final int APPLICATION_HEIGHT = 200;
	
	public double[][] data =new double[10][2];
	
	public void run(){	
		setUpWorld();
		runWorld();
	}
	
	public void init(){
	    resize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}
	
	public void setUpWorld(){
		theWorld = new World(50,50);
		initializePrisoners(theWorld,0.95);
//		theWorld.getCreatureList().add( new Cooperator( new Location(0,0), theWorld ));
//		theWorld.getCreatureList().add( new Defector( new Location(0,1), theWorld ));
//		theWorld.getCreatureList().add( new Defector( new Location(0,2), theWorld ));
//		theWorld.getCreatureList().add( new Cooperator( new Location(0,3), theWorld ));
//		theWorld.getCreatureList().add( new Defector( new Location(1,0), theWorld ));
//		theWorld.getCreatureList().add( new Cooperator( new Location(1,1), theWorld ));
//		theWorld.getCreatureList().add( new Cooperator( new Location(1,2), theWorld ));
//		theWorld.getCreatureList().add( new Defector( new Location(1,3), theWorld ));
//		theWorld.getCreatureList().add( new Cooperator( new Location(2,0), theWorld ));
//		theWorld.getCreatureList().add( new Defector( new Location(2,1), theWorld ));
//		theWorld.getCreatureList().add( new Defector( new Location(2,2), theWorld ));
//		theWorld.getCreatureList().add( new Cooperator( new Location(2,3), theWorld ));
//		theWorld.getCreatureList().add( new Defector( new Location(3,0), theWorld ));
//		theWorld.getCreatureList().add( new Cooperator( new Location(3,1), theWorld ));
//		theWorld.getCreatureList().add( new Cooperator( new Location(3,2), theWorld ));
//		theWorld.getCreatureList().add( new Defector( new Location(3,3), theWorld ));
		theWorldCanvas = this.getGCanvas();
	}
	
	public void initializePrisoners(World world ,double p) {
		for(int row = 0; row < theWorld.getHeight(); row++) {
			for(int col = 0; col < theWorld.getWidth(); col++) {
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
		for(int i=0; i<10;i++){
			getData(i);
			//waitForClick();
			System.out.println("---------------------ITERATION "+i+"---------------------");
			theWorld.letTimePass();
			//pause(500);
			drawWorld();
		}
		System.out.println("---------------------COMPLETE---------------------");
		writeCSV("Prisoner's Dilemma Test 2",data);
	}	
	
	public void getData(int i){
		data[i][0]=theWorld.ratioCtoD();
		data[i][1]=theWorld.clusteringFactor();
	}
	
	public boolean writeCSV(String fn, double[][] data) {
		try {
			FileWriter writer = new FileWriter(fn + ".csv");
			for (double[] point : data) {
				writer.write(point[0] + ", " + point[1] + "\n");
			}
			writer.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
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