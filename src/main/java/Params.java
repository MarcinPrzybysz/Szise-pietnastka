public class Params {
	public static Params INSTANCE;
	private static String[] searchOrder;
	public static int MAX_DEPTH = 20; //min 20

	public Params() {
		this.INSTANCE = this;
	}

	public String[] getSearchOrder() {
		return searchOrder;
	}

	public void setSearchOrder(String[] searchOrder) {
		this.searchOrder = searchOrder;
	}
}
