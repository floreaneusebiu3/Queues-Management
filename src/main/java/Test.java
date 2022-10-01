import controller.Controller;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletionService;


public class Test {
    public static void main(String[] args) throws InterruptedException {

        Controller c = new Controller();
        c.startSimulation();

    }
}
