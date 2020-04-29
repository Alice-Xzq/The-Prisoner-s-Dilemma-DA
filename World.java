import java.util.ArrayList;

public class World {

	private int width;

	private int height;

	private ArrayList<LifeForm> creatureList;

	private boolean[][] isTaken;

	public World(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.creatureList = new ArrayList<LifeForm>();
		this.isTaken = new boolean[height][width];
	}

	//commands each individual creature to interact with its neighbor one at a time in order of creatureList 
	//resetScores() can be remoeved to allow for memory
	public void letTimePass() {
		allInteract();
		allInherit();
		//resetScores();
	}

	public void allInteract() {
		int currentSizeOfCreatureList = creatureList.size();
		for (int i = 0; i < currentSizeOfCreatureList; i++) {
			interact(i);
		}
	}

	//calculates and sets the score when a prisoner interacts with its neighbors
	public void interact(int myIndex) {
		double rawScore = 0;
		int numberOfNeighbors = 0;
		double oldScore = creatureList.get(myIndex).getScore();
		for (int i = 0; i < 4; i++) {
			double scoreOfOneNeighbor = creatureList.get(myIndex)
					.interactOneNeighbor(i);
			if (scoreOfOneNeighbor != -1) {
				rawScore += scoreOfOneNeighbor;
				numberOfNeighbors++;
			}
		}
		creatureList.get(myIndex).setScore(
				oldScore + (rawScore / numberOfNeighbors));
	}

	public void allInherit() {
		int currentSizeOfCreatureList = creatureList.size();
		for (int i = 0; i < currentSizeOfCreatureList; i++) {
			inherit(i);
		}
	}

	//finds the neighbor with the highest score, if neighbor's score is higher than own score, 
	public void inherit(int myIndex) {
		Location myLocation = creatureList.get(myIndex).getMyLocation();
		double myScore = creatureList.get(myIndex).getScore();
		int myType = creatureList.get(myIndex).getMyType();
		double indexAndScores[][] = getNeighborScores(myLocation);
		double max = indexAndScores[0][1];
		int indexMax = 0;
		for (int i = 0; i < 4; i++) {
			if (indexAndScores[i][1] > max) {
				max = indexAndScores[i][1];
				indexMax = (int) indexAndScores[i][0];
			} else if (indexAndScores[i][1] == max) {
				if (Math.random() > 0.49) {
					indexMax = (int) indexAndScores[i][0];
				}
			}
		}
		if ((max > myScore)
				&& (myType != creatureList.get(indexMax).getMyType())) {
			creatureList.get(myIndex).refill(myIndex);
		}
	}

	public void resetScores() {
		for (LifeForm l : creatureList) {
			l.setScore(0);
		}
	}

	//finds the index of 4 neighbors based on location of current prisoner
	//it was written this way to be compatible with methods of generating prisoners that do not place prisoners filling rows or columns in order
	public int findNeighborIndex(Location l, int neighbor) {
		int creatureIndex = 0;
		if (neighbor == 0) {
			for (LifeForm f : creatureList) {
				if ((f.getMyLocation().getX() == l.getX())
						&& (f.getMyLocation().getY() == l.getY() + 1)) {
					return creatureIndex;
				}
				creatureIndex++;
			}
		}
		if (neighbor == 1) {
			for (LifeForm f : creatureList) {
				if ((f.getMyLocation().getX() == l.getX() + 1)
						&& (f.getMyLocation().getY() == l.getY())) {
					return creatureIndex;
				}
				creatureIndex++;
			}
		}
		if (neighbor == 2) {
			for (LifeForm f : creatureList) {
				if ((f.getMyLocation().getX() == l.getX())
						&& (f.getMyLocation().getY() == l.getY() - 1)) {
					return creatureIndex;
				}
				creatureIndex++;
			}
		}
		if (neighbor == 3) {
			for (LifeForm f : creatureList) {
				if ((f.getMyLocation().getX() == l.getX() - 1)
						&& (f.getMyLocation().getY() == l.getY())) {
					return creatureIndex;
				}
				creatureIndex++;
			}
		}
		return -1;
	}

	public double[][] getNeighborScores(Location myLocation) {
		double[][] indexAndScores = new double[4][2];
		for (double[] row : indexAndScores) {
			for (double cell : row) {
				cell = -1;
			}
		}
		for (int i = 0; i < 4; i++) {
			int index = findNeighborIndex(myLocation, i);
			if (index != -1) {
				indexAndScores[i][0] = index;
				indexAndScores[i][1] = creatureList.get(index).getScore();
			}
		}
		return indexAndScores;
	}

	//data gathering methods
	
	public double ratioCtoD() {
		double c = 0;
		for (LifeForm l : creatureList) {
			if (l.getMyType() == 0)
				c+=1.0;
		}
		return c / creatureList.size();
	}

	//in the end, this was not used in data collection because it is ambiguous; the same clustering factor can look very different 
	public double clusteringFactor() {
		double c = 0;
		for (int i = 0; i < creatureList.size(); i++) {
			c += clusteringScoreForOne(i);
		}
		return c / creatureList.size();
	}

	public double clusteringScoreForOne(int myIndex) {
		double rawScore = 0;
		int numberOfNeighbors = 0;
		for (int i = 0; i < 4; i++) {
			double scoreOfOneNeighbor = creatureList.get(myIndex)
					.clusteringOneNeighbor(i);
			if (scoreOfOneNeighbor != -1) {
				rawScore += scoreOfOneNeighbor;
				numberOfNeighbors++;
			}
		}
		return rawScore / numberOfNeighbors;
	}

	//setters and getters
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public ArrayList<LifeForm> getCreatureList() {
		return creatureList;
	}

	public void setCreatureList(ArrayList<LifeForm> creatureList) {
		this.creatureList = creatureList;
	}

	@Override
	public String toString() {
		return "World [width=" + width + ", height=" + height
				+ ", creatureList=" + creatureList + "]";
	}
}
