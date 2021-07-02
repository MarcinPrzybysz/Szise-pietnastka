import org.apache.commons.math3.util.Precision;

import java.util.*;

public class AStar {
	Set<State> explored = new HashSet<>();
	ArrayList<State> openStates = new ArrayList<>();
	StringBuilder movesBuilder;
	AbstractHeuristic heuristic;
	int maxDepth = 0;
	public AStar() {
		movesBuilder = new StringBuilder();
	}

	public ResultParams execute(State initState, String method) {
		switch (method) {
			case "manh":
				heuristic = new ManhattanHeuristic(initState.getWidth(), initState.getHeight());
				break;
			case "hamm":
				heuristic = new HammingHeuristic(initState.getWidth(), initState.getHeight());
				break;
			default:
				throw new RuntimeException("brak impementacji heurystyki: " + method);
		}


		ResultParams resultParams = new ResultParams();
		long startTime = System.currentTimeMillis();


		if (StateUtils.isFinalState(initState)) {
			movesBuilder.append(initState.getPath()).reverse();
			printReport(startTime);
			resultParams.setResultLength(movesBuilder.toString().length());
			resultParams.setResultPath(movesBuilder.toString());
			resultParams.setMaxDepthRecursion(initState.getDepth());
			resultParams.setExplored(explored.size() + openStates.size());
			resultParams.setProcessed(explored.size());
			resultParams.setTime((long) Precision.round((System.currentTimeMillis() - startTime),3));
			return resultParams;
		}

		openStates.add(initState);

		while (!openStates.isEmpty()) {
			State currentState = openStates.get(0);
			openStates.remove(0);
			explored.add(currentState);
			if(currentState.getDepth()>maxDepth){
				maxDepth=currentState.getDepth();
			}

			List<State> neighbours = StateUtils.getNeighbours(currentState);
			for (State neighbour : neighbours) {
				neighbour.addPath(currentState.getPath());
				if(neighbour.getDepth()>maxDepth){
					maxDepth=neighbour.getDepth();
				}

				if (explored.contains(neighbour) || openStates.contains(neighbour)) {
					continue;
				}

				if (StateUtils.isFinalState(neighbour)) {
					movesBuilder.append(neighbour.getPath()).reverse();
					printReport(startTime);
					resultParams.setResultLength(movesBuilder.toString().length());
					resultParams.setResultPath(movesBuilder.toString());
					resultParams.setMaxDepthRecursion(neighbour.getDepth());
					resultParams.setExplored(explored.size() + openStates.size());
					resultParams.setProcessed(explored.size());
					resultParams.setTime((long) Precision.round((System.currentTimeMillis() - startTime),3));
					return resultParams;
				} else {
					neighbour.setFScore(heuristic.distance(neighbour) + neighbour.getDepth());
					addToOpenStateWithFScoreOrder(neighbour);
				}

			}
		}
		resultParams.setResultLength(-1);
		return resultParams;
	}

	private void addToOpenStateWithFScoreOrder(State stateToAdd) {
		int newStateFScore = stateToAdd.getFScore();
		for (int i = 0; i < openStates.size(); i++) {
			if (newStateFScore <= openStates.get(i).getFScore()) {
				openStates.add(i, stateToAdd);
				return;
			}
		}
		openStates.add(stateToAdd);
	}

	private void printReport(long startTime) {
		System.out.println("długość znalezionego rozwiązania: " + movesBuilder.toString().length() + "\n" +
				"liczbę stanów odwiedzonych: " + (explored.size() + openStates.size()) + "\n" +
				"liczbę stanów przetworzonych: " + explored.size() + "\n" +
				"maksymalną osiągniętą głębokość rekursji: "+maxDepth+"\n" +
				"czas trwania procesu obliczeniowego:" + (System.currentTimeMillis() - startTime) + " [ms]");
	}
}
