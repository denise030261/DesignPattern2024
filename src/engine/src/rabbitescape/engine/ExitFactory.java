package rabbitescape.engine;

public class ExitFactory implements ThingFactory {
    @Override
    public Thing cloneThing(Thing thing) {
        if (!(thing instanceof Exit)) {
            throw new IllegalArgumentException("Invalid type for ExitFactory");
        }
        return new Exit(thing.x, thing.y);
    }
}