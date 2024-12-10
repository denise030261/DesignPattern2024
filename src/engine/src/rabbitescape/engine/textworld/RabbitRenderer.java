package rabbitescape.engine.textworld;

import static rabbitescape.engine.Direction.*;

import java.util.List;

import rabbitescape.engine.ChangeDescription.State;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.AbstractRabbit;
import rabbitescape.engine.Rabbot;

public class RabbitRenderer
{
    public static void render( 
        Chars chars, 
        List<AbstractRabbit> rabbits,
        boolean runtimeMeta 
    )
    {
        for ( AbstractRabbit rabbit : rabbits )
        {
            if ( State.RABBIT_OUT_OF_BOUNDS == rabbit.state )
            {
                continue;
            }
            chars.set(
                rabbit.x,
                rabbit.y,
                charForRabbit( rabbit ),
                rabbit.saveState( runtimeMeta )
            );
        }
    }

    private static char charForRabbit( AbstractRabbit rabbit )
    {
	return rabbit.rabbitChar();
	/*
        if ( rabbit.dir == RIGHT )
        {
            if ( rabbit.type == Rabbit.Type.RABBIT )
            {
                return 'r';
            }
            else
            {
                return 't';
            }
        }
        else
        {
            if ( rabbit.type == Rabbit.Type.RABBIT )
            {
                return 'j';
            }
            else
            {
                return 'y';
            }
        }
	*/
    }
}
