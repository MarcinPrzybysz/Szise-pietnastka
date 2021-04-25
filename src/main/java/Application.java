public class Application {

	public static void main(String[] args) {

		PuzzleLoader loader = new PuzzleLoader();
		State initState = loader.load("src/main/resources/4x4_5/4x4_05_00054.txt");
		Params params = new Params();
		params.setSearchOrder(new String[]{"u", "d", "l", "r"});

		BreadthFirstSearch bfs = new BreadthFirstSearch();
		State finalState = bfs.execute(initState);

	}

}
