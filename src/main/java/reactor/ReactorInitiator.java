package reactor;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;

/**
 * Created by tenghuanhe on 2017/9/23.
 */
public class ReactorInitiator {
    private static final int NIO_SERVER_PORT = 1700;

    public void initiateReactiveServer(int port) throws Exception {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.socket().bind(new InetSocketAddress(NIO_SERVER_PORT));
        server.configureBlocking(false);

        Dispatcher dispatcher = new Dispatcher();
        dispatcher.registerChannel(SelectionKey.OP_ACCEPT, server);

        dispatcher.registerEventHandler(SelectionKey.OP_ACCEPT, new AcceptEventHandler(
                dispatcher.getDemultiplexer()
        ));

        dispatcher.registerEventHandler(SelectionKey.OP_READ, new ReadEventHandler(
                dispatcher.getDemultiplexer()
        ));

        dispatcher.registerEventHandler(SelectionKey.OP_WRITE, new WriteEventHandler());

        dispatcher.run();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Starting NIO server at port: " + NIO_SERVER_PORT);
        new ReactorInitiator().initiateReactiveServer(NIO_SERVER_PORT);
    }
}
