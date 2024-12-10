package rabbitescape.engine;

public class EntranceFactory implements ThingFactory {
    @Override
    public Thing cloneThing(Thing thing) {
        if (!(thing instanceof Entrance)) {
            throw new IllegalArgumentException("Invalid type for EntranceFactory");
        }
        return new Entrance(thing.x, thing.y);
    }
}
