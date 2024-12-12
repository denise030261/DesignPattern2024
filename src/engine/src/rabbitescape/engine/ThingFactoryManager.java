package rabbitescape.engine;

import java.util.HashMap;
import java.util.Map;

import rabbitescape.engine.token.*;
import rabbitescape.engine.util.VariantGenerator;

public class ThingFactoryManager {
    private static final Map<Class<? extends Thing>, ThingFactory> factories = new HashMap<>();
    private static final Map<Character, ThingFactory> mapFactories = new HashMap<>();
    
    static {
        factories.put(Entrance.class, new EntranceFactory());
        factories.put(Exit.class, new ExitFactory());
        factories.put(Rabbit.class, new RabbitFactory(0,Direction.RIGHT));
        factories.put(ExplodeToken.class, new TokenFactory(Token.Type.explode));
        factories.put(BashToken.class, new TokenFactory(Token.Type.bash));
        factories.put(BlockToken.class, new TokenFactory(Token.Type.block));
        factories.put(BridgeToken.class, new TokenFactory(Token.Type.bridge));
        factories.put(BrollyToken.class, new TokenFactory(Token.Type.brolly));
        factories.put(ClimbToken.class, new TokenFactory(Token.Type.climb));
        factories.put(DigToken.class, new TokenFactory(Token.Type.dig));
        factories.put(Fire.class, new FireFactory());
        factories.put(Pipe.class, new PipeFactory());
    }

    public static ThingFactory getFactory(Class<? extends Thing> subFactory) {
        ThingFactory factory = factories.get(subFactory);
        if (factory == null) {
            throw new IllegalStateException("No factory found for class: " + subFactory.getName());
        }
        return factory;
    } 
    
    public static Thing createThing(char c, int x, int y, VariantGenerator variantGen) {
        ThingFactory mapFactory = mapFactories.get(c);
        if (mapFactory == null) {
            throw new IllegalArgumentException("Unknown character: " + c);
        }
        return mapFactory.mapCreate(x, y, variantGen);
    } 
    
    static {
        mapFactories.put('Q', new EntranceFactory());
        mapFactories.put('O', new ExitFactory());
        mapFactories.put('A', new FireFactory());
        mapFactories.put('P', new PipeFactory());
        mapFactories.put('b', new TokenFactory(Token.Type.bash));
        mapFactories.put('r', new RabbitFactory(0, Direction.RIGHT));
        mapFactories.put('j', new RabbitFactory(0, Direction.LEFT));
        mapFactories.put('t', new RabbitFactory(1, Direction.RIGHT));
        mapFactories.put('y', new RabbitFactory(1, Direction.LEFT));
        mapFactories.put('x', new RabbitFactory(2, Direction.RIGHT));
        mapFactories.put('d', new TokenFactory(Token.Type.dig));
        mapFactories.put('i', new TokenFactory(Token.Type.bridge));
        mapFactories.put('k', new TokenFactory(Token.Type.block));
        mapFactories.put('c', new TokenFactory(Token.Type.climb));
        mapFactories.put('p', new TokenFactory(Token.Type.explode));
         mapFactories.put('l', new TokenFactory(Token.Type.brolly));
        // Add more mappings for other characters as needed
    }
}

