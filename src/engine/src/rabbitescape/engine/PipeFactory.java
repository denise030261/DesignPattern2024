package rabbitescape.engine;

import java.util.Map;

public class PipeFactory implements ThingFactory {
    @Override
    public Thing createThing(int x, int y, Map<String, Object> params) {
        Pipe pipe = new Pipe(x, y);
        return pipe;
    }
}
