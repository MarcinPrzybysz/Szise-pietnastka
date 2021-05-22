public class ManhattanHeuristic extends AbstractHeuristic {

	public ManhattanHeuristic(int[][] finalState) {
		super(finalState);
	}

	public ManhattanHeuristic(int width, int height) {
		super(width, height);
	}


	//liczymy odległość każdego z punktu do jego właściwego położenia (oprócz zera)
	//	 * 1	2	3	4
	//	 * 5	6	7	8
	//	 * 9	10	11	12
	//	 * 13	14	15   0


	//	 * 1	2	3	4
	//	 * 5	6	9	8
	//	 * 9	10	11	12
	//	 * 13	14	15   0

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
					distance += Math.abs(i - finalState2.get(value).getY()) + Math.abs(j - finalState2.get(value).getX());
				}
			}
		}

		return distance;
	}
}
