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

	public void letTimePass() {
		allInteract();
		allInherit();
	}

	public void allInherit() {
		int currentSizeOfCreatureList = creatureList.size();
		System.out.println("size of list is " + currentSizeOfCreatureList);
		for (int i = 0; i < currentSizeOfCreatureList; i++) {
			inherit(i);
		}
	}

	public void purgeTheDead() {
		int i = 0;
		while (i < creatureList.size()) {
			if (creatureList.get(i).isDead())
				creatureList.remove(i);
			else
				i++;
		}
	}

	public void allInteract() {
		for (LifeForm l : creatureList) {
			l.interact();
		}
	}

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
		if ((max > myScore) && (myType!=creatureList.get(indexMax).getMyType())) {
			creatureList.get(myIndex).refill(myIndex);
		}
	}

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
