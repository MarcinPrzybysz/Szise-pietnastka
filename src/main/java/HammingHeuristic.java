public class HammingHeuristic extends AbstractHeuristic {

	public HammingHeuristic(int[][] finalState) {
		super(finalState);
	}

	public HammingHeuristic(int width, int height) {
		super(width, height);
	}

	@Override
	public int distance(State state) {
		if (this.finalState == null) {
			throw new RuntimeException("finalState not initialized!");
		}
		int distance = 0;
		int value = 1;
		int height = state.getHeight();
		int width = state.getWidth();

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if ((i == height - 1 && j == width - 1)) {
					if (state.getPositionValue(i, j) != 0) {
						distance++;
					}
				} else if (state.getPositionValue(i, j) != value) {
					distance++;
				}
				value++;
			}
		}
		return distance;
	}
}
