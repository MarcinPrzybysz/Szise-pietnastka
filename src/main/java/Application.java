public class Application {

	public static void main(String[] args) {

		PuzzleLoader loader = new PuzzleLoader();
		State initState = loader.load("src/main/resources/4x4_5/4x4_03_00004.txt");
		Params params = new Params();
		params.setSearchOrder(new String[]{"u", "d", "l", "r"});

//		BreadthFirstSearch bfs = new BreadthFirstSearch();
//		ResultParams searchParams = bfs.execute(initState);
////
//		DepthFirstSearch dfs = new DepthFirstSearch();
//		ResultParams searchParams2 = dfs.execute(initState);

//
		AStar aStar = new AStar();
		ResultParams searchParams3 = aStar.execute(initState);


		//todo:
		// PLIK Z ROZWIAZANIEM
		//  PLIK Z DODATKOWYMI INFORMACJAMI
		//  1 linia (liczba całkowita): długość znalezionego rozwiązania - o takiej samej wartości jak w pliku z rozwiązaniem (przy czym gdy program nie znalazł rozwiązania, wartość ta to -1);
		//  2 linia (liczba całkowita): liczbę stanów odwiedzonych;
		//  3 linia (liczba całkowita): liczbę stanów przetworzonych;
		//  4 linia (liczba całkowita): maksymalną osiągniętą głębokość rekursji;
		//  5 linia (liczba rzeczywista z dokładnością do 3 miejsc po przecinku): czas trwania procesu obliczeniowego w milisekundach.
	}

}
