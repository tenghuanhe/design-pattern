package proactor;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Created by tenghuanhe on 2017/9/23.
 */
public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, SessionState> {

    private AsynchronousServerSocketChannel listener;

    public AcceptCompletionHandler(AsynchronousServerSocketChannel listener) {
        this.listener = listener;
    }

    @Override
    public void completed(AsynchronousSocketChannel socketChannel, SessionState sessionState) {

        SessionState newSessionState = new SessionState();
        listener.accept(newSessionState, this); // accept the next connection !!!

        // handle this connection
        ByteBuffer inputBuffer = ByteBuffer.allocate(2048);
        ReadCompletionHandler readCompletionHandler =
                new ReadCompletionHandler(socketChannel, inputBuffer);
        socketChannel.read(inputBuffer, sessionState, readCompletionHandler);
    }

    @Override
    public void failed(Throwable exc, SessionState attachment) {
        // Handle connection failure
    }
}
