package rabbitescape.engine;

import rabbitescape.engine.util.VariantGenerator;

public class ExitFactory extends ThingFactory {
    //use map 
    
    @Override
    public Thing cloneThing(Thing thing) {
        if (!(thing instanceof Exit)) {
            throw new IllegalArgumentException("Invalid type for ExitFactory");
        }
        return new Exit(thing.x, thing.y);
    }
    
    @Override
    public Thing mapCreate(int x,int y,VariantGenerator variantGen)
    {
        return new Exit(x, y);
    }
}