package rabbitescape.engine;

public class FireFactory implements ThingFactory {
    @Override
    public Thing cloneThing(Thing thing) {
        if (!(thing instanceof Fire)) {
            throw new IllegalArgumentException("Invalid type for FireFactory");
        }
        Fire fire = (Fire) thing;
        return new Fire(fire.x, fire.y, fire.variant);
    }
}

