import java.util.*;

public class AStar {
	Set<State> explored = new HashSet<>();
	ArrayList<State> openStates = new ArrayList<>();
	StringBuilder movesBuilder;

	public AStar() {
		movesBuilder = new StringBuilder();
	}

	public ResultParams execute(State initState) {

		//kosztem będzie głebokość ??
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
			State currentState = openStates.get(0);
			openStates.remove(0);
			explored.add(currentState);

			List<State> neighbours = StateUtils.getNeighbours(currentState);
			for (State neighbour : neighbours) {
				neighbour.calculatePathCost();
			}
//		neighbours.sort(new StateCostComparator());

			for (State neighbour : neighbours) {
				if (!explored.contains(neighbour)) {
					if (StateUtils.isFinalState(currentState)) {
						printReport(startTime);
						resultParams.setResultLength(movesBuilder.toString().length());
						resultParams.setResultPath(movesBuilder.toString());
						return resultParams;
					}
					addToOpenStateWithCostOrder(neighbour);

				} else {
					System.out.println("already been there");
				}
			}
		}

		return resultParams;
	}

	private void addToOpenStateWithCostOrder(State stateToAdd) {
		int newStateTotalCost = stateToAdd.getTotalCost();
//		if (openStates.isEmpty()) {
//			openStates.add(stateToAdd);
//			return;
//		}

		for (int i = 0; i < openStates.size(); i++) {
			if (newStateTotalCost <= openStates.get(i).getTotalCost()) {
				openStates.add(i, stateToAdd);
				return;
			}
		}
		openStates.add(stateToAdd);
	}


	private void printReport(long startTime) {
		System.out.println("długość znalezionego rozwiązania: " + movesBuilder.toString().length() + "\n" +
				"liczbę stanów odwiedzonych: " + explored.size() + "\n" +
				"liczbę stanów przetworzonych: " + (explored.size() + openStates.size()) + "\n" +  //NIE JESTEM PEWIEN
				"maksymalną osiągniętą głębokość rekursji:\n" +  //NIE DOTYCZY BFS
				"czas trwania procesu obliczeniowego:" + (System.currentTimeMillis() - startTime) + " [ms]");
	}

	class StateCostComparator implements Comparator<State> {
		@Override
		public int compare(State a, State b) {
			return -1 * a.getTotalCost().compareTo(b.getTotalCost());
		}
	}
}
