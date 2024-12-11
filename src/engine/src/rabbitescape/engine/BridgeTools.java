package rabbitescape.engine;

import rabbitescape.engine.util.Position;

public class BridgeTools
{

    public static boolean someoneIsBridgingAt( World world, int x, int y )
    {
        for ( AbstractRabbit rabbit : world.rabbits )
        {
            if ( rabbitIsBridgingAt( rabbit, x, y ) )
            {
                return true;
            }
        }
        return false;
    }

    public static boolean rabbitIsBridgingAt( AbstractRabbit rabbit, int x, int y )
    {
        Position bridging = RabbitStates.whereBridging(
            new StateAndPosition( rabbit.state, rabbit.x, rabbit.y ) );

        if ( bridging == null )
        {
            return false;
        }
        else
        {
            return ( bridging.x == x && bridging.y == y );
        }
    }

}
