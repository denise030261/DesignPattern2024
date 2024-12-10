package rabbitescape.engine;

public class RabbitFactory implements ThingFactory {
    @Override
    public Thing cloneThing(Thing thing) {
        if (!(thing instanceof Rabbit)) {
            throw new IllegalArgumentException("Invalid type for RabbitFactory");
        }
        Rabbit rabbit = (Rabbit) thing;
        return new Rabbit(rabbit.x, rabbit.y, rabbit.dir, rabbit.type);
    }
}
