package proactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;

/**
 * Created by tenghuanhe on 2017/9/23.
 */

// [COMPONENT] Proactive Initiator
public class ProactorInitiator {
    private static int ASYNC_SERVER_PORT = 1585;

    public void initiateProactiveServer(int port) throws IOException {
        final AsynchronousServerSocketChannel listener =
                AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(port));

        AcceptCompletionHandler acceptCompletionHandler = new AcceptCompletionHandler(listener);

        SessionState state = new SessionState();
        listener.accept(state, acceptCompletionHandler);
    }

    public static void main(String[] args) {
        try {
            System.out.println("Async server listening on port: " + ASYNC_SERVER_PORT);
            new ProactorInitiator().initiateProactiveServer(ASYNC_SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
