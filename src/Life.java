public class Life {
    private final int width;
    private final int height;
    private int[][] currState;
    private int[][] neighMatrix;

    Life(int width, int height){
        this.width = width;
        this.height = height;
        this.currState = this.genRandomState();
        this.neighMatrix = this.neighbourMatrix();
    }

    private int[][] genRandomState(){

        int[][] randomState = new int[this.height][this.width];

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                randomState[i][j] = Math.random() >= 0.5f ? 1 : 0;
            }
        }

        return randomState;
    }

    public void prettyPrint(){

        String top = "_".repeat(this.width + 2);
        String down = "-".repeat(this.width + 2);

        System.out.println(top);
        for (int i = 0; i < this.height; i++) {

            StringBuilder result = new StringBuilder("|");
            for (int j = 0; j < this.width; j++) {
                if (currState[i][j] == 1){
                    result.append("#");
                }else {
                    result.append(" ");
                }
            }
            result.append("|");
            System.out.println(result);
        }
        System.out.println(down);

    }

    public void nextStep(int a) throws InterruptedException {

        for (int j = 0; j < a; j++) {
            this.currState = this.calculateNextState();
            this.prettyPrint();
            Thread.sleep(1000);
        }

    }
    public void nextStep() throws InterruptedException {
        this.nextStep(1);
    }

    private int[][] calculateNextState(){

        int[][] lifeMatrix = this.currState;
        int[][] neighMatrix = this.neighbourMatrix();
        int[][] nextState = lifeMatrix;


        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {

                if (lifeMatrix[i][j] == 1){

                    switch (neighMatrix[i][j]){
                        case 2,3:
                            //stay alive
                            nextState[i][j] = 1;
                            break;
                        default:
                            //die
                            nextState[i][j] = 0;
                            break;
                    }

                } else if (neighMatrix[i][j] == 3) {
                    //resurrect
                    nextState[i][j] = 1;
                }

            }
        }

        return nextState;
    }

    private int[][] neighbourMatrix(){

        int[][] matrix = new int[this.height][this.width];

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                matrix[i][j] = this.calculateNeighbourOfPoint(i,j);
            }
        }

        this.neighMatrix = matrix;
        return matrix;
    }

    private int calculateNeighbourOfPoint(int i, int j){

        int count = 0;
        int[][] life = this.currState;

        for (int k = -1; k <= 1; k++) {
            for (int l = -1; l <= 1; l++) {

                if (i+k != -1 && i+k != this.height && j+l != -1 && j+l != this.width) {

                    count += life[i+k][j+l];

                }

            }

        }

        return count - life[i][j];
    }

    public int[][] getNeighMatrix() {
        return neighMatrix;
    }

    public void setNeighMatrix(int[][] neighMatrix) {
        this.neighMatrix = neighMatrix;
    }

    public int[][] getCurrState() {
        return currState;
    }

    public void setCurrState(int[][] currState) {
        if (currState.length == this.height && currState[0].length == this.width) {
            this.currState = currState;
        }else {
            System.out.println("Must be same as first declaration");
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
