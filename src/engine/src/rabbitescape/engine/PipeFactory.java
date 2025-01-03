package rabbitescape.engine;

import rabbitescape.engine.util.VariantGenerator;

public class PipeFactory extends ThingFactory {
    @Override
    public Thing cloneThing(Thing thing) {
        if (!(thing instanceof Pipe)) {
            throw new IllegalArgumentException("Invalid type for PipeFactory");
        }
        Pipe pipe = (Pipe) thing;
        return new Pipe(pipe.x, pipe.y);
    }
    
    @Override
    public Thing mapCreate(int x,int y,VariantGenerator variantGen)
    {
        return new Pipe(x, y);
    }
}