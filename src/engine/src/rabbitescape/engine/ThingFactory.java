package rabbitescape.engine;

import java.util.HashMap;
import java.util.Map;

import rabbitescape.engine.util.VariantGenerator;

public interface ThingFactory {

    Thing cloneThing(Thing thing);
    Thing mapCreate(int x,int y,VariantGenerator variantGen);
    
    static final Map<Character,ThingFactoryManager> mapCreators = new HashMap<>();
}
