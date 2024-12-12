package rabbitescape.engine;

import rabbitescape.engine.util.VariantGenerator;

public class FireFactory extends ThingFactory {
    @Override
    public Thing cloneThing(Thing thing) {
        if (!(thing instanceof Fire)) {
            throw new IllegalArgumentException("Invalid type for FireFactory");
        }
        Fire fire = (Fire) thing;
        return new Fire(fire.x, fire.y, fire.variant);
    }
    
    @Override
    public Thing mapCreate(int x,int y,VariantGenerator variantGen)
    {
        return new Fire(x, y,variantGen.next( 4 ));
    }
}

