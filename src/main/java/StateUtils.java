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
	 * 13	14	15 0
	 */
	static boolean isFinalState(State state) {
		int[][] arrangement = state.getArrangement();
		int value = 1;
		int height = arrangement[1].length;
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

	static List<State> getNeighbours(State state) {
		if (Params.INSTANCE.getSearchOrder() == null) {
			throw new RuntimeException("searchOrder is not set");
		}
		List states = new ArrayList();

		for (String direction : Params.INSTANCE.getSearchOrder()) {
			switch (direction.toLowerCase()) {
				case "l":
					addConditionally(states, direction, state.move(-1, 0));
					break;
				case "d":
					addConditionally(states, direction, state.move(0, 1));
					break;
				case "u":
					addConditionally(states, direction, state.move(0, -1));
					break;
				case "r":
					addConditionally(states, direction, state.move(1, 0));
					break;
			}
		}
		return states;
	}

	private static void addConditionally(List states, String direction, State state) {
		if (state != null) {
			state.setMoveDirection(direction);
			state.setPath(direction);
			states.add(state);
		}
	}

}
