package rabbitescape.engine;

import rabbitescape.engine.util.VariantGenerator;

public class RabbitFactory implements ThingFactory {
    final int type;
    final Direction direction;
    
    public RabbitFactory(int type, Direction direction) {
        this.type=type;
        this.direction=direction;
    }
    
    @Override
    public Thing cloneThing(Thing thing) {
        if (!(thing instanceof AbstractRabbit)) {
            throw new IllegalArgumentException("Invalid type for RabbitFactory");
        }
        AbstractRabbit rabbit = (AbstractRabbit) thing;

        if (rabbit instanceof Rabbit) {
            return Rabbit.createRabbit(rabbit.x, rabbit.y, rabbit.dir, 0);
        } else if (rabbit instanceof Rabbot) {
            return Rabbot.createRabbit(rabbit.x, rabbit.y, rabbit.dir,1);
        }

        throw new IllegalArgumentException("Unsupported AbstractRabbit type");
    }
    
    @Override
    public Thing mapCreate(int x,int y,VariantGenerator variantGen)
    {
        if(type == 0) {
            return Rabbit.createRabbit(x, y, direction, 0);
        }
        else if(type==1) {
            return Rabbit.createRabbit(x, y, direction, 1);
        }
        else {
            return null;
        }
    }
}
