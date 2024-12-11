package rabbitescape.engine;

import java.util.HashMap;
import java.util.Map;

import rabbitescape.engine.util.VariantGenerator;

public class ThingFactoryManager {
    private static final Map<Class<? extends Thing>, ThingFactory> factories = new HashMap<>();
    private static final Map<Character, ThingFactory> mapFactories = new HashMap<>();
    
    static {
        factories.put(Entrance.class, new EntranceFactory());
        factories.put(Exit.class, new ExitFactory());
        factories.put(Rabbit.class, new RabbitFactory(0,Direction.RIGHT));
        factories.put(Token.class, new TokenFactory());
        factories.put(Fire.class, new FireFactory());
        factories.put(Pipe.class, new PipeFactory());
    }

    public static ThingFactory getFactory(Class<? extends Thing> subFactory) {
        ThingFactory factory = factories.get(subFactory);
        if (factory == null) {
            throw new IllegalStateException("No factory found for class: " + subFactory.getName());
        }
        return factory;
    } // Get real Map
    
    public static Thing createThing(char c, int x, int y, VariantGenerator variantGen) {
        ThingFactory mapFactory = mapFactories.get(c);
        if (mapFactory == null) {
            throw new IllegalArgumentException("Unknown character: " + c);
        }
        
        return mapFactory.mapCreate(x, y, variantGen);
    } // Get UI Map
    
    static {
        mapFactories.put('Q', new EntranceFactory());
        mapFactories.put('O', new ExitFactory());
        mapFactories.put('r', new RabbitFactory(0, Direction.RIGHT));
        mapFactories.put('j', new RabbitFactory(0, Direction.LEFT));
        mapFactories.put('t', new RabbitFactory(1, Direction.RIGHT));
        mapFactories.put('y', new RabbitFactory(1, Direction.LEFT));
        mapFactories.put('x', new RabbitFactory(2, Direction.RIGHT));
        // Add more mappings for other characters as needed
    }
}

