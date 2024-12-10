package rabbitescape.engine;

import java.util.Map;

public interface ThingFactory {
    Thing createThing(int x, int y, Map<String, Object> params);
}
