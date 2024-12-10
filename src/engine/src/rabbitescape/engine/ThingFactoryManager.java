package rabbitescape.engine;

import java.util.HashMap;
import java.util.Map;

public class ThingFactoryManager {
    private static final Map<Class<? extends Thing>, ThingFactory> factories = new HashMap<>();

    static {
        factories.put(Entrance.class, new EntranceFactory());
        factories.put(Exit.class, new ExitFactory());
        factories.put(Rabbit.class, new RabbitFactory());
        //factories.put(Token.class, new TokenFactory());
        factories.put(Fire.class, new FireFactory());
        factories.put(Pipe.class, new PipeFactory());
    }

    public static ThingFactory getFactory(Class<? extends Thing> clazz) {
        ThingFactory factory = factories.get(clazz);
        if (factory == null) {
            throw new IllegalStateException("No factory found for class: " + clazz.getName());
        }
        return factory;
    }
}

