import client.ControllerTest;
import client.ClientHandler;

public class MainProgram {
    public static void main(String[] args) throws Throwable {
        ClientHandler client = ClientHandler.getInstance();
        client.connect();
        ControllerTest cl = new ControllerTest(client);
        cl.start();
        Thread.sleep(2000);
    }
}
