import java.util.HashMap;

abstract class AbstractHeuristic {
	HashMap<Integer, Position> finalState2;

	int finalState[][];

	public AbstractHeuristic(int[][] finalState) {
		this.finalState = finalState;
	}

	public AbstractHeuristic(int width, int height) {
		finalState = new int[width][height];
		finalState2 = new HashMap<>();
		int value = 1;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				if (i == height - 1 && j == width - 1) {
					finalState[i][j] = 0;
					finalState2.put(0, new Position(i, j));
				} else {
					finalState[i][j] = value;
					finalState2.put(value, new Position(i, j));

				}
				value++;
			}
		}
	}

	public abstract int distance(State state);
}
