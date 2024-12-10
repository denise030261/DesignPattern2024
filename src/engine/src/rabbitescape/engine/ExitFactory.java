package rabbitescape.engine;

import java.util.Map;

public class ExitFactory implements ThingFactory {
    @Override
    public Thing createThing(int x, int y, Map<String, Object> params) {
        return new Exit(x, y);
    }
}