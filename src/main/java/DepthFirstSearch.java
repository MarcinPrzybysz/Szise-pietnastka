import org.apache.commons.math3.util.Precision;

import java.util.*;

public class DepthFirstSearch {
	Set<State> explored = new HashSet<>();
	ArrayDeque<State> openStates = new ArrayDeque<>();
	StringBuilder movesBuilder;
	int maxDepth = 0;
	public DepthFirstSearch() {
		movesBuilder = new StringBuilder();
	}

	public ResultParams execute(State initState) {
		ResultParams resultParams = new ResultParams();
		long startTime = System.currentTimeMillis();
		if(initState.getDepth()>maxDepth){
			maxDepth=initState.getDepth();
		}
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
			State currentState = openStates.pollLast();
			explored.add(currentState);

			if (currentState.getDepth() == Params.MAX_DEPTH) {
				//wracamy
				continue;
			}

			if(currentState.getDepth()>maxDepth){
				maxDepth=currentState.getDepth();
			}

			List<State> neighbours = StateUtils.getNeighbours(currentState);
			for (State neighbour : neighbours) {
				neighbour.addPath(currentState.getPath());
				if(neighbour.getDepth()>maxDepth){
					maxDepth=neighbour.getDepth();
				}
				if (!explored.contains(neighbour)) {
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
						openStates.add(neighbour);
					}

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
				"liczbę stanów odwiedzonych: " + (explored.size() + openStates.size()) + "\n" +
				"liczbę stanów przetworzonych: " + explored.size() + "\n" +
				"maksymalną osiągniętą głębokość rekursji: "+maxDepth+"\n" +  //NIE DOTYCZY BFS
				"czas trwania procesu obliczeniowego:" + (System.currentTimeMillis() - startTime) + " [ms]");
	}

}
