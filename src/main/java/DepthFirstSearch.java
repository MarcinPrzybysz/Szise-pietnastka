import java.util.*;

public class DepthFirstSearch {
	Set<State> explored = new HashSet<>();
	ArrayDeque<State> openStates = new ArrayDeque<>();
	StringBuilder movesBuilder;

	public DepthFirstSearch() {
		movesBuilder = new StringBuilder();
	}

	//LIFO

	public ResultParams execute(State initState) {
		ResultParams resultParams = new ResultParams();
		long startTime = System.currentTimeMillis();

		if (StateUtils.isFinalState(initState)) {
			printReport(startTime);
			resultParams.setResultLength(movesBuilder.toString().length());
			resultParams.setResultPath(movesBuilder.toString());
			return resultParams;
		}
		openStates.add(initState);

		while (!openStates.isEmpty()) {
			State currentState = openStates.pollLast();
			explored.add(currentState);

			if (currentState.getDepth() > Params.MAX_DEPTH) {
				//wracamy
				continue;
			}

			List<State> neighbours = StateUtils.getNeighbours(currentState);
			for (State neighbour : neighbours) {
				if (!explored.contains(neighbour)) {
					if (StateUtils.isFinalState(currentState)) {
						printReport(startTime);
						resultParams.setResultLength(movesBuilder.toString().length());
						resultParams.setResultPath(movesBuilder.toString());
						return resultParams;
					} else {
						openStates.add(neighbour);
					}

				} else {
					System.out.println("already been there");
				}
			}

		}

		return resultParams;
	}


	private void printReport(long startTime) {
		System.out.println("długość znalezionego rozwiązania: " + movesBuilder.toString().length() + "\n" +
				"liczbę stanów odwiedzonych: " + explored.size() + "\n" +
				"liczbę stanów przetworzonych: " + (explored.size() + openStates.size()) + "\n" +  //NIE JESTEM PEWIEN
				"maksymalną osiągniętą głębokość rekursji:\n" +  //NIE DOTYCZY BFS
				"czas trwania procesu obliczeniowego:" + (System.currentTimeMillis() - startTime) + " [ms]");
	}

}
