package proactor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutionException;

/**
 * Created by tenghuanhe on 2017/9/23.
 */
public class TestClient {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        try (Socket socket = new Socket("localhost", 1700)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.print("Hello Proactor!");
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String reply = in.readLine();
            System.out.println("Server responded: " + reply);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
