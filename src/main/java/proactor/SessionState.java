package proactor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by tenghuanhe on 2017/9/23.
 */
public class SessionState {
    private Map<String, String> sessionProps = new ConcurrentHashMap<>();

    public String getProperty(String key) {
        return sessionProps.get(key);
    }

    public void setProperty(String key, String value) {
        sessionProps.put(key, value);
    }
}
