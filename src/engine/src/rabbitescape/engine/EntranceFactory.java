package rabbitescape.engine;

import java.util.Map;

public class EntranceFactory implements ThingFactory {
    @Override
    public Thing createThing(int x, int y, Map<String, Object> params) {
        return new Entrance(x, y);
    }
}
