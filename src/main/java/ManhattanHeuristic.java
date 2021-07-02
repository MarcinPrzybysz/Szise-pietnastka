public class ManhattanHeuristic extends AbstractHeuristic {

	public ManhattanHeuristic(int[][] finalState) {
		super(finalState);
	}

	public ManhattanHeuristic(int width, int height) {
		super(width, height);
	}

	@Override
	public int distance(State state) {
		if (this.finalState == null) {
			throw new RuntimeException("finalState not initialized!");
		}
		int distance = 0;
		for (int i = 0; i < state.getWidth(); i++) {
			for (int j = 0; j < state.getHeight(); j++) {
				int value = state.getPositionValue(i, j);
				if (value != 0) {
					distance += Math.abs(i - finalState2.get(value).getX()) + Math.abs(j - finalState2.get(value).getY());
				}
			}
		}

		return distance;
	}
}
