import java.util.*;

public class BreadthFirstSearch {
	Set<State> explored = new HashSet<>();
	ArrayDeque<State> openStates = new ArrayDeque<>();
	StringBuilder movesBuilder;

	public BreadthFirstSearch() {
		movesBuilder = new StringBuilder();
	}

	//FIFO

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
			State currentState = openStates.pollFirst();
			explored.add(currentState);

			if (StateUtils.isFinalState(currentState)) {
				printReport(startTime);
				resultParams.setResultLength(movesBuilder.toString().length());
				resultParams.setResultPath(movesBuilder.toString());
				return resultParams;
			}
			movesBuilder.append(currentState.getMoveDirection());

			List<State> neighbours = StateUtils.getNeighbours(currentState);
			for (State neighbour : neighbours) {
				if (!explored.contains(neighbour)) {
					if (StateUtils.isFinalState(neighbour)) {
						printReport(startTime);
						resultParams.setResultLength(movesBuilder.toString().length());
						resultParams.setResultPath(movesBuilder.toString());
						return resultParams;
					}
					openStates.add(neighbour);
				} else {
					System.out.println("already been there");
				}
			}

		}
		resultParams.setResultLength(-1);
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
