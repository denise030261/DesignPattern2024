package rabbitescape.engine;

import java.util.HashMap;
import java.util.Map;

import rabbitescape.engine.util.VariantGenerator;

//import java.util.Map;

public interface ThingFactory {
    //Thing createThing(int x, int y, Map<String, Object> params);
    Thing cloneThing(Thing thing);
    Thing mapCreate(int x,int y,VariantGenerator variantGen);
    
    static final Map<Character,ThingFactoryManager> mapCreators = new HashMap<>();
}
