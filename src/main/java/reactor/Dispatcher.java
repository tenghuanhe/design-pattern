package reactor;

import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by tenghuanhe on 2017/9/23.
 */
public class Dispatcher {
    private Map<Integer, EventHandler> registeredHandlers =
            new ConcurrentHashMap<>();
    private Selector demultiplexer;

    public Dispatcher() throws Exception {
        demultiplexer = Selector.open();
    }

    public Selector getDemultiplexer() {
        return demultiplexer;
    }

    public void registerEventHandler(int eventType, EventHandler eventHandler) {
        registeredHandlers.put(eventType, eventHandler);
    }

    // Used to register ServerSocketChannel with the selector to accept incoming
    // client connections
    public void registerChannel(int eventType, SelectableChannel channel) throws Exception {
        channel.register(demultiplexer, eventType);
    }

    public void run() {
        try {
            while (true) { // Loop indefinitely
                demultiplexer.select();
                Set<SelectionKey> readyHandles = demultiplexer.selectedKeys();
                Iterator<SelectionKey> handleIterator = readyHandles.iterator();

                while (handleIterator.hasNext()) {
                    SelectionKey handle = handleIterator.next();

                    if (handle.isAcceptable()) {
                        EventHandler handler = registeredHandlers.get(SelectionKey.OP_ACCEPT);
                        handler.handleEvent(handle);
                        // Note: Here we don't remove this handle from selector since we
                        // want to keep listening to new client connections
                    }

                    if (handle.isReadable()) {
                        EventHandler handler = registeredHandlers.get(SelectionKey.OP_READ);
                        handler.handleEvent(handle);
                        handleIterator.remove();
                    }

                    if (handle.isWritable()) {
                        EventHandler handler = registeredHandlers.get(SelectionKey.OP_WRITE);
                        handler.handleEvent(handle);
                        handleIterator.remove();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
