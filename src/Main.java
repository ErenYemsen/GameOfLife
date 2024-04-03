import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Life life = new Life(Integer.parseInt(args[0]),Integer.parseInt(args[1]));

        life.prettyPrint();

        try {
            life.nextStep(Integer.parseInt(args[2]));
        } catch (Exception e) {
            life.nextStep();
        }

    }
}
