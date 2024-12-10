package rabbitescape.engine;

import java.util.Map;

public class RabbitFactory implements ThingFactory {
    @Override
    public Thing createThing(int x, int y, Map<String, Object> params) {
        Pipe rabbit = new Pipe(x, y);
        return rabbit;
    }
}
