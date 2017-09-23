package reactor;

import java.nio.channels.SelectionKey;

/**
 * Created by tenghuanhe on 2017/9/23.
 */
public interface EventHandler {
    public void handleEvent(SelectionKey handle) throws Exception;
}
