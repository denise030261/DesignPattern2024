package rabbitescape.engine;

import java.util.HashMap;
import java.util.Map;

import rabbitescape.engine.util.VariantGenerator;

public abstract class ThingFactory {

    public abstract Thing cloneThing(Thing thing);
    public abstract Thing mapCreate(int x,int y,VariantGenerator variantGen);
    
    static final Map<Character,ThingFactoryManager> mapCreators = new HashMap<>();
}
