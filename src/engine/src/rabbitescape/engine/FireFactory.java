package rabbitescape.engine;

import java.util.Map;

public class FireFactory implements ThingFactory {
    @Override
    public Thing createThing(int x, int y, Map<String, Object> params) {
        int variant = (int) params.getOrDefault("variant", 0);
        return new Fire(x, y, variant);
    }
}

