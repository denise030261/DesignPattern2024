package rabbitescape.engine;

import rabbitescape.engine.util.VariantGenerator;

public class EntranceFactory extends ThingFactory {
    @Override
    public Thing cloneThing(Thing thing) {
        if (!(thing instanceof Entrance)) {
            throw new IllegalArgumentException("Invalid type for EntranceFactory");
        }
        return new Entrance(thing.x, thing.y);
    }
    
    @Override
    public Thing mapCreate(int x,int y,VariantGenerator variantGen)
    {
        return new Entrance(x, y);
    }
}
