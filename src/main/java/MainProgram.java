import client.ControllerTest;
import client.MinaClient;

public class MainProgram {
    public static void main(String[] args) throws Throwable {
        MinaClient client = new MinaClient();
        client.connect();
        ControllerTest cl = new ControllerTest(client);
        cl.start();
        Thread.sleep(2000);
    }
}
