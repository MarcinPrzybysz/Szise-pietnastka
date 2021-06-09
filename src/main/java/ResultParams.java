public class ResultParams {
    private int resultLength;
    private String resultPath;
    private int explored;
    private int processed;
    private int maxDepthRecursion;
    private long time;

    public int getResultLength() {
        return resultLength;
    }

    public void setResultLength(int resultLength) {
        this.resultLength = resultLength;
    }

    public String getResultPath() {
        return resultPath;
    }

    public void setResultPath(String resultPath) {
        this.resultPath = resultPath;
    }

    public int getExplored() {
        return explored;
    }

    public void setExplored(int explored) {
        this.explored = explored;
    }

    public int getProcessed() {
        return processed;
    }

    public void setProcessed(int processed) {
        this.processed = processed;
    }

    public int getMaxDepthRecursion() {
        return maxDepthRecursion;
    }

    public void setMaxDepthRecursion(int maxDepthRecursion) {
        this.maxDepthRecursion = maxDepthRecursion;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
