import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StateUtils {

	private static HashMap<Integer, Position> valuePositions;

	/**
	 * 1	2	3	4
	 * 5	6	7	8
	 * 9	10	11	12
	 * 13	14	15
	 */
	public static boolean isFinalState(State state) {
		int[][] arrangement = state.getArrangement();
		int value = 1;
		int height = arrangement.length;
		int width = arrangement[0].length;

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (i == height - 1 && j == width - 1) { //last element
					return arrangement[i][j] == 0;
				} else if (arrangement[i][j] != value) {
					return false;
				}
				value++;
			}
		}
		return true;
	}

	public static List<State> getNeighbours(State state) {
		if (Params.INSTANCE.getSearchOrder() == null) {
			throw new RuntimeException("searchOrder is not set");
		}
		List states = new ArrayList();

		for (String direction : Params.INSTANCE.getSearchOrder()) {
			switch (direction.toLowerCase()) {
				case "l":
					addConditionally(states, direction, state.move(-1, 0));
					break;
				case "r":
					addConditionally(states, direction, state.move(0, 1));
					break;
				case "u":
					addConditionally(states, direction, state.move(0, -1));
					break;
				case "d":
					addConditionally(states, direction, state.move(1, 0));
					break;
			}
		}
		return states;
	}

	private static void addConditionally(List states, String direction, State state) {
		if (state != null) {
			state.setMoveDirection(direction);
			states.add(state);
		}
	}


	//	 * 1	2	3	4
	//	 * 5	6	7	8
	//	 * 9	10	11	12
	//	 * 13	14	0   15
	// dla tego uklad upowinno wyjść 1 a nie 2

	public static int hamming(State state) {
		int[][] arrangement = state.getArrangement();
		int value = 1;
		int incorrectPositions = 0;
		int height = arrangement.length;
		int width = arrangement[0].length;

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (arrangement[i][j] == 0) { // ze względu na "dopuszaczalność" nie możemy przeszacowywać, omijamy wartość 0

				} else if (i == height - 1 && j == width - 1 && arrangement[i][j] != 0) { //last element is not 0, fixme? nie bierzemy zera ale czy patrzymky ostatnią pozycję?
					incorrectPositions++;
				} else if (arrangement[i][j] != value) { //value incorrect
					incorrectPositions++;
				}
				value++;
			}
		}
		return incorrectPositions;
	}

	public static int manhattan(State state) {
		int[][] arrangement = state.getArrangement();
		int distanceSum = 0;
		int height = arrangement.length;
		int width = arrangement[0].length;
		if (valuePositions == null) {
			setPositionsMap(width, height);
		}

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (arrangement[i][j] == 0) {
					// skip 0
				} else {
					Position finalPos = valuePositions.get(arrangement[i][j]);
					distanceSum = distanceSum + Math.abs(finalPos.getX() - j) + Math.abs(finalPos.getY() - i); //todo sprawdzić czy nie odwrotnie wspólrzedne
				}
			}
		}
		return distanceSum;
	}

	private static void setPositionsMap(int width, int height) {
		valuePositions = new HashMap<>();
		int value = 1;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (i == height - 1 && j == width - 1) { //last element
					valuePositions.put(0, new Position(i, j));
				} else {
					valuePositions.put(value, new Position(i, j));
					value++;
				}
			}
		}
	}

}
