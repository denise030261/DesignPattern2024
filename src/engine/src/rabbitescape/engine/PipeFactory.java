package rabbitescape.engine;

public class PipeFactory implements ThingFactory {
    @Override
    public Thing cloneThing(Thing thing) {
        if (!(thing instanceof Pipe)) {
            throw new IllegalArgumentException("Invalid type for PipeFactory");
        }
        Pipe pipe = (Pipe) thing;
        return new Pipe(pipe.x, pipe.y);
    }
}